package tn.esprit.spring;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.services.CourseServicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;

@Slf4j
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class GestionStationCourseMockito {

    @Mock
    ICourseRepository courseRepository;
    @InjectMocks
    CourseServicesImpl courseServices;
    Course course  = Course.builder().numCourse(12L).level(3).typeCourse(TypeCourse.COLLECTIVE_CHILDREN).support(Support.SNOWBOARD).price(200f).timeSlot(5).build();
    List<Course> listTest= new ArrayList<Course>() {
        {
            add(Course.builder().numCourse(12L).level(3).typeCourse(TypeCourse.COLLECTIVE_CHILDREN).support(Support.SNOWBOARD).price(200f).timeSlot(5).build());
            add(Course.builder().numCourse(15L).level(4).typeCourse(TypeCourse.INDIVIDUAL).support(Support.SKI).price(300f).timeSlot(4).build());
        }
    };
    @Test
    public void addCourseTest() {
        Mockito.when(courseRepository.save(Mockito.any(Course.class))).then(invocation -> {
            Course model = invocation.getArgument(0, Course.class);
            model.setNumCourse((long) 1);
            return model;
        });
        log.info("Avant ==> " + course.toString());
        Course c = courseServices.addCourse(course);
        assertSame(c, course);
        log.info("Après ==> " + course.toString());
    }

    @Test
    public void retreiveCourseTest() {
        Mockito.when(courseRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(course));
        Course c = courseServices.retrieveCourse((long) 3);
        assertNotNull(c);
        log.info("get ==> " + c.toString());
        verify(courseRepository).findById(Mockito.anyLong());

    }
    @Test
    public void retreiveAllMagasinTest() {
        Mockito.when(courseRepository.findAll()).thenReturn(listTest);
        List<Course> courses = courseServices.retrieveAllCourses();
        assertNotNull(courses);
    }

}
