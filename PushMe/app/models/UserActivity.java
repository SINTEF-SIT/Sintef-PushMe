package models;

import java.util.List;
import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;

@Entity
public class UserActivity extends Model {
	
	@Id
	public int id;
    @ManyToOne
    public User belongsTo;
    @ManyToOne
    public Activity activity;
    public double steps;

    
    public UserActivity(int id, User belongsTo, Activity activity, double steps) {
      this.id = id;
      this.belongsTo = belongsTo;
      this.activity = activity;
      this.steps = steps;
    }

    public static Finder<Long,UserActivity> find = new Finder<Long,UserActivity>(
        Long.class, UserActivity.class
    ); 
    
    public static List<UserActivity> all() {
        return find.all();
    }

	public static void save(UserActivity ua) {
		ua.save();		
	}
}