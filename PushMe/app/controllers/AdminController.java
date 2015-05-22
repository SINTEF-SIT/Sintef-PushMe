package controllers;

import static play.data.Form.form;

import play.*;
import play.data.*;
import play.mvc.*;
import models.*;
import views.html.*;

public class AdminController extends Controller {

	@Security.Authenticated(Secured.class)
	public static Result calendar() {
        return ok(adminStatistics.render(ProfileController.findUser()));
    }
	
}
