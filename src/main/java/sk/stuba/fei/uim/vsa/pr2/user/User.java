package sk.stuba.fei.uim.vsa.pr2.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User implements Principal, Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Long Id;
    private String firstName;
    private String username;
    private String password;
    @ElementCollection
    private List<Role> role;

    public User() {
        role = new ArrayList<>();
    }

    public String getName() {
        return username;
    }

    public User(String firstName, String username, String password) {
        this();
        this.firstName = firstName;
        this.username = username;
        this.password = password;
    }

    public void addRole(Role role) {
        this.role.add(role);
    }
}
