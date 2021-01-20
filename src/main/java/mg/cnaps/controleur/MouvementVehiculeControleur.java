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
import mg.cnaps.models.MouvementVehiculeMod;
import mg.cnaps.models.ParamCalculprixandlitreAntenne;
import mg.cnaps.models.ParamCalculprixandlitreSiege;
import mg.cnaps.models.ParamChauffeurMvmVehicule;
import mg.cnaps.models.ParamMouvementVehicule;
import mg.cnaps.models.ParamMouvvehic;
import mg.cnaps.models.ParamMvtVehicule;
import mg.cnaps.models.PrixCarbuMod;
import mg.cnaps.repository.MouvementVehiculeRepository;
import mg.cnaps.services.DemandeCarbService;
import mg.cnaps.services.MouvementVehiculeService;
import mg.cnaps.services.PrixCarbuService;
import mg.cnaps.utils.DateUtil;
import mg.cnaps.utils.ReferenceUtil;

@RestController
@CrossOrigin
public class MouvementVehiculeControleur {
	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(MouvementVehiculeControleur.class);

	@Autowired
	MouvementVehiculeService service;

	@Autowired
	PrixCarbuService serviceprixcarb;
	@Autowired
	DemandeCarbService servicecarb;

	@Autowired
	MouvementVehiculeRepository mvtRepository;

	@PostMapping(value = "/listeMvmVehicule")
	public ResponseEntity<Object> listeMvmVehiculeReq(@RequestBody ParamMvtVehicule m) {
		try {
			Pageable pageable = new PageRequest(m.getPage() - 1, m.getSize());
			Page<MouvementVehiculeMod> liste = mvtRepository.rechercheParServiceAndRefDem(m.getRefDem(), m.getService(),
					pageable);
			log.info("listpage test: " + om.writeValueAsString(liste));
			m.setMouvementVehiculeMod(liste.getContent());
			m.setNbPage(liste.getTotalPages());
			return new ResponseEntity<Object>(m, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "nbrpageMvmVehicule")
	public ResponseEntity<Object> nbrpageMvmVehiculeReq() {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/ajoutMvmVehicule")
	public ResponseEntity<Object> ajoutMvmVehiculeReq(@RequestBody ParamMouvvehic param) {
		try {
			log.info("reo e : " + param);
			MouvementVehiculeMod io = new MouvementVehiculeMod();
			io.setIdcartecarb(param.getIdcartecarb());
			io.setDatedepart(param.getDatedepart());
			io.setDatearriver(param.getDatearriver());
			io.setKmdepart(param.getKmdepart());
			io.setKmarriver(param.getKmarriver());
			io.setIdchauffeur(param.getIdchauffeur());
			io.setNbrpersonne(param.getNbrpersonne());
			io.setIdmission(param.getIdmission());
			io.setQuantitecarb(param.getQuantitecarb());
			io.setPrixcarb(param.getPrixcarb());
			io.setRefdmdvh(param.getRefdmdvh());
			io.setIdvehicule(param.getIdvehicule());
			io.setIddemande(param.getIddemande());
			io.setConsomveh(param.getConsomveh());
			io.setService(param.getCodeservice());
			io.setIdmouv(Integer.valueOf(String.valueOf(service.seqDemande())));
			service.save(io);
			String defdmd = param.getDefdmd();
			if (defdmd.compareToIgnoreCase("mission") == 0) {
				String codedr = param.getCodedr();
				log.info("codedr : " + codedr);
				if (codedr.compareToIgnoreCase("42") == 0) {
					PrixCarbuMod val = serviceprixcarb.findprixcarburant(param.getTypecarbu());
					String prestation = "CR";
					resultat = ReferenceUtil.getReferenceDemande(prestation, service.seqDemande(), param.getCodedr());
					DemandeCarbMod ins = new DemandeCarbMod();
					ins.setReferencecarbu(resultat);
					ins.setIdprix(param.getPrixcarb());
					ins.setDatedemande(DateUtil.sqlDateNow());
					ins.setDistance(param.getDistance());
					ins.setDurermission(param.getSejour());
					ins.setValidation(param.getValidation());
					servicecarb.save(ins);
					Double prix = param.getDistance() * 2 * (param.getConsomveh() / 100) * val.getPrix()
							* (Double.valueOf(String.valueOf(param.getSejour())));
					Double quantite = param.getDistance() * 2 * (param.getConsomveh() / 100)
							* (Double.valueOf(String.valueOf(param.getSejour())));
					service.updatemouvveh(prix, quantite, io.getIdmouv());
					return new ResponseEntity<Object>(param, HttpStatus.OK);
				} else {
					PrixCarbuMod val = serviceprixcarb.findprixcarburant(param.getTypecarbu());
					String prestation = "CR";
					resultat = ReferenceUtil.getReferenceDemande(prestation, service.seqDemande(), param.getCodedr());
					DemandeCarbMod ins = new DemandeCarbMod();
					ins.setReferencecarbu(resultat);
					ins.setIdprix(param.getPrixcarb());
					ins.setDatedemande(DateUtil.sqlDateNow());
					ins.setDistance(param.getDistance());
					ins.setDurermission(param.getSejour());
					ins.setValidation(param.getValidation());
					servicecarb.save(ins);
					Double prix = param.getDistance() * 2 * (param.getConsomveh() / 100) * val.getPrix();
					Double quantite = param.getDistance() * 2 * (param.getConsomveh() / 100);
					service.updatemouvveh(prix, quantite, io.getIdmouv());
					return new ResponseEntity<Object>(param, HttpStatus.OK);
				}
			}
			if (defdmd.compareToIgnoreCase("location") == 0) {
				PrixCarbuMod val = serviceprixcarb.findprixcarburant(param.getTypecarbu());
				String prestation = "CR";
				resultat = ReferenceUtil.getReferenceDemande(prestation, service.seqDemande(), param.getCodedr());
				DemandeCarbMod ins = new DemandeCarbMod();
				ins.setReferencecarbu(resultat);
				ins.setIdprix(param.getPrixcarb());
				ins.setDatedemande(DateUtil.sqlDateNow());
				ins.setDistance(param.getDistance());
				ins.setDurermission(param.getSejour());
				ins.setValidation(param.getValidation());
				servicecarb.save(ins);
				Double prix = param.getDistance() * 2 * (param.getConsomveh() / 100) * val.getPrix()
						* (Double.valueOf(String.valueOf(param.getSejour())));
				Double quantite = param.getDistance() * 2 * (param.getConsomveh() / 100)
						* (Double.valueOf(String.valueOf(param.getSejour())));
				service.updatemouvveh(prix, quantite, io.getIdmouv());
				return new ResponseEntity<Object>(param, HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>(resultat, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/rechMvmVehiculeDate")
	public ResponseEntity<Object> rechMvmVehiculeDateReq(@RequestBody ParamMouvementVehicule param) {
		try {
			log.info("rechMvmVehicule:" + param);
			List<MouvementVehiculeMod> access = service.getByDatedepart(param.getDatedepart());
			resultat = om.writeValueAsString(access);
			log.info("res: " + resultat);
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/rechChauffeurMvmVehicule")
	public ResponseEntity<Object> rechChauffeurMvmVehiculeReq(@RequestBody ParamChauffeurMvmVehicule param) {
		try {
			log.info("rechChauffeur:" + param);
			List<MouvementVehiculeMod> access = service.getByIdchauffeur(param.getIdchauffeur());
			resultat = om.writeValueAsString(access);
			log.info("res: " + resultat);
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/supprmouvementVehicule/{id}")
	public ResponseEntity<Object> supprmouvementVehiculeReq(@PathVariable int id) {
		try {
			MouvementVehiculeMod mod = new MouvementVehiculeMod();
			mod = service.getById(id);
			if (mod.getIdmouv() != 0) {
				service.delete(mod);
			}
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/calculprixandlitreantenne")
	public ResponseEntity<Object> calculprixandlitreantenneReq(@RequestBody ParamCalculprixandlitreAntenne param) {
		try {
			PrixCarbuMod val = serviceprixcarb.findprixcarburant(param.getTypecarburant());
			Double prix = param.getDistance() * 2 * param.getConsommationveh() * val.getPrix();
			Double quantite = param.getDistance() * 2 * param.getConsommationveh();
			service.updatemouvveh(prix, quantite, param.getIdmouv());
			return new ResponseEntity<Object>(param, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/calculprixandlitresiege")
	public ResponseEntity<Object> calculprixandlitresiegeReq(@RequestBody ParamCalculprixandlitreSiege param) {
		try {
			PrixCarbuMod val = serviceprixcarb.findprixcarburant(param.getTypecarburant());
			Double prix = param.getDistance() * 2 * param.getConsommationveh() * val.getPrix()
					* (Double.valueOf(String.valueOf(param.getSejour())));
			Double quantite = param.getDistance() * 2 * param.getConsommationveh()
					* (Double.valueOf(String.valueOf(param.getSejour())));
			service.updatemouvveh(prix, quantite, param.getIdmouv());
			return new ResponseEntity<Object>(param, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/updatedatearriver")
	public ResponseEntity<Object> updatedatearriverReq(@RequestBody MouvementVehiculeMod param) {
		try {
			service.updatearrivermouv(param.getDatearriver(), param.getIdmouv());
			return new ResponseEntity<Object>(param, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/Rechbydepartarrivchaufrefveh")
	public ResponseEntity<Object> RechbydepartarrivchaufrefvehReq(@RequestBody MouvementVehiculeMod param) {
		try {
			List<MouvementVehiculeMod> list = service.rechmultipleMvt(param.getDatedepart(), param.getDatearriver(),
					param.getIdvehicule(), param.getIdchauffeur());
			resultat = om.writeValueAsString(list);
			log.info("res: " + resultat);
			return new ResponseEntity<Object>(param, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}
}
