/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import soap_client.Database;
import soap_client.User;

/**
 * REST Web Service
 *
 * @author brb25
 */
@Path("databases")
public class DatabasesResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DatabasesResource
     */
    public DatabasesResource() {
    }

    /**
     * Retrieves representation of an instance of restful.DatabasesResource
     * @return an instance of java.lang.String
     */
    @GET
    //@Consumes(MediaType.APPLICATION_XML) User user
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        return "<r>Okas</r>";
    }

    /**
     * PUT method for updating or creating an instance of DatabasesResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }

    private static java.util.List<Database> getDatabases(int iduser) {
        soap_client.DatabaseConnection_Service service = new soap_client.DatabaseConnection_Service();
        soap_client.DatabaseConnection port = service.getDatabaseConnectionPort();
        return port.getDatabases(iduser);
    }
}
