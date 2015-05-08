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

public class LeaderboardController extends Controller {
	@Security.Authenticated(Secured.class)
	public static Result leaderboard() {
		User loggedOnUser = User.find.byId(request().username());
        return ok(leaderboard.render(loggedOnUser,
       		 DashboardController.updateLeaderboards(),
    		 DashboardController.getUserActivities(),
    		 DashboardController.getTopLeaderboard(DashboardController.updateLeaderboards())));
    }

}