package sk.stuba.fei.uim.vsa.pr2.web.response.factory;

import sk.stuba.fei.uim.vsa.pr1.entities.Assignment;
import sk.stuba.fei.uim.vsa.pr1.entities.Student;
import sk.stuba.fei.uim.vsa.pr1.entities.Teacher;
import sk.stuba.fei.uim.vsa.pr2.web.response.StudentAltResponse;
import sk.stuba.fei.uim.vsa.pr2.web.response.TeacherAltResponse;
import sk.stuba.fei.uim.vsa.pr2.web.response.ThesisDto;

public class ThesisResponseFactory {

    private TeacherAltResponse teacherAltResponse;
    private StudentAltResponse studentAltResponse;
    public ThesisDto transformToDto(Assignment thesis) {
        ThesisDto dto = new ThesisDto();
        this.teacherAltResponse = new TeacherAltResponse();
        this.studentAltResponse = new StudentAltResponse();
        setTeacherAltAsTeacher(thesis.getSupervisor());
        if(thesis.getStudent() != null) {
            setStudentAltResponseAsStudent(thesis.getStudent());
        }
        dto.setId(thesis.getId());
        dto.setRegistrationNumber(thesis.getRegistrationNumber());
        dto.setTitle(thesis.getTitle());
        dto.setDescription(thesis.getDescription());
        dto.setInstitution(thesis.getInstitution());
        dto.setSupervisor(this.teacherAltResponse);
        if(thesis.getStudent() != null)
            dto.setAuthor(this.studentAltResponse);
        else
            dto.setAuthor(null);
        dto.setPublicationDate(thesis.getPublicationDate().toString());
        dto.setDeadline(thesis.getDeadline().toString());
        dto.setType(thesis.getType());
        dto.setStatus(thesis.getStatus());
        return dto;
    }

    private void setTeacherAltAsTeacher(Teacher teacher) {
        this.teacherAltResponse.setId(teacher.getAisId());
        this.teacherAltResponse.setAisId(teacher.getAisId());
        this.teacherAltResponse.setName(teacher.getName());
        this.teacherAltResponse.setEmail(teacher.getEmail());
        this.teacherAltResponse.setInstitute(teacher.getInstitute());
        this.teacherAltResponse.setDepartment(teacher.getDepartment());

        //get teachers theses ids and add them to teacherAltResponse thesesIds list
        teacher.getSupervisedTheses().forEach(thesis -> {
            this.teacherAltResponse.addThesisIds(thesis.getId());
        });

    }

    private void setStudentAltResponseAsStudent(Student student) {
        this.studentAltResponse.setId(student.getAisId());
        this.studentAltResponse.setAisId(student.getAisId());
        this.studentAltResponse.setName(student.getName());
        this.studentAltResponse.setEmail(student.getEmail());
        this.studentAltResponse.setYear(student.getStudyYear());
        this.studentAltResponse.setTerm(student.getStudySemester());
        this.studentAltResponse.setProgramme(student.getStudyProgram());
        this.studentAltResponse.setThesis(student.getAssignment().getId());
    }
}
