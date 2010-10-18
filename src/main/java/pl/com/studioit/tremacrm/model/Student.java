package org.fluttercode.demo.crudapp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Student entity that extends the {@link Person} class and adds a
 * <code>gpa</code> attribute as well as a list of classes they are enrolled in.
 * 
 * @author Andy Gibson
 * 
 */
@Entity
@DiscriminatorValue("STU")
public class Student extends Person {

	public Student() {
	}

	public Student(String firstName, String lastName) {
		super(firstName, lastName);
	}

	@Min(0)
	@Max(4)
	private Float gpa;

	



	public Float getGpa() {
		return gpa;
	}

	public void setGpa(Float gpa) {
		this.gpa = gpa;
	}

}
