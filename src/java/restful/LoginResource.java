/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import soap_client.User;

/**
 * REST Web Service
 *
 * @author brb25
 */
@Path("login")
public class LoginResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of LoginResource
     */
    public LoginResource() {
    }

    /**
     * Retrieves representation of an instance of restful.LoginResource
     * @return an instance of java.lang.String
     */
    @POST
    @Produces("application/xml")
    @Consumes(MediaType.APPLICATION_XML)
    public String postXML(User user,  @Context ServletContext context,
        @Context HttpServletRequest request,
        @Context HttpServletResponse response) {
        //TODO return proper representation object

         
        if (user.getPass().equals(getUserData(user.getName()).getPass())){
         HttpSession session = request.getSession();
         session.setMaxInactiveInterval(20*60);
         session.setAttribute("connected_user", user.getName());
           try {
                String myJsfPage = "index.html";
                context.getRequestDispatcher(myJsfPage).forward(request, response);
            } catch (ServletException | IOException ex) {
                
            }
         return "Ok :) " + getUserData("POLO").getName();

        }
        else{
          return "No " + user.getName() + " " + user.getPass();
        }
    }
    
    @GET
    @Produces("application/xml")
    public String getXML() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of LoginResource
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
