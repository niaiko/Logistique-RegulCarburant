package mg.cnaps.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//
//import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import mg.cnaps.models.DemandeCarbMod;

@RepositoryRestResource
public interface DemandeCarbRepository extends JpaRepository<DemandeCarbMod, Integer> {
	@Query(value = "select nextval('\"RFM\".demande_carbu_id_carbu_seq')", nativeQuery = true)
	long seqDemande();

	@Query("select u from DemandeCarbMod u where validation = ?1 and (referencecarbu like concat(lower(?2), '%') or ?2 = '')")
	List<DemandeCarbMod> getBysansDateDemande(Boolean validation, String referencecarbu);

	@Query("select u from DemandeCarbMod u where (datedemande=?1 or ?1 = '') and validation = ?2 and (referencecarbu like concat(lower(?3), '%') or ?3 = '')")
	List<DemandeCarbMod> getByavecDateDemande(Date datedemande, Boolean validation, String referencecarbu);

	@Transactional
	@Modifying
	@Query(value = "update DemandeCarbMod u set u.validation = 'true' where u.idcarbu = ?1 ")
	public void updatevalidationdmdcarbu(int iddemande);

	@Query("select u from DemandeCarbMod u where u.validation = 'true' ")
	List<DemandeCarbMod> listevalidationdmdcarbu(Pageable pageable);

//	@Query("select u from DemandeCarbMod u where (u.validation = 'false') and (u.referencecarbu =:#{#x.referencecarbu} or coalesce(:#{#x.referencecarbu},null) is null) and"
//			+ " (u.matrdmd =:#{#x.matrdmd} or coalesce(:#{#x.matrdmd},null) is null) and "
//			+ "(u.datedemande =:#{#x.datedemande} or coalesce(:#{#x.datedemande},null) is null) order by u.datedemande desc ")
//	public Page<DemandeCarbMod> listenonvalidationdmdcarbu(@Param("x") DemandeCarbMod dcm, Pageable pageable);

	@Query("select u from DemandeCarbMod u where (u.validation = 'false') and ( lower(u.referencecarbu) like '%' || lower(cast(?1 as string)) || '%' or ?1 is null ) and ( lower(u.matrdmd) like '%' || lower(cast(?2 as string)) || '%' or ?2 is null ) and (u.datedemande = ?3 or ?3 is null) order by u.datedemande DESC")
	Page<DemandeCarbMod> searchMulti(String reference, String matricule, Date dateDmd, Pageable pageable);

	@Query("select u from DemandeCarbMod u where (u.validation = 'false') order by u.datedemande desc ")
	public Page<DemandeCarbMod> listenonvalidationdmdcarbu2(Pageable pageable);
}
