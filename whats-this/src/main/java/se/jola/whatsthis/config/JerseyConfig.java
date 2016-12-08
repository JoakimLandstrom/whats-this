package se.jola.whatsthis.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import se.jola.whatsthis.resource.PoiResource;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig(){
	register(PoiResource.class);
    }
    
}
