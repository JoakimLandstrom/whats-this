package se.jola.whatsthis.externalapi.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import se.jola.whatsthis.exceptions.ApiException;
import se.jola.whatsthis.externalapi.InfoApi;

public class WikipediaInfoImpl implements InfoApi {

    private URLConnection getConnection(String name) throws IOException {

	StringBuilder builder = new StringBuilder();

	builder.append("https://en.wikipedia.org/w/api.php?format=json&action=query&titles=").append(name)
		.append("&prop=revisions&rvprop=content&callback=?");

	URL url = new URL(builder.toString());

	return url.openConnection();
    }

    @Override
    public String getInfoAboutLocation(String name) throws ApiException {

	try {

	    URLConnection connection = getConnection(name);

	    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

	    StringBuilder builder = new StringBuilder();

	    String line = "";

	    while ((line = reader.readLine()) != null) {
		builder.append(line).append("\n");
		System.out.println(line);
	    }

	    reader.close();

	    return builder.toString();
	} catch (IOException e) {
	    throw new ApiException("Couldnt get wiki-info of: " + name);
	}
    }

}
