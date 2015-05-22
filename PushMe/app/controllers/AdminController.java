package controllers;

import static play.data.Form.form;

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
	public static Result findUser() {
		if(ProfileController.findUser().isAdmin == true){
			return ok(findUser.render(ProfileController.findUser()));
		} else {
			return IndexController.index();
			}        
    }
	
}
