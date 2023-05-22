package sk.stuba.fei.uim.vsa.pr2.web.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import sk.stuba.fei.uim.vsa.pr1.entities.AssignmentType;
import sk.stuba.fei.uim.vsa.pr1.entities.Teacher;

@Data
@NoArgsConstructor
public class CreateThesisDto {
    private String registrationNumber;
    private String title;
    private String description;
    private String type;
    private Teacher supervisor;
}
