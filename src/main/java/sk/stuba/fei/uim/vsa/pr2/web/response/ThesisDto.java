package sk.stuba.fei.uim.vsa.pr2.web.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import sk.stuba.fei.uim.vsa.pr1.entities.AssignmentStatus;
import sk.stuba.fei.uim.vsa.pr1.entities.AssignmentType;
import sk.stuba.fei.uim.vsa.pr1.entities.Student;
import sk.stuba.fei.uim.vsa.pr1.entities.Teacher;

@Data
@NoArgsConstructor
public class ThesisDto {
    private Long id;
    private String registrationNumber;
    private String title;
    private String description;
    private String institution;
    private TeacherAltResponse supervisor;
    private StudentAltResponse author;
    private String publicationDate;
    private String deadline;
    private AssignmentType type;
    private AssignmentStatus status;
}
