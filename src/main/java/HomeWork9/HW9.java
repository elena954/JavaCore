package HomeWork9;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class HW9 {

    public static void main(String[] args) {
        List<GBStudent> students = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            GBStudent student = new GBStudent();
            student.setName("Name" + i);
            for (int j = 0; j <= i; j++) {
                for (int k = 0; k < j; k++) {
                    Course course = new GBCourse("course" + j);
                    student.setCourse(course);
                }
            }
            students.add(student);
        }

        List<String> uniqueCourses = uniqCoursesNames(students);
        System.out.println("Список уникальных курсов: " + uniqueCourses.toString());

        System.out.println("Тройка самых любознательных студентов: ");
        topThreeMostCurious(students).stream()
                .map(Student::getName)
                .forEach(System.out::println);

        Course course = new GBCourse("course7");
        System.out.printf("Студенты на курсе %s: \n", course.getName());
        studentsOnCourse(students, course).stream()
                .map(Student::getName)
                .forEach(System.out::println);
    }

    static List<String> uniqCoursesNames(List<GBStudent> students) {
        return students.stream()
                .map(GBStudent::getAllCourses)
                .flatMap(Collection::stream)
                .map(Course::getName)
                .distinct()
                .collect(Collectors.toList());
    }
    static List<Student> topThreeMostCurious(List<GBStudent> students) {
        return students.stream()
                .sorted((stud1, stud2) -> stud2.getAllCourses().size() - stud1.getAllCourses().size())
                .limit(3)
                .collect(Collectors.toList());
    }
    static List<Student> studentsOnCourse(List<GBStudent> students, Course course) {
        String courseName = course.getName();
        return students.stream()
                .filter(stud -> stud.getAllCourses().stream().anyMatch(c -> c.getName().equals(courseName)))
                .collect(Collectors.toList());
    }
}