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
@Table(name = "organisation_carte")
public class OrganisationCarteMod {
	
	
	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="MY_SEQ_GEN", initialValue=205, allocationSize=12)
	@GeneratedValue(strategy= GenerationType.IDENTITY, generator="seq-gen")
	@Column(name = "id_org", unique = true , nullable = false )
	private int idOrg;
	
	@Column(name = "id_carte_carb")
	private int idCarteCarb;
	
	@Column(name = "id_carbu")
	private int idCarbu;
	
	@Column(name = "montant")
	private double montant;
	
	@Column(name = "quantite")
	private double quantite;
	
	
	public int getIdOrg() {
		return idOrg;
	}

	public void setIdOrg(int  idOrg) {
		this.idOrg =  idOrg;
	}
	
	public int getIdCarteCarb() {
		return idCarteCarb;
	}

	public void setIdCarteCarb(int idCarteCarb) {
		this.idCarteCarb = idCarteCarb;
	}
	
	public int getIdCarbu() {
		return idCarbu;
	}

	public void setIdCarbu(int idCarbu) {
		this.idCarbu = idCarbu;
	}
		

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}
	
	public double getQuantite() {
		return quantite;
	}

	public void setQuantite(double quantite) {
		this.quantite = quantite;
	}
	
}