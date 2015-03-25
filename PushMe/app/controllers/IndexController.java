package controllers;

import static play.data.Form.form;

import java.util.ArrayList;
import java.util.List;

import play.*;
import play.data.*;
import play.mvc.*;
import models.*;
import views.html.*;

public class IndexController extends Controller {

	public static Result index() {
        return ok(index.render());
    }

}
