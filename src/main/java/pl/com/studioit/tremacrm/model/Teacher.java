package org.fluttercode.demo.crudapp.model;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * Teacher entity that extends the {@link Person} class and adds a list of
 * classes they are teaching.
 * 
 * @author Andy Gibson
 * 
 */
@Entity
@DiscriminatorValue("TEA")
public class Teacher extends Person {

	public Teacher() {
	}

	public Teacher(String firstName, String lastName) {
		super(firstName, lastName);
	}

	

}
