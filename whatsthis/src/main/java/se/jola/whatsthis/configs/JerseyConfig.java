package se.jola.whatsthis.configs;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.stereotype.Component;
import se.jola.whatsthis.exceptions.ServiceExceptionMapper;
import se.jola.whatsthis.filter.CORSFilter;
import se.jola.whatsthis.resources.LocationResource;
import se.jola.whatsthis.resources.UserResource;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {

        register(LocationResource.class);
        register(UserResource.class);
        register(ServiceExceptionMapper.class);
        register(CORSFilter.class);
        property(ServletProperties.FILTER_FORWARD_ON_404, true);
    }
}
