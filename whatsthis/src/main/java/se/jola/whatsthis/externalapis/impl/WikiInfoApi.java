package se.jola.whatsthis.externalapis.impl;

import org.springframework.stereotype.Component;
import se.jola.whatsthis.exceptions.ApiException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

@Component
public final class WikiInfoApi {

    private URLConnection getConnection(String name) throws IOException {

        StringBuilder builder = new StringBuilder();

        builder.append("https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts|info&exintro=&inprop=url&explaintext=&titles=").append(URLEncoder.encode(name, "UTF-8"));

        URL url = new URL(builder.toString());

        return url.openConnection();
    }

    public String getInfoAboutLocation(String name) throws ApiException {

        try {

            URLConnection connection = getConnection(name);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder builder = new StringBuilder();

            String line = "";

            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }

            reader.close();

            return builder.toString();
        } catch (IOException e) {
            throw new ApiException("Couldnt get wiki-info of: " + name);
        }
    }

}
