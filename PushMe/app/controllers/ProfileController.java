package controllers;


import static play.data.Form.form;

import java.util.ArrayList;
import java.util.List;

import play.*;
import play.data.*;
import play.mvc.*;
import models.*;
import views.html.*;

public class ProfileController extends Controller {

//    public static Result profile() {
//        return ok(userinfo.render(Form.form(User.class)));
//    }
	

 @Security.Authenticated(Secured.class)
 public static Result profile() {
 	User loggedOnUser = User.find.byId(request().username());
 return ok(views.html.profileinfo.render(loggedOnUser, findUserInfo(loggedOnUser), Form.form(User.class), Form.form(Userinformation.class), ActivityLevel.all()));
    }

public static Userinformation findUserInfo(User user){
	List<Userinformation> allUserinfo = Userinformation.all();
	Userinformation userinfo = null;
	for(int i=0;i<allUserinfo.size();i++){
		if(allUserinfo.get(i).belongsTo == user){
			userinfo = allUserinfo.get(i);
		}
	}
	return userinfo;
}
 @Security.Authenticated(Secured.class)
 public static Result updateUser()
{
	User current_user = User.find.byId(request().username());
	Form<User> filledForm = Form.form(User.class).bindFromRequest();

	filledForm.get().email = current_user.email;
	filledForm.get().name = current_user.name;
	User.create(filledForm.get());

	return redirect(routes.DashboardController.dashboard());
}

@Security.Authenticated(Secured.class)
public static Result updateUserInfo(){
	Form<Userinformation> filledForm = Form.form(Userinformation.class).bindFromRequest();
	User current_user = User.find.byId(request().username());
	Form<Userinformation> oldForm = Form.form(Userinformation.class);
	oldForm.fill(findUserInfo(current_user));

	filledForm.get().belongsTo = current_user;
	filledForm.get().dob = oldForm.get().dob;
	filledForm.get().gender = oldForm.get().gender;
	Userinformation.create(filledForm.get());
	return redirect(routes.DashboardController.dashboard());
}
}

