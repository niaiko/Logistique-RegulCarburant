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
public class TypeAssuranceMod {

	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="MY_SEQ_GEN", initialValue=205, allocationSize=12)
	@GeneratedValue(strategy= GenerationType.IDENTITY, generator="seq-gen")
	@Column(name = "id_type2", unique = true , nullable = false )
	private int idType2;
	
	@Column(name = "libelle",length = 100, nullable = false)
	private String libelle;
	
	
	
	public int getIdType2() {
		return idType2;
	}

	public void setIdType2(int idType2) {
		this.idType2 = idType2;
	}
	
	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
}
	