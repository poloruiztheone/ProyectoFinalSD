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
import soap_client.User;

/**
 * REST Web Service
 *
 * @author brb25
 */
@Path("users")
public class SOAPUsers {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SOAPUsers
     */
    public SOAPUsers() {
    }

    /**
     * Retrieves representation of an instance of restful.SOAPUsers
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        //TODO return proper representation object
       return "Ok :) " + getUserData("POLO").getName();
    }

    /**
     * PUT method for updating or creating an instance of SOAPUsers
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }

    private static User getUserData(java.lang.String username) {
        soap_client.DatabaseConnection_Service service = new soap_client.DatabaseConnection_Service();
        soap_client.DatabaseConnection port = service.getDatabaseConnectionPort();
        return port.getUserData(username);
    }
}
