package sk.stuba.fei.uim.vsa.pr2.auth;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import sk.stuba.fei.uim.vsa.pr2.BCryptService;
import sk.stuba.fei.uim.vsa.pr2.error.UnauthorizedException;
import sk.stuba.fei.uim.vsa.pr2.user.User;
import sk.stuba.fei.uim.vsa.pr2.user.UserService;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

@Slf4j
@Secured
@Priority(Priorities.AUTHENTICATION)
@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext request) {
        String authHeader = request.getHeaderString(HttpHeaders.AUTHORIZATION);
        if(authHeader == null || !authHeader.contains("Basic")){
            log.info("No auth header found");
            reject(request);
            return;
        }
        String[] credentials = extractFromAuthHeader(authHeader);
        log.info("Received credentials: " + credentials[0] + " " + credentials[1]);

        UserService userService = UserService.getInstance();
        Optional<User> userOptional = userService.getUserByUsername(credentials[0]);
        if(!userOptional.isPresent() || !BCryptService.verify(credentials[1], userOptional.get().getPassword())){
            log.info("User not found or password is incorrect");
            reject(request);
            return;
        }
        final SecurityContext securityContext = request.getSecurityContext();
        MySecurityContext context = new MySecurityContext(userOptional.get());
        context.setSecure(securityContext.isSecure());
        request.setSecurityContext(context);
    }

    private void reject(ContainerRequestContext request){
        throw new UnauthorizedException();
//        request.abortWith(Response.status(Response.Status.UNAUTHORIZED).header(HttpHeaders.WWW_AUTHENTICATE, "Basic realm=\"VSA\"").build());
    }

    private String[] extractFromAuthHeader(String authHeader){
        return new String(Base64.getDecoder().decode(authHeader.replace("Basic", "").trim())).split(":");
    }
}
