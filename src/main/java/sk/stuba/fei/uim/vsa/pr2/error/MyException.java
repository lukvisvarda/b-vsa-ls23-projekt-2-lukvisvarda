package sk.stuba.fei.uim.vsa.pr2.error;


import lombok.Getter;

import javax.ws.rs.WebApplicationException;

public class MyException extends WebApplicationException {


    public MyException(String message, Integer code) {
        super(message, code);
    }
}
