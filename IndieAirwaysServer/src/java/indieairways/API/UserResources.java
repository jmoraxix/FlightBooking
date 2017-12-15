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
package indieairways.API;

import com.google.gson.Gson;
import indieairways.model.Usuario;
import indieairways.util.Util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author jmora
 */
@Path("user")
public class UserResources {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UserResource
     */
    public UserResources() {
    }

    /**
     * Retrieves representation of an instance of API.UserResources
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson(@QueryParam("e") String email, @QueryParam("p") String password) {

        // Generating MD5 password
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserResources.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Usuario i : Util.LISTA_USURIOS) {
            if (i.getEmail().equals(email)) {
                if (i.getPassword().equals(md.digest())) {
                    return Response.ok(new Gson().toJson(i)).build();
                }
            }
        }

        return Response.status(401).build();
    }

    /**
     * PUT method for updating users
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putJson(String content) {

        Usuario usuario = new Gson().fromJson(content, Usuario.class);

        for (Usuario u : Util.LISTA_USURIOS) {
            if (u.getUser().equals(u.getUser())) {
                u = usuario;
                return Response.ok().build();
            }
        }

        return Response.status(404).build();
    }

    /**
     * POST method for creating users
     *
     * @param content representation for the resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postJson(String content) {

        Usuario usuario = new Gson().fromJson(content, Usuario.class);

        if (usuario.getUser().isEmpty()) {
            return Response.status(400).build();
        }

        if (usuario.getEmail().isEmpty()) {
            return Response.status(400).build();
        }

        if (usuario.getPassword().isEmpty()) {
            return Response.status(400).build();
        }

        if (usuario.getName().isEmpty()) {
            return Response.status(400).build();
        }

        Util.LISTA_USURIOS.add(usuario);

        return Response.ok().build();
    }
}
