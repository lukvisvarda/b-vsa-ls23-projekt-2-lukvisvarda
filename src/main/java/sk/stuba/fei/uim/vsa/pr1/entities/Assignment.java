package sk.stuba.fei.uim.vsa.pr1.entities;



import lombok.Data;
import sk.stuba.fei.uim.vsa.pr2.web.response.TeacherAltResponse;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
public class Assignment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String registrationNumber;
    private String title;
    private String description;
    private String institution;
    @ManyToOne
    private Teacher supervisor;
    @OneToOne
    private Student student;
    private LocalDate publicationDate;
    private LocalDate deadline;
    @Enumerated(EnumType.STRING)
    private AssignmentType type;
    @Enumerated(EnumType.STRING)
    private AssignmentStatus status;

    public Assignment() {
    }

    public Assignment(Teacher supervisor, String title, AssignmentType type, String description)  {
        this.title = title;
        this.supervisor = supervisor;
        this.type = type;
        this.description = description;
        if(supervisor!= null && supervisor.getInstitute() != null)
            this.institution = supervisor.getInstitute();
        this.status = AssignmentStatus.OPEN;
        this.publicationDate = LocalDate.now();
        LocalDate newDeadline = LocalDate.now().plusMonths(3);
        deadline = newDeadline;
    }

    public Assignment(String registrationNumber, String title, String description, String type, Teacher supervisor, TeacherAltResponse supervisorAlt) {
        this.registrationNumber = registrationNumber;
        this.title = title;
        this.description = description;
        this.type = AssignmentType.valueOf(type);
        this.status = AssignmentStatus.OPEN;
        this.publicationDate = LocalDate.now();
        LocalDate newDeadline = LocalDate.now().plusMonths(3);
        deadline = newDeadline;
        this.supervisor = supervisor;
    }

}



