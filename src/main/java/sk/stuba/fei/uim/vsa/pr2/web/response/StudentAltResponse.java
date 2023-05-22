package sk.stuba.fei.uim.vsa.pr2.web.response;

import lombok.Data;

@Data
public class StudentAltResponse {
    private Long id;
    private Long aisId;
    private String name;
    private String email;
    private Integer year;
    private Integer term;
    private String programme;
    private Long thesis;
}
