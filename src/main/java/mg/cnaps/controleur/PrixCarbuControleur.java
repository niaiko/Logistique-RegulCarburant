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

import mg.cnaps.models.HistoriqueMod;
import mg.cnaps.models.ParamCalculprixandlitreAntenne;
import mg.cnaps.models.PrixCarbuMod;
import mg.cnaps.services.HistoriqueService;
import mg.cnaps.services.PrixCarbuService;
import mg.cnaps.utils.DateUtil;

@RestController
@CrossOrigin
public class PrixCarbuControleur {
	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(PrixCarbuControleur.class);

	@Autowired
	PrixCarbuService service;

	@Autowired
	HistoriqueService servicehisto;

	@GetMapping(value = "/listePrixCarbu/{page}")
	public ResponseEntity<Object> listePrixCarbuReq(@PathVariable int page) {
		try {
			log.info("pageliste: " + page);
			List<PrixCarbuMod> liste = service.getAll(page);
			log.info("listcou: " + om.writeValueAsString(liste));
			return new ResponseEntity<>(liste, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/nbrpagePrixCarbu")
	public ResponseEntity<Object> nbrpagePrixCarbuReq() {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			return new ResponseEntity<>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/ajoutPrixCarbu")
	public ResponseEntity<Object> ajoutPrixCarbuReq(@RequestBody PrixCarbuMod dvm) {
		try {
			service.save(dvm);
			HistoriqueMod param1 = new HistoriqueMod();
			param1.setTache("Mise a jour du prix de carburant de  : " + dvm.getPrix());
			param1.setDatehisto(DateUtil.sqlDateNow());
			servicehisto.save(param1);
			return new ResponseEntity<>(dvm, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/supprPrixCarbu/{id}")
	public ResponseEntity<Object> supprPrixCarbuReq(@PathVariable int id) {
		try {
			PrixCarbuMod carbuMod = new PrixCarbuMod();
			carbuMod = service.getById(id);
			if (carbuMod.getIdPrix() != 0) {
				service.delete(carbuMod);
			}
			return new ResponseEntity<>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/getprixcarburantbycarb")
	public ResponseEntity<Object> getprixcarburantbycarbReq(@RequestBody ParamCalculprixandlitreAntenne dvm) {
		try {
			log.info("requete: " + dvm);
			PrixCarbuMod val = service.findprixcarburant(dvm.getTypecarburant());
			return new ResponseEntity<>(val, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}
	}

}
