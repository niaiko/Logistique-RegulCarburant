package mg.cnaps.controleur;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import mg.cnaps.models.CarteCarbMod;
import mg.cnaps.models.HistoriqueMod;
import mg.cnaps.models.ParamCompPetrol;
import mg.cnaps.models.Paramcartecarbu;
import mg.cnaps.repository.CarteCarbRepository;
import mg.cnaps.services.CarteCarbService;
import mg.cnaps.services.HistoriqueService;
import mg.cnaps.utils.DateUtil;

@RestController
@CrossOrigin
public class CarteCarbControleur {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(CarteCarbControleur.class);

	@Autowired
	CarteCarbService service;

	@Autowired
	HistoriqueService servicehisto;

	@Autowired
	CarteCarbRepository carteRepository;

	@GetMapping(value = "/listecartecarb/{page}")
	public ResponseEntity<Object> listecartecarbReq(@PathVariable int page) {
		try {
			log.info("pageliste: " + page);
			List<CarteCarbMod> liste = service.fillalllistecarte(new PageRequest(page, 5));
			log.info("listecarte: " + om.writeValueAsString(liste));
			return new ResponseEntity<Object>(liste, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	// Recherche par Compagnie Petroliere
	@PostMapping(path = "/RechbyComppetrol")
	public ResponseEntity<Object> getByCompPetrole(@RequestBody ParamCompPetrol c) {
		try {
			Page<CarteCarbMod> liste = carteRepository.getByCompePetrol(c.getComptrol(),
					new PageRequest(c.getPage() - 1, 10));
			c.setL(liste.getContent());
			c.setNbPage(liste.getTotalPages());
			return new ResponseEntity<>(c, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/listecartecarbdispo")
	public ResponseEntity<Object> listecartecarbdispoReq() {
		try {
			List<CarteCarbMod> liste = service.listecartecarbdispo();
			log.info("listecarte: " + om.writeValueAsString(liste));
			return new ResponseEntity<Object>(liste, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/nbrpagecartecarb")
	public ResponseEntity<Object> nbrpagecartecarbReq() {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/ajoutcartecarb")
	public ResponseEntity<Object> ajoutcartecarbReq(@RequestBody Paramcartecarbu param2) {
		try {
			log.info("valeur : " + param2);
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
			HistoriqueMod param1 = new HistoriqueMod();
			param1.setTache("ajout nouvelle carte : " + param2.getNumerocarte());
			param1.setDatehisto(DateUtil.sqlDateNow());
			param1.setMatriculeuser(param2.getMatriculeuser());
			param1.setUserinsert(param2.getUserinsert());
			param1.setIdhistorique(Integer.valueOf(String.valueOf(servicehisto.seqDemande())));
			servicehisto.save(param1);
			return new ResponseEntity<Object>(param2, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/supprcartecarb")
	public ResponseEntity<Object> supprcartecarbReq(@PathVariable int id) {
		try {
			CarteCarbMod carte = new CarteCarbMod();
			carte = service.getById(id);
			if (carte.getIdcartecarburant() != 0) {
				service.delete(carte);
			}
			return new ResponseEntity<Object>("Succes", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/CarteDisponibilite")
	public ResponseEntity<Object> CarteDisponibiliteReq(@RequestBody CarteCarbMod param) {
		try {
			List<CarteCarbMod> list = service.getCarteDispo(param.getIddisponibilite());
			resultat = om.writeValueAsString(list);
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/RechbyDispoandType")
	public ResponseEntity<Object> RechbyDispoandTypeReq(@RequestBody CarteCarbMod param) {
		try {
			log.info("valeur : " + param);
			List<CarteCarbMod> list = service.getbydispoandtype(param.getIddisponibilite(), param.getIdtypecarte(),
					param.getDrpossession(), param.getNumerocarte());
			resultat = om.writeValueAsString(list);
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/auotcomplitecartecarbu")
	public ResponseEntity<Object> auotcomplitecartecarbuReq(@RequestBody CarteCarbMod param) {
		try {
			List<CarteCarbMod> list = service.getautocompliteCarteDispo(param.getNumerocarte());
			resultat = om.writeValueAsString(list);
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/updatecartecarbu")
	public ResponseEntity<Object> updatecartecarbuReq(@RequestBody Paramcartecarbu param) {
		try {
			service.updatecartecarbu(param.getIdcartecarburant(), param.getIdcomptrol(), param.getDrpossession(),
					param.getCodecarte(), param.getUtilisation(), param.getCodedr(), param.getIddisponibilite(),
					param.getIdtypecarte(), param.getNumerocarte(), param.getMontant());
			HistoriqueMod param1 = new HistoriqueMod();
			param1.setDatehisto(DateUtil.sqlDateNow());
			param1.setTache("Update des données du carte " + param.getNumerocarte());
			param1.setMatriculeuser(param.getMatriculeuser());
			param1.setUserinsert(param.getUserinsert());
			param1.setIdhistorique(Integer.valueOf(String.valueOf(servicehisto.seqDemande())));
			log.info("tonga ato");
			servicehisto.save(param1);
			return new ResponseEntity<Object>("updatecartecarbu", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

}
