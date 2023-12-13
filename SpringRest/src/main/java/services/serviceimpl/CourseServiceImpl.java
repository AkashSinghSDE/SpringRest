package services.serviceimpl;

import dao.CourseDao;
import entities.Course;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.CourseService;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    @Override
    public List<Course> getCourses() {
        return courseDao.findAll();
    }

    @Override
    public Optional<Course> getCourse(Long courseId) {
        return courseDao.findById(courseId);
    }

    @Override
    public Course addCourse(Course course) {
        return courseDao.save(course);
    }

    @Override
    public Course updateCourse(Long id, Course newCourse) {
        // Only update an course if it can be found first.
        Course course1 = new Course();
        courseDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + id));

        course1.setTitle(newCourse.getTitle());
        course1.setDescription(newCourse.getDescription());
        courseDao.save(course1);
        return (course1);
    }

    @Override
    public boolean deleteCourse(long id) {
        if (courseDao.existsById(id)) {
            courseDao.deleteById(id);
            return true; // Course deleted successfully
        } else {
            return false; // Course not found
        }
    }

}
