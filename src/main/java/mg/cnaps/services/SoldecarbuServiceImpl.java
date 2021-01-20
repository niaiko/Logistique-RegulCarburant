package mg.cnaps.services;
import java.io.Serializable;
//import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import mg.cnaps.models.DemandeVehiculeMod;
import mg.cnaps.models.SoldeMod;
import mg.cnaps.repository.SoldeRepository;

@Service
public class SoldecarbuServiceImpl implements SoldeService {

	public static int max=50;
	
	@Autowired
	SoldeRepository repository;

	@Override
	public SoldeMod save(SoldeMod veh) {
		return repository.save(veh);
	}
	
	@Override
		public SoldeMod getById(Serializable id) {
			// TODO Auto-generated method stub
			return null;
}

		@Override
		public List<SoldeMod> getAll(int page) {
			return (repository.findAll(new PageRequest(page-1,max))).getContent();
		}
		
		@Override
		public void delete(SoldeMod entity) {
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
		public List<SoldeMod> getAll() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Page<DemandeVehiculeMod> getAll(PageRequest gotoPage) {
			// TODO Auto-generated method stub
			return null;
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
