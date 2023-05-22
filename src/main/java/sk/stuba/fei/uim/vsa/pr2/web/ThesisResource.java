package sk.stuba.fei.uim.vsa.pr2.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import sk.stuba.fei.uim.vsa.pr1.ThesisService;
import sk.stuba.fei.uim.vsa.pr1.entities.Assignment;
import sk.stuba.fei.uim.vsa.pr1.entities.Student;
import sk.stuba.fei.uim.vsa.pr2.auth.Secured;
import sk.stuba.fei.uim.vsa.pr2.error.ForbiddenException;
import sk.stuba.fei.uim.vsa.pr2.user.Role;
import sk.stuba.fei.uim.vsa.pr2.user.User;
import sk.stuba.fei.uim.vsa.pr2.user.UserService;
import sk.stuba.fei.uim.vsa.pr2.web.response.CreateThesisDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.ThesisDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.factory.TeacherResponseFactory;
import sk.stuba.fei.uim.vsa.pr2.web.response.factory.ThesisResponseFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static sk.stuba.fei.uim.vsa.pr2.JAXRSApplicationConfiguration.log;

@Path("/theses")
public class ThesisResource {

    @Context
    SecurityContext securityContext;
    private final ObjectMapper json = new ObjectMapper();
    private final ThesisService service = new ThesisService();

    private final UserService userService = new UserService();

    private final ThesisResponseFactory factory = new ThesisResponseFactory();

    @GET
    @Secured({Role.TEACHER, Role.STUDENT})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() throws NotFoundException, InternalError {
            List<Assignment> theses = service.getTheses();
            List<ThesisDto> thesisDtos = theses.stream().map(factory::transformToDto).collect(Collectors.toList());
        try {
            String json = this.json.writeValueAsString(thesisDtos);
            return Response.ok().entity(json).build();
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    //TODO: add teacher which is creating the thesis
    @POST
    @Secured({Role.TEACHER})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(String thesisDtoString) throws InternalError{
        try {
            CreateThesisDto thesisDto = json.readValue(thesisDtoString, CreateThesisDto.class);
            User user = userService.getUserByUsername(securityContext.getUserPrincipal().getName()).orElseThrow(InternalError::new);
            Assignment created = service.makeThesisAssignment(thesisDto, user.getId());
            //add thesis ids to created theacherAlt from teacher supervised theses
            String json = this.json.writeValueAsString(factory.transformToDto(created));
            return Response.status(Response.Status.CREATED).entity(json).build();
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    @GET
    @Secured({Role.TEACHER, Role.STUDENT})
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id) throws NotFoundException {
        try {
            Assignment thesis = service.getThesis(id);
            String json = this.json.writeValueAsString(factory.transformToDto(thesis));
            return Response.ok().entity(json).build();
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    @DELETE
    @Path("/{id}")
    @Secured({Role.TEACHER})
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("id") Long id) {
        try {
            Optional<User> user = userService.getUserByUsername(securityContext.getUserPrincipal().getName());
            if(user.isPresent() && user.get().getRole().contains(Role.TEACHER) && !user.get().getId().equals(id)){
                log.info("Teacher is trying to delete thesis which is not his own.");
                throw new ForbiddenException();
            }
            Assignment thesis = service.deleteThesis(id);
            String json = this.json.writeValueAsString(factory.transformToDto(thesis));
            return Response.ok().entity(json).build();
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    //TODO: UNCOMMENT AND COMPLETE THE METHOD
    @POST
    @Secured({Role.TEACHER, Role.STUDENT})
    @Path("/{id}/assign")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response assign(@PathParam("id") Long id, String studentIdRequestString) throws NotFoundException {
        try {
            StudentIdRequest studentIdRequest = json.readValue(studentIdRequestString, StudentIdRequest.class);
            //if student is trying to assign thesis to himself assign it
            Optional<User> user = userService.getUserByUsername(securityContext.getUserPrincipal().getName());
            if(user.isPresent() && user.get().getRole().contains(Role.STUDENT)){
                Assignment thesis = service.assignThesis(id, user.get().getId());
                String json = this.json.writeValueAsString(factory.transformToDto(thesis));
                return Response.ok().entity(json).build();
            }
            Student student = service.getStudent(studentIdRequest.getStudentId());
            if(student == null){
                throw new NotFoundException();
            }
            Assignment thesis = service.assignThesis(id, studentIdRequest.getStudentId());
            String json = this.json.writeValueAsString(factory.transformToDto(thesis));
            return Response.ok().entity(json).build();
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    //TODO: UNCOMMENT AND COMPLETE THE METHOD
    @POST
    @Secured({Role.TEACHER, Role.STUDENT})
    @Path("/{id}/submit")
    @Produces(MediaType.APPLICATION_JSON)
    public Response submit(@PathParam("id") Long id, String studentIdRequestString) throws NotFoundException {
        try {
            StudentIdRequest studentIdRequest = json.readValue(studentIdRequestString, StudentIdRequest.class);
            //if student is trying to submit thesis to himself submit it
            Optional<User> user = userService.getUserByUsername(securityContext.getUserPrincipal().getName());
            Student student = service.getStudent(studentIdRequest.getStudentId());
            if(student == null){
                throw new NotFoundException();
            }
            Assignment thesis = service.getThesis(id);
            if(user.isPresent() && user.get().getRole().contains(Role.STUDENT) && !user.get().getId().equals(thesis.getStudent().getAisId())) {
                log.info("Student is trying to submit thesis which is not his own.");
                throw new ForbiddenException();
            } else if(user.isPresent() && user.get().getRole().contains(Role.TEACHER) && !Objects.equals(studentIdRequest.getStudentId(), thesis.getStudent().getAisId())) {
                log.info("Teacher is trying to submit thesis which is not owned by student with that ID.");
                throw new ForbiddenException();
            } else {
                thesis = service.submitThesis(id);
                String json = this.json.writeValueAsString(factory.transformToDto(thesis));
                return Response.ok().entity(json).build();
            }
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }
}
