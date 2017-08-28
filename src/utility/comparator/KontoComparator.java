package utility.comparator;

import java.util.Comparator;

import konten.Konto;

public class KontoComparator implements Comparator<Konto> {

	@Override
	public int compare(Konto o1, Konto o2) {
		if (o2.getVerrechnungKonto() == o1.getKuerzel()) {
			return 1;
		}
		if (o1.getVerrechnungKonto() == o2.getKuerzel()) {
			return -1;
		}
		
		return 0;
	}

}
