package com.cnaps.kafka;


import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import mg.cnaps.models.HistoriqueMod;
import mg.cnaps.models.ParamRechHistorique;
import mg.cnaps.services.HistoriqueService;
import mg.cnaps.utils.DateUtil;

@Component
public class HistoriqueConsumer {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(HistoriqueConsumer.class);
	@Autowired
	Producer producer;

	@Autowired
	HistoriqueService service;
	

	@KafkaListener(topics = "listeallhistoriquereetrReq")
	public void listeall(ConsumerRecord<?, ?> record) {
		try {
			log.info("pois: "+record.value().toString());
			List<HistoriqueMod> liste = service.findallhistorique(new PageRequest(Integer.parseInt(record.value().toString()), 10));
			log.info("nbrligne: "+om.writeValueAsString(liste));
			producer.send(record.key().toString(), "listeallhistoriquereetrRes", om.writeValueAsString(liste));
		} catch (Exception e) {
			producer.send(record.key().toString(), "listeallhistoriquereetrRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "listeallhistoriquereetrioReq")
	public void liste(ConsumerRecord<?, ?> record) {
		try {
			log.info("pois: "+record.value().toString());
			Object[] liste = service.findallty();
			//List<ParamValueHisto> io = new ArrayList<>();
			int size = liste.length;
			log.info("size : "+ size);
			log.info("nbrligne: "+om.writeValueAsString(liste));
			producer.send(record.key().toString(), "listeallhistoriquereetrioRes", om.writeValueAsString(liste));
		} catch (Exception e) {
			e.printStackTrace();
			producer.send(record.key().toString(), "listeallhistoriquereetrioRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "nbrpagehistoriquereetrReq")
	public void nombredepage(ConsumerRecord<?, ?> record) {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			producer.send(record.key().toString(), "nbrpagehistoriquereetrRes", resultat);
		} catch (Exception e) {
			producer.send(record.key().toString(), "nbrpagehistoriquereetrRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "ajouthistoriquereetrReq")
	public void ajout(ConsumerRecord<?, ?> record) {
		try {
			log.info("insert: "+record.value().toString());
			HistoriqueMod param =om.readValue(record.value().toString(),HistoriqueMod.class);
			param.setDatehisto(DateUtil.sqlDateNow());
			service.save(param);
			producer.send(record.key().toString(), "ajouthistoriquereetrRes", "{\"success\":true}");
		} catch (Exception e) {
			producer.send(record.key().toString(), "ajouthistoriquereetrRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "recherhcehistoriquereetrReq")
	public void recherhcehistoriquereetr(ConsumerRecord<?, ?> record) {
		try {
			log.info(record.value().toString());
			ParamRechHistorique param =om.readValue(record.value().toString(),ParamRechHistorique.class);
			log.info("io "+param.getMatriculeuser());
			log.info("io "+param.getDatehisto());
			if(param.getDatehisto() == null) {
				List<HistoriqueMod> liste = service.findbydatehistoandmatriculeuser2(param.getMatriculeuser());
				resultat = om.writeValueAsString(liste);
				producer.send(record.key().toString(), "recherhcehistoriquereetrRes", resultat);
			}
			else {
				List<HistoriqueMod> liste = service.findbydatehistoandmatriculeuser(param.getDatehisto(),param.getMatriculeuser());
				resultat = om.writeValueAsString(liste);
				producer.send(record.key().toString(), "recherhcehistoriquereetrRes", resultat);
			}
		} catch (Exception e) {
			e.printStackTrace();
			producer.send(record.key().toString(), "recherhcehistoriquereetrRes", e.getMessage());
		}
	}
}
