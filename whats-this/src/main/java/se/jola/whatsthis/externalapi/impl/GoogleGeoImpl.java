package se.jola.whatsthis.externalapi.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import se.jola.whatsthis.exceptions.ApiException;
import se.jola.whatsthis.externalapi.GeoApi;

public final class GoogleGeoImpl implements GeoApi {

    private URLConnection getConnection(String lat, String lng) throws IOException {

	StringBuilder builder = new StringBuilder();

	builder.append("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=").append(lat).append(",")
		.append(lng).append("&radius=50&type=point_of_interest&key=").append(getKey());

	URL url = new URL(builder.toString());

	return url.openConnection();
    }

    public List<String> getLocationInfo(String lat, String lng) throws ApiException {

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
	} catch (JSONException e) {
	    throw new ApiException("JSON error");
	}
    }

    private String getKey() throws IOException {

	Properties properties = new Properties();

	InputStream inputStream = getClass().getClassLoader().getResourceAsStream("keys.properties");

	if (inputStream != null) {
	    properties.load(inputStream);
	} else {
	    throw new FileNotFoundException();
	}

	return properties.getProperty("key");
    }

    private List<String> retrieveName(String jsonFile) {

	JSONObject jsonObject = new JSONObject(jsonFile);

	JSONArray jsonArray = jsonObject.getJSONArray("results");

	List<String> nameList = new ArrayList<>();

	for (int i = 0; i < jsonArray.length(); i++) {

	    JSONObject jsObject = (JSONObject) jsonArray.get(i);

	    nameList.add(jsObject.getString("name"));
	}

	return nameList;

    }

}
