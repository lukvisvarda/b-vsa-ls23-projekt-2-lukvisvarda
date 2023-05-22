package sk.stuba.fei.uim.vsa.pr2.auth;

import sk.stuba.fei.uim.vsa.pr2.user.Role;

import javax.ws.rs.NameBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@NameBinding
@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface Secured {
    Role[] value() default {}; // Option to authorization / permission / roles check
}
