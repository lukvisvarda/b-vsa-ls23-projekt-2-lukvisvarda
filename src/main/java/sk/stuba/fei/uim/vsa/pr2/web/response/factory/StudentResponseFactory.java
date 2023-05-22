package sk.stuba.fei.uim.vsa.pr2.web.response.factory;

import sk.stuba.fei.uim.vsa.pr1.entities.Student;
import sk.stuba.fei.uim.vsa.pr2.web.response.StudentDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.ThesisDto;
public class StudentResponseFactory {

    public StudentDto transformToDto(Student student){
        StudentDto dto = new StudentDto();
        ThesisDto thesisDto = new ThesisDto();
        ThesisResponseFactory thesisResponseFactory = new ThesisResponseFactory();
        if(student.getAssignment() != null)
            thesisDto = thesisResponseFactory.transformToDto(student.getAssignment());
        dto.setId(student.getAisId());
        dto.setAisId(student.getAisId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setYear(student.getStudyYear());
        dto.setTerm(student.getStudySemester());
        dto.setProgramme(student.getStudyProgram());
        if(student.getAssignment() != null)
            dto.setThesis(thesisDto);
        else dto.setThesis(null);
        return dto;
    }
}
