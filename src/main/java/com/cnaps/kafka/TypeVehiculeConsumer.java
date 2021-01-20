package com.cnaps.kafka;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import mg.cnaps.models.TypeCarteMod;
//import mg.cnaps.models.ParamDemandes;
	import mg.cnaps.services.TypeCarteService;

	@Component
	public class TypeVehiculeConsumer {

		ObjectMapper om = new ObjectMapper();
		String resultat;

		private static final Logger log = LoggerFactory.getLogger(TypeVehiculeConsumer.class);
		@Autowired
		Producer producer;

		@Autowired
		TypeCarteService service;
		

		@KafkaListener(topics = "listetypevehicReq")
		public void liste(ConsumerRecord<?, ?> record) {
			try {
				log.info("pageliste: "+record.value().toString());
				List<TypeCarteMod> liste = service.getAll(Integer.parseInt(record.value().toString()));
				log.info("listcou: "+om.writeValueAsString(liste));
				producer.send(record.key().toString(), "listetypevehicRes", om.writeValueAsString(liste));
			} catch (Exception e) {
				producer.send(record.key().toString(), "listetypevehicRes", e.getMessage());
			}
		}
		
		@KafkaListener(topics = "nbrpagetypevehicReq")
		public void nombredepage(ConsumerRecord<?, ?> record) {
			try {
				int page = service.nombrepage();
				resultat = om.writeValueAsString(page);
				producer.send(record.key().toString(), "nbrpagetypevehicRes", resultat);
			} catch (Exception e) {
				producer.send(record.key().toString(), "nbrpagetypevehicRes", e.getMessage());
			}
		}
		
		@KafkaListener(topics = "ajouttypevehicReq")
		public void ajout(ConsumerRecord<?, ?> record) {
			try {
				service.save(om.readValue(record.value().toString(), TypeCarteMod.class));
				producer.send(record.key().toString(), "ajouttypevehicRes", "{\"success\":true}");
			} catch (Exception e) {
				producer.send(record.key().toString(), "ajouttypevehicRes", e.getMessage());
			}
		}

		@KafkaListener(topics = "supprtypevehicReq")
		public void suppr(ConsumerRecord<?, ?> record) {
			try {
				service.delete(om.readValue(record.value().toString(), TypeCarteMod.class));
				producer.send(record.key().toString(), "supprtypevehicRes", "{\"success\":true}");
			} catch (Exception e) {
				producer.send(record.key().toString(), "supprtypevehicRes", e.getMessage());
			}
		}
	}
	
	

