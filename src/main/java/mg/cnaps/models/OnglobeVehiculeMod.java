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
	@Table(name = "onglobe_vehicule")
	public class OnglobeVehiculeMod {
		
		@Id
		@SequenceGenerator(name="seq-gen",sequenceName="MY_SEQ_GEN", initialValue=205, allocationSize=12)
		@GeneratedValue(strategy= GenerationType.IDENTITY, generator="seq-gen")
		@Column(name = "id_onglobe", unique = true , nullable = false )
		private int idOnglobe;
		
		@Column(name = "id_type2")
		private int idType2;
		
		@Column(name = "id_vehicule")
		private int idVehicule;
		
		@Column(name = "libelle")
		private String libelle;
		
		@Column(name = "date_debut")
		private Date dateDebut;
		
		@Column(name = "date_fin")
		private Date dateFin;
		
		@Column(name = "date_visite")
		private Date datevisite;
		
		@Column(name = "date_cnavto")
		private Date datecnavto;

		public int getIdOnglobe() {
			return idOnglobe;
		}

		public void setIdOnglobe(int  idOnglobe) {
			this.idOnglobe =  idOnglobe;
		}
		
		public int getIdType2() {
			return  idType2;
		}

		public void setIdType2(int  idType2) {
			this.idType2 =  idType2;
		}
		
		public String getLibelle() {
			return libelle;
		}

		public void setLibelle(String libelle) {
			this.libelle= libelle;
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

		public int getIdVehicule() {
			return idVehicule;
		}

		public void setIdVehicule(int idVehicule) {
			this.idVehicule = idVehicule;
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
		
}
