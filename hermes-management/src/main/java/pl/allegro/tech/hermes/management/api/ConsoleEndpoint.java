package pl.allegro.tech.hermes.management.api;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import java.net.URI;
import javax.ws.rs.GET;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthStatusHttpMapper;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import pl.allegro.tech.hermes.management.domain.console.ConsoleService;

@Component
@Path("/")
@Api(value = "/", description = "Hermes console")
public class ConsoleEndpoint {

    private ConsoleService service;

    public ConsoleEndpoint(ConsoleService service) {
        this.service = service;
    }

    @GET
    @Path("/health")
    @Produces("application/json")
    @ApiOperation(value = "Provides health check endpoint", httpMethod = HttpMethod.GET)
    public Response healthCheck() {
        HealthStatusHttpMapper mapper = new HealthStatusHttpMapper();
        return Response.status(Response.Status.OK)
            .entity(new Health.Builder().status(Status.UP).build())
        .build();
    }

    @GET
    @ApiOperation(value = "Redirect to Hermes console", httpMethod = HttpMethod.GET)
    public Response redirectToConsole() {
        return Response.status(Response.Status.FOUND)
                .location(URI.create("/ui/index.html"))
                .build();
    }

    @GET
    @Path("/console")
    @Produces("application/javascript")
    @ApiOperation(value = "Hermes console configuration", httpMethod = HttpMethod.GET)
    public String getConfiguration() {
        return service.getConfiguration();
    }
}
