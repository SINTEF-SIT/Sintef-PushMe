package models;

import java.util.List;

import javax.persistence.*;
import play.db.ebean.*;
import play.db.ebean.Model.Finder;
import com.avaje.ebean.*;

@Entity
public class Activity extends Model {

    @Id
    public String name;
    public int step_factor;
    
    public Activity(String name, int step_factor) {
      this.name = name;
      this.step_factor = step_factor;
    }

    public static Finder<String,Activity> find = new Finder<String,Activity>(
        String.class, Activity.class
    ); 
    
    public static List<Activity> all() {
        return find.all();
    }
}