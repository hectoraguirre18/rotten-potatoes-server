package com.mycompany.rottenpotatoes.resource;

import java.net.ConnectException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.mycompany.rottenpotatoes.model.Movie;
import com.mycompany.rottenpotatoes.service.MovieService;

@Path("/movies")
public class MoviesResource {

    @GET
    @Produces("application/json")
    public Response getAllMovies() {
        Status status;
        Object entity;
        try {
            entity = MovieService.getInstance().getAllMovies();
            status = Status.OK;
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
    public Response createMovie(Movie movie) {
        Status status;
        Object entity;
        try {
            if(movie == null) {
                System.err.println("Attempting to insert a movie but user didn\'t add movie parameter.");
                entity = "Missing account parameter.";
                status = Status.BAD_REQUEST;
            } else {
                entity = MovieService.getInstance().saveMovie(movie);
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