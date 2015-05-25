package controllers;

import static play.data.Form.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import play.*;
import play.data.*;
import play.mvc.*;
import models.*;
import views.html.*;
//import play.libs.Mail;
import static play.data.Form.form;


public class SignupController extends Controller {
	
	
	
    public static Result signup() {
        return ok(signup.render(Form.form(User.class), ActivityLevel.all()));
    }
    
    public static Result createUser() {
        Form<User> userForm = Form.form(User.class).bindFromRequest();
        userForm.get().isAdmin = false;
        if(userForm.hasErrors()) {
            return badRequest(
                    views.html.signup.render(userForm, ActivityLevel.all())
            );
        } else {
            User.create(userForm.get());
            generateUserModules(User.find.byId(userForm.get().email));
            return redirect(routes.IndexController.index());
        }
    }

    /*Generate modules in the db where clicks and activity are registered*/
    public static  void generateUserModules(User user){
    	
    }
}
