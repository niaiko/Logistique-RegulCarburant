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
public class TypeVehiculeControleur {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(TypeVehiculeControleur.class);

	@Autowired
	TypeCarteService service;

	@GetMapping(value = "/listetypevehic/{page}")
	public ResponseEntity<Object> listetypevehicReq(@PathVariable int page) {
		try {
			List<TypeCarteMod> liste = service.getAll(page);
			log.info("listcou: " + om.writeValueAsString(liste));
			return new ResponseEntity<Object>(liste, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/nbrpagetypevehic")
	public ResponseEntity<Object> nbrpagetypevehicReq() {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/ajouttypevehic")
	public ResponseEntity<Object> resajouttypevehicReq(@RequestBody TypeCarteMod record) {
		try {
			service.save(record);
			return new ResponseEntity<Object>(record, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/supprtypevehic")
	public ResponseEntity<Object> supprtypevehicReq(@PathVariable int type) {
		try {
			TypeCarteMod d = new TypeCarteMod();
			d = service.getById(type);
			if (d.getIdtype() != 0) {
				service.delete(d);
			}
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}
}
