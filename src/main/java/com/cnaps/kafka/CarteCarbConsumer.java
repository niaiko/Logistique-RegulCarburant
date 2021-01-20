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
//import mg.cnaps.models.ParamDemandes;

import mg.cnaps.models.CarteCarbMod;
import mg.cnaps.models.HistoriqueMod;
import mg.cnaps.models.Paramcartecarbu;
import mg.cnaps.services.CarteCarbService;
import mg.cnaps.services.HistoriqueService;
import mg.cnaps.utils.DateUtil;


@Component
public class CarteCarbConsumer {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(CarteCarbConsumer.class);
	@Autowired
	Producer producer;

	@Autowired
	CarteCarbService service;
	
	@Autowired
	HistoriqueService servicehisto;
	
	@KafkaListener(topics = "listecartecarbReq")
	public void liste(ConsumerRecord<?, ?> record) {
		try {
			log.info("pageliste: "+record.value().toString());
			List<CarteCarbMod> liste = service.fillalllistecarte(new PageRequest(Integer.parseInt(record.value().toString()), 5));
			log.info("listecarte: "+om.writeValueAsString(liste));
			producer.send(record.key().toString(), "listecartecarbRes", om.writeValueAsString(liste));
		} catch (Exception e) {
			e.printStackTrace();
			producer.send(record.key().toString(), "listecartecarbRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "listecartecarbdispoReq")
	public void listecartecarbdispo(ConsumerRecord<?, ?> record) {
		try {
			log.info("pageliste: "+record.value().toString());
			List<CarteCarbMod> liste = service.listecartecarbdispo();
			log.info("listecarte: "+om.writeValueAsString(liste));
			producer.send(record.key().toString(), "listecartecarbdispoRes", om.writeValueAsString(liste));
		} catch (Exception e) {
			e.printStackTrace();
			producer.send(record.key().toString(), "listecartecarbdispoRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "nbrpagecartecarbReq")
	public void nombredepage(ConsumerRecord<?, ?> record) {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			producer.send(record.key().toString(), "nbrpagecartecarbRes", resultat);
		} catch (Exception e) {
			producer.send(record.key().toString(), "nbrpagecartecarbRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "ajoutcartecarbReq")
	public void ajout(ConsumerRecord<?, ?> record) {
		try {
			log.info("valeur : "+record.value().toString());
			Paramcartecarbu param2 =om.readValue(record.value().toString(), Paramcartecarbu.class);
			CarteCarbMod param = new CarteCarbMod();
			param.setIdcartecarburant(Integer.valueOf(String.valueOf(service.seqDemande())));
			param.setIdcomptrol(param2.getIdcomptrol());
			param.setDrpossession(param2.getDrpossession());
			param.setCodecarte(param2.getCodecarte());
			param.setUtilisation(param2.getUtilisation());
			param.setCodedr(param2.getCodedr());
			param.setIddisponibilite(2);
			param.setIdtypecarte(param2.getIdtypecarte());
			param.setNumerocarte(param2.getNumerocarte());
			param.setMontant(param2.getMontant());
			service.save(param);
			HistoriqueMod param1 =new HistoriqueMod();
			param1.setTache("ajout nouvelle carte : "+ param2.getNumerocarte());
			param1.setDatehisto(DateUtil.sqlDateNow());
			param1.setMatriculeuser(param2.getMatriculeuser());
			param1.setUserinsert(param2.getUserinsert());
			param1.setIdhistorique(Integer.valueOf(String.valueOf(servicehisto.seqDemande())));
			servicehisto.save(param1);
			producer.send(record.key().toString(), "ajoutcartecarbRes", "{\"success\":true}");
		} catch (Exception e) {
			e.printStackTrace();
			producer.send(record.key().toString(), "ajoutcartecarbRes", e.getMessage());
		}
	}
	

	@KafkaListener(topics = "supprcartecarbReq")
	public void suppr(ConsumerRecord<?, ?> record) {
		try {
			service.delete(om.readValue(record.value().toString(), CarteCarbMod.class));
			producer.send(record.key().toString(), "supprcartecarbRes", "{\"success\":true}");
		} catch (Exception e) {
			producer.send(record.key().toString(), "supprcartecarbRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "CarteDisponibiliteReq")
	public void CarteDispo(ConsumerRecord<?, ?> record) {
		try {
			CarteCarbMod param =om.readValue(record.value().toString(),CarteCarbMod.class);
			List<CarteCarbMod> list = service.getCarteDispo(param.getIddisponibilite());
			resultat=om.writeValueAsString(list);
			producer.send(record.key().toString(), "CarteDisponibiliteRes", resultat);
		} catch (Exception e) {
			e.printStackTrace();
			producer.send(record.key().toString(), "CarteDisponibiliteRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "RechbyDispoandTypeReq")
	public void RechDispoandtype(ConsumerRecord<?, ?> record) {
		try {
			log.info("valeur : "+record.value().toString());
			CarteCarbMod param =om.readValue(record.value().toString(),CarteCarbMod.class);
			List<CarteCarbMod> list = service.getbydispoandtype(param.getIddisponibilite(), param.getIdtypecarte(), param.getDrpossession(), param.getNumerocarte());
			resultat=om.writeValueAsString(list);
			producer.send(record.key().toString(), "RechbyDispoandTypeRes", resultat);
		} catch (Exception e) {
			e.printStackTrace();
			producer.send(record.key().toString(), "RechbyDispoandTypeRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "auotcomplitecartecarbuReq")
	public void auotcomplitecartecarbu(ConsumerRecord<?, ?> record) {
		try {
			CarteCarbMod param =om.readValue(record.value().toString(),CarteCarbMod.class);
			List<CarteCarbMod> list = service.getautocompliteCarteDispo(param.getNumerocarte());
			resultat=om.writeValueAsString(list);
			producer.send(record.key().toString(), "auotcomplitecartecarbuRes", resultat);
		} catch (Exception e) {
			e.printStackTrace();
			producer.send(record.key().toString(), "auotcomplitecartecarbuRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "updatecartecarbuReq")
	public void updatecartecarbu(ConsumerRecord<?, ?> record) {
		try {
			Paramcartecarbu param =om.readValue(record.value().toString(),Paramcartecarbu.class);
			service.updatecartecarbu(param.getIdcartecarburant(),param.getIdcomptrol(), param.getDrpossession(),param.getCodecarte(),param.getUtilisation(),param.getCodedr(), param.getIddisponibilite(),param.getIdtypecarte(),param.getNumerocarte(),param.getMontant());
			HistoriqueMod param1 =new HistoriqueMod();
			param1.setDatehisto(DateUtil.sqlDateNow());
			param1.setTache("Update des données du carte " + param.getNumerocarte());
			param1.setMatriculeuser(param.getMatriculeuser());
			param1.setUserinsert(param.getUserinsert());
			param1.setIdhistorique(Integer.valueOf(String.valueOf(servicehisto.seqDemande())));
			log.info("tonga ato");
			servicehisto.save(param1);
			producer.send(record.key().toString(), "updatecartecarbuRes", record.value().toString());
		} catch (Exception e) {
			e.printStackTrace();
			producer.send(record.key().toString(), "updatecartecarbuRes", e.getMessage());
		}
	}
}
