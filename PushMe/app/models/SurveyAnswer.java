package models;

import java.util.List;

import javax.persistence.*;

import play.db.ebean.*;
import play.db.ebean.Model.Finder;
import play.mvc.Result;

import com.avaje.ebean.*;

import controllers.AdminController;

@Entity
public class SurveyAnswer extends Model {

    @Id
    public long id;
    public String name;
    public int question1;    
    public int question2;    
    public int question3;    
    public int question4;    
    public int question5;
    
    public User user;
    
    public static Finder<Long,SurveyAnswer> find = new Finder<Long,SurveyAnswer>(
        Long.class, SurveyAnswer.class
    ); 
    
    public static List<SurveyAnswer> all() {
        return find.all();
    }
    
    public static String update(Long id, SurveyAnswer survey) {
        survey.update((Object)id);
        return ("Your profile has been updated");
        }
    
    public static Result createSurveyAnswer(){
    	return AdminController.survey();
    }
}