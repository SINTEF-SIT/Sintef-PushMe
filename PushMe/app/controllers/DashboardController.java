package controllers;

import static play.data.Form.form;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import play.*;
import play.data.*;
import play.mvc.*;
import models.*;
import views.html.*;

public class DashboardController extends Controller {
	
	@Security.Authenticated(Secured.class)
	public static Result dashboard() {
		User user = ProfileController.findUser();
		TreeMap<User, Integer> leaderboard = StatisticsController.updateLeaderboards();
        return ok(dashboard.render(user, Tips.all(), 0.0 , StatisticsController.getGoals(user), leaderboard, UserActivityController.getRecentUA(), UserActivityController.findPedoRecordings(), StatisticsController.getTopLeaderboard(leaderboard)));
    }
	
}
