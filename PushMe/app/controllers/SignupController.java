package controllers;

import static play.data.Form.form;

import java.util.ArrayList;
import java.util.List;

import play.*;
import play.data.*;
import play.mvc.*;
import models.*;
import views.html.*;

public class SignupController extends Controller {
	
    public static Result signup() {
        return ok(signup.render(Form.form(User.class)));
    }
    
    public static Result createUser() {
        Form<User> userForm = Form.form(User.class).bindFromRequest();
        if(userForm.hasErrors()) {
            return badRequest(
                    views.html.signup.render(userForm)
            );
        } else {
            User.create(userForm.get());
            return redirect(routes.Application.login());
        }
    }
    
}
