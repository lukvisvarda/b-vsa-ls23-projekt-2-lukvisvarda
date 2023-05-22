package sk.stuba.fei.uim.vsa.pr2.error;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.grizzly.http.util.HttpStatus;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Arrays;

@Provider
public class MyExceptionMapper implements ExceptionMapper<WebApplicationException>{

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Response toResponse(WebApplicationException exception) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setCode(exception.getResponse().getStatus());
        errorMessage.setMessage(exception.getMessage());
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setType(exception.getClass().getName());
        errorDetail.setTrace(Arrays.toString(exception.getStackTrace()));
        errorMessage.setError(errorDetail);
        String json = null;
        try {
            json = objectMapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            json = "{}";
        }
        return Response.status(exception.getResponse().getStatus()).entity(json).type(MediaType.APPLICATION_JSON).build();
    }
}
