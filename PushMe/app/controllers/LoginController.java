package controllers;

import static play.data.Form.form;

import java.util.ArrayList;
import java.util.List;

import play.*;
import play.data.*;
import play.mvc.*;
import models.*;
import views.html.*;

public class LoginController extends Controller {

    public static class Login {

        public String email;
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
            return badRequest(index.render(loginForm, Form.form(User.class)));
        } else {
            session().clear();
            session("email", loginForm.get().email);
            return redirect(
                routes.UserActivityController.useractivity()
            );
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
