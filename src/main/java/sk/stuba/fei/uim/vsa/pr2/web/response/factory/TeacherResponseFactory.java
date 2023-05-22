package sk.stuba.fei.uim.vsa.pr2.web.response.factory;

import sk.stuba.fei.uim.vsa.pr1.entities.Teacher;
import sk.stuba.fei.uim.vsa.pr2.web.response.TeacherDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.ThesisDto;

import java.util.ArrayList;

public class TeacherResponseFactory {

    public TeacherDto transformToDto(Teacher teacher) {
        TeacherDto dto = new TeacherDto();
        ArrayList<ThesisDto> thesisDto = new ArrayList<>();
        ThesisResponseFactory thesisResponseFactory = new ThesisResponseFactory();
        for (int i = 0; i < teacher.getSupervisedTheses().size(); i++) {
            thesisDto.add(thesisResponseFactory.transformToDto(teacher.getSupervisedTheses().get(i)));
        }
        dto.setId(teacher.getAisId());
        dto.setAisId(teacher.getAisId());
        dto.setName(teacher.getName());
        dto.setEmail(teacher.getEmail());
        dto.setInstitute(teacher.getInstitute());
        dto.setDepartment(teacher.getDepartment());
        dto.setTheses(thesisDto);
        return dto;
    }
}
