package se.jola.whatsthis.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import se.jola.whatsthis.exceptions.ServiceExceptionMapper;
import se.jola.whatsthis.resource.LocationResource;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig(){
	register(LocationResource.class);
	register(ServiceExceptionMapper.class);
    }
    
}
