package com.cnaps.kafka;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import mg.cnaps.models.CompagnieCarbMod;
import mg.cnaps.models.HistoriqueMod;
import mg.cnaps.models.ParamCompagniepetrol;
import mg.cnaps.services.CompagnieCarbService;
import mg.cnaps.services.HistoriqueService;
import mg.cnaps.utils.DateUtil;

//import mg.cnaps.models.AccueilMod;

//import mg.cnaps.models.ParamDemandes;
@Component
public class CompagnieCarbConsumer {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(CompagnieCarbConsumer.class);
	@Autowired
	Producer producer;

	@Autowired
	CompagnieCarbService service;
	
	@Autowired
	HistoriqueService servicehisto;
	
	@KafkaListener(topics = "listecompagnieReq")
	public void liste(ConsumerRecord<?, ?> record) {
		try {
			log.info("pois: "+record.value().toString());
			List<CompagnieCarbMod> liste = service.getAll(Integer.parseInt(record.value().toString()));
			log.info("nbrligne: "+om.writeValueAsString(liste));
			producer.send(record.key().toString(), "listecompagnieRes", om.writeValueAsString(liste));
		} catch (Exception e) {
			producer.send(record.key().toString(), "listecompagnieRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "nbrpagecompagnieReq")
	public void nombredepage(ConsumerRecord<?, ?> record) {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			producer.send(record.key().toString(), "nbrpagecompagnieRes", resultat);
		} catch (Exception e) {
			producer.send(record.key().toString(), "nbrpagecompagnieRes", e.getMessage());
		}
	}

	@KafkaListener(topics = "ajoutcompagniecarbReq")
	public void ajout(ConsumerRecord<?, ?> record) {
		try {
			log.info("insert: "+record.value().toString());
			ParamCompagniepetrol param =om.readValue(record.value().toString(),ParamCompagniepetrol.class);
			service.save(om.readValue(record.value().toString(), CompagnieCarbMod.class));
			HistoriqueMod param1 =new HistoriqueMod();
			param1.setDatehisto(DateUtil.sqlDateNow());
			param1.setTache("Ajout du nouvelle compagnie pétrolier : " + param.getIdcartecarb());
			param1.setMatriculeuser(param.getMatriculeuser());
			param1.setUserinsert(param.getUserinsert());
			param1.setIdhistorique(Integer.valueOf(String.valueOf(servicehisto.seqDemande())));
			servicehisto.save(param1);
			producer.send(record.key().toString(), "ajoutcompagnieRes", "{\"success\":true}");
		} catch (Exception e) {
			producer.send(record.key().toString(), "ajoutcompagnieRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "supprcompagniecarbReq")
	public void suppr(ConsumerRecord<?, ?> record) {
		try {
			service.delete(om.readValue(record.value().toString(), CompagnieCarbMod.class));
			producer.send(record.key().toString(), "supprcompagniecarbRes", "{\"success\":true}");
		} catch (Exception e) {
			producer.send(record.key().toString(), "supprcompagniecarbRes", e.getMessage());
		}
	}
	
}
