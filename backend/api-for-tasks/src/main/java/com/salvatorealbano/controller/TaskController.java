package com.salvatorealbano.controller;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.salvatorealbano.model.Task;

@Path("/task")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class TaskController {
	@GET
    @Path("{id}")
    public Task findEmployeeById(@PathParam("id") Long id) {
        return (Task) Task.findByIdOptional(id).orElseThrow(NotFoundException::new);
    }

    @GET
    public List<Task> getAllEmployee() {
        return Task.listAll();
    }

    @POST
    @Transactional
    public Task addEmployee(Task employee) {
        Task.persist(employee);
               
        return employee;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Task updateTaskReminder(@PathParam("id") Long id, Task task) {
        Optional<Task> employeeById = Optional.ofNullable(findEmployeeById(id));
        employeeById.orElseThrow(NotFoundException::new);
        Task empToUpdate = employeeById.get();
        empToUpdate.setReminder(task.getReminder());
        Task.getEntityManager().merge(empToUpdate);
        return task;
    }


    @DELETE
    @Path("/{id}")
    @Transactional
    public void deleteTask(@PathParam("id") Long id) {
        Task.deleteById(id);
    }

}
