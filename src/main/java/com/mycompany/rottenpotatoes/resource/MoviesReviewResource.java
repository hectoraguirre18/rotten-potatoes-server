package com.mycompany.rottenpotatoes.resource;

import java.net.ConnectException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.mycompany.rottenpotatoes.model.MovieReview;
import com.mycompany.rottenpotatoes.service.MovieReviewService;

@Path("/reviews/movies")
public class MoviesReviewResource {

    @GET
    @Path("/{movieId}")
    @Produces("application/json")
    public Response getReviewsForMovie(@PathParam("movieId") int movieId) {
        Status status;
        Object entity;
        try {
            entity = MovieReviewService.getInstance().getReviewsForMovie(movieId);
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
    public Response createMovieReview(MovieReview review) {
        Status status;
        Object entity;
        try {
            if(review == null) {
                System.err.println("Attempting to upload a review but user didn\'t add review parameter.");
                entity = "Missing account parameter.";
                status = Status.BAD_REQUEST;
            } else {
                entity = MovieReviewService.getInstance().saveReview(review);
                status = Status.CREATED;
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
}