package cours.ulaval.glo4003.controller.model;

import java.util.HashMap;
import java.util.Map;

public class SortedSlotsCache {

	private static SortedSlotsCache instance;

	private Map<String, SortedSlotsModel> cachedValues = new HashMap<String, SortedSlotsModel>();

	public SortedSlotsModel getCachedValue(String username) {
		return cachedValues.get(username);
	}

	public void setCachedValue(String username, SortedSlotsModel model) {
		cachedValues.put(username, model);
	}

	public static SortedSlotsCache getCache() {
		if (instance == null) {
			instance = new SortedSlotsCache();
		}

		return instance;
	}

	private SortedSlotsCache() {
	}

	// / For tests purpose only
	public int getCacheCount() {
		return cachedValues.values().size();
	}

}
