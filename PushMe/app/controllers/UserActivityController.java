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
    			Activity.all(),
    			Form.form(UserSteps.class),
    			Form.form(UserActivity.class), 
    			stepCounter()));
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
    
    //Add steps from pedometer
    @Security.Authenticated(Secured.class)
    public static Result addSteps(){
    	Form<UserSteps> newSteps = form(UserSteps.class).bindFromRequest();
    	if(newSteps.hasErrors()) {
            return badRequest(
                    views.html.useractivity.render(
                			User.find.byId(request().username()), 
                			findUserActivities(),
                			Activity.all(),
                			newSteps,
                			Form.form(UserActivity.class), 
                			stepCounter()));
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
    
    public static boolean trophyGained(User user, Date date) {
    	List<Trophy> trophies = Trophy.userTrophies(user);
    	for (Trophy trophy: trophies) {
    		if(DateUtils.isSameDay(trophy.endDate, date))
    			return true;
    	}
    	return false;
    }
    
    public static void updateUserTrophies(User user) {
    	List<Goal> goals = Goal.all();
    	List<UserActivity> userActivities = findUserActivities();
    	List<UserSteps> userSteps = findPedoRecordings();
    	Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
    	Date monthCheckDate = cal.getTime();
    	cal = Calendar.getInstance();
    	while (cal.get(Calendar.DAY_OF_WEEK) > cal.getFirstDayOfWeek()) {
    	    cal.add(Calendar.DATE, -1);
    	}
    	double weekSteps = 0;
    	double monthSteps = 0;
    	for(UserActivity ua: userActivities) {
			if (ua.date.after(cal.getTime())) {
				cal.add(Calendar.DATE, 7);
				if (ua.date.before(cal.getTime())) 
					weekSteps += ua.steps;
				cal.add(Calendar.DATE, -7);
			} if (DateUtils.isSameDay(ua.date, monthCheckDate))
				monthSteps += ua.steps;
		}
    	for(UserSteps us: userSteps) {
    		if (us.date.after(cal.getTime())) {
				cal.add(Calendar.DATE, 7);
				if (us.date.before(cal.getTime())) 
					weekSteps += us.steps;
				cal.add(Calendar.DATE, -7);
			} if (DateUtils.isSameDay(us.date, monthCheckDate))
				monthSteps += us.steps;
		}
    	for (Goal g: goals) {
    		if (g.activityLevel.description == user.current_al) {
    			if (g.type == "week") {
    				cal.add(Calendar.DATE, 7);
    				if (g.steps <= weekSteps && !trophyGained(user, cal.getTime())) {
    					Trophy.createTrophy(1, "Week trophy: " + cal.toString(), cal.getTime(), user);
    				}
    			} else if (g.type == "month") {
    				cal = Calendar.getInstance();
					cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
    				if (g.steps <= monthSteps && !trophyGained(user, cal.getTime())) {
    					Trophy.createTrophy(2, "Month trophy" + cal.toString(), cal.getTime(), user);
    				}
    			} 
    		}
    	}
    }
}
