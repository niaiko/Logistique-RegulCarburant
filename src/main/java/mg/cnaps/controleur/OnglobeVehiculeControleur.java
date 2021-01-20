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

import mg.cnaps.models.OnglobeVehiculeMod;
import mg.cnaps.services.OnglobeVehiculeService;

@RestController
@CrossOrigin
public class OnglobeVehiculeControleur {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(OnglobeVehiculeControleur.class);

	@Autowired
	OnglobeVehiculeService service;

	@GetMapping(value = "/listeOnglVehCarbu/{page}")
	public ResponseEntity<Object> listeOnglVehCarbuReq(@PathVariable int page) {
		try {
			log.info("pageliste: " + page);
			List<OnglobeVehiculeMod> liste = service.getAll(page);
			log.info("listcou: " + om.writeValueAsString(liste));
			return new ResponseEntity<Object>(liste, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/nbrpageOnglVehCarbu")
	public ResponseEntity<Object> nbrpageOnglVehCarbuReq() {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/ajoutOnglVehCarbu")
	public ResponseEntity<Object> ajoutOnglVehCarbuReq(@RequestBody OnglobeVehiculeMod mod) {
		try {
			service.save(mod);
			return new ResponseEntity<Object>(mod, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/supprOnglVehCarbu/{id}")
	public ResponseEntity<Object> supprOnglVehCarbuReq(@PathVariable int id) {
		try {
			OnglobeVehiculeMod vehiculeMod = new OnglobeVehiculeMod();
			vehiculeMod = service.getById(id);
			if (vehiculeMod.getIdVehicule() != 0) {
				service.delete(vehiculeMod);
			}
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}
}
