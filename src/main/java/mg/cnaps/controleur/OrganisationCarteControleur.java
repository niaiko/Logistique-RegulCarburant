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

import mg.cnaps.models.OrganisationCarteMod;
import mg.cnaps.services.OrganisationCarteService;

@RestController
@CrossOrigin
public class OrganisationCarteControleur {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(OrganisationCarteControleur.class);

	@Autowired
	OrganisationCarteService service;

	@GetMapping(value = "/listeOrgCarteCarbu/{page}")
	public ResponseEntity<Object> listeOrgCarteCarbuReq(@PathVariable int page) {
		try {
			log.info("pageliste: " + page);
			List<OrganisationCarteMod> liste = service.getAll(page);
			log.info("listcou: " + om.writeValueAsString(liste));
			return new ResponseEntity<Object>(liste, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/nbrpageOrgCarteCarbu")
	public ResponseEntity<Object> nbrpageOrgCarteCarbuReq() {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/ajoutOrgCarteCarbu")
	public ResponseEntity<Object> ajoutOrgCarteCarbufr(@RequestBody OrganisationCarteMod carteMod) {
		try {
			service.save(carteMod);
			return new ResponseEntity<Object>(carteMod, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/supprOrgCarteCarbu/{id}")
	public ResponseEntity<Object> supprOrgCarteCarbuReq(@PathVariable int id) {
		try {
			OrganisationCarteMod carteMod = new OrganisationCarteMod();
			carteMod = service.getById(id);
			if (carteMod.getIdOrg() != 0) {
				service.delete(carteMod);
			}
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}
}
