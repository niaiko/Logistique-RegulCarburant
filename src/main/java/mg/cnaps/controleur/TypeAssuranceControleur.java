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

import mg.cnaps.models.TypeAssuranceMod;
import mg.cnaps.services.TypeAssuranceService;

@RestController
@CrossOrigin
public class TypeAssuranceControleur {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(TypeAssuranceControleur.class);

	@Autowired
	TypeAssuranceService service;

	@GetMapping(value = "/listeTypeAssurance/{page}")
	public ResponseEntity<Object> listeTypeAssuranceReq(@PathVariable int page) {
		try {
			log.info("pageliste: " + page);
			List<TypeAssuranceMod> liste = service.getAll(page);
			log.info("listcou: " + om.writeValueAsString(liste));
			return new ResponseEntity<Object>(liste, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/nbrpageTypeAssurance")
	public ResponseEntity<Object> nbrpageTypeAssuranceReq() {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "ajoutTypeAssurance")
	public ResponseEntity<Object> ajoutTypeAssuranceReq(@RequestBody TypeAssuranceMod assuranceMod) {
		try {
			service.save(assuranceMod);
			return new ResponseEntity<Object>(assuranceMod, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/supprTypeAssurance/{idType}")
	public ResponseEntity<Object> supprTypeAssuranceReq(@PathVariable int idType) {
		try {
			TypeAssuranceMod record = new TypeAssuranceMod();
			record = service.getById(idType);
			if (record.getIdType2() != 0) {
				service.delete(record);
			}
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}
}
