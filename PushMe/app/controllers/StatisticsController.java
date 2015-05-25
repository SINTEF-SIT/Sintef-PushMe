package controllers;

import static play.data.Form.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

import play.*;
import play.data.*;
import play.mvc.*;
import models.*;
import views.html.*;
//import play.libs.Mail;
import static play.data.Form.form;


public class StatisticsController extends Controller {
	
	
	@Security.Authenticated(Secured.class)
    public static Result statistics() {
    	TreeMap<User, Integer> leaderboard = DashboardController.updateLeaderboards();
        return ok(statistics.render(User.find.byId(request().username()), 
        		DashboardController.getRecentUA(),
        		UserActivityController.findPedoRecordings(),
        		leaderboard,
        		DashboardController.getTopLeaderboard(leaderboard)
        		));
    }
    
}
