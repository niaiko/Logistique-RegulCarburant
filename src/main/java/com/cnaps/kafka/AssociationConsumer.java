package com.cnaps.kafka;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import mg.cnaps.models.AssociationMod;
import mg.cnaps.services.AssociationService;
//import mg.cnaps.models.ParamDemandes;

@Component
public class AssociationConsumer {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(AssociationConsumer.class);
	@Autowired
	Producer producer;

	@Autowired
	AssociationService service;
	

	@KafkaListener(topics = "listeassociationReq")
	public void liste(ConsumerRecord<?, ?> record) {
		try {
			log.info("pois: "+record.value().toString());
			List<AssociationMod> liste = service.getAll(Integer.parseInt(record.value().toString()));
			log.info("nbrligne: "+om.writeValueAsString(liste));
			producer.send(record.key().toString(), "listeassociationRes", om.writeValueAsString(liste));
		} catch (Exception e) {
			producer.send(record.key().toString(), "listeassociationRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "nbrpageassociationReq")
	public void nombredepage(ConsumerRecord<?, ?> record) {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			producer.send(record.key().toString(), "nbrpageassociationRes", resultat);
		} catch (Exception e) {
			producer.send(record.key().toString(), "nbrpageassociationRes", e.getMessage());
		}
	}
	@KafkaListener(topics = "ajoutassociationReq")
	public void ajout(ConsumerRecord<?, ?> record) {
		try {
			log.info("insert: "+record.value().toString());
			service.save(om.readValue(record.value().toString(), AssociationMod.class));
			producer.send(record.key().toString(), "ajoutassociationRes", "{\"success\":true}");
		} catch (Exception e) {
			producer.send(record.key().toString(), "ajoutassociationRes", e.getMessage());
		}
	}

	@KafkaListener(topics = "supprassociationReq")
	public void suppr(ConsumerRecord<?, ?> record) {
		try {
			service.delete(om.readValue(record.value().toString(), AssociationMod.class));
			producer.send(record.key().toString(), "supprassociationRes", "{\"success\":true}");
		} catch (Exception e) {
			producer.send(record.key().toString(), "supprassociationRes", e.getMessage());
		}
	}
	
	
}
