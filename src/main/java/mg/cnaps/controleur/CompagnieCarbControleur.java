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

import mg.cnaps.models.CompagnieCarbMod;
import mg.cnaps.models.HistoriqueMod;
import mg.cnaps.models.ParamCompagniepetrol;
import mg.cnaps.services.CompagnieCarbService;
import mg.cnaps.services.HistoriqueService;
import mg.cnaps.utils.DateUtil;

@RestController
@CrossOrigin
public class CompagnieCarbControleur {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(CompagnieCarbControleur.class);

	@Autowired
	CompagnieCarbService service;

	@Autowired
	HistoriqueService servicehisto;

	@GetMapping(value = "/listecompagnie/{id}")
	public ResponseEntity<Object> listecompagnieReq(@PathVariable int id) {
		try {
			log.info("pois: " + id);
			List<CompagnieCarbMod> liste = service.getAll(id);
			log.info("nbrligne: " + om.writeValueAsString(liste));
			return new ResponseEntity<Object>(liste, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/nbrpagecompagnie")
	public ResponseEntity<Object> nbrpagecompagnieReq() {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/ajoutcompagniecarb")
	public ResponseEntity<Object> ajoutcompagniecarb(@RequestBody CompagnieCarbMod carbMod,
			@RequestBody ParamCompagniepetrol param) {
		try {
			service.save(carbMod);
			HistoriqueMod param1 = new HistoriqueMod();
			param1.setDatehisto(DateUtil.sqlDateNow());
			param1.setTache("Ajout du nouvelle compagnie pétrolier : " + param.getIdcartecarb());
			param1.setMatriculeuser(param.getMatriculeuser());
			param1.setUserinsert(param.getUserinsert());
			param1.setIdhistorique(Integer.valueOf(String.valueOf(servicehisto.seqDemande())));
			servicehisto.save(param1);
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/supprcompagniecarb/{id}")
	public ResponseEntity<Object> supprcompagniecarbReq(@PathVariable int id) {
		try {
			CompagnieCarbMod carbMod = new CompagnieCarbMod();
			carbMod = service.getById(id);
			if (carbMod.getIdcomppetrol() != 0) {
				service.delete(carbMod);
			}
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}
}
