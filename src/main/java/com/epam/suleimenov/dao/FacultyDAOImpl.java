package com.epam.suleimenov.dao;

import com.epam.suleimenov.model.*;
import com.epam.suleimenov.service.Connector;
import com.epam.suleimenov.service.Service;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by admin on 6/30/2016.
 */
public class FacultyDAOImpl implements FacultyDAO {

    private final String single_quote = new String("'");

    public ArrayList<User> getAllUsers() {

        ArrayList<User> users = new ArrayList<User>();

        String sql = "select * from users";
        try (Connection connection = Connector.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultset = statement.executeQuery();

            while (resultset.next()) {
                User user = new User();
                user.setId(resultset.getInt("id"));
                user.setName(resultset.getString("name"));
                user.setSurname(resultset.getString("surname"));
                user.setEmail(resultset.getString("email"));
                user.setUserRole(resultset.getString("user_role"));
                user.setPassword(resultset.getString("password"));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }


    ///////////////////////////////////////


    public User findUserByEmail(String email) {

        String sql = "SELECT * FROM users WHERE email = " + single_quote + email + single_quote;
        User user = null;

        try (Connection connection = Connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setEmail(resultSet.getString("email"));
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setUserRole(resultSet.getString("user_role"));
                user.setPassword(resultSet.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public boolean checkUser(String login, String password, String userRole) {

        User user = findUserByEmail(login);
        if (user == null)
            return false;

        if (user.getPassword().equals(password) && user.getUserRole().equals(userRole))
            return true;

        return false;
    }

    public User getUserById(int id) {
        String sql = "SELECT * FROM USERS where ID = " + id;
        User user = null;
        try (Connection connection = Connector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setUserRole(resultSet.getString("user_role"));
                user.setPassword(resultSet.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }


    public int findTeacherIdByUserId(int id) {

        String sql = "SELECT * FROM TEACHERS WHERE USER_ID = " + id;
        int teacher_id = 0;

        try (Connection connection = Connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                teacher_id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teacher_id;

    }

    public int findStudentIdByUserId(int id) {
        String sql = "SELECT * FROM STUDENTS WHERE USER_ID = " + id;
        int student_id = 0;
        try (Connection connection = Connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ;
                student_id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student_id;

    }

    public int findUserIdByTeacherId(int id) {

        String sql = "SELECT * FROM TEACHERS WHERE ID = " + id;
        int user_id = 0;

        try (Connection connection = Connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user_id = resultSet.getInt("user_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user_id;

    }

    public Teacher getTeacherById(int id) {
        String sql = "SELECT * FROM TEACHERS WHERE ID = " + id;
        int user_id;
        Teacher teacher = new Teacher();
        ArrayList<Course> courses = new ArrayList<Course>();

        try (Connection connection = Connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user_id = resultSet.getInt("user_id");
                teacher.setId(id);
                teacher.setName(getUserById(user_id).getName());
            } else {
                System.out.println("Cannot find the user");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "SELECT * FROM COURSES WHERE TEACHER_ID = " + id;
        try (Connection connection = Connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Course course = new Course();
                course.setId(resultSet.getInt("id"));
                course.setName(resultSet.getString("name"));
                course.setDescription(resultSet.getString("description"));
                course.setTeacherId(resultSet.getInt("teacher_id"));
                course.setStatus(resultSet.getString("status"));
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        teacher.setCourses(courses);

        return teacher;

    }


    public int getNextIdBySequence(String sequence) {

        String sql = "SELECT " + sequence + "_sequence.NEXTVAL FROM DUAL";

        try (Connection connection = Connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;

    }


    public Course getCourseById(int id) {

        String sql = "SELECT * FROM COURSES WHERE ID = " + id;
        Course course = null;
        ArrayList<Student> students = new ArrayList<Student>();

        try (Connection connection = Connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                course = new Course();
                course.setId(resultSet.getInt("id"));
                course.setName(resultSet.getString("name"));
                course.setDescription(resultSet.getString("description"));
                course.setTeacherId(resultSet.getInt("teacher_id"));
                course.setStatus(resultSet.getString("status"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return course;
    }

    public Student getStudentById(int id) {

        Student student = null;
        String sql = "SELECT * FROM STUDENTS WHERE ID = " + id;

        try (Connection connection = Connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(getUserById(resultSet.getInt("user_id")).getName());
                student.setSurname(getUserById(resultSet.getInt("user_id")).getSurname());
                student.setCourses(getCoursesByStudentId(id));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return student;

    }


    public ArrayList<Student> getStudentsByCourseId(int id) {

        String sql = "SELECT * FROM COURSE_TO_STUDENT WHERE COURSE_ID = " + id;

        ArrayList<Student> students = null;

        try (Connection connection = Connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            students = new ArrayList<Student>();

            while (resultSet.next()) {
                students.add(getStudentById(resultSet.getInt("student_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;

    }


    public ArrayList<Course> getCoursesByStudentId(int id) {

        String sql = "SELECT * FROM COURSE_TO_STUDENT WHERE STUDENT_ID = " + id;

        ArrayList<Course> courses = new ArrayList<Course>();
        ;

        try (Connection connection = Connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                courses.add(getCourseById(resultSet.getInt("course_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;

    }


    public void addToArchive(int id, int student_id, int course_id, int grade) {

        SimpleDateFormat date_formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        String sql = "INSERT INTO archive " +
                "values (" + id + "," + student_id + "," + course_id + "," + grade + ",TO_DATE(" + single_quote + date_formatter.format(new Date()) + single_quote + "," + single_quote + "yyyy-mm-dd hh:mi:ss" + single_quote + "))";

        Statement statement = null;
        try (Connection connection = Connector.getConnection()) {

            statement = connection.createStatement();
            statement.executeUpdate(sql);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addUser(User user) {

        String sql = "INSERT INTO users values(" + user.getId() + "," + single_quote + user.getName() + single_quote + "," + single_quote + user.getSurname() + single_quote + "," + single_quote + user.getEmail() + single_quote + "," + single_quote + user.getUserRole() + single_quote + "," + single_quote + user.getPassword() + single_quote + ")";

        try (Connection connection = Connector.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addTeacher(int teacher_id, int user_id) {

        String sql = "INSERT INTO teachers " +
                "values (" + teacher_id + "," + user_id + ")";

        Statement statement = null;
        try (Connection connection = Connector.getConnection()) {
            statement = connection.createStatement();
            statement.executeUpdate(sql);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addStudent(int student_id, int user_id) {

        String sql = "INSERT INTO students " +
                "values (" + student_id + "," + user_id + ")";

        Statement statement = null;
        try (Connection connection = Connector.getConnection()) {
            statement = connection.createStatement();
            statement.executeUpdate(sql);


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addCourse(Course course) {

        String sql = "INSERT INTO courses " +
                "values (" + course.getId() + "," + single_quote + course.getName() + single_quote + "," + single_quote + course.getDescription() + single_quote + "," + single_quote + course.getTeacherId() + single_quote + "," + single_quote + course.getStatus() + single_quote + ")";

        Statement statement = null;
        try (Connection connection = Connector.getConnection()) {
            statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void matchCourseAndStudent(int id, int course_id, int student_id) {

        String sql = "INSERT INTO COURSE_TO_STUDENT " +
                "values (" + id + "," + course_id + "," + student_id + ")";

        Statement statement = null;
        try (Connection connection = Connector.getConnection()) {
            statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void removeCourse(int id) {
        String sql = "DELETE FROM courses WHERE id=" + id;
        Connection connection = null;
        Statement statement = null;

        try {
            connection = Connector.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeStudent(int id) {
        String sql = "DELETE FROM courses WHERE id=" + id;
        Statement statement = null;

        try (Connection connection = Connector.getConnection()) {
            statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeTeacher(int id) {
        String sql = "DELETE FROM teachers WHERE id=" + id;
        Statement statement = null;

        try (Connection connection = Connector.getConnection()) {
            statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void clearArchive() {
        String sql = "DELETE FROM ARCHIVE";
        Statement statement = null;
        try (Connection connection = Connector.getConnection()) {
            statement = connection.createStatement();
            statement.executeUpdate(sql);


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void clearCourseToStudent() {
        String sql = "DELETE FROM COURSE_TO_STUDENT";
        Statement statement = null;
        try (Connection connection = Connector.getConnection()) {
            statement = connection.createStatement();
            statement.executeUpdate(sql);


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void clearStudents() {
        String sql = "DELETE FROM STUDENTS";
        Statement statement = null;
        try (Connection connection = Connector.getConnection()) {
            statement = connection.createStatement();
            statement.executeUpdate(sql);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearTeachers() {
        String sql = "DELETE FROM TEACHERS";
        Statement statement = null;
        try (Connection connection = Connector.getConnection()) {
            statement = connection.createStatement();
            statement.executeUpdate(sql);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearCourses() {
        String sql = "DELETE FROM COURSES";
        Statement statement = null;
        try (Connection connection = Connector.getConnection()) {
            statement = connection.createStatement();
            statement.executeUpdate(sql);


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void clearUsers() {
        String sql = "DELETE FROM COURSES";
        Statement statement = null;
        try (Connection connection = Connector.getConnection()) {
            statement = connection.createStatement();
            statement.executeUpdate(sql);


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public ArrayList<Archive> getArchive() {
        ArrayList<Archive> archives = new ArrayList<>();
        String sql = "select * from archive";


        try (Connection connection = Connector.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Archive archive = new Archive();
                archive.setId(resultSet.getInt("id"));
                archive.setTeacher(getTeacherById(getTeacherIdByCourseId(resultSet.getInt("course_id"))).getName());
                archive.setStudent(getStudentById(resultSet.getInt("student_id")).getName());
                archive.setCourse(getCourseById(resultSet.getInt("course_id")).getName());
                archive.setGrade(resultSet.getInt("grade"));
                archive.setDate(resultSet.getDate("date"));
                archives.add(archive);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return archives;
    }

    public int getTeacherIdByCourseId(int id) {

        String sql = "SELECT * FROM COURSES WHERE ID = " + id;
        int teacher_id = 0;

        try (Connection connection = Connector.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                teacher_id = resultSet.getInt("teacher_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teacher_id;
    }

    public ArrayList<Student> getAllStudents() {

        ArrayList<Student> students = new ArrayList<Student>();
        String sql = "select * from students";

        try (Connection connection = Connector.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                String courses = resultSet.getString("courses");
                ArrayList<String> list = new ArrayList<String>();
                if (courses != null) {
                    for (String course : courses.split(",")) {
                        list.add(course);
                    }

                }
                students.add(student);


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    public ArrayList<Teacher> getAllTeachers() {

        ArrayList<Teacher> teachers = new ArrayList<Teacher>();
        String sql = "select * from teachers";

        try (Connection connection = Connector.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(resultSet.getInt("id"));
                teacher.setName(resultSet.getString("name"));
                String courses = resultSet.getString("courses");
                ArrayList<String> list = new ArrayList<String>();
                if (courses != null) {
                    for (String course : courses.split(",")) {
                        list.add(course);
                    }

                }
                teachers.add(teacher);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teachers;
    }



    public ArrayList<Course> getAllCourses() {

        ArrayList<Course> courses = new ArrayList<Course>();

        String sql = "select * from courses";
        try (Connection connection = Connector.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultset = statement.executeQuery();

            while (resultset.next()) {
                ;
                Course course = new Course();
                course.setId(resultset.getInt("id"));
                course.setName(resultset.getString("name"));
                course.setDescription(resultset.getString("description"));
                course.setTeacherId(resultset.getInt("teacher_id"));
                course.setStatus(resultset.getString("status"));
                courses.add(course);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }

    public ArrayList<Course> getAllCoursesByStatus(String status) {

        ArrayList<Course> courses = new ArrayList<Course>();

        String sql = "select * from courses WHERE STATUS = " + single_quote + status + single_quote;
        try (Connection connection = Connector.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultset = statement.executeQuery();

            while (resultset.next()) {
                ;
                ;
                ;
                Course course = new Course();
                course.setId(resultset.getInt("id"));
                course.setName(resultset.getString("name"));
                course.setDescription(resultset.getString("description"));
                course.setTeacherId(resultset.getInt("teacher_id"));
                course.setStatus(resultset.getString("status"));
                courses.add(course);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }





    public void changeCourseStatus(int id, String status) {
        String sql = "UPDATE COURSES SET STATUS =" + single_quote + status + single_quote + " WHERE ID = " + id;
        try (Connection connection = Connector.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}