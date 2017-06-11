
public class Buchungssatz {

	private String ID_B;
	private String name;
	private String sollKonto;
	private String habenKonto;
	private double betrag;
	private String versionBS;
	
	public Buchungssatz(long ID_G){
		this.ID_B = ID_G + versionBS;
		
	}
	
	private String erhoeheVersion(String versionBS){
		
		return versionBS;
		
	}
}
