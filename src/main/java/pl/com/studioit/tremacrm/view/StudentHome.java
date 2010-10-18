package pl.com.studioit.tremacrm.view;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.fluttercode.demo.crudapp.bean.HomeBean;
import org.fluttercode.demo.crudapp.model.Student;

@Named
@ConversationScoped
public class StudentHome extends HomeBean<Student>{

}
