package sk.stuba.fei.uim.vsa.pr2.web.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import sk.stuba.fei.uim.vsa.pr1.entities.Assignment;

import java.util.ArrayList;
import java.util.List;

@Data
public class TeacherDto {
    private Long id;
    private Long aisId;
    private String name;
    private String email;
    private String institute;
    private String department;
    private ArrayList<ThesisDto> theses;

    public TeacherDto() {
        this.theses = new ArrayList<>();
    }


}
