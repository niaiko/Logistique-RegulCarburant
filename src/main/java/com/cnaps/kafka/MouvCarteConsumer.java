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
import mg.cnaps.models.MouvCarteMensuelMod;
import mg.cnaps.models.MouvCarteMod;
import mg.cnaps.models.ParamCarteMouv;
import mg.cnaps.services.HistoriqueService;
//import mg.cnaps.models.ParamDemandes;
	import mg.cnaps.services.MouvCarteService;
import mg.cnaps.utils.DateUtil;

	@Component
	public class MouvCarteConsumer {

		ObjectMapper om = new ObjectMapper();
		String resultat;

		private static final Logger log = LoggerFactory.getLogger(MouvCarteConsumer.class);
		@Autowired
		Producer producer;

		@Autowired
		MouvCarteService service;
		
		@Autowired
		HistoriqueService servicehisto;
		

		@KafkaListener(topics = "listeMouvCarteReq")
		public void liste(ConsumerRecord<?, ?> record) {
			try {
				log.info("pageliste: "+record.value().toString());
				List<MouvCarteMod> liste = service.getAll(Integer.parseInt(record.value().toString()));
				log.info("listcou: "+om.writeValueAsString(liste));
				producer.send(record.key().toString(), "listeMouvCarteRes", om.writeValueAsString(liste));
			} catch (Exception e) {
				producer.send(record.key().toString(), "listeMouvCarteRes", e.getMessage());
			}
		}
		
		@KafkaListener(topics = "nbrpageMouvCarteReq")
		public void nombredepage(ConsumerRecord<?, ?> record) {
			try {
				int page = service.nombrepage();
				resultat = om.writeValueAsString(page);
				producer.send(record.key().toString(), "nbrpageMouvCarteRes", resultat);
			} catch (Exception e) {
				producer.send(record.key().toString(), "nbrpageMouvCarteRes", e.getMessage());
			}
		}
		
		@KafkaListener(topics = "ajoutMouvCarteReq")
		public void ajout(ConsumerRecord<?, ?> record) {
			try {
				ParamCarteMouv param2 =om.readValue(record.value().toString(), ParamCarteMouv.class);
				MouvCarteMod param = new MouvCarteMod();
				log.info("test :"+record.value().toString());
				param.setDatemouv(DateUtil.sqlDateNow());
				param.setIdmouvcarte(Integer.valueOf(String.valueOf(service.seqDemande())));
				param.setEntree(param2.getEntree());
				param.setMotif(param2.getMotif());
				param.setLibelle(param2.getLibelle());
				param.setNumcarte(param2.getNumcarte());
				param.setRefdmd(param2.getRefdmd());
				param.setSortant(param2.getSortant());
				service.save(param);
				HistoriqueMod param1 =new HistoriqueMod();
				param1.setTache("Ajout d'une mouvement de carte de référence : "+ param2.getRefdmd());
				param1.setDatehisto(DateUtil.sqlDateNow());
				param1.setMatriculeuser(param2.getMatriculeuser());
				param1.setUserinsert(param2.getUserinsert());
				param1.setIdhistorique(Integer.valueOf(String.valueOf(servicehisto.seqDemande())));
				servicehisto.save(param1);
				producer.send(record.key().toString(), "ajoutMouvCarteRes", record.value().toString());
			} catch (Exception e) {
				e.printStackTrace();
				producer.send(record.key().toString(), "ajoutMouvCarteRes", e.getMessage());
			}
		}

		@KafkaListener(topics = "supprMouvCarteReq")
		public void suppr(ConsumerRecord<?, ?> record) {
			try {
				service.delete(om.readValue(record.value().toString(), MouvCarteMod.class));
				producer.send(record.key().toString(), "supprMouvCarteRes", "{\"success\":true}");
			} catch (Exception e) {
				producer.send(record.key().toString(), "supprMouvCarteRes", e.getMessage());
			}
		}
		
		@KafkaListener(topics = "MouvCarteMensuelReq")
		public void rechmultipleMvt(ConsumerRecord<?, ?> record) {
			try {
				MouvCarteMensuelMod param =om.readValue(record.value().toString(),MouvCarteMensuelMod.class);
				List<MouvCarteMensuelMod> list = service.Mouvcartemensuel(param.getMois(),new PageRequest(Integer.parseInt(record.value().toString()), 10));
				resultat=om.writeValueAsString(list);
				log.info("res: "+resultat);
				producer.send(record.key().toString(), "MouvCarteMensuelRes", resultat);
			} catch (Exception e) {
				producer.send(record.key().toString(), "MouvCarteMensuelRes", e.getMessage());
			}
		}
		
		@KafkaListener(topics = "listeCarteRetournerReq")
		public void listeCarteRetourner(ConsumerRecord<?, ?> record) {
			try {
				log.info("valeur: "+record.value().toString());
				List<MouvCarteMod> list = service.listeCarteRetourner();
				resultat=om.writeValueAsString(list);
				log.info("res: "+resultat);
				producer.send(record.key().toString(), "listeCarteRetournerRes", resultat);
			} catch (Exception e) {
				e.printStackTrace();
				producer.send(record.key().toString(), "listeCarteRetournerRes", e.getMessage());
			}
		}
		
		@KafkaListener(topics = "rechercheCarteRetournerReq")
		public void rechCarteRetourner(ConsumerRecord<?, ?> record) {
			try {
				MouvCarteMensuelMod param =om.readValue(record.value().toString(),MouvCarteMensuelMod.class);
				List<MouvCarteMensuelMod> list = service.rechCarteRetourner(param.getDatemouv(), param.getNumcarte());
				resultat=om.writeValueAsString(list);
				log.info("res: "+resultat);
				producer.send(record.key().toString(), "rechercheCarteRetournerRes", resultat);
			} catch (Exception e) {
				producer.send(record.key().toString(), "rechercheCarteRetournerRes", e.getMessage());
			}
		}
		
		@KafkaListener(topics = "ajoutCarteRetournerReq")
		public void ajoutCarteRetourner(ConsumerRecord<?, ?> record) {
			try {
				MouvCarteMod param =om.readValue(record.value().toString(),MouvCarteMod.class);
				service.save(param);
				HistoriqueMod param1 =new HistoriqueMod();
				param1.setTache("Retour du carte qui a le numero carte : "+ param.getNumcarte());
				param1.setDatehisto(DateUtil.sqlDateNow());
				servicehisto.save(param1);
				producer.send(record.key().toString(), "ajoutCarteRetournerRes", record.value().toString());
			} catch (Exception e) {
				producer.send(record.key().toString(), "ajoutCarteRetournerRes", e.getMessage());
			}
		}
		
	}
	
	

