package sk.stuba.fei.uim.vsa.pr1.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Teacher implements Serializable {
    @Id
    private Long aisId;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String institute;
    private String department;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "supervisor")
    private ArrayList<Assignment> supervisedTheses;

    public Teacher() {
    }

    public Teacher(Long aisId, String name, String email, String department) {
        this.aisId = aisId;
        this.name = name;
        this.email = email;
        this.department = department;
        this.institute = department;
        this.supervisedTheses = new ArrayList<>();
    }

    public Teacher(Long aisId, String name, String email, String department, String password) {
        this.aisId = aisId;
        this.name = name;
        this.email = email;
        this.department = department;
        this.institute = department;
        this.password = password;
        this.supervisedTheses = new ArrayList<>();
    }

    public void addThesis(Assignment thesis) {
        this.supervisedTheses.add(thesis);
    }
}
