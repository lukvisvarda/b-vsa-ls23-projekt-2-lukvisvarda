package sk.stuba.fei.uim.vsa.pr2.web.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import sk.stuba.fei.uim.vsa.pr1.entities.Assignment;

import java.util.List;

@Data
@NoArgsConstructor
//@EqualsAndHashCode(callSuper = true)
public class StudentDto {
    private Long id;
    private Long aisId;
    private String name;
    private String email;
    private Integer year;
    private Integer term;
    private String programme;
    private ThesisDto thesis;
}
