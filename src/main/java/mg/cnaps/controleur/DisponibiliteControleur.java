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

import mg.cnaps.models.DisponibiliteMod;
import mg.cnaps.services.DisponibiliteService;

@RestController
@CrossOrigin
public class DisponibiliteControleur {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(DisponibiliteControleur.class);

	@Autowired
	DisponibiliteService service;

	@GetMapping(value = "/listeDisponibilite/{page}")
	public ResponseEntity<Object> listeDisponibiliteReq(@PathVariable int page) {
		try {
			log.info("pageliste: " + page);
			List<DisponibiliteMod> liste = service.getAll(page);
			log.info("listcou: " + om.writeValueAsString(liste));
			return new ResponseEntity<Object>(liste, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/nbrpageDisponibilite")
	public ResponseEntity<Object> nbrpageDisponibiliteReq() {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/ajoutDisponibilite")
	public ResponseEntity<Object> ajoutDisponibiliteReq(@RequestBody DisponibiliteMod disponibiliteMod) {
		try {
			service.save(disponibiliteMod);
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/supprDisponibilite/{id}")
	public ResponseEntity<Object> supprDisponibiliteReq(@PathVariable int id) {
		try {
			DisponibiliteMod mod = new DisponibiliteMod();
			mod = service.getById(id);
			if (mod.getIddispo() != 0) {
				service.delete(mod);
			}
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}
}
