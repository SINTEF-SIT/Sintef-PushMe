package controllers;

import static play.data.Form.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import play.*;
import play.data.*;
import play.mvc.*;
import models.*;
import views.html.*;
//import play.libs.Mail;
import static play.data.Form.form;


public class SignupController extends Controller {
	
	
	
    public static Result signup() {
        return ok(signup.render(Form.form(User.class), ActivityLevel.all()));
    }
    
    public static Result createUser() {
        Form<User> userForm = Form.form(User.class).bindFromRequest();
        userForm.get().isAdmin = false;
        if(userForm.hasErrors()) {
            return badRequest(
                    views.html.signup.render(userForm, ActivityLevel.all())
            );
        } else {
            User.create(userForm.get());
            return redirect(routes.IndexController.index());
        }
    }
 /*   
    public void sendValidationEmail () {
    	SimpleEmail email = new SimpleEmail();
    	email.setFrom("pushMe@sintef.no");
    	email.addTo("test@test.test");
    	email.setSubject("Confirmation code");
    	
    	char[5] code = createRandomCode();
    	email.setMsg("Your confirmation PushMe confirmation code is: " + code + "\nYou can ignore this email if this don't consern you.");
    	Mail.send(email);
    }
    
    private char[] createRandomCode() {
    	char[5] code;
        Random r = new Random();
        String alphabet = "0123456789abcdefghijklmnopqrstuvxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < 5; i++) {
            code[i] = alphabet.charAt(r.nextInt(alphabet.length()));
        } return code;
*/
}
