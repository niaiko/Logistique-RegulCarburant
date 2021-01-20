package mg.cnaps.repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import mg.cnaps.models.DemandeVehiculeMod;

public interface DemandeVehiculeRepository extends JpaRepository<DemandeVehiculeMod, Integer> {
	@Query(value = "Select nextval('\"RFM\".seq_demande_vehicule')", nativeQuery = true)
	Long seqDemande();

	@Query("select u from DemandeVehiculeMod u where datedepart=?1 And heuredepart=?2")
	List<DemandeVehiculeMod> getByDateHeureDepart(Date datedepart, Time heuredepart);

	@Query("select u from DemandeVehiculeMod u where (u.defdmd = ?1 or ?1='') and (u.codedr = ?2 or ?2 = '' ) and validation = ?3 ")
	List<DemandeVehiculeMod> getByDefMouv(String defdmd, String codedr, String validation);

	@Query("select u from DemandeVehiculeMod u where u.datedepart = ?3 and (u.defdmd = ?1 or ?1='') and (u.codedr = ?2 or ?2 = '' ) and validation = ?4 ")
	List<DemandeVehiculeMod> getByDefMouv2(String defdmd, String codedr, Date datedepart, String validation);

	@Transactional
	@Modifying
	@Query(value = "update DemandeVehiculeMod u set u.validation = 'true' where u.iddemande = ?1 ")
	public void updatevalidationdmdveh(int iddemande);

	@Query("select u from DemandeVehiculeMod u where u.validation = 'true' order by u.datedepart desc ")
	public List<DemandeVehiculeMod> listauthoriser(Pageable pageable);

	@Query("select u from DemandeVehiculeMod u where (u.validation = 'true') and (u.matrdmd =:#{#x.matrdmd} or coalesce(:#{#x.matrdmd},null) is null) and"
			+ " (u.datedepart =:#{#x.datedepart} or coalesce(:#{#x.datedepart},null) is null) and "
			+ "(u.codedr =:#{#x.codedr} or coalesce(:#{#x.codedr},null) is null) and "
			+ "(u.refdmdvh =:#{#x.refdmdvh} or coalesce(:#{#x.refdmdvh},null) is null) order by u.datedepart desc ")
	public List<DemandeVehiculeMod> listauthoriser2(@Param("x") DemandeVehiculeMod d, Pageable pageable);

	@Query("select u from DemandeVehiculeMod u where u.validation = 'false' order by u.datedepart desc ")
	public Page<DemandeVehiculeMod> findByValidation(Pageable pageable);

	@Query("select u from DemandeVehiculeMod u where (u.validation = 'false') and (u.matrdmd =:#{#x.matrdmd} or coalesce(:#{#x.matrdmd},null) is null) and"
			+ " (u.datedepart =:#{#x.datedepart} or coalesce(:#{#x.datedepart},null) is null) and "
			+ "(u.codedr =:#{#x.codedr} or coalesce(:#{#x.codedr},null) is null) and "
			+ "(u.refdmdvh =:#{#x.refdmdvh} or coalesce(:#{#x.refdmdvh},null) is null) order by u.datedepart desc ")
	public Page<DemandeVehiculeMod> findByValidation2(@Param("x") DemandeVehiculeMod dvm, Pageable pageable);

	@Query("select u from DemandeVehiculeMod u where (u.validation = 'false') and ( lower(u.codedr) like '%' || lower(cast(?1 as string)) || '%' or ?1 is null ) and ( lower(u.refdmdvh) like '%' || lower(cast(?2 as string)) || '%' or ?2 is null ) order by u.datedepart DESC")
	Page<DemandeVehiculeMod> rechercheMultiAvecFalse(String dr, String refDem, Pageable pageable);
	
	@Query("select u from DemandeVehiculeMod u where (u.validation = 'true') and ( lower(u.codedr) like '%' || lower(cast(?1 as string)) || '%' or ?1 is null ) and ( lower(u.refdmdvh) like '%' || lower(cast(?2 as string)) || '%' or ?2 is null ) order by u.datedepart DESC")
	Page<DemandeVehiculeMod> rechercheMultiAvecTrue(String dr, String refDem, Pageable pageable);

}
