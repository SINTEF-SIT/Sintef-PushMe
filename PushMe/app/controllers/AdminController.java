package controllers;

import static play.data.Form.form;

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
			return ok(survey.render(ProfileController.findUser()));
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
	
	@Security.Authenticated(Secured.class)
	public static Result findUser() {
		if(ProfileController.findUser().isAdmin == true){
			return ok(findUser.render(ProfileController.findUser()));
		} else {
			return IndexController.index();
			}        
    }
	
	//Add a activity-point to the module when called
	public static Result clickTracker(String name, String email){
		Module module = null;
		User loggedOnUser = User.find.byId(email);
		List<Module> moduleList = Module.find.all();
		for(int i=0;i<moduleList.size();i++){
			if(moduleList.get(i).name.equals(name) && moduleList.get(i).belongsTo.equals(loggedOnUser)){
				module = moduleList.get(i);break;
				}
		}
		module.clickCounter++;
		Module.update(module.id, module);
		return ok("Click count registered");
	}
}
