package sk.stuba.fei.uim.vsa.pr2.user;

import lombok.extern.slf4j.Slf4j;
import sk.stuba.fei.uim.vsa.pr1.entities.Student;
import sk.stuba.fei.uim.vsa.pr1.entities.Teacher;
import sk.stuba.fei.uim.vsa.pr2.web.response.CreateStudentDto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Slf4j
public class UserService implements AutoCloseable{
    private static UserService instance;
    private EntityManagerFactory emf;

    public static UserService getInstance(){
        if(instance == null){
            instance = new UserService();
        }
        return instance;
    }

    public UserService(){
        emf = Persistence.createEntityManagerFactory("vsa-project-2");
    }

    public Optional<User> getUserByUsername(String username){
        EntityManager em = emf.createEntityManager();
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        Optional<User> uop = query.getResultStream().findFirst();
        em.close();
        return uop;
    }

    public User save(User user){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            return user;
        } catch (Exception e){
            log.error(e.getMessage(),e);
            if(em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return null;
        } finally {
            em.close();
        }
    }
    @Override
    public void close() throws Exception {
        emf.close();
    }

    public User createUser(Student student) {
        User user = new User();
        user.setId(student.getAisId());
        user.setFirstName(student.getName());
        user.setUsername(student.getEmail());
        user.setPassword(student.getPassword());
        user.addRole(Role.STUDENT);
        return save(user);
    }

    public User createUser(Teacher teacher) {
        User user = new User();
        user.setId(teacher.getAisId());
        user.setFirstName(teacher.getName());
        user.setUsername(teacher.getEmail());
        user.setPassword(teacher.getPassword());
        user.addRole(Role.TEACHER);
        return save(user);
    }
}
