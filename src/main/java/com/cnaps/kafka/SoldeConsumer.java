package com.cnaps.kafka;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import mg.cnaps.models.SoldeMod;
//import mg.cnaps.models.ParamDemandes;
	import mg.cnaps.services.SoldeService;

	@Component
	public class SoldeConsumer {

		ObjectMapper om = new ObjectMapper();
		String resultat;

		private static final Logger log = LoggerFactory.getLogger(SoldeConsumer.class);
		@Autowired
		Producer producer;

		@Autowired
		SoldeService service;
		

		@KafkaListener(topics = "listeSoldeReq")
		public void liste(ConsumerRecord<?, ?> record) {
			try {
				log.info("pageliste: "+record.value().toString());
				List<SoldeMod> liste = service.getAll(Integer.parseInt(record.value().toString()));
				log.info("listcou: "+om.writeValueAsString(liste));
				producer.send(record.key().toString(), "listeSoldeRes", om.writeValueAsString(liste));
			} catch (Exception e) {
				producer.send(record.key().toString(), "listeSoldeRes", e.getMessage());
			}
		}
		
		@KafkaListener(topics = "nbrpageSoldeReq")
		public void nombredepage(ConsumerRecord<?, ?> record) {
			try {
				int page = service.nombrepage();
				resultat = om.writeValueAsString(page);
				producer.send(record.key().toString(), "nbrpageSoldeRes", resultat);
			} catch (Exception e) {
				producer.send(record.key().toString(), "nbrpageSoldeRes", e.getMessage());
			}
		}
		
		@KafkaListener(topics = "ajoutSoldeReq")
		public void ajout(ConsumerRecord<?, ?> record) {
			try {
				service.save(om.readValue(record.value().toString(), SoldeMod.class));
				producer.send(record.key().toString(), "ajoutSoldeRes", "{\"success\":true}");
			} catch (Exception e) {
				producer.send(record.key().toString(), "ajoutSoldeRes", e.getMessage());
			}
		}

		@KafkaListener(topics = "supprSoldeReq")
		public void suppr(ConsumerRecord<?, ?> record) {
			try {
				service.delete(om.readValue(record.value().toString(), SoldeMod.class));
				producer.send(record.key().toString(), "supprSoldeRes", "{\"success\":true}");
			} catch (Exception e) {
				producer.send(record.key().toString(), "supprSoldeRes", e.getMessage());
			}
		}

	}
	
	

