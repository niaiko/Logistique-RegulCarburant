package mg.cnaps.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "historique")
public class HistoriqueMod{
	
	
	@Id
	@Column(name = "id_historique", unique = true , nullable = false )
	private int idhistorique;

	@Column(name = "tache")
	private String tache ;
	
	@Column(name = "date_histo")
	private Date datehisto ;
	
	@Column(name = "user_insert")
	private String userinsert ;
	
	@Column(name = "matricule_user")
	private String matriculeuser ;

	public int getIdhistorique() {
		return idhistorique;
	}

	public void setIdhistorique(int idhistorique) {
		this.idhistorique = idhistorique;
	}

	public String getTache() {
		return tache;
	}

	public void setTache(String tache) {
		this.tache = tache;
	}

	public Date getDatehisto() {
		return datehisto;
	}

	public void setDatehisto(Date datehisto) {
		this.datehisto = datehisto;
	}

	public String getUserinsert() {
		return userinsert;
	}

	public void setUserinsert(String userinsert) {
		this.userinsert = userinsert;
	}

	public String getMatriculeuser() {
		return matriculeuser;
	}

	public void setMatriculeuser(String matriculeuser) {
		this.matriculeuser = matriculeuser;
	}

}
