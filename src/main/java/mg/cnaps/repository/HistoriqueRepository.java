package mg.cnaps.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//
//import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import mg.cnaps.models.HistoriqueMod;

@RepositoryRestResource
public interface HistoriqueRepository extends JpaRepository<HistoriqueMod, Integer> {
	@Query(value = "select nextval('\"RFM\".historique_seq')", nativeQuery = true)
	long seqDemande();

	@Query("select u from HistoriqueMod u ")
	List<HistoriqueMod> findallhistorique(Pageable pageable);

	@Query("select u from HistoriqueMod u ")
	Page<HistoriqueMod> findallhistoriquepage(Pageable pageable);

	@Query("select u from HistoriqueMod u where datehisto = ?1 and (matriculeuser = ?2 or ?2 = '' ) ")
	List<HistoriqueMod> findbydatehistoandmatriculeuser(Date date, String matr);
	
	@Query("select u from HistoriqueMod u where datehisto = ?1 and (matriculeuser = ?2 or ?2 = '' ) ")
	Page<HistoriqueMod> findbydatehistoandmatriculeuserPage(Date date, String matr, Pageable pageable);

	@Query("select u from HistoriqueMod u where (matriculeuser = ?1 or ?1 = '' ) ")
	List<HistoriqueMod> findbydatehistoandmatriculeuser2(String matr);
	
	@Query("select u from HistoriqueMod u where (matriculeuser = ?1 or ?1 = '' ) ")
	Page<HistoriqueMod> findbydatehistoandmatriculeuser2Page(String matr, Pageable pageable);

	@Query("select DISTINCT(userinsert) as userinsert, matriculeuser as matriculeuser from HistoriqueMod u group by idhistorique")
	Object[] findallty();
}