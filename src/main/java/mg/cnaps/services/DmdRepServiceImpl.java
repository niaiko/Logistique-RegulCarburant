package mg.cnaps.services;

import java.io.Serializable;
//import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import mg.cnaps.models.DemandeVehiculeMod;
import mg.cnaps.models.DmdRepMod;
import mg.cnaps.repository.DmdRepRepository;
@Service
public class DmdRepServiceImpl implements DmdRepService{
	
	public static int max=50;
	
	@Autowired
	DmdRepRepository repository;

//	@Override
//	public void save(DmdRepMod cou) {
//		repository.save(cou);
		
//	}

	@Override
	public DmdRepMod getById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DmdRepMod> getAll(int page) {
		return (repository.findAll(new PageRequest(page-1,max))).getContent();
	}

	@Override
	public void delete(DmdRepMod entity) {
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
	public DmdRepMod save(DmdRepMod entity) {
		// TODO Auto-generated method stub
		return repository.save(entity);
	}

	@Override
	public List<DmdRepMod> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<DemandeVehiculeMod> getAll(PageRequest gotoPage) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
//	@Override
//	public List<CsLogMod> getByDestinataireDateCou(String destinataire, Date dateInsert) {
//		return repository.getByDestinataireDateCou(destinataire, dateInsert);
//	}

}
