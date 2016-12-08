package se.jola.whatsthis.externalapi.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import se.jola.whatsthis.exceptions.ApiException;
import se.jola.whatsthis.externalapi.GeoApi;

@Component
public final class GoogleGeoImpl implements GeoApi {

    private URLConnection getConnection(String lat, String lng) throws IOException {

	StringBuilder builder = new StringBuilder();

	builder.append("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=").append(lat).append(",")
		.append(lng).append("&radius=50&type=point_of_interest&key=").append(getKey());

	URL url = new URL(builder.toString());

	return url.openConnection();
    }

    public JSONArray getLocationInfo(String lat, String lng) throws ApiException {

	try {
	    URLConnection connection = getConnection(lat, lng);

	    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

	    StringBuilder builder = new StringBuilder();

	    String line = "";

	    while ((line = reader.readLine()) != null) {
		builder.append(line).append("\n");
	    }

	    reader.close();

	    return retrieveName(builder.toString());

	} catch (IOException e) {
	    e.printStackTrace();
	    throw new ApiException("Couldnt get info from lat:" + lat + "and lng:" + lng + " from google");
	}
    }

    private String getKey() throws IOException {

	Properties properties = new Properties();

	InputStream inputStream = new FileInputStream("src/main/resources/keys.properties");

	properties.load(inputStream);

	String property = properties.getProperty("key");

	return property;
    }

    private JSONArray retrieveName(String jsonFile) {

	JSONObject jsonObject = new JSONObject(jsonFile);

	JSONArray jsonArray = jsonObject.getJSONArray("results");

	List<String> nameList = new ArrayList<>();

	for (int i = 0; i < jsonArray.length(); i++) {

	    JSONObject jsObject = (JSONObject) jsonArray.get(i);

	    nameList.add(jsObject.getString("name"));
	}

	return new JSONArray(nameList);

    }

}
