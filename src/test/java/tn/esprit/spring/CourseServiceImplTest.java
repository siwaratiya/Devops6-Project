package tn.esprit.spring;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.util.CollectionUtils;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.services.CourseServicesImpl;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@ActiveProfiles("test")
public class CourseServiceImplTest {
    @Autowired
    private CourseServicesImpl courseServices;

    @Test
    void addCourse() {
    }

   /* @Test
    @DatabaseSetup("/data-set/course-data.xml")
    void retrieveStock() {
        final Course course = this.courseServices.retrieveCourse(1L);
        assertEquals("course 1", course.getTypeCourse());
    }*/

  /*  @Test
    @DatabaseSetup("/data-set/course-data.xml")
    void retrieveAllStock() {
        final List<Course> allStocks = this.courseServices.retrieveAllCourses();
        if (!CollectionUtils.isEmpty(allStocks)) {
            assertEquals(allStocks.size(), 1);
        }
    }*/
}
