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
    public int low_intensity;
    public int medium_intensity;
    public int high_intensity;
    
    public Activity(String name, int low_intensity, int medium_intensity, int high_intensity) {
      this.name = name;
      this.low_intensity = low_intensity;
      this.medium_intensity = medium_intensity;
      this.high_intensity = high_intensity;
    }

    public static Finder<String,Activity> find = new Finder<String,Activity>(
        String.class, Activity.class
    ); 
    
    public static List<Activity> all() {
        return find.all();
    }
}