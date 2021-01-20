package mg.cnaps.models;


	import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
	
	@Entity
	@Table(name = "mouvement_carte")
	public class MouvCarteMod {
		
		@Id
		@Column(name = "id_mouv_carte", unique = true , nullable = false )
		private int idmouvcarte;
		
		@Column(name = "num_carte")
		private String numcarte;
		
		@Column(name = "date_mouv")
		private Date datemouv;
		
		@Column(name = "entree")
		private double entree;
		
		@Column(name = "Sortant")
		private double sortant;
		
		@Column(name = "motif")
		private String motif;
		
		@Column(name = "reference_demande")
		private String refdmd;
		
		@Column(name = "libelle")
		private String libelle;
		
		@Column(name = "date_debut")
		private Date datedebut;
		
		@Column(name = "date_fin")
		private Date datefin;
		
		@Column(name = "lieu_depart")
		private String lieudepart;
		
		@Column(name = "lieu_arrive")
		private String lieuarrive;
		
		@Column(name = "distance")
		private Long distance;

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

		public String getLibelle() {
			return libelle;
		}

		public void setLibelle(String libelle) {
			this.libelle = libelle;
		}

		public String getRefdmd() {
			return refdmd;
		}

		public void setRefdmd(String refdmd) {
			this.refdmd = refdmd;
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

		public Long getDistance() {
			return distance;
		}

		public void setDistance(Long distance) {
			this.distance = distance;
		}
		
}
