/*
 * Programacion Concurrente Cliente Servidor
 * 
 * Emilio Evans Rodriguez
 * Jose David Mora Loria
 * Carlos Oreamuno Alfaro
 * 
 * Tercer cuatrimestre, 2017
 * Ulacit
 */
package indieairways.server.API;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author jmora
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(indieairways.server.API.AirplanesResource.class);
        resources.add(indieairways.server.API.AirportsResource.class);
        resources.add(indieairways.server.API.FlightsResource.class);
        resources.add(indieairways.server.API.LoginResource.class);
        resources.add(indieairways.server.API.ReservationsResource.class);
        resources.add(indieairways.server.API.SectorsResource.class);
        resources.add(indieairways.server.API.UsersResource.class);
    }
    
}
