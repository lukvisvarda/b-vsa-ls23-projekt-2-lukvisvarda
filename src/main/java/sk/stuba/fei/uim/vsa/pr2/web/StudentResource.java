package sk.stuba.fei.uim.vsa.pr2.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import sk.stuba.fei.uim.vsa.pr1.ThesisService;
import sk.stuba.fei.uim.vsa.pr1.entities.Student;
import sk.stuba.fei.uim.vsa.pr2.auth.Secured;
import sk.stuba.fei.uim.vsa.pr2.error.ForbiddenException;
import sk.stuba.fei.uim.vsa.pr2.user.Role;
import sk.stuba.fei.uim.vsa.pr2.user.User;
import sk.stuba.fei.uim.vsa.pr2.user.UserService;
import sk.stuba.fei.uim.vsa.pr2.web.response.CreateStudentDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.StudentDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.factory.StudentResponseFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static sk.stuba.fei.uim.vsa.pr2.JAXRSApplicationConfiguration.log;

@Path("/students")
public class StudentResource {

    @Context
    SecurityContext securityContext;
    private final ObjectMapper json = new ObjectMapper();
    private final ThesisService service = new ThesisService();

    private final UserService userService = new UserService();
    private final StudentResponseFactory factory = new StudentResponseFactory();


    @GET
    @Secured({Role.STUDENT, Role.TEACHER})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll()  {
        List<Student> students = service.getStudents();
        List<StudentDto> studentDtos = students.stream().map(factory::transformToDto).collect(Collectors.toList());
        try {
            String json = this.json.writeValueAsString(studentDtos);
            return Response.ok().entity(json).build();
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    @POST

    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(String studentDtoString) {
        try {
            CreateStudentDto studentDto = json.readValue(studentDtoString, CreateStudentDto.class);
            Student created = service.createStudent(studentDto);
            userService.createUser(created);

            String json = this.json.writeValueAsString(factory.transformToDto(created));
            return Response.status(Response.Status.CREATED).entity(json).build();

        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    @GET
    @Secured({Role.STUDENT, Role.TEACHER})
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id) {
        try {
            Student student = service.getStudent(id);
            // ak je student nulovy chod do catchu s NotFoundException
            if (student == null) {
                throw new NotFoundException();
            }
            String json = this.json.writeValueAsString(factory.transformToDto(student));
            return Response.ok().entity(json).build();
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    @DELETE
    @Path("/{id}")
    @Secured({Role.TEACHER, Role.STUDENT})
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("id") Long id) {
        //get user which is logged in
        Optional<User> user = userService.getUserByUsername(securityContext.getUserPrincipal().getName());
        //if user is teacher continue
        if(user.isPresent() && user.get().getRole().contains(Role.TEACHER)){
            log.info("Teacher is deleting student");
            throw new ForbiddenException();
        }
        //if user is student, he has to have same id as it is deleting
        else if(user.isPresent() && user.get().getRole().contains(Role.STUDENT) && !user.get().getId().equals(id)){
            log.info("Student is trying to delete other student");
            throw new ForbiddenException();
        }
        try {
            Student student = service.deleteStudent(id);
            // ak je student nulovy chod do catchu s NotFoundException
            if (student == null) {
                throw new NotFoundException();
            }
            String json = this.json.writeValueAsString(factory.transformToDto(student));
            return Response.ok().entity(json).build();
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }
}
