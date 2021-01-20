package com.cnaps.kafka;

//import java.sql.Date;
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
import mg.cnaps.models.MouvementVehiculeMod;
import mg.cnaps.models.ParamCalculprixandlitreAntenne;
import mg.cnaps.models.ParamCalculprixandlitreSiege;
import mg.cnaps.models.ParamChauffeurMvmVehicule;
import mg.cnaps.models.ParamMouvementVehicule;
import mg.cnaps.models.ParamMouvvehic;
import mg.cnaps.models.PrixCarbuMod;
import mg.cnaps.services.DemandeCarbService;
import mg.cnaps.services.MouvementVehiculeService;
//import mg.cnaps.models.ParamDemandes;
import mg.cnaps.services.PrixCarbuService;
import mg.cnaps.utils.DateUtil;
import mg.cnaps.utils.ReferenceUtil;


@Component
public class MouvementVehiculeConsumer {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(MouvementVehiculeConsumer.class);
	@Autowired
	Producer producer;

	@Autowired
	MouvementVehiculeService service;
	
	@Autowired
	PrixCarbuService serviceprixcarb;
	@Autowired
	DemandeCarbService servicecarb;
	

	@KafkaListener(topics = "listeMvmVehiculeReq")
	public void liste(ConsumerRecord<?, ?> record) {
		try {
			log.info("pageliste: "+record.value().toString());
			List<MouvementVehiculeMod> liste = service.findallmouv(new PageRequest(Integer.parseInt(record.value().toString()), 10));
			log.info("listpage test: "+om.writeValueAsString(liste));
			producer.send(record.key().toString(), "listeMvmVehiculeRes", om.writeValueAsString(liste));
		} catch (Exception e) {
			e.printStackTrace();
			producer.send(record.key().toString(), "listeMvmVehiculeRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "nbrpageMvmVehiculeReq")
	public void nombredepage(ConsumerRecord<?, ?> record) {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			producer.send(record.key().toString(), "nbrpageMvmVehiculeeRes", resultat);
		} catch (Exception e) {
			producer.send(record.key().toString(), "nbrpageMvmVehiculeRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "ajoutMvmVehiculeReq")
	public void ajout(ConsumerRecord<?, ?> record) {
		try {
			log.info("reo e : "+record.value().toString());
			ParamMouvvehic  param =om.readValue(record.value().toString(),ParamMouvvehic.class);
			MouvementVehiculeMod io = new MouvementVehiculeMod();
			io.setIdcartecarb(param.getIdcartecarb());
			io.setDatedepart(param.getDatedepart());
			io.setDatearriver(param.getDatearriver());
			io.setKmdepart(param.getKmdepart());
			io.setKmarriver(param.getKmarriver());
			io.setIdchauffeur(param.getIdchauffeur());
			io.setNbrpersonne(param.getNbrpersonne());
			io.setIdmission(param.getIdmission());
			io.setQuantitecarb(param.getQuantitecarb());
			io.setPrixcarb(param.getPrixcarb());
			io.setRefdmdvh(param.getRefdmdvh());
			io.setIdvehicule(param.getIdvehicule());
			io.setIddemande(param.getIddemande());
			io.setConsomveh(param.getConsomveh());
			io.setService(param.getCodeservice());
			io.setIdmouv(Integer.valueOf(String.valueOf(service.seqDemande())));
			service.save(io);
			String defdmd = param.getDefdmd();
			if(defdmd.compareToIgnoreCase("mission") == 0) {
				String codedr = param.getCodedr();
				log.info("codedr : " + codedr);
				if( codedr.compareToIgnoreCase("42") == 0) {
					PrixCarbuMod val = serviceprixcarb.findprixcarburant(param.getTypecarbu());
					String prestation = "CR";
					resultat = ReferenceUtil.getReferenceDemande(prestation, service.seqDemande(), param.getCodedr());
					DemandeCarbMod ins = new DemandeCarbMod();
					ins.setReferencecarbu(resultat);
					ins.setIdprix(param.getPrixcarb());
					ins.setDatedemande(DateUtil.sqlDateNow());
					ins.setDistance(param.getDistance());
					ins.setDurermission(param.getSejour());
					ins.setValidation(param.getValidation());
					servicecarb.save(ins);
					Double prix = param.getDistance()*2*(param.getConsomveh()/100)*val.getPrix()*(Double.valueOf(String.valueOf(param.getSejour())));
					Double quantite = param.getDistance()*2*(param.getConsomveh()/100)*(Double.valueOf(String.valueOf(param.getSejour())));
					service.updatemouvveh(prix , quantite, io.getIdmouv());
					producer.send(record.key().toString(), "ajoutMvmVehiculeRes", record.value().toString());
				}
				else {
					PrixCarbuMod val = serviceprixcarb.findprixcarburant(param.getTypecarbu());
					String prestation = "CR";
					resultat = ReferenceUtil.getReferenceDemande(prestation, service.seqDemande(), param.getCodedr());
					DemandeCarbMod ins = new DemandeCarbMod();
					ins.setReferencecarbu(resultat);
					ins.setIdprix(param.getPrixcarb());
					ins.setDatedemande(DateUtil.sqlDateNow());
					ins.setDistance(param.getDistance());
					ins.setDurermission(param.getSejour());
					ins.setValidation(param.getValidation());
					servicecarb.save(ins);
					Double prix = param.getDistance()*2*(param.getConsomveh()/100)*val.getPrix();
					Double quantite = param.getDistance()*2*(param.getConsomveh()/100);
					service.updatemouvveh(prix , quantite, io.getIdmouv());
					producer.send(record.key().toString(), "ajoutMvmVehiculeRes", record.value().toString());
				}
			}
			if(defdmd.compareToIgnoreCase("location") == 0) {
				PrixCarbuMod val = serviceprixcarb.findprixcarburant(param.getTypecarbu());
				String prestation = "CR";
				resultat = ReferenceUtil.getReferenceDemande(prestation, service.seqDemande(), param.getCodedr());
				DemandeCarbMod ins = new DemandeCarbMod();
				ins.setReferencecarbu(resultat);
				ins.setIdprix(param.getPrixcarb());
				ins.setDatedemande(DateUtil.sqlDateNow());
				ins.setDistance(param.getDistance());
				ins.setDurermission(param.getSejour());
				ins.setValidation(param.getValidation());
				servicecarb.save(ins);
				Double prix = param.getDistance()*2*(param.getConsomveh()/100)*val.getPrix()*(Double.valueOf(String.valueOf(param.getSejour())));
				Double quantite = param.getDistance()*2*(param.getConsomveh()/100)*(Double.valueOf(String.valueOf(param.getSejour())));
				service.updatemouvveh(prix , quantite, io.getIdmouv());
				producer.send(record.key().toString(), "ajoutMvmVehiculeRes", record.value().toString());
			}
			else {
				producer.send(record.key().toString(), "ajoutMvmVehiculeRes", "{\"success\":true}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			producer.send(record.key().toString(), "ajoutMvmVehiculeRes", e.getMessage());
		}
	}

	@KafkaListener(topics="rechMvmVehiculeDateReq")
	public void recherche(ConsumerRecord<?, ?> record) {
		try {
			log.info("rechMvmVehicule:"+record.value().toString() );
			ParamMouvementVehicule  param =om.readValue(record.value().toString(),ParamMouvementVehicule.class );
			List<MouvementVehiculeMod>access=service.getByDatedepart(param.getDatedepart());
			resultat=om.writeValueAsString(access);
			log.info("res: "+resultat);
			producer.send(record.key().toString(),"rechercheMvmVehiculeRes","{\"success\":true}");
		} catch(Exception e) {
			producer.send(record.key().toString(),"rechercheMvmVehiculeRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics="rechChauffeurMvmVehiculeReq")
	public void rechChauffeur(ConsumerRecord<?, ?> record) {
		try {
			log.info("rechChauffeur:"+record.value().toString() );
			ParamChauffeurMvmVehicule  param =om.readValue(record.value().toString(),ParamChauffeurMvmVehicule.class );
			List<MouvementVehiculeMod>access=service.getByIdchauffeur(param.getIdchauffeur());
			resultat=om.writeValueAsString(access);
			log.info("res: "+resultat);
			producer.send(record.key().toString(),"rechercheChauffeurMvmRes","{\"success\":true}");
		} catch(Exception e) {
			producer.send(record.key().toString(),"rechercheChauffeurMvmRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "supprmouvementVehiculeReq")
	public void suppr(ConsumerRecord<?, ?> record) {
		try {
			service.delete(om.readValue(record.value().toString(), MouvementVehiculeMod.class));
			producer.send(record.key().toString(), "supprmouvementVehiculeRes", "{\"success\":true}");
		} catch (Exception e) {
			producer.send(record.key().toString(), "supprmouvementVehiculeRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "calculprixandlitreantenneReq")
	public void calculprixandlitreAntennes(ConsumerRecord<?, ?> record) {
		try {
			ParamCalculprixandlitreAntenne param =om.readValue(record.value().toString(),ParamCalculprixandlitreAntenne.class);
			PrixCarbuMod val = serviceprixcarb.findprixcarburant(param.getTypecarburant());
			Double prix = param.getDistance()*2*param.getConsommationveh()*val.getPrix();
			Double quantite = param.getDistance()*2*param.getConsommationveh();
			service.updatemouvveh(prix , quantite, param.getIdmouv());
			producer.send(record.key().toString(), "calculprixandlitreantenneRes", "{\"success\":true}");
		} catch (Exception e) {
			producer.send(record.key().toString(), "calculprixandlitreantenneRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "calculprixandlitresiegeReq")
	public void calculprixandlitresiege(ConsumerRecord<?, ?> record) {
		try {
			ParamCalculprixandlitreSiege param =om.readValue(record.value().toString(),ParamCalculprixandlitreSiege.class);
			PrixCarbuMod val = serviceprixcarb.findprixcarburant(param.getTypecarburant());
			Double prix = param.getDistance()*2*param.getConsommationveh()*val.getPrix()*(Double.valueOf(String.valueOf(param.getSejour())));
			Double quantite = param.getDistance()*2*param.getConsommationveh()*(Double.valueOf(String.valueOf(param.getSejour())));
			service.updatemouvveh(prix , quantite, param.getIdmouv());
			producer.send(record.key().toString(), "calculprixandlitresiegeRes", "{\"success\":true}");
		} catch (Exception e) {
			producer.send(record.key().toString(), "calculprixandlitresiegeRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "updatedatearriverReq")
	public void updatedatearriver(ConsumerRecord<?, ?> record) {
		try {
			MouvementVehiculeMod param =om.readValue(record.value().toString(),MouvementVehiculeMod.class);
			service.updatearrivermouv(param.getDatearriver(), param.getIdmouv());
			producer.send(record.key().toString(), "updatedatearriverRes", record.value().toString());
		} catch (Exception e) {
			producer.send(record.key().toString(), "updatedatearriverRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "RechbydepartarrivchaufrefvehReq")
	public void rechmultipleMvt(ConsumerRecord<?, ?> record) {
		try {
			MouvementVehiculeMod param =om.readValue(record.value().toString(),MouvementVehiculeMod.class);
			List<MouvementVehiculeMod> list = service.rechmultipleMvt(param.getDatedepart(),param.getDatearriver(),param.getIdvehicule(),param.getIdchauffeur());
			resultat=om.writeValueAsString(list);
			log.info("res: "+resultat);
			producer.send(record.key().toString(), "RechbydepartarrivchaufrefvehRes", resultat);
		} catch (Exception e) {
			producer.send(record.key().toString(), "RechbydepartarrivchaufrefvehRes", e.getMessage());
		}
	}
}

