package models;

import java.sql.Timestamp;
import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
public class UserSteps extends Model {

	@Id
	public int id;
	@ManyToOne
    public User belongsTo;
    public double steps;
    public Date date;

    
    public UserSteps(int id, User belongsTo, double steps, Date date) {
    	this.id = id;
    	this.belongsTo = belongsTo;
    	this.steps = steps;
    	this.date = date;
    }

    public static Finder<Long,UserSteps> find = new Finder<Long,UserSteps>(
        Long.class, UserSteps.class
    ); 
    
    public static List<UserSteps> all() {
        return find.all();
    }
    
	public static void save(UserSteps newSteps) {
		newSteps.save();		
	}
}