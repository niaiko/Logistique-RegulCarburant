package mg.cnaps.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "carte_carburant")
public class CarteCarbMod{
		
	@Id
	@Column(name = "id_carte_carb", unique = true , nullable = false )
	private Integer idcartecarburant;

	@Column(name = "id_comp_petrol")
	private Integer idcomptrol ;
	
	@Column(name = "numero_carte")
	private String numerocarte ;
	
	@Column(name="id_type_carte")
	private Integer idtypecarte;
	
	@Column(name="\"DR_possession\"")
	private String drpossession;
	
	@Column(name="code_carte")
	private Integer codecarte;
	
	@Column(name="utilisation")
	private String utilisation ;
	
	@Column(name="\"code_DR\"")
	private String codedr;
	
	@Column(name="id_disponibilite")
	private Integer iddisponibilite;
	
	@Column(name="montant")
	private Double montant;

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public Integer getIdcartecarburant() {
		return idcartecarburant;
	}

	public void setIdcartecarburant(Integer idcartecarburant) {
		this.idcartecarburant = idcartecarburant;
	}

	public Integer getIdcomptrol() {
		return idcomptrol;
	}

	public void setIdcomptrol(Integer idcomptrol) {
		this.idcomptrol = idcomptrol;
	}

	public String getNumerocarte() {
		return numerocarte;
	}

	public void setNumerocarte(String numerocarte) {
		this.numerocarte = numerocarte;
	}

	public Integer getIdtypecarte() {
		return idtypecarte;
	}

	public void setIdtypecarte(Integer idtypecarte) {
		this.idtypecarte = idtypecarte;
	}

	public String getDrpossession() {
		return drpossession;
	}

	public void setDrpossession(String drpossession) {
		this.drpossession = drpossession;
	}

	public Integer getCodecarte() {
		return codecarte;
	}

	public void setCodecarte(Integer codecarte) {
		this.codecarte = codecarte;
	}

	public String getUtilisation() {
		return utilisation;
	}

	public void setUtilisation(String utilisation) {
		this.utilisation = utilisation;
	}

	public String getCodedr() {
		return codedr;
	}

	public void setCodedr(String codedr) {
		this.codedr = codedr;
	}

	public Integer getIddisponibilite() {
		return iddisponibilite;
	}

	public void setIddisponibilite(Integer iddisponibilite) {
		this.iddisponibilite = iddisponibilite;
	}
	
}
