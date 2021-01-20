package com.cnaps.kafka;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import mg.cnaps.models.ChargementCarbMod;
import mg.cnaps.models.HistoriqueMod;
import mg.cnaps.models.Parammouvcarte;
import mg.cnaps.services.ChargementCarbService;
import mg.cnaps.services.HistoriqueService;
//import mg.cnaps.models.ParamDemandes;
import mg.cnaps.utils.DateUtil;


@Component
public class ChargementcarbConsumer {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(ChargementcarbConsumer.class);
	@Autowired
	Producer producer;

	@Autowired
	ChargementCarbService service;
	
	@Autowired
	HistoriqueService servicehisto;
	
	@KafkaListener(topics = "listechargementcarbReq")
	public void liste(ConsumerRecord<?, ?> record) {
		try {
			log.info("pageliste: "+record.value().toString());
			List<ChargementCarbMod> liste = service.getAll(Integer.parseInt(record.value().toString()));
			log.info("listechargement: "+om.writeValueAsString(liste));
			producer.send(record.key().toString(), "listechargementcarbRes", om.writeValueAsString(liste));
		} catch (Exception e) {
			producer.send(record.key().toString(), "listechargementcarbRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "nbrpagechargementcarbReq")
	public void nombredepage(ConsumerRecord<?, ?> record) {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			producer.send(record.key().toString(), "nbrpagechargementcarbRes", resultat);
		} catch (Exception e) {
			producer.send(record.key().toString(), "nbrpagechargementcarbRes", e.getMessage());
		}
	}
	

	@KafkaListener(topics = "ajoutchargementcarbReq")
	public void ajout(ConsumerRecord<?, ?> record) {
		try {
			log.info("valeur : "+record.value().toString());
			Parammouvcarte param =om.readValue(record.value().toString(), Parammouvcarte.class);
			log.info("test :"+param.getSortant());
			ChargementCarbMod io = new ChargementCarbMod();
			io.setDatecharge(DateUtil.sqlDateNow());
//			if(param.getSortant() != 0) {
//				io.setMontant(param.getSortant());
//			}
//			else{
				io.setMontant(param.getEntree());
//			}
			io.setNumcarte(param.getNumcarte());
			service.save(io);
			log.info("io :"+ io);
			HistoriqueMod param1 =new HistoriqueMod();
			param1.setDatehisto(DateUtil.sqlDateNow());
			param1.setTache("Chargement du carte : " + param.getNumcarte());
			param1.setMatriculeuser(param.getMatriculeuser());
			param1.setUserinsert(param.getUserinsert());
			param1.setIdhistorique(Integer.valueOf(String.valueOf(servicehisto.seqDemande())));
			servicehisto.save(param1);
//			producer.send(record.key().toString(), "ajoutMouvCarteReq", record.value().toString());
			producer.send(record.key().toString(), "ajoutchargementcarbRes", record.value().toString());
		} catch (Exception e) {
			e.printStackTrace();
			producer.send(record.key().toString(), "ajoutchargementcarbRes", e.getMessage());
		}
	}

	@KafkaListener(topics = "supprchargementcarbReq")
	public void suppr(ConsumerRecord<?, ?> record) {
		try {
			service.delete(om.readValue(record.value().toString(), ChargementCarbMod.class));
			producer.send(record.key().toString(), "supprchargementcarbRes", "{\"success\":true}");
		} catch (Exception e) {
			producer.send(record.key().toString(), "supprchargementcarbRes", e.getMessage());
		}
	}
}
