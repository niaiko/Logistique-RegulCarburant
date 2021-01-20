package mg.cnaps.controleur;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import mg.cnaps.models.DemandeCarbMod;
import mg.cnaps.models.HistoriqueMod;
import mg.cnaps.models.ParamCarbMod;
import mg.cnaps.models.ParamDemandeCarbu;
import mg.cnaps.models.ReferenceCarb;
import mg.cnaps.repository.DemandeCarbRepository;
import mg.cnaps.repository.HistoriqueRepository;
import mg.cnaps.services.DemandeCarbService;
import mg.cnaps.services.HistoriqueService;
import mg.cnaps.utils.DateUtil;
import mg.cnaps.utils.ReferenceUtil;

@RestController
@CrossOrigin
public class DemandeCarbControleur {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(DemandeCarbControleur.class);

	@Autowired
	DemandeCarbService service;

	@Autowired
	HistoriqueService servicehisto;

	@Autowired
	DemandeCarbRepository dmdRepository;

	@Autowired
	HistoriqueRepository hRepository;

	@GetMapping(value = "/listedemandecarb/{page}")
	public ResponseEntity<Object> listedemandecarbReq(@PathVariable int page) {
		try {
			log.info("pageliste: " + page);
			List<DemandeCarbMod> liste = service.getAll(page);
			log.info("listedemande: " + om.writeValueAsString(liste));
			return new ResponseEntity<Object>(liste, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/nbrpagedemandecarb")
	public ResponseEntity<Object> nbrpagedemandecarbReq() {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/ajoutdemandecarb")
	public ResponseEntity<Object> ajoutdemandecarb(@RequestBody ParamDemandeCarbu param) {
		try {
			log.info("insert: " + param);
			DemandeCarbMod io = new DemandeCarbMod();
			io.setDatedemande(param.getDatedemande());
			io.setDistance(param.getDistance());
			io.setDurermission(param.getDurermission());
			// io.setIdcarbu(param.getIdcarbu());
			io.setValidation(param.getValidation());
			io.setIdprix(param.getIdprix());
			io.setMatrdmd(param.getMatrdmd());
			io.setMotif(param.getMotif());
			io.setReferencecarbu(param.getReferencecarbu());
			io.setDatedebut(param.getDatedebut());
			io.setDatefin(param.getDatefin());
			io.setLieudepart(param.getLieudepart());
			io.setLieuarrive(param.getLieuarrive());
			service.save(io);

			HistoriqueMod param1 = new HistoriqueMod();
			param1.setDatehisto(DateUtil.sqlDateNow());
			param1.setTache("Ajout d'une demande de carburant d'une motif de " + param.getMotif());
			param1.setMatriculeuser(param.getMatrdmd());
			param1.setUserinsert(param.getUserinsert());
			param1.setIdhistorique(Integer.valueOf(String.valueOf(servicehisto.seqDemande())));
			servicehisto.save(param1);
			return new ResponseEntity<Object>("Données coul envoyé!!!!!!!!!", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/supprdemandecarb/{id}")
	public ResponseEntity<Object> supprdemandecarbReq(@PathVariable int id) {
		try {
			DemandeCarbMod carbMod = new DemandeCarbMod();
			carbMod = service.getById(id);
			if (carbMod.getIdcarbu() != 0) {
				service.delete(carbMod);
			}
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/referenceCarbuDmd")
	public ResponseEntity<Object> referenceCarbuDmdReq(@RequestBody ReferenceCarb param) {
		try {
			log.info("Reference: " + param);
			resultat = ReferenceUtil.getReferenceDemande(param.getPrestation(), service.seqDemande(), param.getDr());
			log.info("ref resultat: " + resultat);
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/recherchedatedemande")
	public ResponseEntity<Object> recherchedatedemandeReq(@RequestBody DemandeCarbMod param) {
		try {
			log.info("rech:" + param);
			log.info("date : " + param.getDatedemande());
			if (param.getDatedemande() == null) {
				List<DemandeCarbMod> access = service.getBysansDateDemande(param.getValidation(),
						param.getReferencecarbu());
				resultat = om.writeValueAsString(access);
				log.info("res: " + resultat);
				return new ResponseEntity<Object>(resultat, HttpStatus.OK);
			} else {
				List<DemandeCarbMod> access = service.getByavecDateDemande(param.getDatedemande(),
						param.getValidation(), param.getReferencecarbu());
				resultat = om.writeValueAsString(access);
				log.info("res: " + resultat);
				return new ResponseEntity<Object>(resultat, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/updatevalidationdmdecarbu")
	public ResponseEntity<Object> updatevalidationdmdecarbuReq(@RequestBody ParamDemandeCarbu dvm) {
		try {
			log.info("insert: " + dvm);
			log.info("insert222: " + om.writeValueAsString(dvm));
			service.updatevalidationdmdcarbu(dvm.getIdcarbu());
			HistoriqueMod param1 = new HistoriqueMod();
			param1.setDatehisto(DateUtil.sqlDateNow());
			param1.setTache("Validation du demande de carburant de reference : " + dvm.getReferencecarbu());
			param1.setMatriculeuser(dvm.getMatriculeuser());
			param1.setUserinsert(dvm.getUserinsert());
			param1.setIdhistorique(Integer.valueOf(String.valueOf(servicehisto.seqDemande())));
			servicehisto.save(param1);
			return new ResponseEntity<Object>(dvm, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/listevalidationdmdecarbu/{page}")
	public ResponseEntity<Object> listevalidationdmdecarbuReq(@PathVariable int page) {
		try {
			log.info("id : " + page);
			List<DemandeCarbMod> io = service.listevalidationdmdcarbu(new PageRequest(page, 10));
			return new ResponseEntity<Object>(io, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/listenonvalidationdmdecarbu")
	public ResponseEntity<Object> liste(@RequestBody ParamCarbMod param) {
		try {
			Pageable pageable = new PageRequest(param.getPage() - 1, 10);
			Page<DemandeCarbMod> page = dmdRepository.searchMulti(param.getRefCarbu(), param.getMatrDmd(),
					param.getDateDmd(), pageable);
			log.info("Pageeeee " + page);
			param.setDemande(page.getContent());
			param.setMatrDmd(param.getMatrDmd());
			param.setNbPages(page.getTotalPages());
			param.setRefCarbu(param.getRefCarbu());
			return new ResponseEntity<>(param, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

}
