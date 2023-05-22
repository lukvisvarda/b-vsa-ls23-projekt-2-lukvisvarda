package sk.stuba.fei.uim.vsa.pr2.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import sk.stuba.fei.uim.vsa.pr1.ThesisService;
import sk.stuba.fei.uim.vsa.pr1.entities.Teacher;
import sk.stuba.fei.uim.vsa.pr2.auth.Secured;
import sk.stuba.fei.uim.vsa.pr2.error.ForbiddenException;
import sk.stuba.fei.uim.vsa.pr2.user.Role;
import sk.stuba.fei.uim.vsa.pr2.user.User;
import sk.stuba.fei.uim.vsa.pr2.user.UserService;;
import sk.stuba.fei.uim.vsa.pr2.web.response.CreateTeacherDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.TeacherDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.factory.TeacherResponseFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static sk.stuba.fei.uim.vsa.pr2.JAXRSApplicationConfiguration.log;

@Path("/teachers")
public class TeacherResource {

    @Context
    SecurityContext securityContext;
    private final ObjectMapper json = new ObjectMapper();
    private final ThesisService service = new ThesisService();

    private final UserService userService = new UserService();
    private final TeacherResponseFactory factory = new TeacherResponseFactory();


    @GET
    @Secured({Role.STUDENT, Role.TEACHER})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() throws NotFoundException, InternalError {
        List<Teacher> teachers = service.getTeachers();
        List<TeacherDto> teacherDtos = teachers.stream().map(factory::transformToDto).collect(Collectors.toList());
        try {
            String json = this.json.writeValueAsString(teacherDtos);
            return Response.ok().entity(json).build();
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(String teacherDtoString) {
        try {
            CreateTeacherDto teacherDto = json.readValue(teacherDtoString, CreateTeacherDto.class);
            Teacher created = service.createTeacher(teacherDto);
            userService.createUser(created);
            String json = this.json.writeValueAsString(factory.transformToDto(created));
            return Response.status(Response.Status.CREATED).entity(json).build();

        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    @GET
    @Path("/{id}")
    @Secured({Role.STUDENT, Role.TEACHER})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id) throws NotFoundException {
        try {
            Teacher teacher = service.getTeacher(id);
            // ak je student nulovy chod do catchu s NotFoundException
            if (teacher == null) {
                throw new NotFoundException();
            }
            String json = this.json.writeValueAsString(factory.transformToDto(teacher));
            return Response.ok().entity(json).build();
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    @DELETE
    @Secured({Role.TEACHER})
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("id") Long id) throws NotFoundException {
        Optional<User> user = userService.getUserByUsername(securityContext.getUserPrincipal().getName());
        if(user.isPresent() && user.get().getRole().contains(Role.TEACHER) && !user.get().getId().equals(id)){
            log.info("Teacher is trying to delete other teacher");
            throw new ForbiddenException();
        }
        if(user.isPresent() && user.get().getRole().contains(Role.STUDENT)){
            log.info("Student is trying to delete teacher");
            throw new ForbiddenException();
        }
        try {
            Teacher teacher = service.deleteTeacher(id);
            // ak je student nulovy chod do catchu s NotFoundException
            if (teacher == null) {
                throw new NotFoundException();
            }
            String json = this.json.writeValueAsString(factory.transformToDto(teacher));
            return Response.ok().entity(json).build();
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }
}
