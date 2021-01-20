package mg.cnaps.models;

//import java.sql.Time;

public class ParamCalculprixandlitreSiege {
	
	private int idmouv;
	private Double distance;
	private Double Consommationveh;
	private String typecarburant;
	private int sejour;
	
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public Double getConsommationveh() {
		return Consommationveh;
	}
	public void setConsommationveh(Double consommationveh) {
		Consommationveh = consommationveh;
	}
	public String getTypecarburant() {
		return typecarburant;
	}
	public void setTypecarburant(String typecarburant) {
		this.typecarburant = typecarburant;
	}
	public int getIdmouv() {
		return idmouv;
	}
	public void setIdmouv(int idmouv) {
		this.idmouv = idmouv;
	}
	public int getSejour() {
		return sejour;
	}
	public void setSejour(int sejour) {
		this.sejour = sejour;
	}
	
}
