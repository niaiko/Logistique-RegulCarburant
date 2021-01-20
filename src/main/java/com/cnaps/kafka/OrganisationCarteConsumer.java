package com.cnaps.kafka;


import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import mg.cnaps.models.OrganisationCarteMod;
//import mg.cnaps.models.ParamDemandes;
import mg.cnaps.services.OrganisationCarteService;

@Component
public class OrganisationCarteConsumer {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(OrganisationCarteConsumer.class);
	@Autowired
	Producer producer;

	@Autowired
	OrganisationCarteService service;
	

	@KafkaListener(topics = "listeOrgCarteCarbuReq")
	public void liste(ConsumerRecord<?, ?> record) {
		try {
			log.info("pageliste: "+record.value().toString());
			List<OrganisationCarteMod> liste = service.getAll(Integer.parseInt(record.value().toString()));
			log.info("listcou: "+om.writeValueAsString(liste));
			producer.send(record.key().toString(), "listeOrgCarteCarbuRes", om.writeValueAsString(liste));
		} catch (Exception e) {
			producer.send(record.key().toString(), "listeOrgCarteCarbuRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "nbrpageOrgCarteCarbuReq")
	public void nombredepage(ConsumerRecord<?, ?> record) {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			producer.send(record.key().toString(), "nbrpageOrgCarteCarbuRes", resultat);
		} catch (Exception e) {
			producer.send(record.key().toString(), "nbrpageOrgCarteCarbuRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "ajoutOrgCarteCarbu")
	public void ajout(ConsumerRecord<?, ?> record) {
		try {
			service.save(om.readValue(record.value().toString(), OrganisationCarteMod.class));
			producer.send(record.key().toString(), "ajoutOnglVehCarbuRes", "{\"success\":true}");
		} catch (Exception e) {
			producer.send(record.key().toString(), "ajoutOrgCarteCarbuRes", e.getMessage());
		}
	}

	@KafkaListener(topics = "supprOrgCarteCarbuReq")
	public void suppr(ConsumerRecord<?, ?> record) {
		try {
			service.delete(om.readValue(record.value().toString(), OrganisationCarteMod.class));
			producer.send(record.key().toString(), "supprOrgCarteCarbuRes", "{\"success\":true}");
		} catch (Exception e) {
			producer.send(record.key().toString(), "supprOnglVehCarbuRes", e.getMessage());
		}
	}
}

