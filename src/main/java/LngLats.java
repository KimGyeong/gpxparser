import java.util.ArrayList;
import java.util.List;

public class LngLats {
	private static final double MAXIMUM_LONGITUDE_VALUE = 180.0;
	private static final double MINIMUM_LONGITUDE_VALUE = -180.0;
	private static final double MAXIMUM_LATITUDE_VALUE = 90.0;
	private static final double MINIMUM_LATITUDE_VALUE = -90.0;

	private List<LngLat> lngLats;

	public LngLats() {
		lngLats = new ArrayList<>();
	}

	public LngLats(List<LngLat> lngLats) {
		this.lngLats = lngLats;
	}

	public List<LngLat> getLngLats() {
		return lngLats;
	}

	public LngLat get(int index) {
		return lngLats.get(index);
	}

	public void addLngLatWhenValuesAreNormal(Double latitude, Double longitude) {
		if (MINIMUM_LATITUDE_VALUE <= latitude && latitude <= MAXIMUM_LATITUDE_VALUE &&
			MINIMUM_LONGITUDE_VALUE <= longitude && longitude <= MAXIMUM_LONGITUDE_VALUE) {
			lngLats.add(new LngLat(longitude, latitude));
		}
	}
}
