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

import mg.cnaps.models.TypeCarteMod;
import mg.cnaps.services.TypeCarteService;

@RestController
@CrossOrigin
public class TypeCarteControleur {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(TypeCarteControleur.class);

	@Autowired
	TypeCarteService service;

	@GetMapping(value = "/listetypecarte/{page}")
	public ResponseEntity<Object> listetypecarteReq(@PathVariable int page) {
		try {
			log.info("pageliste: " + page);
			List<TypeCarteMod> liste = service.getAll(page);
			log.info("listcou: " + om.writeValueAsString(liste));
			return new ResponseEntity<Object>(liste, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/nbrpagetypecarte")
	public ResponseEntity<Object> nbrpagetypecarteReq() {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/ajouttypecarte")
	public ResponseEntity<Object> ajouttypecarteReq(@RequestBody TypeCarteMod record) {
		try {
			service.save(record);
			return new ResponseEntity<Object>(record, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/supprtypecarte/{idType}")
	public ResponseEntity<Object> supprtypecarteReq(@PathVariable Integer idType) {
		try {
			TypeCarteMod record = new TypeCarteMod();
			record = service.getById(idType);
			if (record != null) {
				service.delete(record);
			}
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}
}
