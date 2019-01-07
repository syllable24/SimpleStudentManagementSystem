package edu.service;

/**
 *
 * @author newe
 */
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


//Url-Path
@Path("msg")
public class RestMessage {


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage() {
        
        return "{ Message:{ ID:19,"
                + "         Body:'Eine Resourece in JSON Notation'"
                + "}";
    }
}

