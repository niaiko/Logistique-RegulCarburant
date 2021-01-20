package mg.cnaps.controleur;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import mg.cnaps.models.HistoriqueMod;
import mg.cnaps.models.MouvCarteMensuelMod;
import mg.cnaps.models.MouvCarteMod;
import mg.cnaps.models.ParamCarteMouv;
import mg.cnaps.services.HistoriqueService;
import mg.cnaps.services.MouvCarteService;
import mg.cnaps.utils.DateUtil;

@RestController
@CrossOrigin
public class MouvCarteControleur {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(MouvCarteControleur.class);

	@Autowired
	MouvCarteService service;

	@Autowired
	HistoriqueService servicehisto;

	@GetMapping(value = "/listeMouvCarte/{page}")
	public ResponseEntity<Object> listeMouvCarteReq(@PathVariable int page) {
		try {
			log.info("pageliste: " + page);
			List<MouvCarteMod> liste = service.getAll(page);
			log.info("listcou: " + om.writeValueAsString(liste));
			return new ResponseEntity<Object>(liste, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/nbrpageMouvCarte")
	public ResponseEntity<Object> nbrpageMouvCarteReq() {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/ajoutMouvCarte")
	public ResponseEntity<Object> ajoutMouvCarteReq(@RequestBody ParamCarteMouv param2) {
		try {
			MouvCarteMod param = new MouvCarteMod();
			param.setDatemouv(DateUtil.sqlDateNow());
			param.setIdmouvcarte(Integer.valueOf(String.valueOf(service.seqDemande())));
			param.setEntree(param2.getEntree());
			param.setMotif(param2.getMotif());
			param.setLibelle(param2.getLibelle());
			param.setNumcarte(param2.getNumcarte());
			param.setRefdmd(param2.getRefdmd());
			param.setSortant(param2.getSortant());
			param.setDatedebut(param2.getDatedebut());
			param.setDatefin(param2.getDatefin());
			param.setDistance(param2.getDistance());
			param.setLieudepart(param2.getLieudepart());
			param.setLieuarrive(param2.getLieuarrive());
			service.save(param);
			
			HistoriqueMod param1 = new HistoriqueMod();
			param1.setTache("Ajout d'une mouvement de carte de référence : " + param2.getRefdmd());
			param1.setDatehisto(DateUtil.sqlDateNow());
			param1.setMatriculeuser(param2.getMatriculeuser());
			param1.setUserinsert(param2.getUserinsert());
			param1.setIdhistorique(Integer.valueOf(String.valueOf(servicehisto.seqDemande())));
			servicehisto.save(param1);
			return new ResponseEntity<Object>(param2, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/supprMouvCarte/{id}")
	public ResponseEntity<Object> supprMouvCarteReq(@PathVariable int id) {
		try {
			MouvCarteMod carteMod = new MouvCarteMod();
			carteMod = service.getById(id);
			if (carteMod.getIdmouvcarte() != 0) {
				service.delete(carteMod);
			}
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/MouvCarteMensuel/{page}")
	public ResponseEntity<Object> MouvCarteMensuelReq(@RequestBody MouvCarteMensuelMod param, @PathVariable int page) {
		try {
			List<MouvCarteMensuelMod> list = service.Mouvcartemensuel(param.getMois(), new PageRequest(page, 10));
			resultat = om.writeValueAsString(list);
			log.info("res: " + resultat);
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/listeCarteRetourner")
	public ResponseEntity<Object> listeCarteRetourner() {
		try {
			List<MouvCarteMod> list = service.listeCarteRetourner();
			resultat = om.writeValueAsString(list);
			log.info("res: " + resultat);
			return new ResponseEntity<>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/rechercheCarteRetourner")
	public ResponseEntity<Object> rechercheCarteRetournerReq(@RequestBody MouvCarteMensuelMod param) {
		try {
			List<MouvCarteMensuelMod> list = service.rechCarteRetourner(param.getDatemouv(), param.getNumcarte());
			resultat = om.writeValueAsString(list);
			log.info("res: " + resultat);
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/ajoutCarteRetourner")
	public ResponseEntity<Object> respajoutCarteRetournerReq(@RequestBody MouvCarteMod param) {
		try {
			service.save(param);
			HistoriqueMod param1 = new HistoriqueMod();
			param1.setTache("Retour du carte qui a le numero carte : " + param.getNumcarte());
			param1.setDatehisto(DateUtil.sqlDateNow());
			servicehisto.save(param1);
			return new ResponseEntity<Object>(param, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}
}
