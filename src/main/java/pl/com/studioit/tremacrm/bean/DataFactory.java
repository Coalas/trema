package pl.com.studioit.tremacrm.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;

import org.fluttercode.demo.crudapp.model.Student;
import org.fluttercode.demo.crudapp.model.Teacher;
import pl.com.studioit.tremacrm.qualifier.DataRepository;

/**
 * This class initializes the data in the database for the demo applications. It
 * is invoked when the JSF page calls for the value of the
 * <code>#{dataStatus}</code> expression. This bean produces that value using
 * the CDI {@link Produces} annotation. When invoked, it checks to see if the
 * data already exists and if not, it generates the data before returning the
 * value. 
 * 
 * @author Andy Gibson
 * 
 */
public class DataFactory {

	private static Random random;


	@Inject
	@DataRepository
	private EntityManager entityManager;

	private List<Student> students = new ArrayList<Student>();

	private List<Teacher> teachers = new ArrayList<Teacher>();






	private Date createDate(int minDaysBack) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -random.nextInt(minDaysBack));
		return cal.getTime();
	}

	

	private <T> T getRandomItem(List<T> items) {
		return items.get(random.nextInt(items.size()));
	}

	private boolean isDataCreated() {
		return entityManager.createQuery("select c from Course c").getResultList().size() != 0;
	}

	

	private void doCustomData() {
		// do nothing, users can add additional data creation here
	}
	
	@Produces
	@Named("students")
	public List<Student> getStudents() {
		List<Student> students = entityManager.createQuery(
				"select s from Student s").setMaxResults(20)
				.getResultList();
		return students;
	}

	
}
