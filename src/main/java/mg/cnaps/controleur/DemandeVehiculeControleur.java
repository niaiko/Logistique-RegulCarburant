package mg.cnaps.controleur;

import java.util.ArrayList;
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

import mg.cnaps.models.DemandeVehiculeMod;
import mg.cnaps.models.ParamDemandesVehicule;
import mg.cnaps.models.ParamDmdVehicule;
import mg.cnaps.models.ReferenceVehicule;
import mg.cnaps.repository.DemandeVehiculeRepository;
import mg.cnaps.services.DemandeVehiculeService;
import mg.cnaps.services.MouvementVehiculeService;
import mg.cnaps.utils.ReferenceUtil;

@RestController
@CrossOrigin
public class DemandeVehiculeControleur {

	ObjectMapper om = new ObjectMapper();

	String resultat;
	public static final Logger log = LoggerFactory.getLogger(DemandeVehiculeControleur.class);

	@Autowired
	DemandeVehiculeService service;

	@Autowired
	MouvementVehiculeService serviceMouv;

	@Autowired
	DemandeVehiculeRepository dmdVeRepository;

//	@PostMapping(value = "/listeDemandeVehicule/{page}")
//	public ResponseEntity<Object> listeDemandeVehiculeReq(@PathVariable int page, @RequestBody DemandeVehiculeMod dvm) {
//		try {
//			Pageable pageable = new PageRequest(page-1, 10);
//			if (dvm.getCodedr()!=null || dvm.getRefdmdvh()!=null || 
//				dvm.getDatedepart()!=null || dvm.getMatrdmd()!=null) 
//			{
//				Page<DemandeVehiculeMod> liste = dmdVeRepository.findByValidation2(dvm, pageable);
//				log.info("liste:" + om.writeValueAsString(liste));
//				return new ResponseEntity<>(liste, HttpStatus.OK);
//			}else {
//				Page<DemandeVehiculeMod> liste = dmdVeRepository.findByValidation(pageable);
//				return new ResponseEntity<>(liste, HttpStatus.OK);
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
//		}
//	}

	@PostMapping(value = "/listeDemandeVehicule")
	public ResponseEntity<Object> listeDemandeVehiculeReq(@RequestBody ParamDmdVehicule dvm) {
		try {
			log.info("liste:" + om.writeValueAsString(dvm));
			Pageable pageable = new PageRequest(dvm.getPage() - 1, dvm.getSize());
			Page<DemandeVehiculeMod> liste = null;
			if (dvm.getCodeDr() != null || dvm.getRefDem() != null) {
				liste = dmdVeRepository.rechercheMultiAvecFalse(dvm.getCodeDr(), dvm.getRefDem(), pageable);
				log.info("liste:" + om.writeValueAsString(liste));
				dvm.setVehiculeMod(liste.getContent());
				dvm.setNbPage(liste.getTotalPages());
			} else {
				liste = dmdVeRepository.findByValidation(pageable);
				log.info("liste:" + om.writeValueAsString(liste));
				dvm.setVehiculeMod(liste.getContent());
				dvm.setNbPage(liste.getTotalPages());
			}
			return new ResponseEntity<>(dvm, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}

//	@PostMapping(value = "/listeDemandeauthoriser/{page}")
//	public ResponseEntity<Object> listeDemandeauthoriserReq(@PathVariable int page, @RequestBody DemandeVehiculeMod d) {
//		try {
//			log.info("pageliste:" + page);
//			if (d.getCodedr() != null || d.getRefdmdvh() != null || d.getDatedepart() != null
//					|| d.getMatrdmd() != null) {
//				List<DemandeVehiculeMod> liste = dmdVeRepository.listauthoriser2(d, new PageRequest(page - 1, 10));
//				List<DemandeVehiculeMod> io = new ArrayList<>();
//				int size = liste.size();
//				for (int i = 0; i < size; i++) {
//					log.info("iddemande : " + liste.get(i).getIddemande());
//					int val = serviceMouv.countMouv(liste.get(i).getIddemande());
//					log.info("val : " + val);
//					if (val == 0) {
//						io.add(liste.get(i));
//					}
//				}
//				log.info("liste:" + om.writeValueAsString(io));
//				return new ResponseEntity<Object>(io, HttpStatus.OK);
//			} else {
//				List<DemandeVehiculeMod> liste = dmdVeRepository.listauthoriser(new PageRequest(page - 1, 10));
//				List<DemandeVehiculeMod> io = new ArrayList<>();
//				int size = liste.size();
//				for (int i = 0; i < size; i++) {
//					log.info("iddemande : " + liste.get(i).getIddemande());
//					int val = serviceMouv.countMouv(liste.get(i).getIddemande());
//					log.info("val : " + val);
//					if (val == 0) {
//						io.add(liste.get(i));
//					}
//				}
//				log.info("liste:" + om.writeValueAsString(io));
//				return new ResponseEntity<>(io, HttpStatus.OK);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//	}

	@PostMapping(value = "/listeDemandeauthoriser")
	public ResponseEntity<Object> listeDemandeauthoriserReq(@RequestBody ParamDmdVehicule d) {
		try {
			Pageable pageable = new PageRequest(d.getPage() - 1, 10);
			Page<DemandeVehiculeMod> liste = dmdVeRepository.rechercheMultiAvecTrue(d.getCodeDr(), d.getRefDem(),
					pageable);
			log.info("liste Premierrrrrrrrr :" + om.writeValueAsString(liste));
			List<DemandeVehiculeMod> io = new ArrayList<>();
			io = liste.getContent();
			log.info("liste Premierrrrrrrrr :" + om.writeValueAsString(io));
//			int size = io.size();
//			for (int i = 0; i < size; i++) {
//				log.info("iddemande : " + io.get(i).getIddemande());
//				int val = serviceMouv.countMouv(io.get(i).getIddemande());
//				log.info("val : " + val);
//				if (val == 0) {
//					io.add(io.get(i));
//				}
//			}
			d.setVehiculeMod(liste.getContent());
			d.setNbPage(liste.getTotalPages());
			log.info("liste:" + om.writeValueAsString(io));
			return new ResponseEntity<Object>(d, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/nbrpageDemandeVehicule")
	public ResponseEntity<Object> nbrpageDemandeVehiculeReq() {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/referenceDmdVehi")
	public ResponseEntity<Object> referenceDmdVehiReq(@RequestBody ReferenceVehicule param) {
		try {
			log.info("ReferenceDmd: " + param);
			resultat = ReferenceUtil.getReferenceDemande(param.getPrestation(), service.seqDemande(), param.getDr());
			log.info("ref resultat: " + resultat);
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/rechercheDateHeureSortieVeh")
	public ResponseEntity<Object> rechercheDateHeureSortieVehReq(@RequestBody ParamDemandesVehicule param) {
		try {
			log.info("rechdate:" + param);
			List<DemandeVehiculeMod> access = service.getByDateHeureDepart(param.getDatedepart(),
					param.getHeuredepart());
			resultat = om.writeValueAsString(access);
			log.info("res: " + resultat);
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/recherchebydefmouvnonvalider")
	public ResponseEntity<Object> recherchebydefmouvnonvaliderReq(@RequestBody DemandeVehiculeMod param) {
		try {
			log.info("rechdate:" + param);
			String validation = "false";
			if (param.getDatedepart() == null) {
				List<DemandeVehiculeMod> access = service.getByDefMouv(param.getDefdmd(), param.getCodedr(),
						validation);
				resultat = om.writeValueAsString(access);
				log.info("res: " + resultat);
				return new ResponseEntity<Object>(resultat, HttpStatus.OK);
			} else {
				List<DemandeVehiculeMod> access = service.getByDefMouv2(param.getDefdmd(), param.getCodedr(),
						param.getDatedepart(), validation);
				resultat = om.writeValueAsString(access);
				log.info("res: " + resultat);
				return new ResponseEntity<Object>(resultat, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/recherchebydefmouvvalider")
	public ResponseEntity<Object> recherchebydefmouvvaliderReq(@RequestBody DemandeVehiculeMod param) {
		try {
			log.info("rechdate:" + param);
			String validation = "true";
			if (param.getDatedepart() == null) {
				List<DemandeVehiculeMod> access = service.getByDefMouv(param.getDefdmd(), param.getCodedr(),
						validation);
				resultat = om.writeValueAsString(access);
				log.info("res: " + resultat);
				return new ResponseEntity<Object>(resultat, HttpStatus.OK);
			} else {
				List<DemandeVehiculeMod> access = service.getByDefMouv2(param.getDefdmd(), param.getCodedr(),
						param.getDatedepart(), validation);
				resultat = om.writeValueAsString(access);
				log.info("res: " + resultat);
				return new ResponseEntity<Object>(resultat, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/ajoutDemandeVehicule")
	public ResponseEntity<Object> ajoutDemandeVehiculeReq(@RequestBody DemandeVehiculeMod val) {
		try {
			log.info("insert: " + val);
			val.setIddemande(Integer.valueOf(String.valueOf(service.seqDemande())));
			log.info("res " + om.writeValueAsString(val));
			service.save(val);
			return new ResponseEntity<Object>(val, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/supprDemandeVehicule/{id}")
	public ResponseEntity<Object> supprDemandeVehiculeReq(@PathVariable int id) {
		try {
			DemandeVehiculeMod vehiculeMod = new DemandeVehiculeMod();
			vehiculeMod = service.getById(id);
			if (vehiculeMod.getIddemande() != 0) {
				service.delete(vehiculeMod);
			}
			return new ResponseEntity<Object>("Succes", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/updatevalidationdmdveh")
	public ResponseEntity<Object> updatevalidationdmdvehReq(@RequestBody DemandeVehiculeMod dvm) {
		try {
			log.info("insert: " + dvm);
			log.info("insert222: " + om.writeValueAsString(dvm));
			service.updatevalidationdmdveh(dvm.getIddemande());
			return new ResponseEntity<Object>(dvm, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

}
