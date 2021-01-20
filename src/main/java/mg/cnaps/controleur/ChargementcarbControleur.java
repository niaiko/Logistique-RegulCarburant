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

import mg.cnaps.models.ChargementCarbMod;
import mg.cnaps.models.HistoriqueMod;
import mg.cnaps.models.Parammouvcarte;
import mg.cnaps.services.ChargementCarbService;
import mg.cnaps.services.HistoriqueService;
import mg.cnaps.utils.DateUtil;

@RestController
@CrossOrigin
public class ChargementcarbControleur {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(ChargementcarbControleur.class);

	@Autowired
	ChargementCarbService service;

	@Autowired
	HistoriqueService servicehisto;

	@GetMapping(value = "/listechargementcarb/{page}")
	public ResponseEntity<Object> listechargementcarbReq(@PathVariable int page) {
		try {
			List<ChargementCarbMod> liste = service.getAll(page);
			log.info("listechargement: " + om.writeValueAsString(liste));
			return new ResponseEntity<Object>(liste, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/nbrpagechargementcarb")
	public ResponseEntity<Object> nbrpagechargementcarbReq() {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/ajoutchargementcarb")
	public ResponseEntity<Object> ajoutchargementcarbReq(@RequestBody Parammouvcarte param) {
		try {
			log.info("valeur : " + param);
			log.info("test :" + param.getSortant());
			ChargementCarbMod io = new ChargementCarbMod();
			io.setDatecharge(DateUtil.sqlDateNow());
			io.setMontant(param.getEntree());
			io.setNumcarte(param.getNumcarte());
			service.save(io);
			log.info("io :" + io);
			HistoriqueMod param1 = new HistoriqueMod();
			param1.setDatehisto(DateUtil.sqlDateNow());
			param1.setTache("Chargement du carte : " + param.getNumcarte());
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

	@DeleteMapping(value = "/supprchargementcarb/{id}")
	public ResponseEntity<Object> supprchargementcarbReq(@PathVariable int id) {
		try {
			ChargementCarbMod charge = new ChargementCarbMod();
			charge = service.getById(id);
			if (charge.getIdchargementcarte() != 0) {
				service.delete(charge);
			}
			return new ResponseEntity<Object>("Succes", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

}
