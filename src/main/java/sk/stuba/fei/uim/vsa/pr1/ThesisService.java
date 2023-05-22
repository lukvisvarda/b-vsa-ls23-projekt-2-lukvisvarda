package sk.stuba.fei.uim.vsa.pr1;

import sk.stuba.fei.uim.vsa.pr1.entities.*;
import sk.stuba.fei.uim.vsa.pr2.BCryptService;
import sk.stuba.fei.uim.vsa.pr2.web.response.CreateStudentDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.CreateTeacherDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.CreateThesisDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.TeacherAltResponse;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class ThesisService extends AbstractThesisService<Student, Teacher, Assignment> implements Serializable {

    private final EntityManagerFactory emf;

    public ThesisService() {
        super();
        this.emf = super.emf;
    }

    @Override
    public Student createStudent(Long aisId, String name, String email) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Student student = new Student(aisId, name, email);
            //check if there is teacher with same aisID
            if (aisId != null && em.find(Teacher.class, aisId) != null) {
                em.getTransaction().rollback();
                return null;
            }
            //check if there are teachers with same email
            List<Teacher> teachers = em.createQuery("SELECT t FROM Teacher t WHERE t.email = :email", Teacher.class)
                    .setParameter("email", email)
                    .getResultList();

            if (teachers.size() > 0) {
                em.getTransaction().rollback();
                return null;
            }
            em.persist(student);
            em.getTransaction().commit();
            return student;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    @Override
    public Student getStudent(Long id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Student student = em.find(Student.class, id);
            em.getTransaction().commit();
            return student;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    @Override
    public Student updateStudent(Student student) throws IllegalArgumentException {
        if (student == null) {
            throw new IllegalArgumentException("student is null");
        }

        if (student.getAisId() == null) {
            throw new IllegalArgumentException("student id is null");
        }
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Student existingStudent = em.find(Student.class, student.getAisId());
            if (existingStudent == null) {
                return null;
            }

            Student updatedStudent = em.merge(student);
            em.getTransaction().commit();
            em.close();
            return updatedStudent;

        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    @Override
    public List<Student> getStudents() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            List<Student> students = em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
            em.getTransaction().commit();
            return students;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    @Override
    public Student deleteStudent(Long id) throws IllegalArgumentException{
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Student student = em.find(Student.class, id);
            if(student == null) {
                return null;
            }
            if (student.getAssignment() != null) {
                Assignment thesis = student.getAssignment();
                thesis.setStudent(null);
                thesis.setStatus(AssignmentStatus.OPEN);
                em.merge(thesis);
            }
            em.remove(student);
            em.getTransaction().commit();
            em.close();
            return student;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    @Override
    public Teacher createTeacher(Long aisId, String name, String email, String department) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Teacher teacher = new Teacher(aisId, name, email, department);

            //check if there is already student with same aisId
            if (aisId!=null && em.find(Student.class, aisId) != null) {
                em.getTransaction().rollback();
                return null;
            }

            //check if there are students with same email
            List<Student> students = em.createQuery("SELECT s FROM Student s WHERE s.email = :email", Student.class)
                    .setParameter("email", email)
                    .getResultList();

            if (students.size() > 0) {
                em.getTransaction().rollback();
                return null;
            }
            em.persist(teacher);
            em.getTransaction().commit();
            return teacher;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    @Override
    public Teacher getTeacher(Long id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Teacher teacher = em.find(Teacher.class, id);
            em.getTransaction().commit();
            return teacher;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    @Override
    public Teacher updateTeacher(Teacher teacher) {
        if (teacher == null) {
            throw new IllegalArgumentException("teacher is null");
        }

        if (teacher.getAisId() == null) {
            throw new IllegalArgumentException("teacher id is null");
        }
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Teacher existingTeacher = em.find(Teacher.class, teacher.getAisId());
            if(existingTeacher == null) {
                return null;
            }
            Teacher updatedTeacher = em.merge(teacher);
            em.getTransaction().commit();
            return updatedTeacher;

        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    @Override
    public List<Teacher> getTeachers() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            List<Teacher> teachers = em.createQuery("SELECT t FROM Teacher t", Teacher.class).getResultList();
            em.getTransaction().commit();
            return teachers;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    @Override
    public Teacher deleteTeacher(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Teacher teacher = em.find(Teacher.class, id);
            if(teacher == null) {
                return null;
            }
            em.remove(teacher);
            em.getTransaction().commit();
            return teacher;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    //registration number has to start with FEI- and has to be unique
    public String generateRegistrationNumber() {

        String registrationNumber = "FEI-";
        Random random = new Random();
        int randomNumber = random.nextInt(1000000);
        registrationNumber += randomNumber;
        return registrationNumber;
    }

    @Override
    public Assignment makeThesisAssignment(Long supervisor, String title, String type, String description) throws IllegalArgumentException {
        if (supervisor == null) {
            throw new IllegalArgumentException("supervisor is null");
        }

        if (type == null) {
            return null;
        }
        //check if type is valid with enum or is not null
        if(!type.equals("BACHELOR") && !type.equals("MASTER") && !type.equals("DISSERTATION")){
            return null;
        }

        AssignmentType assignmentType = AssignmentType.valueOf(type);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Teacher teacher = em.find(Teacher.class, supervisor);
            if (teacher == null) {
                return null;
            }
            Assignment assignment = new Assignment(teacher, title, assignmentType, description);

            //set unique registration number
            String registrationNumber = generateRegistrationNumber();
            //check if any assignement in db has the same registration number
            List<Assignment> assignments = em.createQuery("SELECT a FROM Assignment a", Assignment.class).getResultList();
            for (Assignment a : assignments) {
                if (a.getRegistrationNumber().equals(registrationNumber)) {
                    registrationNumber = generateRegistrationNumber();
                }
            }
            assignment.setRegistrationNumber(registrationNumber);
            teacher.getSupervisedTheses().add(assignment);
            em.persist(assignment);
            em.getTransaction().commit();
            em.close();
            return assignment;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    @Override
    public Assignment assignThesis(Long thesisId, Long studentId) throws IllegalArgumentException, IllegalStateException {
        EntityManager em = emf.createEntityManager();
        if (thesisId == null) {
            throw new IllegalArgumentException("assignment is null");
        }
        if (studentId == null) {
            throw new IllegalArgumentException("student is null");
        }
        em.getTransaction().begin();
        Assignment assignment = em.find(Assignment.class, thesisId);
        Student student = em.find(Student.class, studentId);

        //Študent si môže zapísať ľubovolnú záverečnú prácu, ktorej dátum odovzdania je väčší, ako aktuálny dátum a jej stav je nastavený na "Volná". Študent môže odovzdať iba priradenú prácu do deadlinu (termínu) odovzdania, ktorá je v stave "Zabraná".
        if (assignment.getDeadline().isBefore(LocalDate.now())) {
            throw new IllegalStateException("deadline is before current date");
        }
        if (assignment.getStatus() != AssignmentStatus.OPEN) {
            throw new IllegalStateException("assignment is not free");
        }
        try {
            if(student.getAssignment() != null){
                return null;
            }
            assignment.setStudent(student);
            student.setAssignment(assignment);
            assignment.setStatus(AssignmentStatus.IN_PROGRESS);
            em.merge(assignment);
            em.merge(student);
            em.getTransaction().commit();
            return assignment;

        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    @Override
    public Assignment submitThesis(Long thesisId) throws IllegalArgumentException, IllegalStateException {
        EntityManager em = emf.createEntityManager();
        if (thesisId == null) {
            throw new IllegalArgumentException("thesisId is null");
        }
        em.getTransaction().begin();
        Assignment assignment = em.find(Assignment.class, thesisId);
        if(assignment == null){
            return null;
        }
        if (assignment.getDeadline().isBefore(LocalDate.now())) {
            throw new IllegalStateException("deadline is before current date");
        }
        if (assignment.getStatus() != AssignmentStatus.IN_PROGRESS && assignment.getStatus() != AssignmentStatus.COMPLETED) {
            throw new IllegalStateException("assignment is not in progress");
        }
        if (assignment.getStudent() == null) {
            throw new IllegalStateException("assignment is not assigned to any student");
        }
        try {
            assignment.setStatus(AssignmentStatus.COMPLETED);
            em.getTransaction().commit();
            return assignment;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    @Override
    public Assignment deleteThesis(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Assignment assignment = em.find(Assignment.class, id);
            if(assignment == null){
                return null;
            }
            em.remove(assignment);
            em.getTransaction().commit();
            return assignment;

        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    @Override
    public List<Assignment> getTheses() {
        EntityManager em = emf.createEntityManager();
        List<Assignment> assignments = em.createQuery("SELECT a FROM Assignment a", Assignment.class).getResultList();
        em.close();
        return assignments;
    }

    @Override
    public List<Assignment> getThesesByTeacher(Long teacherId) {
        EntityManager em = emf.createEntityManager();
        List<Assignment> assignments = em.createQuery("SELECT a FROM Assignment a WHERE a.supervisor.aisId = :teacherId", Assignment.class).setParameter("teacherId", teacherId).getResultList();
        em.close();
        return assignments;
    }

    @Override
    public Assignment getThesisByStudent(Long studentId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Student student = em.find(Student.class, studentId);
            if (student == null) {
                return null;
            }
            Assignment assignment = student.getAssignment();
            em.getTransaction().commit();
            return assignment;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    @Override
    public Assignment getThesis(Long id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        EntityManager em = emf.createEntityManager();
        Assignment assignment = em.find(Assignment.class, id);
        em.close();
        return assignment;
    }

    @Override
    public Assignment updateThesis(Assignment thesis) throws IllegalArgumentException {
        if (thesis == null) {
            throw new IllegalArgumentException("thesis is null");
        }
        if (thesis.getId() == null) {
            throw new IllegalArgumentException("thesis id is null");
        }
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Assignment existingAssignment = em.find(Assignment.class, thesis.getId());
            if (existingAssignment == null) {
                em.close();
                return null;
            }

            if(thesis.getStudent() != null) {
                Student student = em.find(Student.class, thesis.getStudent().getAisId());
                if (student == null) {
                    em.close();
                    return null;
                }
                if (student.getAssignment() != null && !Objects.equals(student.getAssignment().getId(), thesis.getId())) {
                    em.close();
                    return null;
                }
            }

            if(thesis.getPublicationDate().isAfter(thesis.getDeadline())) {
                em.close();
                return null;
            }
            if(thesis.getDeadline().isBefore(thesis.getPublicationDate())) {
                em.close();
                return null;
            }
            Assignment updatedThesis = em.merge(thesis);
            em.getTransaction().commit();
            return updatedThesis;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public Student createStudent(CreateStudentDto studentDto) {
        if (studentDto == null) {
            throw new IllegalArgumentException("student is null");
        }

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            String newPassword = BCryptService.hash(studentDto.getPassword());
            Student student = new Student(studentDto.getAisId(), studentDto.getName(), studentDto.getEmail(), studentDto.getProgramme(), studentDto.getYear(), studentDto.getTerm(), newPassword);
            if (student.getAisId() != null && em.find(Teacher.class, student.getAisId()) != null) {
                em.getTransaction().rollback();
                return null;
            }
            //check if there are teachers with same email
            List<Teacher> teachers = em.createQuery("SELECT t FROM Teacher t WHERE t.email = :email", Teacher.class)
                    .setParameter("email", student.getEmail())
                    .getResultList();

            if (teachers.size() > 0) {
                em.getTransaction().rollback();
                return null;
            }
            em.persist(student);
            em.getTransaction().commit();
            return student;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public Teacher createTeacher(CreateTeacherDto teacherDto) {
        if (teacherDto == null) {
            throw new IllegalArgumentException("teacher is null");
        }
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            String newPassword = BCryptService.hash(teacherDto.getPassword());
            Teacher teacher = new Teacher(teacherDto.getAisId(), teacherDto.getName(), teacherDto.getEmail(), teacherDto.getDepartment(), newPassword);
            if (teacher.getAisId()!=null && em.find(Student.class, teacher.getAisId()) != null) {
                em.getTransaction().rollback();
                return null;
            }

            //check if there are students with same email
            List<Student> students = em.createQuery("SELECT s FROM Student s WHERE s.email = :email", Student.class)
                    .setParameter("email", teacher.getEmail())
                    .getResultList();

            if (students.size() > 0) {
                em.getTransaction().rollback();
                return null;
            }
            em.persist(teacher);
            em.getTransaction().commit();
            return teacher;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public Assignment makeThesisAssignment(CreateThesisDto thesisDto, Long teacherId) {
        if (thesisDto == null) {
            throw new IllegalArgumentException("thesis is null");
        }

        //check if registration number is valid and has FEI prefix
        if (!thesisDto.getRegistrationNumber().startsWith("FEI-") || thesisDto.getRegistrationNumber() == null) {
            return null;
        }
        //check if title is not null
        if (thesisDto.getTitle() == null) {
            return null;
        }


        if (thesisDto.getType() == null) {
            return null;
        }
        //check if type is valid with enum or is not null
        if(!thesisDto.getType().toString().equals("BACHELOR") && !thesisDto.getType().toString().equals("MASTER") && !thesisDto.getType().toString().equals("DISSERTATION")){
            return null;
        }

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Teacher teacher = em.find(Teacher.class, teacherId);
            TeacherAltResponse teacherAltResponse = new TeacherAltResponse(teacher);
            Assignment assignment = new Assignment(thesisDto.getRegistrationNumber(), thesisDto.getTitle(), thesisDto.getDescription(), thesisDto.getType(), teacher, teacherAltResponse);
            teacher.getSupervisedTheses().add(assignment);
            teacherAltResponse.addThesisIds(assignment.getId());
            em.persist(assignment);
            em.getTransaction().commit();
            return assignment;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
}