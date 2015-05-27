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

    
	public static void createWeekGoal(String activityLevel) {
		Goal goal = new Goal();
		goal.type = "week";
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, cal.getActualMaximum(Calendar.DAY_OF_WEEK));
		goal.end = cal.getTime();
		cal.add(Calendar.DATE, -6);
		goal.start = cal.getTime();
		for (ActivityLevel al: ActivityLevel.all()) {
			if(al.description.equals(activityLevel)) {
				goal.activityLevel =  al;
				goal.steps = 7*5000*al.level;
				break;
			}
		}
		addGoal(goal);
	}
	
	public static void createMonthGoal(String activityLevel) {
		Goal goal = new Goal();
		goal.type = "month";
		Calendar cal = Calendar.getInstance();
		cal.set(cal.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		goal.end = cal.getTime();
		cal.add(cal.DAY_OF_MONTH, -(cal.getActualMaximum(Calendar.DAY_OF_MONTH) - 1));
		goal.start = cal.getTime();
		for (ActivityLevel al: ActivityLevel.all()) {
			if(al.description.equals(activityLevel)) {
				goal.activityLevel =  al;
				goal.steps = cal.getActualMaximum(Calendar.DAY_OF_MONTH)*5000*al.level;
			}
		}
		addGoal(goal);
	}
}
