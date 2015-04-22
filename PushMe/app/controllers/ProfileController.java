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
		 Form.form(User.class),
		 ActivityLevel.all()));
    }


@Security.Authenticated(Secured.class)
public static User findUser(){
 	User loggedOnUser = User.find.byId(request().username());
	return loggedOnUser;
}

 @Security.Authenticated(Secured.class)
 public static Result updateUser(){
	User current_user = findUser();
	Form<User> filledForm = Form.form(User.class).bindFromRequest();
	User.create(filledForm.get());
	return redirect(routes.DashboardController.dashboard());
}
}

