package controllers;

import static play.data.Form.form;

import java.util.ArrayList;
import java.util.List;

import play.*;
import play.data.*;
import play.mvc.*;
import models.*;
import views.html.*;

public class Application extends Controller {

	public static Result index() {
        return ok(index.render());
    }

    public static Result login() {
        return ok(login.render(Form.form(Login.class)));
    }
    
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
    
    @Security.Authenticated(Secured.class)
    public static Result userInfo() {
    return ok(
            views.html.userinfo.render(User.find.byId(request().username()),Userinformation.all(), ActivityLevel.all(), Form.form(Userinformation.class))
    );
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
        return redirect(routes.Application.index());
    }
}
    
    public static Result authenticate() {
        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        } else {
            session().clear();
            session("email", loginForm.get().email);
            return redirect(
                routes.Application.useractivity()
            );
        }
    }
    
    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(
            routes.Application.index()
        );
    }
    
    @Security.Authenticated(Secured.class)
    public static Result useractivity(){
    	return ok(useractivity.render(
    			User.find.byId(request().username()), 
    			findUseractivities(),
    			Activity.all(),
    			Form.form(UserActivity.class)));
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

    public static Result updateUA(Long id){
    	Form<UserActivity> newForm = form(UserActivity.class).bindFromRequest();
    	Form<UserActivity> oldForm = form(UserActivity.class).fill(UserActivity.find.byId(id));
        oldForm.get().steps += newForm.get().steps;
        oldForm.get().save();
        return redirect(routes.Application.useractivity());
    }
    
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
}