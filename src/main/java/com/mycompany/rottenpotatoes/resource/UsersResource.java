package com.mycompany.rottenpotatoes.resource;

import java.net.ConnectException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.mycompany.rottenpotatoes.model.User;
import com.mycompany.rottenpotatoes.service.UserService;

@Path("/users")
public class UsersResource {

    @GET
    @Produces("application/json")
    public Response getUserToken(@QueryParam("name") String name, @QueryParam("password") String password) {
        Status status;
        Object entity;
        try {
            if(name == null && password == null) {
                entity = "Missing name and password parameters.";
                status = Status.BAD_REQUEST;
            } else if(name == null) {
                entity = "Missing name parameter.";
                status = Status.BAD_REQUEST;
            } else if(password == null) {
                entity = "Missing password parameter.";
                status = Status.BAD_REQUEST;
            } else {
                User user = new User();
                user.setName(name);
                user.setPassword(password);
                Integer token = UserService.getInstance().getUserToken(user);
                if(token != null){
                    entity = token;
                    status = Status.OK;
                } else {
                    entity = "Failed to authenticate user";
                    status = Status.NOT_ACCEPTABLE;
                }
            }
        } catch (ConnectException e) {
            entity = "Servers under maintenance. Please try again later.";
            status = Status.SERVICE_UNAVAILABLE;
        } catch (Exception e) {
            System.out.println(e);
            entity = "An unknown error ocurred.";
            status = Status.INTERNAL_SERVER_ERROR;
        }
        return Response.status(status).entity(entity).build();
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response registerUser(User user) {
        Status status;
        Object entity;
        try {
            if(user == null) {
                System.err.println("Attempting to register a user but the server wasn\'t given user parameter.");
                entity = "Missing account parameter.";
                status = Status.BAD_REQUEST;
            } else {
                entity = UserService.getInstance().registerUser(user);
                status = Status.CREATED;
            }
        } catch (ConnectException e) {
            entity = "Servers under maintenance. Please try again later.";
            status = Status.SERVICE_UNAVAILABLE;
        } catch (Exception e) {
            entity = "An unknown error ocurred.";
            status = Status.INTERNAL_SERVER_ERROR;
        }
        return Response.status(status).entity(entity).build();
    }
}