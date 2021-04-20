import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class GPXParser {
	private static final String LATITUDE = "lat";
	private static final String TRACK_POINT_NAME = "trkpt";
	private static final String LONGITUDE = "lon";
	private static final String UTF_8 = "UTF-8";

	public LngLats parse(String location) throws XmlPullParserException, IOException {
		LngLats lngLats = new LngLats();
		String fileContent = getFileContent(new FileInputStream(location), UTF_8);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(fileContent.getBytes());
		XmlPullParser gpxParser = getXmlPullParser(inputStream);

		while (gpxParserIsEnd(gpxParser)) {
			if (TRACK_POINT_NAME.equals(gpxParser.getName()) &&
				gpxParser.getEventType() == XmlPullParser.START_TAG) {
				Double latitude = getDoubleAttributeValue(gpxParser, LATITUDE);
				Double longitude = getDoubleAttributeValue(gpxParser, LONGITUDE);

				lngLats.addLngLatWhenValuesAreNormal(latitude, longitude);
			}
		}
		inputStream.close();

		return lngLats;
	}

	private String getFileContent(FileInputStream fis, String encoding) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(fis, encoding))) {
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append('\n');
			}
			return sb.toString();
		}
	}

	private XmlPullParser getXmlPullParser(ByteArrayInputStream inputStream) throws XmlPullParserException {
		XmlPullParser gpxParser = XmlPullParserFactory.newInstance().newPullParser();
		gpxParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
		gpxParser.setInput(inputStream, null);

		return gpxParser;
	}

	private boolean gpxParserIsEnd(XmlPullParser gpxParser) throws XmlPullParserException, IOException {
		return gpxParser.next() != XmlPullParser.END_DOCUMENT;
	}

	private Double getDoubleAttributeValue(XmlPullParser gpxParser, String latitude) {
		return Double.valueOf(gpxParser.getAttributeValue(null, latitude));
	}
}
