package models;

import java.sql.Timestamp;
import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
public class UserSteps extends Model {

	@ManyToOne
    public User belongsTo;
    public double steps;
    public Date date;

    
    public UserSteps(User belongsTo, double steps, Date date) {
      this.belongsTo = belongsTo;
      this.steps = steps;
      this.date = date;
    }

    public static Finder<String,UserSteps> find = new Finder<String,UserSteps>(
        String.class, UserSteps.class
    ); 
    
    public static List<UserSteps> all() {
        return find.all();
    }
    
	public static void save(UserSteps newSteps) {
		newSteps.save();		
	}
}