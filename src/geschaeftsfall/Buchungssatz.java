package geschaeftsfall;

public class Buchungssatz {

	private String ID;
	private String name;
	private String sollKonto;
	private String habenKonto;
	private double betrag;
	
	public Buchungssatz(){
		
	}
	
	private String erhoeheVersion(String versionBS){
		
		return versionBS;
		
	}

	public String getID() {
		return ID;
	}

	public void setID_B(String iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSollKonto() {
		return sollKonto;
	}

	public void setSollKonto(String sollKonto) {
		this.sollKonto = sollKonto;
	}

	public String getHabenKonto() {
		return habenKonto;
	}

	public void setHabenKonto(String habenKonto) {
		this.habenKonto = habenKonto;
	}

	public double getBetrag() {
		return betrag;
	}

	public void setBetrag(double betrag) {
		this.betrag = betrag;
	}
	
	
}
