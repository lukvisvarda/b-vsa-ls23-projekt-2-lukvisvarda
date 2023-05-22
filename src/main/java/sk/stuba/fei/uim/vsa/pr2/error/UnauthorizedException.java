package sk.stuba.fei.uim.vsa.pr2.error;

import org.glassfish.grizzly.http.util.HttpStatus;

public class UnauthorizedException extends MyException{

    public UnauthorizedException() {
        super("Unauthorized", 401);
    }
}
