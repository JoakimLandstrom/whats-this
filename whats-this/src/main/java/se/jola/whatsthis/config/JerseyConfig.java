package se.jola.whatsthis.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.stereotype.Component;

import se.jola.whatsthis.exceptions.ServiceExceptionMapper;
import se.jola.whatsthis.resource.LocationResource;

@Component
@ApplicationPath("/rest")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		
		register(LocationResource.class);
		register(ServiceExceptionMapper.class);
		property(ServletProperties.FILTER_FORWARD_ON_404, true);
	}
}
