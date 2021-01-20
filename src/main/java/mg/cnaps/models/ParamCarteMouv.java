package mg.cnaps.models;


	import java.sql.Date;
	
	public class ParamCarteMouv {
		
		private String userinsert;
		private String matriculeuser;
		private int idmouvcarte;
		private String numcarte;
		private Date datemouv;
		private Date datedebut;
		private Date datefin;
		private double entree;
		private double sortant;
		private String motif;
		private String refdmd;
		private String libelle;
		private Long distance;
		private String lieudepart;
		private String lieuarrive;
		
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
		public int getIdmouvcarte() {
			return idmouvcarte;
		}
		public void setIdmouvcarte(int idmouvcarte) {
			this.idmouvcarte = idmouvcarte;
		}
		public String getNumcarte() {
			return numcarte;
		}
		public void setNumcarte(String numcarte) {
			this.numcarte = numcarte;
		}
		public Date getDatemouv() {
			return datemouv;
		}
		public void setDatemouv(Date datemouv) {
			this.datemouv = datemouv;
		}
		public double getEntree() {
			return entree;
		}
		public void setEntree(double entree) {
			this.entree = entree;
		}
		public double getSortant() {
			return sortant;
		}
		public void setSortant(double sortant) {
			this.sortant = sortant;
		}
		public String getMotif() {
			return motif;
		}
		public void setMotif(String motif) {
			this.motif = motif;
		}
		public String getRefdmd() {
			return refdmd;
		}
		public void setRefdmd(String refdmd) {
			this.refdmd = refdmd;
		}
		public String getLibelle() {
			return libelle;
		}
		public void setLibelle(String libelle) {
			this.libelle = libelle;
		}
		public Date getDatedebut() {
			return datedebut;
		}
		public void setDatedebut(Date datedebut) {
			this.datedebut = datedebut;
		}
		public Date getDatefin() {
			return datefin;
		}
		public void setDatefin(Date datefin) {
			this.datefin = datefin;
		}
		public Long getDistance() {
			return distance;
		}
		public void setDistance(Long distance) {
			this.distance = distance;
		}
		public String getLieudepart() {
			return lieudepart;
		}
		public void setLieudepart(String lieudepart) {
			this.lieudepart = lieudepart;
		}
		public String getLieuarrive() {
			return lieuarrive;
		}
		public void setLieuarrive(String lieuarrive) {
			this.lieuarrive = lieuarrive;
		}

}
