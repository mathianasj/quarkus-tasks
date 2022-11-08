package dev.cloudfirst;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;

@Path("/tasks")
public class TaskResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<Task>> list() {
        return Task.listAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Task> create(TaskInput taskInput) {
        return Panache.withTransaction(() -> {
            Task task = new Task();
            task.task = taskInput.task;

            return task.persist();
        });
    }
}