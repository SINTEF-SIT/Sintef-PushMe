package controllers;

import user.java

import static play.data.Form.form;

import java.util.ArrayList;
import java.util.List;

import play.*;
import play.data.*;
import play.mvc.*;
import models.*;
import views.html.*;

public class ProfileController {

    public static Result profile() {
        return ok(userinfo.render(Form.form(User.class)));
    }
	
}
