package controllers;

import static play.data.Form.form;

import java.util.ArrayList;
import java.util.List;

import controllers.LoginController.Login;
import play.*;
import play.data.*;
import play.mvc.*;
import models.*;
import views.html.*;

public class CalendarController extends Controller {
	
	public static Result calendar() {
        return ok(calendar.render());
    }

}