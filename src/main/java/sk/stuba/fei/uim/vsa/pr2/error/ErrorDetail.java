package sk.stuba.fei.uim.vsa.pr2.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDetail {
    private String type;
    private String trace;
}
