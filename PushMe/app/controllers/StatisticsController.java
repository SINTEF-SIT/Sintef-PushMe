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


public class StatisticsController extends Controller {
	
	
	@Security.Authenticated(Secured.class)
    public static Result statistics() {
    	TreeMap<User, Integer> leaderboard = updateLeaderboards();
    	User user = ProfileController.findUser();
        return ok(statistics.render(User.find.byId(request().username()), 
        		UserActivityController.getRecentUA(),
        		UserActivityController.findPedoRecordings(),
        		leaderboard,
        		getTopLeaderboard(leaderboard),
        		updateMonthLine(), 
        		updateYearLine(),
        		updateMorris(),
        		getGoals(user)
        		));
    }
	
	@Security.Authenticated(Secured.class)
	public static Result generateDailyBar(){
		double dailySteps = 0.0;
		Form<UserActivity> filledForm = form(UserActivity.class).bindFromRequest();
		Date date = filledForm.get().date;
		List<UserActivity> ua = UserActivityController.findUserActivities();
		for (UserActivity activity: ua) {
			if(activity.date.equals(date))
				dailySteps += activity.steps;
		} List<UserSteps> us = UserActivityController.findPedoRecordings();
		for (UserSteps step: us) {
			if(step.date.equals(date))
				dailySteps += step.steps;
		} User user = User.find.byId(request().username());
		TreeMap<User, Integer> leaderboard = updateLeaderboards();
		return ok(statistics.render(User.find.byId(request().username()), 
				UserActivityController.getRecentUA(),
        		UserActivityController.findPedoRecordings(),
        		leaderboard,
        		getTopLeaderboard(leaderboard),
        		updateMonthLine(), 
        		updateYearLine(),
        		updateMorris(),
        		getGoals(user)
        		));
	}
    
    @Security.Authenticated(Secured.class)
    public static List<UserActivity> getGlobalActivities(){
    	User user = User.find.byId(request().username());
    	List<UserActivity> uas = UserActivity.all();
    	List<UserActivity> userUas = new ArrayList<UserActivity>();
    	for(UserActivity ua: uas){
    		if(ua.belongsTo.email.equals(user.email))
    			userUas.add(ua);
    	} return userUas;
    }
    
    @Security.Authenticated(Secured.class)
    public static List<Integer> getSteps(Timestamp date){
    	User user = User.find.byId(request().username());
    	int stepsToday = 0;
    	int stepsWeek = 0;
    	int stepsMonth = 0;
    	List<UserSteps> us = UserSteps.all();
    	List<UserSteps> user_us = new ArrayList<UserSteps>();
    	
    	for(int i=0;i<us.size();i++){
    		if(us.get(i).belongsTo.email.equals(user.email)){
    			user_us.add(us.get(i));
    		}
    	}
    	
    	Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
    	for(int i=0;i<us.size();i++){
    		if(user_us.get(i).date.getDate() == date.getDate()){
    			user_us.add(us.get(i));
    		} if(user_us.get(i).date.getDate() == date.getDate()) {
    			
    		}
    	}
    	List<Integer> steps = new ArrayList<Integer>();	
    	
    	return steps;
    }
    
    public static TreeMap<User, Integer> getTopLeaderboard(Map<User, Integer> leaderboard) {
    	Map<User, Integer> topLeaderboard = new HashMap<User, Integer>();
    	int i = 0;
    	for (Map.Entry<User, Integer> entry: leaderboard.entrySet()) {
    		topLeaderboard.put(entry.getKey(), entry.getValue());
    		if(i++ >= 4)
    			break;
    	}
    	LeaderboardComparator comp = new LeaderboardComparator(topLeaderboard);
    	TreeMap<User, Integer> sortedLeaderboard = new TreeMap<User, Integer>(comp);
    	sortedLeaderboard.putAll(topLeaderboard);
    	return sortedLeaderboard;
    }
    
    public static TreeMap<User, Integer> updateLeaderboards() {
    	List<User> users = User.all();
    	Map<User, Integer> leaderboard = new HashMap<User, Integer>();
    	List<Trophy> trophies = Trophy.all();
    	for (User user: users) {
    		leaderboard.put(user, 0);
    	} for (Trophy trophy: trophies) {
    		leaderboard.put(trophy.user, leaderboard.get(trophy.user) + trophy.points);
    	}
    	LeaderboardComparator comp = new LeaderboardComparator(leaderboard);
    	TreeMap<User, Integer> sortedLeaderboard = new TreeMap<User, Integer>(comp);
    	sortedLeaderboard.putAll(leaderboard);
    	return sortedLeaderboard;
    }

static class LeaderboardComparator implements Comparator<User> {
	Map<User, Integer> board;
	
	public LeaderboardComparator(Map<User, Integer> board) {
		this.board = board;
	}
	
	public int compare(User a, User b) {
		if (board.get(a) >= board.get(b))
			return -1;
		else
			return 1;
	}
}
    
    public static List<Goal> getGoals(User user) {
    	autoCreateGoals();
    	List<Goal> goals = Goal.all();
    	List<Goal> userGoals = new ArrayList<Goal>();
    	for (Goal goal: goals) {
    		if (user.current_al.trim().equals(goal.activityLevel.description.trim()))
    			userGoals.add(goal);
    	}
    	return userGoals;
    }
    
    public static void autoCreateGoals() {
		//Checks if there are a goal for this period, creates new if not!
    	boolean weekCheck = false, monthCheck = false;
    	List<Goal> goals = Goal.find.all();
    	Calendar weekCal = Calendar.getInstance();
    	weekCal.set(Calendar.DAY_OF_WEEK, weekCal.getActualMaximum(Calendar.DAY_OF_WEEK));
    	Calendar monthCal = Calendar.getInstance();
    	monthCal.set(Calendar.DAY_OF_MONTH, monthCal.getActualMaximum(Calendar.DAY_OF_MONTH));
    	SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		for (Goal g: goals) {
			if (g.type.equals("week") && fmt.format(g.end).equals(fmt.format(weekCal.getTime())))
				weekCheck = true;
			else if (g.type.equals("month") && fmt.format(g.end).equals(fmt.format(monthCal.getTime())))
				monthCheck = true;
		}
		if (weekCheck == false) {
			for (ActivityLevel al: ActivityLevel.find.all()) {
				Goal.createWeekGoal(al.description);
			}
		} if (monthCheck == false) {
			for (ActivityLevel al: ActivityLevel.find.all()) {
				Goal.createMonthGoal(al.description);
			}
		}
    }
    
	public static HashMap<String, Integer> updateMorris() {
		HashMap<String, Integer> morris = new HashMap<String, Integer>();
		List<UserActivity> userActivities = UserActivityController.findUserActivities();
		for (UserActivity userActivity: userActivities) {
			Double steps = userActivity.steps;
			if (morris.containsKey(userActivity.activity.name))
				morris.put(userActivity.activity.name, morris.get(userActivity.activity.name) + steps.intValue());
			else
				morris.put(userActivity.activity.name, steps.intValue());
		}
		return morris;
	}
	
	public static List<Integer> updateMonthLine() {
		List<Integer> graphData = new ArrayList<Integer>();
		for (int i = 0; i < 30; i++) {
			graphData.add(0);
		}
		List<UserActivity> userActivities = UserActivityController.findUserActivities();
		List<UserSteps> userSteps = UserActivityController.findPedoRecordings();
		Calendar calendar = Calendar.getInstance();
		for (int i = 0; i < 30; i++) {
			SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
			for (UserActivity ua: userActivities) {
				if (fmt.format(calendar.getTime()).equals(fmt.format(ua.date))) {
					Double s = ua.steps;
					graphData.set(29 - i, graphData.get(29 - i) + s.intValue());
				}
			}
			for (UserSteps us: userSteps) {
				if (fmt.format(calendar.getTime()).equals(fmt.format(us.date))) {
					Double s = us.steps;
					graphData.set(29 - i, graphData.get(29 - i) + s.intValue());
				}
			}
			calendar.add(Calendar.DAY_OF_YEAR, -1);
		}
		return graphData;
	}
	
	public static List<Integer> updateYearLine() {
		List<Integer> graphData = new ArrayList<Integer>();
		for (int i = 0; i < 12; i++) {
			graphData.add(0);
		}
		List<UserActivity> userActivities = UserActivityController.findUserActivities();
		List<UserSteps> userSteps = UserActivityController.findPedoRecordings();
		Calendar calendar = Calendar.getInstance();
		for (int i = 0; i < 12; i++) {
			SimpleDateFormat fmt = new SimpleDateFormat("yyyyMM");
			for (UserActivity ua: userActivities) {
				if (fmt.format(calendar.getTime()).equals(fmt.format(ua.date))) {
					Double s = ua.steps;
					graphData.set(11 - i, graphData.get(11 - i) + s.intValue());
				}
			}
			for (UserSteps us: userSteps) {
				if (fmt.format(calendar.getTime()).equals(fmt.format(us.date))) {
					Double s = us.steps;
					graphData.set(11 - i, graphData.get(11 - i) + s.intValue());
				}
			}
			calendar.add(Calendar.MONTH, -1);
		}
		return graphData;
	}
    
}
