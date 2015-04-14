package controllers;

import static play.data.Form.form;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import play.*;
import play.data.*;
import play.mvc.*;
import models.*;
import views.html.*;

public class UserActivityController extends Controller {

    @Security.Authenticated(Secured.class)
    public static Result userInfo() {
    return ok(views.html.userinfo.render(User.find.byId(request().username()),Userinformation.all(), ActivityLevel.all(), Form.form(Userinformation.class)));
    }
    
    public static Result createUserInfo(String email) {
    Form<Userinformation> filledForm = Form.form(Userinformation.class).bindFromRequest();    
    if(filledForm.hasErrors()) {
        return badRequest(
                views.html.userinfo.render(User.find.byId(request().username()),
                		Userinformation.all(), 
                		ActivityLevel.all(), 
                		filledForm)
                		);
    } else {
    	List<User> users = User.all();
    	for(int i = 0; i<users.size();i++){
    		if(users.get(i).email==email){
    			filledForm.get().belongsTo = users.get(i);
    			}
    		}    	
        Userinformation.create(filledForm.get());
        return redirect(routes.IndexController.index());
    	}
    }
    
    @Security.Authenticated(Secured.class)
    public static Result useractivity(){
    	User user = User.find.byId(request().username());
//    	Form<UserSteps> userStepsForm = form(UserSteps.class).fill(UserSteps.find.byId(null));
    	return ok(useractivity.render(
    			user, 
    			findUseractivities(),
    			Activity.all(),
    			Form.form(UserSteps.class),
    			Form.form(UserActivity.class), 
    			stepCounter()));
    }

    public static List<UserActivity> findUseractivities(){
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
    public static Result updateSteps(){
    	Form<UserSteps> newSteps = form(UserSteps.class).bindFromRequest();
    	Form<UserSteps> oldSteps = form(UserSteps.class).fill(UserSteps.find.byId(null));
    	if(newSteps.hasErrors()) {
            return badRequest(
                    views.html.useractivity.render(
                			User.find.byId(request().username()), 
                			findUseractivities(),
                			Activity.all(),
                			newSteps,
                			Form.form(UserActivity.class), 
                			stepCounter()));
        }else{

            oldSteps.get().steps += newSteps.get().steps;
            oldSteps.get().save();
            return redirect(routes.UserActivityController.useractivity());
        }
        }
    
    //Register an activity
    public static Result createUserActivity(String email, String activity_name){
    	Form<UserActivity> newUA = form(UserActivity.class).bindFromRequest();
    	Timestamp date = new Timestamp(newUA.get().date.getTime());
    	newUA.get().date = date;
    	if(newUA.hasErrors()) {
            return badRequest(
                    views.html.useractivity.render(
                			User.find.byId(request().username()), 
                			findUseractivities(),
                			Activity.all(),
                			Form.form(UserSteps.class),
                			newUA, stepCounter()));
        } else {
            newUA.get().belongsTo = User.find.byId("embugge@hotmail.com");
            newUA.get().activity = Activity.find.byId("sailing");
        //Multiply the minutes with the correct step factor, according to the intensity
        if(newUA.get().intensity == 1){
        	newUA.get().steps = newUA.get().steps * newUA.get().activity.low_intensity;
        }else if(newUA.get().intensity == 2){
        	newUA.get().steps = newUA.get().steps * newUA.get().activity.medium_intensity;
        }else if(newUA.get().intensity == 3){
        	newUA.get().steps = newUA.get().steps * newUA.get().activity.high_intensity;
        }
        UserActivity.save(newUA.get());
        return redirect(routes.UserActivityController.useractivity());
        }
        }
    
    //Count total amount of steps
    public static double stepCounter(){
    	double totalSteps = 0;
    	List<UserActivity> ua = findUseractivities();
    	List<UserSteps> us = findPedoRecordings();
    	for(int i = 0;i<ua.size();i++){
    		totalSteps += ua.get(i).steps;
    	}
    	for(int i = 0;i<us.size();i++){
    		totalSteps += us.get(i).steps;
    	}
        return totalSteps;
    }        
}
