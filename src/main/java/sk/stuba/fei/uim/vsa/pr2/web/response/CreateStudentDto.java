package sk.stuba.fei.uim.vsa.pr2.web.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateStudentDto {
    private Long aisId;
    private String name;
    private String email;
    private String password;
    private Integer year;
    private Integer term;
    private String programme;

    public void decodePassword() {
        this.password = new String(java.util.Base64.getDecoder().decode(this.password));
    }
}
