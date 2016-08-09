package com.epam.suleimenov.action;

import com.epam.suleimenov.model.Archive;
import com.epam.suleimenov.model.Course;
import com.epam.suleimenov.model.User;
import com.epam.suleimenov.service.ArchiveService;
import com.epam.suleimenov.service.CourseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;


public class SubmitGradeAction implements Action {

    private ActionResult teacherAction = new ActionResult("teacher");
    private ActionResult gradeAgain = new ActionResult("grade");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {

        Course course = (Course) req.getSession().getAttribute("course");
        User teacher = (User) req.getSession().getAttribute("teacher");
        List<User> students = (List<User>) req.getSession().getAttribute("students");

        if (students != null && students.size() > 0) {
            for (User student : students) {
                String grade = req.getParameter(Integer.toString(student.getId()));
                if (grade == null || grade.equals("")) {
                    req.setAttribute("gradeError", "All grades must be entered!");
                    return gradeAgain;
                } else if (!(grade.equals(Archive.Grade.valueOf("A").toString()) || grade.equals(Archive.Grade.valueOf("B").toString()) || grade.equals(Archive.Grade.valueOf("C").toString())
                        || grade.equals(Archive.Grade.valueOf("D").toString()) || grade.equals(Archive.Grade.valueOf("F").toString()))) {
                    req.setAttribute("gradeError", "Only letter grades are allowed for grade {A, B, C, D, E, F}");
                    return gradeAgain;
                }

            }

            try(ArchiveService archiveService = new ArchiveService();) {
                for (User student : students) {
                    String grade = req.getParameter(Integer.toString(student.getId()));
                    Archive archive = new Archive();
                    archive.setCourse(course);
                    archive.setDate(new Date());
                    archive.setGrade(Archive.Grade.valueOf(grade));
                    archive.setStudent(student);
                    archive.setTeacher(teacher);
                    archiveService.createArchive(archive);
                }
            }

            try(CourseService courseService = new CourseService()) {
                course.setStatus("archived");
                courseService.updateCourse(course);
                teacher.setCourses(courseService.findCoursesByUser(teacher));
                req.getSession().setAttribute("teacher", teacher);
            }
        }
        return teacherAction;

    }
}