package controller;


import entities.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.CourseService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    CourseService courseService;


    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable Long id) {
        Optional<Course> optionalCourse = courseService.getCourse(id);

        // Check if the course is present in the Optional
        if (optionalCourse.isPresent()) {
            return ResponseEntity.ok(optionalCourse.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        Course savedCourse = courseService.addCourse(course);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course newCourse) {
        Course updatedCourse = courseService.updateCourse(id, newCourse);

        if (updatedCourse != null) {
            return ResponseEntity.ok(updatedCourse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable long id) {
        boolean deleted = courseService.deleteCourse(id);

        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
