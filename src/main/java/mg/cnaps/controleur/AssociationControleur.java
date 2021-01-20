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

import mg.cnaps.models.AssociationMod;
import mg.cnaps.services.AssociationService;

@RestController
@CrossOrigin
public class AssociationControleur {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(AssociationControleur.class);

	@Autowired
	AssociationService service;

	@PostMapping(value = "/listeassociation/{id}")
	public ResponseEntity<Object> listeassociationReq(@PathVariable int id) {
		try {
			log.info("pois: " + id);
			List<AssociationMod> liste = service.getAll(id);
			log.info("nbrligne: " + om.writeValueAsString(liste));
			return new ResponseEntity<Object>(liste, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/nbrpageassociation")
	public ResponseEntity<Object> nbrpageassociationReq() {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/ajoutassociation")
	public ResponseEntity<Object> ajoutassociationReq(@RequestBody AssociationMod mod) {
		try {
			log.info("insert: " + mod);
			service.save(mod);
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/supprassociation/{id}")
	public ResponseEntity<Object> supprassociationReq(@PathVariable int id) {
		try {
			AssociationMod mod = new AssociationMod();
			mod = service.getById(id);
			if (mod.getIdvehicule() != 0) {
				service.delete(mod);
			}
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}
}
