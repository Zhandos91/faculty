//import com.epam.suleimenov.dao.FacultyDAO;
//import com.epam.suleimenov.dao.FacultyDAOImpl;
//import com.epam.suleimenov.model.*;
//
//import com.epam.suleimenov.service.DBConnectionPool;
//import com.epam.suleimenov.service.Service;
//
//import java.util.ArrayList;


/**
 * Created by admin on 6/28/2016.
 */
public class TestStudent {

//    private static FacultyDAO dao = new FacultyDAOImpl();
//
//    public static void main(String args[]) {
//        DBConnectionPool connector = new DBConnectionPool();
//        Service service = new Service();
//        dao.setService(service);
//        dao.setConnector(connector);
//        dao.clearArchive();
//        dao.clearStudents();
//        dao.clearTeachers();
//        dao.clearCourses();
//
//        //System.out.println(UUID.randomUUID().getMostSignificantBits());
//        Student jack = new Student();
//        jack.setName("Jack");
//        jack.setCourses(new ArrayList<String>());
//        jack.setId((int) Math.random() * 1000000);
//        dao.addStudent(jack);
//
//        Student anne = new Student();
//        anne.setName("Anne");
//        anne.setCourses(new ArrayList<String>());
//        anne.setId((int) (Math.random() * 1000000));
//        dao.addStudent(anne);
//
//        Student caroline = new Student();
//        caroline.setName("Caroline");
//        caroline.setCourses(new ArrayList<String>());
//        caroline.setId((int) (Math.random() * 1000000));
//        dao.addStudent(caroline);
//
//        Teacher liza = new Teacher();
//        liza.setName("Prof.Liza");
//        liza.setCourses(new ArrayList<String>());
//        liza.setId((int) (Math.random() * 1000000));
//        dao.addTeacher(liza);
//
//
//        Course history = new Course();
//        history.setName("History Of KZ");
//        history.setDescription("This course is intended to prepare students for ...");
//        history.setTeacher(liza.getName());
//        history.setId((int) (Math.random() * 1000000));
//
//
//
//        Course wh = new Course();
//        wh.setName("World History");
//        wh.setDescription("World Empires that arose ...");
//        wh.setTeacher(liza.getName());
//        wh.setId((int) (Math.random() * 1000000));
//
//
//        dao.openCourse(history,liza);
//        dao.openCourse(wh, liza);
//        service.addCourse(liza, history);
//        service.addCourse(liza, wh);
//
//        dao.registerForCourse(jack, history);
//        dao.registerForCourse(anne, history);
//        dao.registerForCourse(caroline, history);
//
//
//        dao.registerForCourse(jack, wh);
//        dao.registerForCourse(anne, wh);
//        dao.registerForCourse(caroline, wh);
//        service.giveGrade(dao, liza.getName(), anne.getName(), history.getName());
//        service.giveGrade(dao, liza.getName(), jack.getName(), history.getName());
//        service.giveGrade(dao, liza.getName(), caroline.getName(), history.getName());
//
//
//        ////////////////
//
//        Teacher snow = new Teacher();
//        snow.setName("Prof.Snow");
//        snow.setCourses(new ArrayList<String>());
//        snow.setId((int) (Math.random() * 1000000));
//        dao.addTeacher(snow);
//
//
//        Course chemistry = new Course();
//        chemistry.setName("General Chemistry");
//        chemistry.setDescription("Chemical processes start ...");
//        chemistry.setTeacher(snow.getName());
//        chemistry.setId((int) (Math.random() * 1000000));
//
//        dao.openCourse(chemistry, snow);
//        dao.registerForCourse(jack, chemistry);
//        dao.registerForCourse(anne, chemistry);
//        dao.registerForCourse(caroline, chemistry);
//
//
//        service.giveGrade(dao, snow.getName(), anne.getName(), chemistry.getName());
//        service.giveGrade(dao, snow.getName(), jack.getName(), chemistry.getName());
//        service.giveGrade(dao, snow.getName(), caroline.getName(), chemistry.getName());
//
//
//        for(Teacher teacher: dao.getAllTeachers())
//        System.out.println(teacher);
//        System.out.println();
//        for(Student student: dao.getAllStudents())
//            System.out.println(student);
//        System.out.println();
//        for(Course course: dao.getAllCourses())
//            System.out.println(course);
//        System.out.println();
//        for(Archive archive: dao.getArchive())
//            System.out.println(archive);
//
//    }
}
