package mg.cnaps.controleur;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import mg.cnaps.models.HistoriqueMod;
import mg.cnaps.models.ParamHistoReetra;
import mg.cnaps.models.ParamRechHistorique;
import mg.cnaps.repository.HistoriqueRepository;
import mg.cnaps.services.HistoriqueService;
import mg.cnaps.utils.DateUtil;

@RestController
@CrossOrigin
public class HistoriqueControleur {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(HistoriqueControleur.class);

	@Autowired
	HistoriqueService service;

	@Autowired
	HistoriqueRepository historique;

	@PostMapping(value = "/listeallhistoriquereetr")
	public ResponseEntity<Object> listeallhistoriquereetrReq(@RequestBody ParamHistoReetra p) {
		try {
			log.info("pois: " + p);
			Page<HistoriqueMod> liste = historique.findallhistoriquepage(new PageRequest(p.getPage() - 1, 10));
			p.setL(liste.getContent());
			p.setNbPage(liste.getTotalPages());
			log.info("nbrligne: " + om.writeValueAsString(p));
			return new ResponseEntity<Object>(p, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/listeallhistoriquereetrio")
	public ResponseEntity<Object> listeallhistoriquereetrioReq() {
		try {
			Object[] liste = service.findallty();
			// List<ParamValueHisto> io = new ArrayList<>();
			int size = liste.length;
			log.info("size : " + size);
			log.info("nbrligne: " + om.writeValueAsString(liste));
			return new ResponseEntity<Object>(liste, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/nbrpagehistoriquereetr")
	public ResponseEntity<Object> nbrpagehistoriquereetrReq() {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/ajouthistoriquereetr")
	public ResponseEntity<Object> ajouthistoriquereetrReq(@RequestBody HistoriqueMod param) {
		try {
			log.info("insert: " + param);
			param.setDatehisto(DateUtil.sqlDateNow());
			service.save(param);
			return new ResponseEntity<Object>(param, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/recherhcehistoriquereetr")
	public ResponseEntity<Object> recherhcehistoriquereetrReq(@RequestBody ParamRechHistorique param) {
		try {
			log.info("INFO" + param);
			log.info("io " + param.getMatriculeuser());
			log.info("io " + param.getDatehisto());
			if (param.getDatehisto() == null) {
				Page<HistoriqueMod> liste = historique.findbydatehistoandmatriculeuser2Page(param.getMatriculeuser(),
						new PageRequest(param.getPage() - 1, 10));
				param.setL(liste.getContent());
				param.setNbPage(liste.getTotalPages());
				resultat = om.writeValueAsString(param);
				return new ResponseEntity<Object>(resultat, HttpStatus.OK);
			} else {
				Page<HistoriqueMod> liste = historique.findbydatehistoandmatriculeuserPage(param.getDatehisto(),
						param.getMatriculeuser(), new PageRequest(param.getPage() - 1, 10));
				param.setL(liste.getContent());
				param.setNbPage(liste.getTotalPages());
				resultat = om.writeValueAsString(param);
				return new ResponseEntity<Object>(resultat, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}
}
