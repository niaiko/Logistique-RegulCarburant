package mg.cnaps.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "statue_reparation")
public class StatueRepMod{
	
	
	@Id
	@Column(name = "id_statue_rep", length = 100, nullable = false )
	private String idstatuerep;
	
	@Column(name = "statut")
	private String statue;
	
	@Column(name = "date_statue_deb")
	private Date datestatuedeb;
	
	@Column(name = "date_statue_fin")
	private Date datestatuefin;
	
	@Column(name = "statue_apres_rep")
	private String statueapresrep;

	public String getIdstatuerep() {
		return idstatuerep;
	}

	public void setIdstatuerep(String idstatuerep) {
		this.idstatuerep = idstatuerep;
	}

	public String getStatue() {
		return statue;
	}

	public void setStatue(String statue) {
		this.statue = statue;
	}

	public Date getDatestatuedeb() {
		return datestatuedeb;
	}

	public void setDatestatuedeb(Date datestatuedeb) {
		this.datestatuedeb = datestatuedeb;
	}

	public Date getDatestatuefin() {
		return datestatuefin;
	}

	public void setDatestatuefin(Date datestatuefin) {
		this.datestatuefin = datestatuefin;
	}

	public String getStatueapresrep() {
		return statueapresrep;
	}

	public void setStatueapresrep(String statueapresrep) {
		this.statueapresrep = statueapresrep;
	}
}
