package mg.cnaps.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "prix_carbu")
public class PrixCarbuMod{
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "id_prix", unique = true , nullable = false )
	private int idPrix;
	
	@Column(name = "prix")
	private double prix;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "carburant")
	private String carburant;
	
	public int getIdPrix() {
		return idPrix;
	}

	public void setIdPrix(int idPrix) {
		this.idPrix = idPrix;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCarburant() {
		return carburant;
	}

	public void setCarburant(String carburant) {
		this.carburant = carburant;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

}	