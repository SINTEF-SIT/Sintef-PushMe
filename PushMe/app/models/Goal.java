package models;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import play.db.ebean.*;
import play.db.ebean.Model.Finder;

import com.avaje.ebean.*;

@Entity
public class Goal extends Model {
	
	//Goals are handled in DashboardController
	@Id
	public int goal_number;
	public double steps;
	public Date start;
	public Date end;
	public String type;
	@ManyToOne
	public ActivityLevel activityLevel;
	
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

    
	public void createWeekGoal(String activityLevel) {
		Goal goal = new Goal();
		goal.steps = 70000; //TODO: add ActivityLevel
		Calendar cal = Calendar.getInstance();
		goal.start = cal.getTime();
		cal.add(Calendar.DATE, 7);
		goal.end = cal.getTime();
		for (ActivityLevel al: ActivityLevel.all()) {
			if(al.description == activityLevel) {
				goal.activityLevel =  al;
				break;
			}
		}
	}
	
	public void createMonthGoal(String activityLevel) {
		Goal goal = new Goal();
		goal.steps = 300000; //TODO add activityLevel
		Calendar cal = Calendar.getInstance();
		cal.set(cal.YEAR, cal.MONTH, 1);
		goal.start = cal.getTime();
		cal.set(cal.YEAR, cal.MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		goal.end = cal.getTime();
		for (ActivityLevel al: ActivityLevel.all()) {
			if(al.description == activityLevel) {
				goal.activityLevel =  al;
				break;
			}
		}
	}
}
