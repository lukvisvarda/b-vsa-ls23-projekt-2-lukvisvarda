package sk.stuba.fei.uim.vsa.pr2.error;

public class ForbiddenException extends MyException{
    public ForbiddenException() {
        super("Forbidden", 403);
    }
}
