package com.cnaps.kafka;

	import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import mg.cnaps.models.HistoriqueMod;
import mg.cnaps.models.ParamCalculprixandlitreAntenne;
import mg.cnaps.models.PrixCarbuMod;
import mg.cnaps.services.HistoriqueService;
import mg.cnaps.services.PrixCarbuService;
import mg.cnaps.utils.DateUtil;

	@Component
	public class PrixCarbuConsumer {

		ObjectMapper om = new ObjectMapper();
		String resultat;

		private static final Logger log = LoggerFactory.getLogger(PrixCarbuConsumer.class);
		@Autowired
		Producer producer;

		@Autowired
		PrixCarbuService service;
		
		@Autowired
		HistoriqueService servicehisto;
		

		@KafkaListener(topics = "listePrixCarbuReq")
		public void liste(ConsumerRecord<?, ?> record) {
			try {
				log.info("pageliste: "+record.value().toString());
				List<PrixCarbuMod> liste = service.getAll(Integer.parseInt(record.value().toString()));
				log.info("listcou: "+om.writeValueAsString(liste));
				producer.send(record.key().toString(), "listePrixCarbuRes", om.writeValueAsString(liste));
			} catch (Exception e) {
				producer.send(record.key().toString(), "listePrixCarbuRes", e.getMessage());
			}
		}
		
		@KafkaListener(topics = "nbrpagePrixCarbuReq")
		public void nombredepage(ConsumerRecord<?, ?> record) {
			try {
				int page = service.nombrepage();
				resultat = om.writeValueAsString(page);
				producer.send(record.key().toString(), "nbrpagePrixCarbuRes", resultat);
			} catch (Exception e) {
				producer.send(record.key().toString(), "nbrpagePrixCarbuRes", e.getMessage());
			}
		}
		
		@KafkaListener(topics = "ajoutPrixCarbuReq")
		public void ajout(ConsumerRecord<?, ?> record) {
			try {
				PrixCarbuMod dvm = om.readValue(record.value().toString(), PrixCarbuMod.class);
				service.save(om.readValue(record.value().toString(), PrixCarbuMod.class));
				HistoriqueMod param1 =new HistoriqueMod();
				param1.setTache("Mise a jour du prix de carburant de  : "+ dvm.getPrix() );
				param1.setDatehisto(DateUtil.sqlDateNow());
				servicehisto.save(param1);
				producer.send(record.key().toString(), "ajoutPrixCarbuRes", "{\"success\":true}");
			} catch (Exception e) {
				producer.send(record.key().toString(), "ajoutPrixCarbuRes", e.getMessage());
			}
		}

		@KafkaListener(topics = "supprPrixCarbuReq")
		public void suppr(ConsumerRecord<?, ?> record) {
			try {
				service.delete(om.readValue(record.value().toString(),PrixCarbuMod.class));
				producer.send(record.key().toString(), "supprPrixCarbuRes", "{\"success\":true}");
			} catch (Exception e) {
				producer.send(record.key().toString(), "supprPrixCarbuRes", e.getMessage());
			}
		}
		
		@KafkaListener(topics = "getprixcarburantbycarbReq")
		public void getprixcarburantbycarb(ConsumerRecord<?, ?> record) {
			try {
				log.info("requete: "+record.value().toString());
				ParamCalculprixandlitreAntenne dvm = om.readValue(record.value().toString(), ParamCalculprixandlitreAntenne.class);
				PrixCarbuMod val = service.findprixcarburant(dvm.getTypecarburant());
				log.info("listcou: "+om.writeValueAsString(val));
				producer.send(record.key().toString(), "getprixcarburantbycarbRes", om.writeValueAsString(val));
			} catch (Exception e) {
				producer.send(record.key().toString(), "getprixcarburantbycarbRes", e.getMessage());
			}
		}
	}

