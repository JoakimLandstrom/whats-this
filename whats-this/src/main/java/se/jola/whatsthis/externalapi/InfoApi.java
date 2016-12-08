package se.jola.whatsthis.externalapi;

import se.jola.whatsthis.exceptions.ApiException;

public interface InfoApi {
	
	String getInfoAboutLocation(String name) throws ApiException;

}
