package sk.stuba.fei.uim.vsa.pr2.auth;

import lombok.extern.slf4j.Slf4j;
import sk.stuba.fei.uim.vsa.pr2.error.ForbiddenException;
import sk.stuba.fei.uim.vsa.pr2.user.Role;
import sk.stuba.fei.uim.vsa.pr2.user.User;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Secured
@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;
    @Override
    public void filter(ContainerRequestContext request) throws IOException {
        User user = (User) request.getSecurityContext().getUserPrincipal();

        Method resourceMethod = resourceInfo.getResourceMethod();
        Set<Role> permissions = extractPermissionsFromMethod(resourceMethod);

        if(user.getRole().stream().noneMatch(permissions::contains)) {
//            request.abortWith(Response.status(Response.Status.FORBIDDEN).build());
            throw new ForbiddenException();
//            return;
        }
    }

    private Set<Role> extractPermissionsFromMethod(Method method) {
        if(method == null) {
            return new HashSet<>();
        }
        Secured secured = method.getAnnotation(Secured.class);
        if(secured == null) {
            return new HashSet<>();
        }
        return new HashSet<>(Arrays.asList(secured.value()));
    }
}
