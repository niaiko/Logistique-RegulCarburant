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

import mg.cnaps.models.DemandeCarbMod;
import mg.cnaps.models.HistoriqueMod;
import mg.cnaps.models.ParamDemandeCarbu;
import mg.cnaps.models.ReferenceCarb;
import mg.cnaps.services.DemandeCarbService;
import mg.cnaps.services.HistoriqueService;
import mg.cnaps.utils.DateUtil;
//import mg.cnaps.models.ParamDemandes;
import mg.cnaps.utils.ReferenceUtil;


@Component
public class DemandeCarbConsumer {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(DemandeCarbConsumer.class);
	@Autowired
	Producer producer;

	@Autowired
	DemandeCarbService service; 
	
	@Autowired
	HistoriqueService servicehisto;
	
	@KafkaListener(topics = "listedemandecarbReq")
	public void liste(ConsumerRecord<?, ?> record) {
		try {
			log.info("pageliste: "+record.value().toString());
			List<DemandeCarbMod> liste = service.getAll(Integer.parseInt(record.value().toString()));
			log.info("listedemande: "+om.writeValueAsString(liste));
			producer.send(record.key().toString(), "listedemandecarbRes", om.writeValueAsString(liste));
		} catch (Exception e) {
			producer.send(record.key().toString(), "listedemandecarbRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "nbrpagedemandecarbReq")
	public void nombredepage(ConsumerRecord<?, ?> record) {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			producer.send(record.key().toString(), "nbrpagedemandecarbRes", resultat);
		} catch (Exception e) {
			producer.send(record.key().toString(), "nbrpagedemandecarbRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "ajoutdemandecarbReq")
	public void ajout(ConsumerRecord<?, ?> record) {
		try {
			log.info("insert: "+record.value().toString());
			ParamDemandeCarbu param =om.readValue(record.value().toString(),ParamDemandeCarbu.class);
			DemandeCarbMod io = new DemandeCarbMod();
			io.setDatedemande(param.getDatedemande());
			io.setDistance(param.getDistance());
			io.setDurermission(param.getDurermission());
			io.setIdcarbu(param.getIdcarbu());
			io.setValidation(param.getValidation());
			io.setIdprix(param.getIdprix());
			io.setMatrdmd(param.getMatrdmd());
			io.setMotif(param.getMotif());
			io.setReferencecarbu(param.getReferencecarbu());
			service.save(io);
			HistoriqueMod param1 =new HistoriqueMod();
			param1.setDatehisto(DateUtil.sqlDateNow());
			param1.setTache("Ajout d'une demande de carburant d'une motif de " + param.getMotif());
			param1.setMatriculeuser(param.getMatrdmd());
			param1.setUserinsert(param.getUserinsert());
			param1.setIdhistorique(Integer.valueOf(String.valueOf(servicehisto.seqDemande())));
			servicehisto.save(param1);
			producer.send(record.key().toString(), "ajoutdemandecarbRes", "{\"success\":true}");
		} catch (Exception e) {
			e.printStackTrace();
			producer.send(record.key().toString(), "ajoutdemandecarbRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "supprdemandecarbReq")
	public void suppr(ConsumerRecord<?, ?> record) {
		try {
			service.delete(om.readValue(record.value().toString(),  DemandeCarbMod.class));
			producer.send(record.key().toString(), "supprdemandecarbRes", "{\"success\":true}");
		} catch (Exception e) {
			producer.send(record.key().toString(), "supprdemandecarbRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "referenceCarbuDmdReq")
	public void demandeReference(ConsumerRecord<?, ?> record) {
		try {
			log.info("Reference: " + record.value().toString());
			ReferenceCarb param = om.readValue(record.value().toString(), ReferenceCarb.class);
			resultat = ReferenceUtil.getReferenceDemande(param.getPrestation(), service.seqDemande(), param.getDr());
			log.info("ref resultat: "+resultat);
			producer.send(record.key().toString(), "referenceCarbuDmdRes", om.writeValueAsString(resultat));
		}
		catch(Exception e) {
			producer.send(record.key().toString(), "referenceCarbuDmdRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics="recherchedatedemandeReq")
	public void recherche(ConsumerRecord<?,?> record) {
		try {
			log.info("rech:"+record.value().toString() );
			DemandeCarbMod param =om.readValue(record.value().toString(), DemandeCarbMod.class);
			log.info("date : "+param.getDatedemande());
			if(param.getDatedemande() == null) {
				List<DemandeCarbMod> access=service.getBysansDateDemande(param.getValidation(), param.getReferencecarbu());
				resultat=om.writeValueAsString(access);
				log.info("res: "+resultat);
				producer.send(record.key().toString(), "recherchedatedemandeRes", resultat);
			}
			else {
				List<DemandeCarbMod> access=service.getByavecDateDemande(param.getDatedemande(), param.getValidation(), param.getReferencecarbu());
				resultat=om.writeValueAsString(access);
				log.info("res: "+resultat);
				producer.send(record.key().toString(), "recherchedatedemandeRes", resultat);
			}
		}	catch (Exception e) {
			e.printStackTrace();
			producer.send(record.key().toString(), "recherchedatedemandeRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "updatevalidationdmdecarbuReq")
	public void updatevalidationdmdveh(ConsumerRecord<?, ?> record) {
		try {
			log.info("insert: "+record.value().toString());
			ParamDemandeCarbu dvm = om.readValue(record.value().toString(), ParamDemandeCarbu.class);
			log.info("insert222: "+om.writeValueAsString(dvm));
			service.updatevalidationdmdcarbu(dvm.getIdcarbu());
			HistoriqueMod param1 =new HistoriqueMod();
			param1.setDatehisto(DateUtil.sqlDateNow());
			param1.setTache("Validation du demande de carburant de reference : " + dvm.getReferencecarbu());
			param1.setMatriculeuser(dvm.getMatriculeuser());
			param1.setUserinsert(dvm.getUserinsert());
			param1.setIdhistorique(Integer.valueOf(String.valueOf(servicehisto.seqDemande())));
			servicehisto.save(param1);
			producer.send(record.key().toString(), "updatevalidationdmdecarbuRes", om.writeValueAsString(dvm));
			log.info("Send: "+record.key().toString());
		} catch (Exception e) {
			e.printStackTrace();
			producer.send(record.key().toString(), "updatevalidationdmdecarbuRes", e.getMessage());
			
		}
	}
	
	@KafkaListener(topics = "listevalidationdmdecarbuReq")
	public void listevalidationdmdveh(ConsumerRecord<?, ?> record) {
		try {
			log.info("id : "+record.value().toString());
			List<DemandeCarbMod> io = service.listevalidationdmdcarbu(new PageRequest(Integer.parseInt(record.value().toString()), 10));
			producer.send(record.key().toString(), "listevalidationdmdecarbuRes", om.writeValueAsString(io));
			log.info("Send: "+io);
		} catch (Exception e) {
			e.printStackTrace();
			producer.send(record.key().toString(), "listevalidationdmdecarbuRes", e.getMessage());
			
		}
	}
	
	/*@KafkaListener(topics = "listenonvalidationdmdecarbuReq")
	public void listenonvalidationdmdveh(ConsumerRecord<?, ?> record) {
		try {
//			log.info("insert: "+record.value().toString());
//			DemandeCarbMod dvm = om.readValue(record.value().toString(), DemandeCarbMod.class);
//			log.info("insert222: "+om.writeValueAsString(dvm));
			List<DemandeCarbMod> io = service.listenonvalidationdmdcarbu(new PageRequest(Integer.parseInt(record.value().toString()), 10));
			producer.send(record.key().toString(), "listenonvalidationdmdecarbuRes", om.writeValueAsString(io));
			log.info("Send: "+io);
		} catch (Exception e) {
			e.printStackTrace();
			producer.send(record.key().toString(), "listenonvalidationdmdecarbuRes", e.getMessage());
			
		}
	}*/
}