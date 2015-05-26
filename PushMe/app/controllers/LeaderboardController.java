package controllers;

import static play.data.Form.form;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
        	 StatisticsController.updateLeaderboards(),
        	 UserActivityController.getUserActivities(),
    		 UserActivityController.findPedoRecordings(),
    		 StatisticsController.getTopLeaderboard(StatisticsController.updateLeaderboards()),
    		 getAverageSteps()));
    }
	
	public static Double getAverageUserSteps(User user) {
		Double averageSteps = 0.0;
		Double numberOfSteps = 0.0;
		List<UserActivity> userActivities = UserActivityController.findUserActivities(user);
		List<UserSteps> userSteps = UserActivityController.findPedoRecordings(user);
		Date date = Calendar.getInstance().getTime();
		for (UserActivity ua: userActivities) {
			numberOfSteps += ua.steps;
			if (ua.date.before(date))
				date = ua.date;
		}for (UserSteps us: userSteps) {
			numberOfSteps += us.steps;
			if (us.date.before(date))
				date = us.date;
		} long daysSinceStart = (Calendar.getInstance().getTimeInMillis() - date.getTime()) / (1000 * 60 * 60 * 24);
		if (daysSinceStart < 1)
			daysSinceStart = 1;
		averageSteps = numberOfSteps / daysSinceStart;
		return averageSteps;
	}
	
	public static HashMap<User, Integer> getAverageSteps() {
		HashMap<User, Integer> stepMap = new HashMap<User, Integer>();
		List<User> users = User.find.all();
		for (User user: users) {
			Double averageSteps = getAverageUserSteps(user);
			stepMap.put(user, averageSteps.intValue());
		}
		return stepMap;
	}
}