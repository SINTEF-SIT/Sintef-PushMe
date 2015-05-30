package controllers;

import static play.data.Form.form;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
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
	
	private static List<Long> surveyIds;
	
	@Security.Authenticated(Secured.class)
	public static Result dashboard() {
		User user = ProfileController.findUser();
		TreeMap<User, Integer> leaderboard = StatisticsController.updateLeaderboards();
        return ok(dashboard.render(user, 
        		Tips.all(), 
        		0.0 , 
        		StatisticsController.getGoals(user), 
        		leaderboard, UserActivityController.getRecentUA(), 
        		UserActivityController.findPedoRecordings(), 
        		StatisticsController.getTopLeaderboard(leaderboard), 
        		surveyChecker(user),
        		surveyIds,
        		StatisticsController.updateMonthLine(),
        		StatisticsController.updateYearLine(),
        		updateWeekBar()));
    }
	
	//Check if there are any surveys deployed for the user
	public static List<Survey> surveyChecker(User user){
		List<Survey> surveys = new ArrayList<>();
		surveyIds = new ArrayList<Long>();
		List<SurveyAnswer> allSurveys = SurveyAnswer.find.all();
		for(SurveyAnswer i : allSurveys){
			if(i.user.equals(user) && i.answered == false){
				surveys.add(i.survey);
				surveyIds.add(i.id);
			}
		}
		return surveys;
	}
	
	//Register a survey answer
	@Security.Authenticated(Secured.class)
	public static Result answerSurvey(Long id){
		Form<SurveyAnswer> form = Form.form(SurveyAnswer.class).bindFromRequest();
		SurveyAnswer oldSurvey = SurveyAnswer.find.byId(id);
		form.get().answered = true;
		form.get().user = oldSurvey.user;
		form.get().survey = oldSurvey.survey;
		SurveyAnswer.update(id, form.get());
		return redirect("/dashboard");
	}
	
	public static Integer updateWeekBar() {
		List<Integer> monthStats = StatisticsController.updateMonthLine();
		Calendar cal = Calendar.getInstance();
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		int steps = 0;
		for (int i = 0; i < dayOfWeek; i++) {
			steps += monthStats.get(29 - i);
		} return steps;
	}
}
