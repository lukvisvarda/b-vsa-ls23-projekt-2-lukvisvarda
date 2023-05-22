package sk.stuba.fei.uim.vsa.pr2.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class ErrorMessage {
    private int code;
    private String message;
    private ErrorDetail error;

}
