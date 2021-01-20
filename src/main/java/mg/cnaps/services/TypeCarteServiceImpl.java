package mg.cnaps.services;
import java.io.Serializable;
//import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import mg.cnaps.models.DemandeVehiculeMod;
import mg.cnaps.models.TypeCarteMod;
import mg.cnaps.repository.TypeCarteRepository;

@Service
public class TypeCarteServiceImpl implements TypeCarteService {

	public static int max=50;
	
	@Autowired
	TypeCarteRepository repository;

	@Override
	public TypeCarteMod save(TypeCarteMod veh) {
		return repository.save(veh);
	}
	
	@Override
		public TypeCarteMod getById(Serializable id) {
			// TODO Auto-generated method stub
			return null;
}

		@Override
		public List<TypeCarteMod> getAll(int page) {
			return (repository.findAll(new PageRequest(page-1,max))).getContent();
		}
		
		@Override
		public void delete(TypeCarteMod entity) {
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
		public List<TypeCarteMod> getAll() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Page<DemandeVehiculeMod> getAll(PageRequest gotoPage) {
			// TODO Auto-generated method stub
			return null;
		}
		
}