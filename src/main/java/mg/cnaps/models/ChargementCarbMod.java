package mg.cnaps.models;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "chargement_carte")
public class ChargementCarbMod{
	
	
	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="MY_SEQ_GEN", initialValue=205, allocationSize=12)
	@GeneratedValue(strategy= GenerationType.IDENTITY, generator="seq-gen")
	@Column(name = "id_chargement_car", unique = true , nullable = false )
	private int idchargementcarte;
	
	@Column(name = "montant")
	private Double montant ;
	
	@Column(name = "num_carte")
	private String numcarte ;

	@Column(name = "date_charge")
	private Date datecharge ;

	public Date getDatecharge() {
		return datecharge;
	}

	public void setDatecharge(Date datecharge) {
		this.datecharge = datecharge;
	}

	public String getNumcarte() {
		return numcarte;
	}

	public void setNumcarte(String numcarte) {
		this.numcarte = numcarte;
	}

	public int getIdchargementcarte() {
		return idchargementcarte;
	}

	public void setIdchargementcarte(int idchargementcarte) {
		this.idchargementcarte = idchargementcarte;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	
	
}
