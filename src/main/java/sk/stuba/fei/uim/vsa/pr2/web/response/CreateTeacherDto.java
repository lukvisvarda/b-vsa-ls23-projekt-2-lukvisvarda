package sk.stuba.fei.uim.vsa.pr2.web.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateTeacherDto {
    private Long aisId;
    private String name;
    private String email;
    private String password;
    private String institute;
    private String department;

    public void decodePassword() {
        this.password = new String(java.util.Base64.getDecoder().decode(this.password));
    }

}
