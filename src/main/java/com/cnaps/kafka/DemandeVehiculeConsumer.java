package com.cnaps.kafka;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import mg.cnaps.models.DemandeVehiculeMod;
import mg.cnaps.models.ParamDemandesVehicule;
//import mg.cnaps.models.ReferenceCarb;
import mg.cnaps.models.ReferenceVehicule;
//import mg.cnaps.models.VehiculeMod;
import mg.cnaps.services.DemandeVehiculeService;
import mg.cnaps.services.MouvementVehiculeService;
import mg.cnaps.utils.ReferenceUtil;

@Component
public class DemandeVehiculeConsumer {
	ObjectMapper om= new ObjectMapper() ;
	
	String resultat;
	public static final Logger log=LoggerFactory.getLogger(DemandeVehiculeConsumer.class);
	@Autowired
	Producer producer;
	
	@Autowired
	DemandeVehiculeService service;
	
	@Autowired
	MouvementVehiculeService serviceMouv;
	
	/*@KafkaListener(topics="listeDemandeVehiculeReq")
	public void list(ConsumerRecord<?,?>record) {
		try {
			log.info("pageliste:"+record.value().toString());
//			List<DemandeVehiculeMod> liste = service.getAll(Integer.parseInt(record.value().toString()));
			List<DemandeVehiculeMod> liste = service.findByValidation(new PageRequest(Integer.parseInt(record.value().toString()), 10));
			log.info("liste:"+om.writeValueAsString(liste));
			producer.send(record.key().toString(), "listeDemandeVehiculeRes", om.writeValueAsString(liste));
		}catch(Exception e){
			e.printStackTrace();
			producer.send(record.key().toString(), "listeDemandeVehiculeRes", e.getMessage());
		}
	}*/
	
	/*@KafkaListener(topics="listeDemandeauthoriserReq")
	public void listauthoriser(ConsumerRecord<?,?>record) {
		try {
			log.info("pageliste:"+record.value().toString());
			List<DemandeVehiculeMod> liste = service.listauthoriser(new PageRequest(Integer.parseInt(record.value().toString()), 10));
			List<DemandeVehiculeMod> io = new ArrayList<>();
			int size = liste.size();
			for(int i =0; i<size;i++) {
				log.info("iddemande : "+liste.get(i).getIddemande());
				int val = serviceMouv.countMouv(liste.get(i).getIddemande());
				log.info("val : "+val);
				if(val == 0) {
					io.add(liste.get(i));
				}
			}
			log.info("liste:"+om.writeValueAsString(io));
			producer.send(record.key().toString(), "listeDemandeauthoriserRes", om.writeValueAsString(io));
		}catch(Exception e){
			e.printStackTrace();
			producer.send(record.key().toString(), "listeDemandeauthoriserRes", e.getMessage());
		}
	}*/
	
	@KafkaListener(topics = "nbrpageDemandeVehiculeReq")
	public void nombredepage(ConsumerRecord<?, ?> record) {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			producer.send(record.key().toString(), "nbrpageDemandeVehiculeRes", resultat);
		} catch (Exception e) {
			producer.send(record.key().toString(), "nbrpageDemandeVehiculeRes", e.getMessage());
		}
	}
	
	
	@KafkaListener(topics = "referenceDmdVehiReq")
	public void demandeReference(ConsumerRecord<?, ?> record) throws Exception {
		try {
			log.info("ReferenceDmd: " + record.value().toString());
			ReferenceVehicule param = om.readValue(record.value().toString(), ReferenceVehicule.class);
			resultat = ReferenceUtil.getReferenceDemande(param.getPrestation(), service.seqDemande(), param.getDr());
			log.info("ref resultat: "+resultat);
			producer.send(record.key().toString(), "referenceDmdVehiRes", om.writeValueAsString(resultat));
		}
		catch(Exception e) {
			producer.send(record.key().toString(), "referenceDmdVehiRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "rechercheDateHeureSortieVehReq")
	public void rech(ConsumerRecord<?,?> record) {
		try {
			log.info("rechdate:"+record.value().toString() );
			ParamDemandesVehicule  param =om.readValue(record.value().toString(), ParamDemandesVehicule.class);
			List<DemandeVehiculeMod> access=service.getByDateHeureDepart(param.getDatedepart(),param.getHeuredepart());
			resultat=om.writeValueAsString(access);
			log.info("res: "+resultat);
			producer.send(record.key().toString(),"rechercheDateHeureSortieVehRes","{\"success\":true}");
		} catch(Exception e) {
			producer.send(record.key().toString(),"rechercheDateHeureSortieVehRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "recherchebydefmouvnonvaliderReq")
	public void rechbydefmouv(ConsumerRecord<?,?> record) {
		try {
			log.info("rechdate:"+record.value().toString() );
			DemandeVehiculeMod  param =om.readValue(record.value().toString(), DemandeVehiculeMod.class);
			String validation = "false";
			if( param.getDatedepart() == null) {
				List<DemandeVehiculeMod> access=service.getByDefMouv(param.getDefdmd(),param.getCodedr(), validation);
				resultat=om.writeValueAsString(access);
				log.info("res: "+resultat);
				producer.send(record.key().toString(),"recherchebydefmouvnonvaliderRes", resultat);
			}
			else {
				List<DemandeVehiculeMod> access=service.getByDefMouv2(param.getDefdmd(),param.getCodedr(), param.getDatedepart(),validation);
				resultat=om.writeValueAsString(access);
				log.info("res: "+resultat);
				producer.send(record.key().toString(),"recherchebydefmouvnonvaliderRes", resultat);
			}
		} catch(Exception e) {
			producer.send(record.key().toString(),"recherchebydefmouvnonvaliderRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "recherchebydefmouvvaliderReq")
	public void rechbydefmouvnonvalider(ConsumerRecord<?,?> record) {
		try {
			log.info("rechdate:"+record.value().toString() );
			DemandeVehiculeMod  param =om.readValue(record.value().toString(), DemandeVehiculeMod.class);
			String validation = "true";
			if( param.getDatedepart() == null) {
				List<DemandeVehiculeMod> access=service.getByDefMouv(param.getDefdmd(),param.getCodedr(), validation);
				resultat=om.writeValueAsString(access);
				log.info("res: "+resultat);
				producer.send(record.key().toString(),"recherchebydefmouvvaliderRes", resultat);
			}
			else {
				List<DemandeVehiculeMod> access=service.getByDefMouv2(param.getDefdmd(),param.getCodedr(), param.getDatedepart(),validation);
				resultat=om.writeValueAsString(access);
				log.info("res: "+resultat);
				producer.send(record.key().toString(),"recherchebydefmouvvaliderRes", resultat);
			}
		} catch(Exception e) {
			producer.send(record.key().toString(),"recherchebydefmouvvaliderRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "ajoutDemandeVehiculeReq")
	public void ajout(ConsumerRecord<?, ?> record) {
		try {
			log.info("insert: "+record.value().toString());
			DemandeVehiculeMod  val= om.readValue(record.value().toString(), DemandeVehiculeMod.class);
			val.setIddemande(Integer.valueOf(String.valueOf(service.seqDemande())));
			log.info("res "+om.writeValueAsString(val));
			service.save(val);
			producer.send(record.key().toString(), "ajoutDemandeVehiculeRes", om.writeValueAsString(val));
		} catch (Exception e) {
			e.printStackTrace();
			producer.send(record.key().toString(), "ajoutDemandeVehiculeRes", e.getMessage());
			
		}
	}

	@KafkaListener(topics = "supprDemandeVehiculeReq")
	public void suppr(ConsumerRecord<?, ?> record) {
		try {
			service.delete(om.readValue(record.value().toString(), DemandeVehiculeMod.class));
			producer.send(record.key().toString(), "supprDemandeVehiculeRes", "{\"success\":true}");
		} catch (Exception e) {
			producer.send(record.key().toString(), "supprDemandeVehiculeRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "updatevalidationdmdvehReq")
	public void updatevalidationdmdveh(ConsumerRecord<?, ?> record) {
		try {
			log.info("insert: "+record.value().toString());
			DemandeVehiculeMod dvm = om.readValue(record.value().toString(), DemandeVehiculeMod.class);
			log.info("insert222: "+om.writeValueAsString(dvm));
			service.updatevalidationdmdveh(dvm.getIddemande());
			producer.send(record.key().toString(), "updatevalidationdmdvehRes", om.writeValueAsString(dvm));
			log.info("Send: "+record.key().toString());
		} catch (Exception e) {
			e.printStackTrace();
			producer.send(record.key().toString(), "updatevalidationdmdvehRes", e.getMessage());
			
		}
	}
	
	
}

