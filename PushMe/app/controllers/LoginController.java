package controllers;

import static play.data.Form.form;

import java.util.ArrayList;
import java.util.List;

import play.*;
import play.data.*;
import play.data.validation.Constraints.Required;
import play.mvc.*;
import models.*;
import views.html.*;

public class LoginController extends Controller {

    public static class Login {
        @Required
        public String email;
        @Required
        public String password;
        
        public String validate() {
            if (User.authenticate(email, password) == null) {
              return "Invalid user or password";
            }
            return null;
        }

    }
    
    //A separete login view will be implemented later
    public static Result login() {
        return ok(login.render(Form.form(Login.class)));
    }

    public static Result authenticate() {
        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        } else {
            session().clear();
            session("email", loginForm.get().email);
            if(User.find.byId(loginForm.get().email).isAdmin == false){
            	return redirect(routes.DashboardController.dashboard());
            	} else {
            		return redirect(routes.AdminController.adminDashboard());
            	}
        }
    }
    
    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(
            routes.IndexController.index()
        );
    }
}
