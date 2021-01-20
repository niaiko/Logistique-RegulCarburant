package mg.cnaps.services;

import java.io.Serializable;
//import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import mg.cnaps.models.DemandeVehiculeMod;
import mg.cnaps.models.OrganisationCarteMod;
import mg.cnaps.repository.OrganisationCarteRepository;

@Service
public class OrganisationCarteServiceImpl implements OrganisationCarteService{
	
	public static int max=50;
	
	@Autowired
	OrganisationCarteRepository repository;

	@Override
	public OrganisationCarteMod save(OrganisationCarteMod typ) {
		return repository.save(typ);
		
	}

	@Override
	public OrganisationCarteMod getById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrganisationCarteMod> getAll(int page) {
		return (repository.findAll(new PageRequest(page-1,max))).getContent();
	}

	@Override
	public void delete(OrganisationCarteMod entity) {
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
	public List<OrganisationCarteMod> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<DemandeVehiculeMod> getAll(PageRequest gotoPage) {
		// TODO Auto-generated method stub
		return null;
	}

}




