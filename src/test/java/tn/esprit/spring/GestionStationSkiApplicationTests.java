package tn.esprit.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.ICourseRepository;

import java.util.List;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@Slf4j
class GestionStationSkiApplicationTests {

	@Autowired
	ICourseRepository courseRepository;
	Course course  = Course.builder().numCourse(12L).level(3).typeCourse(TypeCourse.COLLECTIVE_CHILDREN).support(Support.SNOWBOARD).price(200f).timeSlot(5).build();

	@Test
	@Order(0)
	public void ajouterCourseTest() {
		course = courseRepository.save(course);
		log.info(course.toString());
	}

	@Test
	@Order(1)
	public void listSkiersTest() {
		List<Course> list= courseRepository.findAll();
		log.info(list.size()+"");
		Assertions.assertTrue(list.size() > 0);
	}
	@Test
	@Order(2)
	public void deleteCourseTest(){
		courseRepository.delete(course);
	}
	@Test
	@Order(4)
	public void countTest() {
		long size = courseRepository.count();
		Assertions.assertEquals(size, courseRepository.findAll().size());
	}

}
