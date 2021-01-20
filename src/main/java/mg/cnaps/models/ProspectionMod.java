package mg.cnaps.models;

//import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "prospection")
public class ProspectionMod{
	
	
	@Id
	@Column(name = "id_prospection", length = 100, nullable = false )
	private String idprospection;
	
	@Column(name = "id_demande_rep")
	private int iddmdrep;
	
	@Column(name = "nom_piece")
	private int nompiece;
	
	@Column(name = "etat")
	private boolean etat;
	
	@Column(name = "reference")
	private String reference;

	public String getIdprospection() {
		return idprospection;
	}

	public void setIdprospection(String idprospection) {
		this.idprospection = idprospection;
	}

	public int getIddmdrep() {
		return iddmdrep;
	}

	public void setIddmdrep(int iddmdrep) {
		this.iddmdrep = iddmdrep;
	}

	public int getNompiece() {
		return nompiece;
	}

	public void setNompiece(int nompiece) {
		this.nompiece = nompiece;
	}

	public boolean isEtat() {
		return etat;
	}

	public void setEtat(boolean etat) {
		this.etat = etat;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}
	
}
