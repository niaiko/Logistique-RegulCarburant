package mg.cnaps.controleur;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import mg.cnaps.models.SoldeMod;
import mg.cnaps.services.SoldeService;

@RestController
@CrossOrigin
public class SoldeControleur {
	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(SoldeControleur.class);

	@Autowired
	SoldeService service;

	@GetMapping(value = "/listeSolde/{page}")
	public ResponseEntity<Object> listeSoldeReq(@PathVariable int page) {
		try {
			log.info("pageliste: " + page);
			List<SoldeMod> liste = service.getAll(page);
			log.info("listcou: " + om.writeValueAsString(liste));
			return new ResponseEntity<Object>(liste, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/nbrpageSolde")
	public ResponseEntity<Object> respnbrpageSoldeReq() {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/ajoutSolde")
	public ResponseEntity<Object> ajoutSoldeReq(@RequestBody SoldeMod soldeMod) {
		try {
			service.save(soldeMod);
			return new ResponseEntity<Object>(soldeMod, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/supprSolde/{id}")
	public ResponseEntity<Object> supprSoldeReq(@PathVariable int id) {
		try {
			SoldeMod mod = new SoldeMod();
			mod = service.getById(id);
			if (mod.getIdsolde() != 0) {
				service.delete(mod);
			}
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}
}
