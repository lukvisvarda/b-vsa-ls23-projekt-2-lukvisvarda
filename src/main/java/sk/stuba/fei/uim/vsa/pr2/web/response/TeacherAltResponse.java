package sk.stuba.fei.uim.vsa.pr2.web.response;

import lombok.*;
import sk.stuba.fei.uim.vsa.pr1.entities.Teacher;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

@Data
public class TeacherAltResponse {
    @Id
    private Long id;
    private Long aisId;
    private String name;
    private String email;
    private String institute;
    private String department;
    private ArrayList<Long> theses;

    public TeacherAltResponse() {
        this.theses = new ArrayList<>();
    }

    public TeacherAltResponse(Teacher teacher) {
        this.id = teacher.getAisId();
        this.aisId = teacher.getAisId();
        this.name = teacher.getName();
        this.email = teacher.getEmail();
        this.institute = teacher.getInstitute();
        this.department = teacher.getDepartment();
        this.theses = new ArrayList<>();
    }

    public void addThesisIds(Long thesesIds) {
        this.theses.add(thesesIds);
    }

}
