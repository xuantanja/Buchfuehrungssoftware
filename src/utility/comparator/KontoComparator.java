package utility.comparator;

import java.util.Comparator;

import konten.Konto;

public class KontoComparator implements Comparator<Konto> {

	@Override
	public int compare(Konto o1, Konto o2) {
		o1.getVerrechnungKonto();
		o1.getKuerzel();
		o2.getVerrechnungKonto();
		o2.getKuerzel();
		if (o2.getVerrechnungKonto() == o1.getKuerzel() || o1.getKontoart() == 4) {
			return 1;
		}
		if (o1.getVerrechnungKonto() == o2.getKuerzel() || o2.getKontoart() == 4) {
			return -1;
		}
		
		return 0;
	}

}
