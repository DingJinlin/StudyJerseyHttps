package com.dingjinlin.jersey.service;

import com.dingjinlin.jersey.bean.Student;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by DingJinlin on 2016/11/25 21:58.
 * Study
 */

@Path("/bulkload")
public class BulkLoadAPI {
    @POST
    @Path("inference")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Student inferFile() throws URISyntaxException, IOException {
        Student s = new Student();
        return s;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Student getStudent() {
        Student student = new Student("name", 12);
        return student;
    }

    public static void main(String[] args) throws Exception {
        ServletHolder jerseyServlet = new ServletHolder(ServletContainer.class);
        jerseyServlet.setInitParameter("jersey.config.server.provider.classnames", "com.dingjinlin.jersey.service.BulkLoadAPI");
        jerseyServlet.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");

        Server server = new Server(10500);
        ServletContextHandler context = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
        context.addServlet(jerseyServlet, "/*");
        server.start();
        server.join();
    }
}
