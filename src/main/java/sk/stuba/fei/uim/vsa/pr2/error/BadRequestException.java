package sk.stuba.fei.uim.vsa.pr2.error;

public class BadRequestException extends MyException{
    public BadRequestException(String message) {
        super(message, 400);
    }
}
