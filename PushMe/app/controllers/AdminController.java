package controllers;

import static play.data.Form.form;

import java.util.ArrayList;
import java.util.List;

import play.*;
import play.data.*;
import play.mvc.*;
import models.*;
import views.html.*;

public class AdminController extends Controller {

	@Security.Authenticated(Secured.class)
	public static Result adminStatistics() {
		if(ProfileController.findUser().isAdmin == true){
			return ok(adminStatistics.render(ProfileController.findUser()));
		} else {
			return IndexController.index();
			}        
    }
	
	@Security.Authenticated(Secured.class)
	public static Result survey() {
		if(ProfileController.findUser().isAdmin == true){
			return ok(survey.render(ProfileController.findUser(),  Survey.find.all()));
		} else {
			return IndexController.index();
			}        
    }
	
	@Security.Authenticated(Secured.class)
	public static Result createSurvey() {
		if(ProfileController.findUser().isAdmin == true){
			return ok(createSurvey.render(ProfileController.findUser()));
		} else {
			return IndexController.index();
			}        
    }
	
	//Render editSurvey.scala.html
	@Security.Authenticated(Secured.class)
	public static Result editSurvey(Long id) {
		Survey survey = Survey.find.byId(id);
		if(ProfileController.findUser().isAdmin == true){
			return ok(editSurvey.render(ProfileController.findUser(), survey));
		} else {
			return IndexController.index();
			}        
    }
	
	//Create a survey
	@Security.Authenticated(Secured.class)
	public static Result createSurveyForm(){
    	Form<Survey> form = Form.form(Survey.class).bindFromRequest();
    	form.get().save();
    	return AdminController.survey();
    }
	
	//Edit a survey
	@Security.Authenticated(Secured.class)
	public static Result editSurveyForm(Long id){
    	Form<Survey> form = Form.form(Survey.class).bindFromRequest();
    	Survey.update(id, form.get());
    	return AdminController.survey();
    }
	
	@Security.Authenticated(Secured.class)
	public static Result findUser() {
		if(ProfileController.findUser().isAdmin == true){
			return ok(findUser.render(ProfileController.findUser()));
		} else {
			return IndexController.index();
			}        
    }
	
	//Find the modules belonging to the logged on user
	public static List<UserModule> findUserModules(User user){
		List<UserModule> userModuleList = new ArrayList<UserModule>();
		List<UserModule> moduleList = UserModule.find.all();
		for(UserModule i : moduleList){
			if(i.user.equals(user)){
				userModuleList.add(i);
			}
		}
		return userModuleList;
		
	}
	
	//Add a activity-point to the module when called
	public static Result clickTracker(String name, String email){
		UserModule module = null;
		User loggedOnUser = User.find.byId(email);
		List<UserModule> moduleList = findUserModules(loggedOnUser);
		for(UserModule i : moduleList){
			if(i.module.name.equals(name)){
				module = i;
				break;
				}
		}
		module.clickCounter++;
		UserModule.update(module.id, module);
		return ok("Click count registered");
	}
}
