package mg.cnaps.models;
//import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "type_assurance")
public class TypeVehiculeMod {

	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="MY_SEQ_GEN", initialValue=205, allocationSize=12)
	@GeneratedValue(strategy= GenerationType.IDENTITY, generator="seq-gen")
	@Column(name = "id_typ_vehicule", unique = true , nullable = false )
	private int idTypevehic;
	
	@Column(name = "libelle_type",length = 100, nullable = false)
	private String libelle_type;

	public int getIdTypevehic() {
		return idTypevehic;
	}

	public void setIdTypevehic(int idTypevehic) {
		this.idTypevehic = idTypevehic;
	}

	public String getLibelle_type() {
		return libelle_type;
	}

	public void setLibelle_type(String libelle_type) {
		this.libelle_type = libelle_type;
	}
	
}
	