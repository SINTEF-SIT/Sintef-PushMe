package controllers;

import play.*;
import play.mvc.*;

public class JavascriptRoutes extends Controller {

	public static Result javascriptRoutes() {
	    response().setContentType("text/javascript");
	    return ok(
	        Routes.javascriptRouter("jsRoutes",
	            controllers.routes.javascript.DashboardController.deleteUA()
	        )
	    );
	}
	
}
