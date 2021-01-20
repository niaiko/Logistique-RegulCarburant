package mg.cnaps.services;

import java.io.Serializable;
//import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import mg.cnaps.models.DemandeVehiculeMod;
import mg.cnaps.models.TypeAssuranceMod;
import mg.cnaps.repository.TypeAssuranceRepository;

@Service
public class TypeAssuranceServiceImpl implements TypeAssuranceService{
	
	public static int max=50;
	
	@Autowired
	TypeAssuranceRepository repository;

	@Override
	public TypeAssuranceMod save(TypeAssuranceMod typ) {
		return repository.save(typ);
		
	}

	@Override
	public TypeAssuranceMod getById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TypeAssuranceMod> getAll(int page) {
		return (repository.findAll(new PageRequest(page-1,max))).getContent();
	}

	@Override
	public void delete(TypeAssuranceMod entity) {
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
	public List<TypeAssuranceMod> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<DemandeVehiculeMod> getAll(PageRequest gotoPage) {
		// TODO Auto-generated method stub
		return null;
	}

}
