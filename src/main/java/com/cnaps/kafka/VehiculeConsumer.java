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

import mg.cnaps.models.OnglobeVehiculeMod;
import mg.cnaps.models.ParamOnglobeVehicule;
import mg.cnaps.models.VehiculeMod;
import mg.cnaps.models.article.ParamDonneeVehicule;
import mg.cnaps.services.OnglobeVehiculeService;
//import mg.cnaps.models.ParamDemandes;
	import mg.cnaps.services.VehiculeService;

	@Component
	public class VehiculeConsumer {

		ObjectMapper om = new ObjectMapper();
		String resultat;

		private static final Logger log = LoggerFactory.getLogger(VehiculeConsumer.class);
		@Autowired
		Producer producer;

		@Autowired
		VehiculeService service;
		
		@Autowired
		OnglobeVehiculeService serviceOngl;
		

		@KafkaListener(topics = "listeVehiculeReq")
		public void liste(ConsumerRecord<?, ?> record) {
			try {
				log.info("pageliste: "+record.value().toString());
				List<VehiculeMod> liste = service.findioAll();
				log.info("listcou: "+om.writeValueAsString(liste));
				producer.send(record.key().toString(), "listeVehiculeRes", om.writeValueAsString(liste));
			} catch (Exception e) {
				e.printStackTrace();
				producer.send(record.key().toString(), "listeVehiculeRes", e.getMessage());
			}
		}
		
		@KafkaListener(topics = "nbrpageVehiculeReq")
		public void nombredepage(ConsumerRecord<?, ?> record) {
			try {
				int page = service.nombrepage();
				resultat = om.writeValueAsString(page);
				producer.send(record.key().toString(), "nbrpageVehiculeRes", resultat);
			} catch (Exception e) {
				producer.send(record.key().toString(), "nbrpageVehiculeRes", e.getMessage());
			}
		}
		
		@KafkaListener(topics = "ajoutVehiculeReq")
		public void ajout(ConsumerRecord<?, ?> record) {
			try {
				service.save(om.readValue(record.value().toString(), VehiculeMod.class));
				producer.send(record.key().toString(), "ajoutVehiculeRes", "{\"success\":true}");
			} catch (Exception e) {
				producer.send(record.key().toString(), "ajoutVehiculeRes", e.getMessage());
			}
		}

		@KafkaListener(topics = "supprVehiculeReq")
		public void suppr(ConsumerRecord<?, ?> record) {
			try {
				service.delete(om.readValue(record.value().toString(), VehiculeMod.class));
				producer.send(record.key().toString(), "supprVehiculeRes", "{\"success\":true}");
			} catch (Exception e) {
				producer.send(record.key().toString(), "supprVehiculeRes", e.getMessage());
			}
		}
		
		@KafkaListener(topics = "compareinfovehicReq")
		public void compareinfovehic(ConsumerRecord<?, ?> record) {
			try {
				log.info("rech: " + record.value().toString());
				ParamDonneeVehicule[] param =om.readValue(record.value().toString(),ParamDonneeVehicule[].class);
				int count = param.length;
				log.info("isa : "+count);
				for(int i=0; i<count;i++) {
					int count2 = param[i].getCodeArticle().length;
					for(int j=0; j<count2;j++) {
						log.info("isa2 : "+count2);
						int nbr = service.compairevehicule(param[i].getCodeArticle()[j].getIdCodeArt(),param[i].getArticle().getLibelle());
						log.info("nbr : "+nbr);
						if(nbr == 0) 
							{
								VehiculeMod io = new VehiculeMod();
								io.setMarque(param[i].getArticle().getLibelle());
								io.setRefmt(param[i].getCodeArticle()[j].getMarque());
								io.setRefvehicule(param[i].getCodeArticle()[j].getIdCodeArt());
								io.setIdVehicule(Integer.valueOf(String.valueOf(service.seqDemande())));
								service.save(io);
							}
					}
				}
//				List<VehiculeMod> liste = service.getvehiculenondetail();
//				resultat = om.writeValueAsString(liste);
				producer.send(record.key().toString(), "compareinfovehicRes", record.value().toString());
			} catch (Exception e) {
				e.printStackTrace();
				producer.send(record.key().toString(), "compareinfovehicRes", e.getMessage());
			}
		}
		
		@KafkaListener(topics = "AjoutinfovehiReq")
		public void ajoutinfovehi(ConsumerRecord<?, ?> record) {
			try {
				log.info("rech: " + record.value().toString());
				ParamOnglobeVehicule param =om.readValue(record.value().toString(),ParamOnglobeVehicule.class);
				service.updatedonneevehic(param.getIdVehicule(),param.getConsommation(),param.getCodedr(),param.getMatrvehic());
				int nbr = serviceOngl.existevehic(param.getIdVehicule());
				log.info("count: " + nbr);
				if(nbr == 0) {
					OnglobeVehiculeMod io = new OnglobeVehiculeMod();
					io.setIdOnglobe(Integer.parseInt(String.valueOf(service.seqDemande())));
					io.setIdType2(param.getIdType2());
					io.setLibelle(param.getLibelle());
					io.setDateDebut(param.getDateDebut());
					io.setDateFin(param.getDateFin());
					io.setDatevisite(param.getDatevisite());
					io.setDatecnavto(param.getDatecnavto());
					io.setIdVehicule(param.getIdVehicule());
					serviceOngl.save(io);
					producer.send(record.key().toString(), "AjoutinfovehiRes", record.value().toString());
				}
				else {
					producer.send(record.key().toString(), "AjoutinfovehiRes", record.value().toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
				producer.send(record.key().toString(), "AjoutinfovehiRes", e.getMessage());
			}
		}
		
		@KafkaListener(topics = "listevehiculedispoReq")
		public void listevehiculedispo(ConsumerRecord<?, ?> record) {
			try {
				List<VehiculeMod> liste = service.getlistevehiculedispo(new PageRequest(Integer.parseInt(record.value().toString()), 10));
				resultat = om.writeValueAsString(liste);
				producer.send(record.key().toString(), "listevehiculedispoRes", resultat);
			} catch (Exception e) {
				producer.send(record.key().toString(), "listevehiculedispoRes", e.getMessage());
			}
		}
		
		@KafkaListener(topics = "listevehiculenondetailReq")
		public void listevehiculenondispo(ConsumerRecord<?, ?> record) {
			try {
				List<VehiculeMod> liste = service.getvehiculenondetail(new PageRequest(Integer.parseInt(record.value().toString()), 10));
				resultat = om.writeValueAsString(liste);
				producer.send(record.key().toString(), "listevehiculenondetailRes", resultat);
			} catch (Exception e) {
				producer.send(record.key().toString(), "listevehiculenondetailRes", e.getMessage());
			}
		}
	}
	
	

