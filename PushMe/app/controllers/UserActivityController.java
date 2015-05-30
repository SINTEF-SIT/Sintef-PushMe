package controllers;

import static play.data.Form.form;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;

import play.*;
import play.data.*;
import play.mvc.*;
import models.*;
import views.html.*;

public class UserActivityController extends Controller {

    
    @Security.Authenticated(Secured.class)
    public static Result useractivity(){
    	return ok(useractivity.render(
    			User.find.byId(request().username()), 
    			findUserActivities(),
    			UserActivityController.findPedoRecordings(),
    			Activity.all(),
    			Form.form(UserSteps.class),
    			Form.form(UserActivity.class), 
    			stepCounter(),
    			StatisticsController.updateLeaderboards(),
    			StatisticsController.getTopLeaderboard(StatisticsController.updateLeaderboards())
    			));
    }

    public static Activity findActivityByName(String name){
    	List<Activity> activities = Activity.all();
    	Activity activity = null;
    	for(int i=0;i<activities.size();i++){
    		if(activities.get(i).name.equals(name)){
    			activity = activities.get(i);
    			break;
    		}
    	}
    	return activity;
    }
    
    @Security.Authenticated(Secured.class)
    public static List<UserActivity> findUserActivities(){
    	User user = User.find.byId(request().username());
    	List<UserActivity> ua = UserActivity.all();
    	List<UserActivity> user_ua = new ArrayList<UserActivity>();
    	for(int i=0;i<ua.size();i++){
    		if(ua.get(i).belongsTo.email.equals(user.email)){
    			user_ua.add(ua.get(i));
    		}
    	}
    	return user_ua;
    }
    
    @Security.Authenticated(Secured.class)
    public static List<UserActivity> findUserActivities(User user){
    	List<UserActivity> ua = UserActivity.all();
    	List<UserActivity> user_ua = new ArrayList<UserActivity>();
    	for(int i=0;i<ua.size();i++){
    		if(ua.get(i).belongsTo.email.equals(user.email)){
    			user_ua.add(ua.get(i));
    		}
    	}
    	return user_ua;
    }
    
    @Security.Authenticated(Secured.class)
    public static List<UserSteps> findPedoRecordings(){
    	User user = User.find.byId(request().username());
    	List<UserSteps> us = UserSteps.all();
    	List<UserSteps> user_us = new ArrayList<UserSteps>();
    	for(int i=0;i<us.size();i++){
    		if(us.get(i).belongsTo.email.equals(user.email)){
    			user_us.add(us.get(i));
    		}
    	}
    	return user_us;
    }
    
    @Security.Authenticated(Secured.class)
    public static List<UserSteps> findPedoRecordings(User user){
    	List<UserSteps> us = UserSteps.all();
    	List<UserSteps> user_us = new ArrayList<UserSteps>();
    	for(int i=0;i<us.size();i++){
    		if(us.get(i).belongsTo.email.equals(user.email)){
    			user_us.add(us.get(i));
    		}
    	}
    	return user_us;
    }
    
    //Add steps from pedometer
    @Security.Authenticated(Secured.class)
    public static Result addSteps(){
    	Form<UserSteps> newSteps = form(UserSteps.class).bindFromRequest();
    	if(newSteps.hasErrors()) {
            return badRequest(
                    views.html.useractivity.render(
                			User.find.byId(request().username()), 
                			findUserActivities(),
                			UserActivityController.findPedoRecordings(),
                			Activity.all(),
                			newSteps,
                			Form.form(UserActivity.class), 
                			stepCounter(),
                			StatisticsController.updateLeaderboards(),
                			StatisticsController.getTopLeaderboard(StatisticsController.updateLeaderboards())));
        }else{
        	Timestamp date = new Timestamp(newSteps.get().date.getTime());
        	newSteps.get().date = date;
        	User user = User.find.byId(request().username());
        	newSteps.get().belongsTo = user;
        	UserSteps.save(newSteps.get());
        	updateUserTrophies(user);
            return redirect(routes.UserActivityController.useractivity());
        }
    }
    
    //Register an activity
    @Security.Authenticated(Secured.class)
    public static Result createUserActivity(String a){
    	Form<UserActivity> newUA = form(UserActivity.class).bindFromRequest();
    	Timestamp date = new Timestamp(newUA.get().date.getTime());
    	newUA.get().date = date;
    	User user = User.find.byId(request().username());
        newUA.get().belongsTo = user;
        newUA.get().activity = findActivityByName(a);
    	if(newUA.hasErrors()) {
    		return redirect(routes.IndexController.index());
        } else {

        //Multiply the minutes with the correct step factor, according to the intensity
        if(newUA.get().intensity == 1){
        	newUA.get().steps = newUA.get().steps * newUA.get().activity.low_intensity;
        }else if(newUA.get().intensity == 2){
        	newUA.get().steps = newUA.get().steps * newUA.get().activity.medium_intensity;
        }else if(newUA.get().intensity == 3){
        	newUA.get().steps = newUA.get().steps * newUA.get().activity.high_intensity;
        }
        UserActivity.save(newUA.get());
        updateUserTrophies(user);
        return redirect(routes.UserActivityController.useractivity());
        }
    }

    //Count total amount of steps
    @Security.Authenticated(Secured.class)
    public static double stepCounter(){
    	double totalSteps = 0;
    	List<UserActivity> ua = findUserActivities();
    	List<UserSteps> us = findPedoRecordings();
    	for(int i = 0;i<ua.size();i++){
    		totalSteps += ua.get(i).steps;
    	}
    	for(int i = 0;i<us.size();i++){
    		totalSteps += us.get(i).steps;
    	}
        return totalSteps;
    }
    
    public static boolean trophyNotGained(User user, Date date) {
    	List<Trophy> trophies = Trophy.userTrophies(user);
    	for (Trophy trophy: trophies) {
    		if(DateUtils.isSameDay(trophy.endDate, date))
    			return false;
    	}
    	return true;
    }
    
    
    public static List<UserActivity> getRecentUA() {
    	List<UserActivity> activities = UserActivityController.findUserActivities(); 	
    	return activities;
    }
    
    public static Result deleteUA(Long id) {
    	UserActivity.find.ref(id).delete();
    	return controllers.UserActivityController.useractivity();
    }
    
    public static Result deletePedo(Long id) {
    	UserSteps.find.ref(id).delete();
    	return controllers.UserActivityController.useractivity();
    }
    
    public static void updateUserTrophies(User user) {
    	List<Goal> goals = Goal.all();
    	List<UserActivity> userActivities = findUserActivities();
    	List<UserSteps> userSteps = findPedoRecordings();
    	Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
    	Date lastMonthDate = cal.getTime();
    	cal.set(Calendar.DAY_OF_MONTH, 1);
    	Date firstMonthDate = cal.getTime();
    	cal.clear();
    	cal = Calendar.getInstance();
    	cal.set(Calendar.DAY_OF_WEEK, 1);
    	double weekSteps = 0;
    	double monthSteps = 0;
    	for(UserActivity ua: userActivities) {
			if (ua.date.after(cal.getTime())) {
				cal.set(Calendar.DAY_OF_WEEK, cal.getActualMaximum(Calendar.DAY_OF_WEEK));
				if (ua.date.before(cal.getTime())) 
					weekSteps += ua.steps;
				cal.set(Calendar.DAY_OF_WEEK, 1);
			} if (ua.date.after(firstMonthDate) && ua.date.before(lastMonthDate))
				monthSteps += ua.steps;
		}
    	for(UserSteps us: userSteps) {
    		if (us.date.after(cal.getTime())) {
    			cal.set(Calendar.DAY_OF_WEEK, cal.getActualMaximum(Calendar.DAY_OF_WEEK));
				if (us.date.before(cal.getTime())) 
					weekSteps += us.steps;
				cal.set(Calendar.DAY_OF_WEEK, 1);
			} if (us.date.after(firstMonthDate) && us.date.before(lastMonthDate))
				monthSteps += us.steps;
		}
    	for (Goal g: goals) {
    		if (g.activityLevel.description.trim().equals(user.current_al.trim())) {
    			if (g.type.equals("week")) {
    				cal.set(Calendar.DAY_OF_WEEK, cal.getActualMaximum(Calendar.DAY_OF_WEEK));
    				if (g.steps <= weekSteps && trophyNotGained(user, cal.getTime())) {
    					Trophy.createTrophy(1, "Week trophy: " + cal.getTime().toString(), cal.getTime(), user);
    				}
    			} if (g.type.equals("month") ) {
    				cal = Calendar.getInstance();
					cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
    				if (g.steps <= monthSteps && trophyNotGained(user, cal.getTime())) {
    					Trophy.createTrophy(2, "Month trophy: " + cal.getTime().toString(), cal.getTime(), user);
    				}
    			} 
    		}
    	}
    }
}
