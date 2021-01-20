package com.cnaps.kafka;


	import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import mg.cnaps.models.TypeAssuranceMod;
//import mg.cnaps.models.ParamDemandes;
	import mg.cnaps.services.TypeAssuranceService;

	@Component
	public class TypeAssuranceConsumer {

		ObjectMapper om = new ObjectMapper();
		String resultat;

		private static final Logger log = LoggerFactory.getLogger(TypeAssuranceConsumer.class);
		@Autowired
		Producer producer;

		@Autowired
		TypeAssuranceService service;
		

		@KafkaListener(topics = "listeTypeAssuranceReq")
		public void liste(ConsumerRecord<?, ?> record) {
			try {
				log.info("pageliste: "+record.value().toString());
				List<TypeAssuranceMod> liste = service.getAll(Integer.parseInt(record.value().toString()));
				log.info("listcou: "+om.writeValueAsString(liste));
				producer.send(record.key().toString(), "listeTypeAssuranceRes", om.writeValueAsString(liste));
			} catch (Exception e) {
				producer.send(record.key().toString(), "listeTypeAssuranceRes", e.getMessage());
			}
		}
		
		@KafkaListener(topics = "nbrpageTypeAssuranceReq")
		public void nombredepage(ConsumerRecord<?, ?> record) {
			try {
				int page = service.nombrepage();
				resultat = om.writeValueAsString(page);
				producer.send(record.key().toString(), "nbrpageTypeAssuranceRes", resultat);
			} catch (Exception e) {
				producer.send(record.key().toString(), "nbrpageTypeAssuranceRes", e.getMessage());
			}
		}
		
		@KafkaListener(topics = "ajoutTypeAssuranceReq")
		public void ajout(ConsumerRecord<?, ?> record) {
			try {
				service.save(om.readValue(record.value().toString(), TypeAssuranceMod.class));
				producer.send(record.key().toString(), "ajoutTypeAssuranceRes", "{\"success\":true}");
			} catch (Exception e) {
				producer.send(record.key().toString(), "ajoutTypeAssuranceRes", e.getMessage());
			}
		}

		@KafkaListener(topics = "supprTypeAssuranceReq")
		public void suppr(ConsumerRecord<?, ?> record) {
			try {
				service.delete(om.readValue(record.value().toString(), TypeAssuranceMod.class));
				producer.send(record.key().toString(), "supprTypeAssuranceRes", "{\"success\":true}");
			} catch (Exception e) {
				producer.send(record.key().toString(), "supprTypeAssuranceRes", e.getMessage());
			}
		}
	}

