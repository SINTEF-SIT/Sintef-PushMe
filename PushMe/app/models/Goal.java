package models;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import play.db.ebean.*;
import play.db.ebean.Model.Finder;

import com.avaje.ebean.*;

@Entity
public class Goal extends Model {
	
	@Id
	public int goal_number;
	public int steps;
	public Date start;
	public Date end;
	@ManyToOne
	public User belongsTo;
	
    public static Finder<Long,Goal> find = new Finder<Long,Goal>(
            Long.class, Goal.class
        );
    
    public static List<Goal> all() {
    	List<Goal> goals = find.all();
        return goals;
    }
    
    public static void addGoal(Goal goal) {
    	goal.save();
    }
}
