package sk.stuba.fei.uim.vsa.pr2.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import sk.stuba.fei.uim.vsa.pr1.ThesisService;
import sk.stuba.fei.uim.vsa.pr1.entities.Assignment;
import sk.stuba.fei.uim.vsa.pr1.entities.Student;
import sk.stuba.fei.uim.vsa.pr1.entities.Teacher;
import sk.stuba.fei.uim.vsa.pr2.auth.Secured;
import sk.stuba.fei.uim.vsa.pr2.user.Role;
import sk.stuba.fei.uim.vsa.pr2.web.response.ThesisDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.factory.ThesisResponseFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;
import java.util.stream.Collectors;

@Path("/search/theses")
public class SearchThesesResource {
    @Context
    SecurityContext securityContext;
    private final ObjectMapper json = new ObjectMapper();
    private final ThesisService service = new ThesisService();
    private final ThesisResponseFactory factory = new ThesisResponseFactory();

    @POST
    @Secured({Role.TEACHER, Role.STUDENT})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchTheses(String idRequest) {
        try {
            SearchThesesRequest request = json.readValue(idRequest, SearchThesesRequest.class);
            if (request.getStudentId() != null && request.getTeacherId() != null) {
                throw new BadRequestException("Invalid request body.");
            } else if (request.getStudentId() != null) {
                Student student = service.getStudent(request.getStudentId());
                if(student == null) {
                    throw new NotFoundException("Student not found.");
                }
                Assignment theses = service.getThesisByStudent(request.getStudentId());
                String json = this.json.writeValueAsString(factory.transformToDto(theses));
                return Response.ok().entity(json).build();
            } else if (request.getTeacherId() != null) {
                Teacher teacher = service.getTeacher(request.getTeacherId());
                if(teacher == null) {
                    throw new NotFoundException("Teacher not found.");
                }
                List<Assignment> theses = service.getThesesByTeacher(request.getTeacherId());
                List<ThesisDto> thesisDtos = theses.stream().map(factory::transformToDto).collect(Collectors.toList());
                String json = this.json.writeValueAsString(thesisDtos);
                return Response.ok().entity(json).build();
            } else {
                throw new BadRequestException("Invalid request body.");
            }
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }
}
