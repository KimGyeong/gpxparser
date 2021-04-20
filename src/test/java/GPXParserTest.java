import static org.assertj.core.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.xmlpull.v1.XmlPullParserException;

public class GPXParserTest {

	@DisplayName("Check Parsing Success")
	@Test
	void gpxImport_gpxParsing_checkFirstLatLng() throws IOException, XmlPullParserException {
		GPXParser gpxParser = new GPXParser();
		LngLats lngLats = gpxParser.parse("src/test/resources/Alt_Portsmouth.gpx");

		assertThat(lngLats.get(0).getLatitude()).isEqualTo(50.87551);
		assertThat(lngLats.get(0).getLongitude()).isEqualTo(-1.28259);
	}
}