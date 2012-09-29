package cours.ulaval.glo4003.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import cours.ulaval.glo4003.model.Course;
import cours.ulaval.glo4003.model.CourseRepository;
import cours.ulaval.glo4003.utils.ConfigManager;

@XmlRootElement(name = "courses")
public class XMLCourseRepository implements CourseRepository {

	private CoursesDTO coursesDto;

	@XmlElement(name = "course")
	private List<Course> courses = new ArrayList<Course>();

	private XMLSerializer<CoursesDTO> serializer;

	public XMLCourseRepository() throws Exception {
		serializer = new XMLSerializer<CoursesDTO>(CoursesDTO.class);
	}

	public CoursesDTO findAll() throws Exception {
		if (coursesDto == null) {
			coursesDto = serializer.deserialize(ConfigManager.getConfigManager().getCoursesFilePath());
		}

		return coursesDto;
	}

	public void setSerializer(XMLSerializer<CoursesDTO> serializer) {
		this.serializer = serializer;
	}
}
