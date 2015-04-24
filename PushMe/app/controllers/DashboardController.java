package controllers;

import static play.data.Form.form;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import play.*;
import play.data.*;
import play.mvc.*;
import models.*;
import views.html.*;

public class DashboardController extends Controller {
	
	@Security.Authenticated(Secured.class)
	public static Result dashboard() {
        return ok(dashboard.render(User.find.byId(request().username()), Tips.all(), 0.0));
    }
	
	@Security.Authenticated(Secured.class)
	public static Result generateDailyBar(){
		double dailySteps = 0.0;
		Form<UserActivity> filledForm = form(UserActivity.class).bindFromRequest();
		Date date = filledForm.get().date;
		List<UserActivity> ua = UserActivityController.findUseractivities();
		for (UserActivity activity: ua) {
			if(activity.date.equals(date))
				dailySteps += activity.steps;
		} List<UserSteps> us = UserActivityController.findPedoRecordings();
		for (UserSteps step: us) {
			if(step.date.equals(date))
				dailySteps += step.steps;
		} return ok(dashboard.render(User.find.byId(request().username()), Tips.all(), dailySteps));
	}
	
    @Security.Authenticated(Secured.class)
    public static List<UserActivity> getUserActivities(){
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
    public static List<UserActivity> getGlobalActivities(){
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
    public static List<Integer> getSteps(Timestamp date){
    	User user = User.find.byId(request().username());
    	int stepsToday = 0;
    	int stepsWeek = 0;
    	int stepsMonth = 0;
    	List<UserSteps> us = UserSteps.all();
    	List<UserSteps> user_us = new ArrayList<UserSteps>();
    	
    	for(int i=0;i<us.size();i++){
    		if(us.get(i).belongsTo.email.equals(user.email)){
    			user_us.add(us.get(i));
    		}
    	}
    	
    	Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
//        Timestamp todate1 = cal.getTime().; 
    	for(int i=0;i<us.size();i++){
    		if(user_us.get(i).date.getDate() == date.getDate()){
    			user_us.add(us.get(i));
    		} if(user_us.get(i).date.getDate() == date.getDate()) {
    			
    		}
    	}
    	List<Integer> steps = new ArrayList<Integer>();	
    	
    	return steps;
    }
    
	public void updateMorris(User user) {
		//TODO: Update donut with the users activities
		//TODO: Update donut with everyone elses activities
	}
	
	public void updateStepProgressionBar() {
		//TODO: Update dayli progression bar with steps vs goal
		//TODO: Update weekly progression bar with steps vs goal
		//TODO: Update monthly progression bar with steps vs goal
	}
}
