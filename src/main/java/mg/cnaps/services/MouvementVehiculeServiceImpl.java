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
import mg.cnaps.models.MouvementVehiculeMod;
import mg.cnaps.repository.MouvementVehiculeRepository;


@Service
public class MouvementVehiculeServiceImpl implements MouvementVehiculeService{
	
	public static int max=50;
	
	@Autowired
	 MouvementVehiculeRepository repository;

	@Override
	public MouvementVehiculeMod save(MouvementVehiculeMod typ) {
		return repository.save(typ);
		
	}

	@Override
	public  MouvementVehiculeMod getById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List< MouvementVehiculeMod> getAll(int page) {
		return (repository.findAll(new PageRequest(page-1,max))).getContent();
	}
	
	@Override
	public int countMouv(int iddemande) {
		return repository.countMouv(iddemande);
	}

	@Override
	public void delete( MouvementVehiculeMod entity) {
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
	public List<MouvementVehiculeMod>getByDatedepart(Date datedepart){
		return repository.getByDatedepart(datedepart);
	}
	
	@Override
	public List<MouvementVehiculeMod>getByIdchauffeur(String idchauffeur){
		return repository.getByIdchauffeur(idchauffeur);
		
	}
	@Override
	public List<MouvementVehiculeMod>findByRefdmdvh(String refdmdvh){
	    return repository.findByRefdmdvh(refdmdvh); 
	}

	@Override
	public void updatemouvveh(Double prix , Double quantite, int idmouv){
	    repository.updatemouvveh(prix , quantite, idmouv);
	}
	
	@Override
	public void updatearrivermouv( Date datearriver, int idmouv){
	    repository.updatearrivermouv(datearriver, idmouv);
	}

	@Override
	public List<MouvementVehiculeMod> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<DemandeVehiculeMod> getAll(PageRequest gotoPage) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<MouvementVehiculeMod> rechmultipleMvt(Date datedepart,Date arriver,int idvehicule, String idchauffuer){
		return repository.rechmultipleMvt(datedepart,arriver,idvehicule,idchauffuer);
		
	}

	@Override
	public List<MouvementVehiculeMod> findallmouv(Pageable pageable) {
		return repository.findallmouv(pageable);
	}
	
}