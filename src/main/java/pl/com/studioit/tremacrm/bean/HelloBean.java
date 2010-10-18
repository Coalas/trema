package pl.com.studioit.tremacrm.bean;

import pl.com.studioit.tremacrm.qualifier.DataRepository;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import org.fluttercode.demo.crudapp.model.Student;


@Named("helloBean")
@RequestScoped
public class HelloBean {

	private Student person = new Student();
	private List<Student> people;

	@DataRepository
	@Inject
	private EntityManager entityManager;
	
	public String getMessage() {
		return "Hello from JSF!";
	}

	public void savePerson() {
		//save the person
		entityManager.getTransaction().begin();		
		entityManager.persist(person);
		entityManager.getTransaction().commit();
		
		//create a new person to show in the view
		person = new Student();
		
		//invalidate the list of people
		people = null;
	}

	public Student getPerson() {
		return person;
	}
	
	@Produces @Named
        @SuppressWarnings("unchecked")
	public List<Student> getPeople() {
		if (people == null) {
			people = entityManager.createQuery("select s from Student s")
					.getResultList();
		}
		return people;
	}	
}
