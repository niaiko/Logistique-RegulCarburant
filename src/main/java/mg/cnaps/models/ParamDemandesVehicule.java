package mg.cnaps.models;

import java.sql.Date;
import java.sql.Time;

public class ParamDemandesVehicule {
	
	private Date datedepart;
    private Time heuredepart;
	public Date getDatedepart() {
		return datedepart;
	}
	public void setDatedepart(Date datedepart) {
		this.datedepart = datedepart;
	}
	public Time getHeuredepart() {
		return heuredepart;
	}
	public void setHeuredepart(Time heuredepart) {
		this.heuredepart = heuredepart;
	}
	
	
}
