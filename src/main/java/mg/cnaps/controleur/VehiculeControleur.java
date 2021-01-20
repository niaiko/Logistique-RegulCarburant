package mg.cnaps.controleur;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
import mg.cnaps.models.ParamOnglobeVehicule;
import mg.cnaps.models.VehiculeMod;
import mg.cnaps.models.article.ParamDonneeVehicule;
import mg.cnaps.repository.VehiculeRepository;
import mg.cnaps.services.OnglobeVehiculeService;
import mg.cnaps.services.VehiculeService;

@RestController
@CrossOrigin
public class VehiculeControleur {
	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(VehiculeControleur.class);

	@Autowired
	VehiculeService service;

	@Autowired
	OnglobeVehiculeService serviceOngl;

	@Autowired
	VehiculeRepository vRepository;

	@PostMapping(value = "/listeVehicule")
	public ResponseEntity<Object> listeVehiculeReq() {
		try {
			List<VehiculeMod> liste = service.findioAll();
			log.info("listcou: " + om.writeValueAsString(liste));
			return new ResponseEntity<Object>(liste, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/nbrpageVehicule")
	public ResponseEntity<Object> nbrpageVehiculeReq() {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/ajoutVehicule")
	public ResponseEntity<Object> ajoutVehiculeReq(@RequestBody VehiculeMod record) {
		try {
			service.save(record);
			return new ResponseEntity<Object>(record, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/supprVehicule")
	public ResponseEntity<Object> supprVehiculeReq(@PathVariable int id) {
		try {
			VehiculeMod record = new VehiculeMod();
			record = service.getById(id);
			if (record.getIdVehicule() != 0) {
				service.delete(record);
			}
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(path = "/prendVehiculeInt")
	public ResponseEntity<Object> prendVehicule(@RequestBody ParamDonneeVehicule[] param) {
		try {
			ParamDonneeVehicule param2 = new ParamDonneeVehicule();
			param2.setArticle(param[0].getArticle());
			param2.setCodeArticle(param[0].getCodeArticle());
			return new ResponseEntity<>(param2, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/compareinfovehic")
	public ResponseEntity<Object> compareinfovehicReq(@RequestBody ParamDonneeVehicule[] param) {
		try {
			log.info("rech: " + param);
			int count = param.length;
			log.info("isa : " + count);
			for (int i = 0; i < count; i++) {
				int count2 = param[i].getCodeArticle().length;
				for (int j = 0; j < count2; j++) {
					log.info("isa2 : " + count2);
					int nbr = service.compairevehicule(param[i].getCodeArticle()[j].getIdCodeArt(),
							param[i].getArticle().getLibelle());
					log.info("nbr : " + nbr);
					if (nbr == 0) {
						VehiculeMod io = new VehiculeMod();
						io.setMarque(param[i].getArticle().getLibelle());
						io.setRefmt(param[i].getCodeArticle()[j].getMarque());
						io.setRefvehicule(param[i].getCodeArticle()[j].getIdCodeArt());
						io.setIdVehicule(Integer.valueOf(String.valueOf(service.seqDemande())));
						service.save(io);
					}
				}
			}
			return new ResponseEntity<Object>(param, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/Ajoutinfovehi")
	public ResponseEntity<Object> AjoutinfovehiReq(@RequestBody ParamOnglobeVehicule param) {
		try {
			log.info("rech: " + param);
			service.updatedonneevehic(param.getIdVehicule(), param.getConsommation(), param.getCodedr(),
					param.getMatrvehic());
			int nbr = serviceOngl.existevehic(param.getIdVehicule());
			log.info("count: " + nbr);
			if (nbr == 0) {
				OnglobeVehiculeMod io = new OnglobeVehiculeMod();
				io.setIdOnglobe(Integer.parseInt(String.valueOf(service.seqDemande())));
				io.setIdType2(param.getIdType2());
				io.setLibelle(param.getLibelle());
				io.setDateDebut(param.getDateDebut());
				io.setDateFin(param.getDateFin());
				io.setDatevisite(param.getDatevisite());
				io.setDatecnavto(param.getDatecnavto());
				io.setIdVehicule(param.getIdVehicule());
				serviceOngl.save(io);
				return new ResponseEntity<Object>(param, HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>(param, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(path = "/listevehiculedisposanspage")
	public ResponseEntity<Object> getListeDispoSansPage() {
		try {
			List<VehiculeMod> liste = vRepository.getListeVehiculeDispoSansPage();
			return new ResponseEntity<>(liste, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/listevehiculedispo/{page}")
	public ResponseEntity<Object> listevehiculedispoReq(@RequestBody VehiculeMod v, @PathVariable int page) {
		try {
			System.out.println("mety => " + new ObjectMapper().writeValueAsString(v));

			if (v.getMarque() != null || v.getMatrvehic() != null || v.getRefvehicule() != null
					|| v.getTypevehicule() != null) {
				System.out.println("metyQ2 => " + new ObjectMapper().writeValueAsString(v));
				List<VehiculeMod> liste = vRepository.getlistevehiculedispo2(v, new PageRequest(page - 1, 10));
				return new ResponseEntity<>(liste, HttpStatus.OK);
			} else {
				List<VehiculeMod> liste2 = service.getlistevehiculedispo(new PageRequest(page - 1, 10));
				return new ResponseEntity<>(liste2, HttpStatus.OK);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/listevehiculenondetail/{page}")
	public ResponseEntity<Object> listevehiculenondetailReq(@RequestBody VehiculeMod vm, @PathVariable int page) {
		try {

			if (vm.getMarque() != null || vm.getMatrvehic() != null || vm.getRefvehicule() != null
					|| vm.getTypevehicule() != null) {
				List<VehiculeMod> liste = vRepository.getvehiculenondetail2(vm, new PageRequest(page - 1, 10));
				return new ResponseEntity<>(liste, HttpStatus.OK);

			} else {
				List<VehiculeMod> liste = service.getvehiculenondetail(new PageRequest(page - 1, 10));
				return new ResponseEntity<>(liste, HttpStatus.OK);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
