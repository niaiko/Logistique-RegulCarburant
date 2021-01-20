package mg.cnaps.models;

import java.util.Date;
public class ParamOnglobeVehicule {
		
		private int idVehicule;
		private String marque;
		private double consommation;
		private String codedr;
		private String refvehicule;
		private String typevehicule;
		
		private int idOnglobe;
		private int idType2;
		private String libelle;
		private Date dateDebut;
		private Date dateFin;
		private Date datevisite;
		private Date datecnavto;
		private String matrvehic;
		
		public int getIdVehicule() {
			return idVehicule;
		}
		public void setIdVehicule(int idVehicule) {
			this.idVehicule = idVehicule;
		}
		public String getMarque() {
			return marque;
		}
		public void setMarque(String marque) {
			this.marque = marque;
		}
		public double getConsommation() {
			return consommation;
		}
		public void setConsommation(double consommation) {
			this.consommation = consommation;
		}
		public String getCodedr() {
			return codedr;
		}
		public void setCodedr(String codedr) {
			this.codedr = codedr;
		}
		public String getRefvehicule() {
			return refvehicule;
		}
		public void setRefvehicule(String refvehicule) {
			this.refvehicule = refvehicule;
		}
		public String getTypevehicule() {
			return typevehicule;
		}
		public void setTypevehicule(String typevehicule) {
			this.typevehicule = typevehicule;
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
		
		
}
