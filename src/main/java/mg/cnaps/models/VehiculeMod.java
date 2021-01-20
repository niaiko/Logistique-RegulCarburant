package mg.cnaps.models;

	import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
	
	@Entity
	@Table(name = "vehicule")
	public class VehiculeMod {
		
		
		@Id
		@SequenceGenerator(name="seq-gen",sequenceName="MY_SEQ_GEN", initialValue=205, allocationSize=12)
		@GeneratedValue(strategy= GenerationType.IDENTITY, generator="seq-gen")
		@Column(name = "id_vehicule", unique = true , nullable = false )
		private int idVehicule;
		
		@Column(name = "marque")
		private String marque;
		
		@Column(name = "consommation")
		private Double consommation;
		
		@Column(name = "codedr")
		private String codedr;
		
		@Column(name = "reference_vehicule")
		private String refvehicule;
		
		@Column(name = "type_vehicule")
		private String typevehicule;
		
		@Column(name = "matricule_vehicule")
		private String matrvehic;
		
		@Column(name = "ref_mt")
		private String refmt;

		public String getRefmt() {
			return refmt;
		}

		public void setRefmt(String refmt) {
			this.refmt = refmt;
		}

		public String getMatrvehic() {
			return matrvehic;
		}

		public void setMatrvehic(String matrvehic) {
			this.matrvehic = matrvehic;
		}

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

		public Double getConsommation() {
			return consommation;
		}

		public void setConsommation(Double consommation) {
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
		
		
}
