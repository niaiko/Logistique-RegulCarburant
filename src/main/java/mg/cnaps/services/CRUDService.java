package mg.cnaps.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import mg.cnaps.models.DemandeVehiculeMod;

//import mg.cnaps.models.ServiceCouMod;

public interface CRUDService<E> {

	E save(E entity);

	E getById(Serializable id);

	List<E> getAll(int page);

	void delete(E entity);
	
	int nombrepage();

	List<E> getAll();

	Page<DemandeVehiculeMod> getAll(PageRequest gotoPage);
}
