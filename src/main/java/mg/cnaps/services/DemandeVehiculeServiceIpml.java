package mg.cnaps.services;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import mg.cnaps.models.DemandeVehiculeMod;
import mg.cnaps.repository.DemandeVehiculeRepository;

@Service
public class DemandeVehiculeServiceIpml implements DemandeVehiculeService{

	public static int max=10;
	
	@Autowired 
	DemandeVehiculeRepository repository;
	
	@Override
	public DemandeVehiculeMod save(DemandeVehiculeMod veh) {
         return repository.save(veh);
	}

	@Override
	public DemandeVehiculeMod getById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DemandeVehiculeMod> getAll(int page) {
		return (repository.findAll(new PageRequest(page-1,max))).getContent();
	}
	
//	@Override
//	public List<DemandeVehiculeMod> listauthoriser(Pageable pageable) {
//		return repository.listauthoriser(pageable);
//	}
	

	@Override
	public void delete(DemandeVehiculeMod entity) {
		repository.delete(entity);
	}

	@Override
	public int nombrepage() {
		return (int) (repository.count()/max)+1;
	}

	@Override
	public long seqDemande() {
		return repository.seqDemande();
	}
	@Override
	public List<DemandeVehiculeMod>getByDateHeureDepart(Date datedepart,Time heuredepart){
		return repository.getByDateHeureDepart(datedepart, heuredepart);
	}
	
	@Override
	public List<DemandeVehiculeMod> getByDefMouv(String defdmd, String codedr, String validation){
		return repository.getByDefMouv(defdmd,codedr, validation);
	}
	
	@Override
	public List<DemandeVehiculeMod> getByDefMouv2(String defdmd, String codedr, Date datedepart, String validation){
		return repository.getByDefMouv2(defdmd,codedr,datedepart, validation);
	}
	
//	@Override
//	public List<DemandeVehiculeMod> findByValidation(Pageable pageable){
//		return repository.findByValidation(pageable);
//	}
	

	@Override
	public void updatevalidationdmdveh(int iddemande) {
		repository.updatevalidationdmdveh(iddemande);
	}

	@Override
	public List<DemandeVehiculeMod> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}
	
	@Override
	public Page<DemandeVehiculeMod> getAll(PageRequest gotoPage) {
		// TODO Auto-generated method stub
		return repository.findAll(gotoPage);
		
	}
}
