package com.cnaps.kafka;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import mg.cnaps.models.DisponibiliteMod;
//import mg.cnaps.models.ParamDemandes;
	import mg.cnaps.services.DisponibiliteService;

	@Component
public class DisponibiliteConsumer {

		ObjectMapper om = new ObjectMapper();
		String resultat;

		private static final Logger log = LoggerFactory.getLogger(DisponibiliteConsumer.class);
		@Autowired
		Producer producer;

		@Autowired
		DisponibiliteService service;
		

		@KafkaListener(topics = "listeDisponibiliteReq")
		public void liste(ConsumerRecord<?, ?> record) {
			try {
				log.info("pageliste: "+record.value().toString());
				List<DisponibiliteMod> liste = service.getAll(Integer.parseInt(record.value().toString()));
				log.info("listcou: "+om.writeValueAsString(liste));
				producer.send(record.key().toString(), "listeDisponibiliteRes", om.writeValueAsString(liste));
			} catch (Exception e) {
				producer.send(record.key().toString(), "listeDisponibiliteRes", e.getMessage());
			}
		}
		
		@KafkaListener(topics = "nbrpageDisponibiliteReq")
		public void nombredepage(ConsumerRecord<?, ?> record) {
			try {
				int page = service.nombrepage();
				resultat = om.writeValueAsString(page);
				producer.send(record.key().toString(), "nbrpageDisponibiliteRes", resultat);
			} catch (Exception e) {
				producer.send(record.key().toString(), "nbrpageDisponibiliteRes", e.getMessage());
			}
		}
		
		@KafkaListener(topics = "ajoutDisponibiliteReq")
		public void ajout(ConsumerRecord<?, ?> record) {
			try {
				service.save(om.readValue(record.value().toString(), DisponibiliteMod.class));
				producer.send(record.key().toString(), "ajoutDisponibiliteRes", "{\"success\":true}");
			} catch (Exception e) {
				producer.send(record.key().toString(), "ajoutDisponibiliteRes", e.getMessage());
			}
		}

		@KafkaListener(topics = "supprDisponibiliteReq")
		public void suppr(ConsumerRecord<?, ?> record) {
			try {
				service.delete(om.readValue(record.value().toString(), DisponibiliteMod.class));
				producer.send(record.key().toString(), "supprDisponibiliteRes", "{\"success\":true}");
			} catch (Exception e) {
				producer.send(record.key().toString(), "supprDisponibiliteRes", e.getMessage());
			}
		}
		
		

	}
	
	

