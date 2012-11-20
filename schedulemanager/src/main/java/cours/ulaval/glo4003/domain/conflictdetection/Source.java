package cours.ulaval.glo4003.domain.conflictdetection;

public abstract class Source {
	protected Filter firstFilter;

	public void connectToFilter(Filter filter) {
		firstFilter = filter;
	}
}
