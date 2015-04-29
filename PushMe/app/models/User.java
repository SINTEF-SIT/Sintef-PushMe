package models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import play.db.ebean.*;

import com.avaje.ebean.*;

@Entity
public class User extends Model {

    @Id
    public String email;
    public String password;
    public String name;
    public Date dob;
    public int weight;
    public int height;
    public String gender;
    public String current_al;
    public String target_al;
    @OneToMany
    public List<Trophy> trophies;

    public static Finder<String,User> find = new Finder<String,User>(
        String.class, User.class
    );
    
    public static List<User> all() {
        return find.all();
    }
    
    public static void create(User user) {
        user.save();
    }
        
    public static String update(String id, User user) {
        user.update((Object)id);
        return ("Your profile has been updated");
    }
    
    public static User authenticate(String email, String password) {
        return find.where().eq("email", email)
            .eq("password", password).findUnique();
    }
}