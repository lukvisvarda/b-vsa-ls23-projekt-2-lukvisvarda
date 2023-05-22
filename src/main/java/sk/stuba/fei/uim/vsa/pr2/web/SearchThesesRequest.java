package sk.stuba.fei.uim.vsa.pr2.web;

import lombok.Data;

@Data
public class SearchThesesRequest {
    private Long studentId;
    private Long teacherId;
}
