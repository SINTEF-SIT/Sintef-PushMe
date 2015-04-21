package controllers;


import static play.data.Form.form;

import java.util.ArrayList;
import java.util.List;

import play.*;
import play.data.*;
import play.mvc.*;
import models.*;
import views.html.*;
package controllers


public class CalendarController extends Controller  {
	 def index = Action {
        Ok(views.html.calendar())
    }

}
