package mg.cnaps.services;
import java.io.Serializable;
import java.util.Date;
//import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mg.cnaps.models.DemandeVehiculeMod;
import mg.cnaps.models.MouvCarteMensuelMod;
import mg.cnaps.models.MouvCarteMod;
import mg.cnaps.repository.MouvCarteRepository;

@Service
public class MouvCarteServiceImpl implements MouvCarteService {

	public static int max=50;
	
	@Autowired
	MouvCarteRepository repository;

	@Override
	public MouvCarteMod save(MouvCarteMod veh) {
		return repository.save(veh);
	}
	
	@Override
		public MouvCarteMod getById(Serializable id) {
			// TODO Auto-generated method stub
			return null;
}

		@Override
		public List<MouvCarteMod> getAll(int page) {
			return (repository.findAll(new PageRequest(page-1,max))).getContent();
		}
		
		@Override
		public void delete(MouvCarteMod entity) {
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
		public List<MouvCarteMod> getAll() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Page<DemandeVehiculeMod> getAll(PageRequest gotoPage) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public List<MouvCarteMensuelMod> Mouvcartemensuel(String mois,Pageable pageable) {
			return repository.Mouvcartemensuel(mois, pageable);
		}
		
		@Override
		public List<MouvCarteMod> listeCarteRetourner() {
			return repository.listeCarteRetourner();
		}
		
		@Override
		public List<MouvCarteMensuelMod> rechCarteRetourner(Date retour, String numcarte) {
			return repository.rechCarteRetourner(retour, numcarte);
		}
}