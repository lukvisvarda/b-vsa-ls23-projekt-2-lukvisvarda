package sk.stuba.fei.uim.vsa.pr1.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
@Data
public class Student implements Serializable {
    @Id
    private Long aisId;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private Integer studyYear;
    private Integer studySemester;
    private String studyProgram;
    @OneToOne(mappedBy = "student")
    private Assignment assignment;

    public Student() {
    }

    public Student(Long aisId, String name, String email) {
        this.aisId = aisId;
        this.name = name;
        this.email = email;
    }

    public Student(Long aisId, String name, String email, String programme, Integer year, Integer studySemester, String password) {
        this.aisId = aisId;
        this.name = name;
        this.email = email;
        this.studyProgram = programme;
        this.studyYear = year;
        this.studySemester = studySemester;
        this.password = password;
    }
}
