package models;

import java.util.List;

import javax.persistence.*;

import play.data.Form;
import play.db.ebean.*;
import play.db.ebean.Model.Finder;
import play.mvc.*;

import com.avaje.ebean.*;

import controllers.AdminController;
import controllers.ProfileController;


@Entity
public class Survey extends Model {

    @Id
    public long id;
    public String name;
    public String question1;
    public String option1_1;
    public String option2_1;
    public String option3_1;
    
    public String question2;
    public String option1_2;
    public String option2_2;
    public String option3_2;
    
    public String question3;
    public String option1_3;
    public String option2_3;
    public String option3_3;
    
    public String question4;
    public String option1_4;
    public String option2_4;
    public String option3_4;
    
    public String question5;
    public String option1_5;
    public String option2_5;
    public String option3_5;
    
    public static Finder<Long,Survey> find = new Finder<Long,Survey>(
        Long.class, Survey.class
    ); 
    
    public static List<Survey> all() {
        return find.all();
    }
    
    public static String update(Long id, Survey survey) {
        survey.update((Object)id);
        return ("Your profile has been updated");
        }
}