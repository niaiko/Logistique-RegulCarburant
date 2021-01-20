package com.cnaps.kafka;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import mg.cnaps.models.DmdRepMod;
import mg.cnaps.services.DmdRepService;

@Component
public class DmdRepConsumer {

	ObjectMapper om = new ObjectMapper();
	String resultat;
    
	private static final Logger log = LoggerFactory.getLogger(DmdRepConsumer.class);
	@Autowired
	Producer producer;
  
	@Autowired
	DmdRepService service;
	
	@KafkaListener(topics = "listealldmdrepReq")
	public void liste(ConsumerRecord<?, ?> record) {
		try {
			log.info("pageliste: "+record.value().toString());
			List<DmdRepMod> liste = service.getAll(Integer.parseInt(record.value().toString()));
			log.info("listecou: "+om.writeValueAsString(liste));
			producer.send(record.key().toString(), "listealldmdrepRes", om.writeValueAsString(liste));
		} catch (Exception e) {
			producer.send(record.key().toString(), "listealldmdrepRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "nbrpagedmdrepReq")
	public void nombredepage(ConsumerRecord<?, ?> record) {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			producer.send(record.key().toString(), "nbrpagedmdrepRes", resultat);
		} catch (Exception e) {
			producer.send(record.key().toString(), "nbrpagedmdrepRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "ajoutdmdrepReq")
	public void ajout(ConsumerRecord<?, ?> record) {
		try {
			log.info("insert: "+record.value().toString());
			service.save(om.readValue(record.value().toString(), DmdRepMod.class));
			producer.send(record.key().toString(), "ajoutdmdrepRes", "success");
		} catch (Exception e) {
			producer.send(record.key().toString(), "ajoutdmdrepRes", e.getMessage());
		}
	}

	@KafkaListener(topics = "supprdmdrepReq")
	public void suppr(ConsumerRecord<?, ?> record) {
		try {
			service.delete(om.readValue(record.value().toString(), DmdRepMod.class));
			producer.send(record.key().toString(), "supprdmdrepRes", "succes");
		} catch (Exception e) {
			producer.send(record.key().toString(), "supprdmdrepRes", e.getMessage());
		}
	}
	
//	@KafkaListener(topics = "findbyrechcslogReq")
//	public void findbydestinatairedatecou(ConsumerRecord<?, ?> record) {
//		try {
////			log.info("rech1: "+record.value().toString());
//			CsLogMod param = om.readValue(record.value().toString(), CsLogMod.class);
//			log.info("rech1: "+param.getDestinataire());
//			log.info("rech2: "+param.getDateInsert());
//			List<CsLogMod> acces = service.getByDestinataireDateCou(param.getDestinataire(),param.getDateInsert());
//			resultat = om.writeValueAsString(acces);
//			log.info("nbrligne: "+resultat);
//			producer.send(record.key().toString(), "findbyrechcslogRes", resultat);
//		} catch (Exception e) {
//			producer.send(record.key().toString(), "findbyrechcslogRes", e.getMessage());
//		}
//	}
}
