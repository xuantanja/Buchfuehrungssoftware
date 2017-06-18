package geschaeftsfall;

public class Buchungssatz {

	private String ID;
	private String name;
	private String sollKonto;
	private String habenKonto;
	private double betrag;
	
	public Buchungssatz(String name, String sollKonto, String habenKonto, double betrag){
		this.name = name;
		this.sollKonto = sollKonto;
		this.habenKonto = habenKonto;
		this.betrag = betrag;
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
