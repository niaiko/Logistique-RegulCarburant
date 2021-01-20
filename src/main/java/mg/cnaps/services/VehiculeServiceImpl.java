package mg.cnaps.services;
import java.io.Serializable;
//import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mg.cnaps.models.DemandeVehiculeMod;
import mg.cnaps.models.VehiculeMod;
import mg.cnaps.repository.VehiculeRepository;

@Service
public class VehiculeServiceImpl implements VehiculeService {

	public static int max=1000;
	
	@Autowired
	VehiculeRepository repository;

	@Override
	public VehiculeMod save(VehiculeMod veh) {
		return repository.save(veh);
	}
	
	@Override
		public VehiculeMod getById(Serializable id) {
			// TODO Auto-generated method stub
			return null;
}

		@Override
		public List<VehiculeMod> getAll(int page) {
			return (repository.findAll(new PageRequest(page-1,max))).getContent();
		}
		
		@Override
		public void delete(VehiculeMod entity) {
			repository.delete(entity);
}
		
		@Override
		public int nombrepage() {
			return (int)(repository.count()/max)+1;
		}
		

		@Override
		public long seqDemande() {
			return repository.seqDemande();
		}

		@Override
		public List<VehiculeMod> getAll() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Page<DemandeVehiculeMod> getAll(PageRequest gotoPage) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public int compairevehicule(String codearticle, String libelle) {
			return repository.compairevehicule(codearticle, libelle);
		}
		
		@Override
		public List<VehiculeMod> getvehiculenondetail(Pageable pageable) {
			return repository.getvehiculenondetail(pageable);
		}
		
//		@Override
//		public List<VehiculeMod> getvehiculenondetailpaginer() {
//			return repository.getvehiculenondetailpaginer(pageable);
//		}
		
		@Override
		public void updatedonneevehic(int idvehicule,Double Consommation,String coder,String matrvehic) {
			repository.updatedonneevehic(idvehicule,Consommation,coder,matrvehic);
		}

		@Override
		public List<VehiculeMod> getlistevehiculedispo(Pageable pageable) {
			return repository.getlistevehiculedispo(pageable);
		}

		@Override
		public List<VehiculeMod> findioAll() {
			// TODO Auto-generated method stub
			return repository.findioAll();
		}
}


	/*@Override
	public List<CourrierMod> getByDestinataireDateCou(String destinataire, Date dateInsert) {
		return repository.getByDestinataireDateCou(destinataire, dateInsert);
	}

	@Override
	public List<CourrierMod> getByDestinataireDateCouIdserviceAdresse(String destinataire,Date dateCourrier, int idservice, String adresse) {
		return repository.getByDestinataireDateCouIdserviceAdresse(destinataire,dateCourrier,idservice,adresse);
	}
}*/
