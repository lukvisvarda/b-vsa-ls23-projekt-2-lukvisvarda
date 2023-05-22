package sk.stuba.fei.uim.vsa.pr2.auth;

import sk.stuba.fei.uim.vsa.pr2.user.User;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class MySecurityContext implements SecurityContext {

    private User user;
    private boolean secure;

    public MySecurityContext(User user) {
        this.user = user;
    }

    public void setSecure(boolean secure) {
        this.secure = secure;
    }

    @Override
    public Principal getUserPrincipal() {
        return this.user;
    }

    @Override
    public boolean isUserInRole(String s) {
        return true;
    }

    @Override
    public boolean isSecure() {
        return this.secure;
    }

    @Override
    public String getAuthenticationScheme() {
        return "Basic";
    }
}
