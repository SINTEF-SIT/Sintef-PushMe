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
	

 @Security.Authenticated(Secured.class)
 public static Result profile() {
 return ok(views.html.profileinfo.render(findUser(), 
		 findUserInfo(), 
		 Form.form(User.class), 
		 Form.form(Userinformation.class), 
		 ActivityLevel.all()));
    }

@Security.Authenticated(Secured.class)
public static Userinformation findUserInfo(){
 	User loggedOnUser = findUser();
	List<Userinformation> allUserinfo = Userinformation.all();
	Userinformation userinfo = null;
	
	for(int i=0;i<allUserinfo.size();i++){
		if(allUserinfo.get(i).belongsTo.equals(loggedOnUser)){
			userinfo = allUserinfo.get(i);	
	}
}
	return userinfo;
}

@Security.Authenticated(Secured.class)
public static User findUser(){
 	User loggedOnUser = User.find.byId(request().username());
	return loggedOnUser;
}

 @Security.Authenticated(Secured.class)
 public static Result updateUser()
{
	User current_user = findUser();
	Form<User> filledForm = Form.form(User.class).bindFromRequest();

	filledForm.get().email = current_user.email;
	filledForm.get().name = current_user.name;
	User.create(filledForm.get());

	return redirect(routes.DashboardController.dashboard());
}

@Security.Authenticated(Secured.class)
public static Result updateUserInfo(){
	Form<Userinformation> filledForm = Form.form(Userinformation.class).bindFromRequest();
	User currentUser = findUser();
	filledForm.get().belongsTo = currentUser;
	filledForm.get().id = findUserInfo().id;
	Userinformation.update(filledForm.get());;
	return redirect(routes.ProfileController.profile());
}
}

