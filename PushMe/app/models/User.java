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
    
    public User(String email, String password, String name, Date dob, int weight, int height, String gender, String current_al, String target_al) {
      this.email = email;
      this.password = password;
      this.name = name;
      this.dob = dob;
      this.weight = weight;
      this.height = height;
      this.gender = gender;
      this.current_al = current_al;
      this.target_al = target_al;
    }

    public static Finder<String,User> find = new Finder<String,User>(
        String.class, User.class
    );
    
    public static List<User> all() {
        return find.all();
    }
    
    public static void create(User user) {
        user.save();
    }
        
    public static void update(User user) {
        user.update();
    }
    
    public static User authenticate(String email, String password) {
        return find.where().eq("email", email)
            .eq("password", password).findUnique();
    }
}