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
	public class TypeCarteConsumer {

		ObjectMapper om = new ObjectMapper();
		String resultat;

		private static final Logger log = LoggerFactory.getLogger(TypeCarteConsumer.class);
		@Autowired
		Producer producer;

		@Autowired
		TypeCarteService service;
		

		@KafkaListener(topics = "listetypecarteReq")
		public void liste(ConsumerRecord<?, ?> record) {
			try {
				log.info("pageliste: "+record.value().toString());
				List<TypeCarteMod> liste = service.getAll(Integer.parseInt(record.value().toString()));
				log.info("listcou: "+om.writeValueAsString(liste));
				producer.send(record.key().toString(), "listetypecarteRes", om.writeValueAsString(liste));
			} catch (Exception e) {
				producer.send(record.key().toString(), "listetypecarteRes", e.getMessage());
			}
		}
		
		@KafkaListener(topics = "nbrpagetypecarteReq")
		public void nombredepage(ConsumerRecord<?, ?> record) {
			try {
				int page = service.nombrepage();
				resultat = om.writeValueAsString(page);
				producer.send(record.key().toString(), "nbrpagetypecarteRes", resultat);
			} catch (Exception e) {
				producer.send(record.key().toString(), "nbrpagetypecarteRes", e.getMessage());
			}
		}
		
		@KafkaListener(topics = "ajouttypecarteReq")
		public void ajout(ConsumerRecord<?, ?> record) {
			try {
				service.save(om.readValue(record.value().toString(), TypeCarteMod.class));
				producer.send(record.key().toString(), "ajouttypecarteRes", "{\"success\":true}");
			} catch (Exception e) {
				producer.send(record.key().toString(), "ajouttypecarteRes", e.getMessage());
			}
		}

		@KafkaListener(topics = "supprtypecarteReq")
		public void suppr(ConsumerRecord<?, ?> record) {
			try {
				service.delete(om.readValue(record.value().toString(), TypeCarteMod.class));
				producer.send(record.key().toString(), "supprtypecarteRes", "{\"success\":true}");
			} catch (Exception e) {
				producer.send(record.key().toString(), "supprtypecarteRes", e.getMessage());
			}
		}
	}
	
	

