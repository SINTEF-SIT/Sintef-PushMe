package controllers;

import play.mvc.*;

public class LanguageController extends Controller {

	public static Result changeLanguage(String lang){
		Controller.changeLang(lang);
		return redirect("/");
	}
	
}
