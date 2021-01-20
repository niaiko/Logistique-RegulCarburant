package mg.cnaps.services;

import java.io.Serializable;
//import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import mg.cnaps.models.DemandeVehiculeMod;
import mg.cnaps.models.PrixCarbuMod;
import mg.cnaps.repository.PrixCarbuRepository;

@Service
public class PrixCarbuServiceImpl implements  PrixCarbuService{
	
	public static int max=50;
	
	@Autowired
	 PrixCarbuRepository repository;

	@Override
	public PrixCarbuMod save( PrixCarbuMod pri) {
		return repository.save(pri);
		
	}

	@Override
	public  PrixCarbuMod getById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List< PrixCarbuMod> getAll(int page) {
		return (repository.findAll(new PageRequest(page-1,max))).getContent();
	}

	@Override
	public void delete( PrixCarbuMod entity) {
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
	public PrixCarbuMod findprixcarburant(String carb) {
		return repository.findFirstByCarburantOrderByDateDesc(carb);
	}

	@Override
	public List<PrixCarbuMod> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<DemandeVehiculeMod> getAll(PageRequest gotoPage) {
		// TODO Auto-generated method stub
		return null;
	}
}




