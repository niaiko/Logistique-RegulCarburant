package com.cnaps.kafka;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import mg.cnaps.models.OnglobeVehiculeMod;
//import mg.cnaps.models.ParamDemandes;
import mg.cnaps.services.OnglobeVehiculeService;

@Component
public class OnglobeVehiculeConsumer {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(OnglobeVehiculeConsumer.class);
	@Autowired
	Producer producer;

	@Autowired
	OnglobeVehiculeService service;
	

	@KafkaListener(topics = "listeOnglVehCarbuReq")
	public void liste(ConsumerRecord<?, ?> record) {
		try {
			log.info("pageliste: "+record.value().toString());
			List<OnglobeVehiculeMod> liste = service.getAll(Integer.parseInt(record.value().toString()));
			log.info("listcou: "+om.writeValueAsString(liste));
			producer.send(record.key().toString(), "listeOnglVehCarbuRes", om.writeValueAsString(liste));
		} catch (Exception e) {
			producer.send(record.key().toString(), "listeOnglVehCarbuRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "nbrpageOnglVehCarbuReq")
	public void nombredepage(ConsumerRecord<?, ?> record) {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			producer.send(record.key().toString(), "nbrpageOnglVehCarbuRes", resultat);
		} catch (Exception e) {
			producer.send(record.key().toString(), "nbrpageOnglVehCarbuRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "ajoutOnglVehCarbuReq")
	public void ajout(ConsumerRecord<?, ?> record) {
		try {
			service.save(om.readValue(record.value().toString(), OnglobeVehiculeMod.class));
			producer.send(record.key().toString(), "ajoutOnglVehCarbuRes", "{\"success\":true}");
		} catch (Exception e) {
			producer.send(record.key().toString(), "ajoutOnglVehCarbuRes", e.getMessage());
		}
	}

	@KafkaListener(topics = "supprOnglVehCarbuReq")
	public void suppr(ConsumerRecord<?, ?> record) {
		try {
			service.delete(om.readValue(record.value().toString(), OnglobeVehiculeMod.class));
			producer.send(record.key().toString(), "supprOnglVehCarbuRes", "{\"success\":true}");
		} catch (Exception e) {
			producer.send(record.key().toString(), "supprOnglVehCarbuRes", e.getMessage());
		}
	}
}
