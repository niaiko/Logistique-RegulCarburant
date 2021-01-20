package mg.cnaps.models;

//import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "type_reparation")
public class DmdRepMod {

	@Id
	@Column(name = "id_demande_rep", length = 100, nullable = false)
	private String iddmdrep;

	@Column(name = "id_type_rep")
	private int idtyperep;

	@Column(name = "id_statue_rep")
	private int idstatuerep;

	@Column(name = "observation")
	private String observation;

	@Column(name = "id_vehicule")
	private int idvehicule;

	public String getIddmdrep() {
		return iddmdrep;
	}

	public void setIddmdrep(String iddmdrep) {
		this.iddmdrep = iddmdrep;
	}

	public int getIdtyperep() {
		return idtyperep;
	}

	public void setIdtyperep(int idtyperep) {
		this.idtyperep = idtyperep;
	}

	public int getIdstatuerep() {
		return idstatuerep;
	}

	public void setIdstatuerep(int idstatuerep) {
		this.idstatuerep = idstatuerep;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public int getIdvehicule() {
		return idvehicule;
	}

	public void setIdvehicule(int idvehicule) {
		this.idvehicule = idvehicule;
	}

}
