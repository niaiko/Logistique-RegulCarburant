package mg.cnaps.models;


	import java.sql.Date;
	
	public class Parammouvcarte {
		
		private String userinsert;
		private String matriculeuser;
		private int idmouvcarte;
		private String numcarte;
		private Date datemouv;
		private double entree;
		private double sortant;
		private String motif;
		private String refdmd;
		private String libelle;
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

		
}
