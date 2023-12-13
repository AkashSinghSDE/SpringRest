package services;

import entities.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {

     public List<Course> getCourses();

     public Optional<Course> getCourse(Long id);

     public Course addCourse(Course course);

     public Course updateCourse(Long id, Course newCourse);

     public boolean deleteCourse(long id);
}
