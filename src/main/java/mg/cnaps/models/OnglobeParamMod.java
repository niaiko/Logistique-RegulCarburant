package mg.cnaps.models;

import java.util.Date;


public class OnglobeParamMod{
	private int idOnglobe;
	private int idType2;
	private int idVehicule;
	private String libelle;
	private Date dateDebut;
	private Date dateFin;
	private Date datevisite;
	private Date datecnavto;
	private String matrvehic;
	private VehiculeMod d;
	
	public OnglobeParamMod(int idOnglobe,int idType2,int idVehicule, String libelle,Date dateDebut, Date dateFin, Date datevisite,Date datecnavto,String matrvehic,
			VehiculeMod d) {
		super();
		this.idOnglobe = idOnglobe;
		this.idType2 = idType2;
		this.idVehicule = idVehicule;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.libelle = libelle;
		this.datevisite = datevisite;
		this.datecnavto = datecnavto;
		this.matrvehic = matrvehic;
		this.d = d;
	}
	public OnglobeParamMod() {
		super();
	}
	public OnglobeParamMod(int idOnglobe,int idType2,int idVehicule, String libelle,Date dateDebut, Date dateFin, Date datevisite,Date datecnavto,String matrvehic) {
		super();
		this.idOnglobe = idOnglobe;
		this.idType2 = idType2;
		this.idVehicule = idVehicule;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.libelle = libelle;
		this.datevisite = datevisite;
		this.datecnavto = datecnavto;
		this.matrvehic = matrvehic;
	}
	
	public int getIdOnglobe() {
		return idOnglobe;
	}
	public void setIdOnglobe(int idOnglobe) {
		this.idOnglobe = idOnglobe;
	}
	public int getIdType2() {
		return idType2;
	}
	public void setIdType2(int idType2) {
		this.idType2 = idType2;
	}
	public int getIdVehicule() {
		return idVehicule;
	}
	public void setIdVehicule(int idVehicule) {
		this.idVehicule = idVehicule;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	public Date getDatevisite() {
		return datevisite;
	}
	public void setDatevisite(Date datevisite) {
		this.datevisite = datevisite;
	}
	public Date getDatecnavto() {
		return datecnavto;
	}
	public void setDatecnavto(Date datecnavto) {
		this.datecnavto = datecnavto;
	}
	public String getMatrvehic() {
		return matrvehic;
	}
	public void setMatrvehic(String matrvehic) {
		this.matrvehic = matrvehic;
	}
	public VehiculeMod getD() {
		return d;
	}
	public void setD(VehiculeMod d) {
		this.d = d;
	}
	
}
