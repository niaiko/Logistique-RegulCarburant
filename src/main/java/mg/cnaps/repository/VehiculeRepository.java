package mg.cnaps.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

//import java.util.List;
import mg.cnaps.models.VehiculeMod;


public interface VehiculeRepository extends JpaRepository<VehiculeMod,Integer> {
	@Query(value = "select nextval('\"RFM\".vehicule_id_vehicule_seq')", nativeQuery=true)
	long seqDemande();

	@Query("select COUNT(u) from VehiculeMod u where refvehicule = ?1 and marque = ?2 ")
	int compairevehicule(String codearticle, String libelle);
	
	@Query("select u from VehiculeMod u where u.consommation = 0 or u.codedr is null order by u.idVehicule")
	List<VehiculeMod> getvehiculenondetail(Pageable page);
	
	@Query("select u from VehiculeMod u where (u.consommation = 0 or u.codedr is null) and "
			+ "(u.typevehicule =:#{#x.typevehicule} or coalesce(:#{#x.typevehicule},null) is null) and "
			+ "(u.matrvehic =:#{#x.matrvehic} or coalesce(:#{#x.matrvehic},null) is null) and "
			+ "(u.refvehicule =:#{#x.refvehicule} or coalesce(:#{#x.refvehicule},null) is null) and"
			+ "(u.marque like concat('%',:#{#x.marque},'%') or coalesce(:#{#x.marque},null)='') order by u.idVehicule ")
	List<VehiculeMod> getvehiculenondetail2(@Param("x") VehiculeMod vm, Pageable page);
	
//	@Query("select u from VehiculeMod u where consommation = 0 or codedr is null ")
//	List<VehiculeMod> getvehiculenondetailpaginer();
	
	//(u.marque like concat('%',:#{#x.marque},'%') or coalesce(:#{#x.marque},null) is null)
	
	@Transactional
	@Modifying
	@Query(value="update VehiculeMod u set u.consommation = ?2, u.codedr = ?3, u.matrvehic = ?4 where u.idVehicule = ?1 ")
	public void updatedonneevehic(int idvehicule,Double Consommation,String coder,String matrvehic);
	
	@Query("select u from VehiculeMod u where u.consommation != 0 and u.codedr is not null and u.matrvehic is not null")
	List<VehiculeMod> getlistevehiculedispo(Pageable pageable);
	
	@Query("select u from VehiculeMod u where u.consommation != 0 and u.codedr is not null and u.matrvehic is not null")
	List<VehiculeMod> getListeVehiculeDispoSansPage();
	
	@Query("select u from VehiculeMod u where (u.consommation != 0) and (u.codedr is not null) and (u.matrvehic is not null) and "
			+ "(u.typevehicule =:#{#x.typevehicule} or coalesce(:#{#x.typevehicule},null)='') and "
			+ "(u.matrvehic =:#{#x.matrvehic} or coalesce(:#{#x.matrvehic},null)='') and "
			+ "(u.refvehicule =:#{#x.refvehicule} or coalesce(:#{#x.refvehicule},null)='') and"
			+ "(u.marque like concat('%',:#{#x.marque},'%') or coalesce(:#{#x.marque},null)='') ")
	List<VehiculeMod> getlistevehiculedispo2(@Param("x") VehiculeMod v, Pageable pageable);
	
	@Query("select u from VehiculeMod u ")
	List<VehiculeMod> findioAll();
}


