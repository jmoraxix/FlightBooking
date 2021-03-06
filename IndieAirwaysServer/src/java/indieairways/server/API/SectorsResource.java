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

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author emilioevans
 */
@Path("sectors")
public class SectorsResource extends ServerAPIResource {

    /**
     * Creates a new instance of SectorsResource
     */
    public SectorsResource() {
    }

    /**
     * Retrieves representation of an instance of
     * indieairways.API.SectorsResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response getJson(@QueryParam("a") String airplaneName) {
        //TODO Fix get airplanes

//        for (Airplanes i : Util.USER_LIST) {
//            if (i.getEmail().equals(username)) {
//                return Response.ok(new Gson().toJson(i)).build();
//            }
//        }
        return Response.status(404).build();
    }

    /**
     * PUT method for updating or creating an instance of SectorsResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Override
    public Response putJson(String content) {
        return Response.status(405).build();
    }

    /**
     * POST method for updating or creating an instance of SectorsResource
     *
     * @return 405 Response
     */
    @POST
    @Override
    public Response postJson(String content) {
        return Response.status(405).build();
    }

    /**
     * DELETE method for deleting an instance of SectorsResource
     *
     * @return 405 Response
     */
    @DELETE
    @Override
    public Response deleteJson(String content) {
        return Response.status(405).build();
    }

}
