package models;

import java.util.*;

import play.data.validation.Constraints.Required;
import play.db.ebean.*;

import javax.persistence.*;

@Entity
public class UserActivity extends Model {
	
	@Id
	public Long id;
    @ManyToOne
    public User belongsTo;
    @ManyToOne
    public Activity activity;
    @Required
    public int intensity;
    @Required
    public double steps;
    @Required
    public Date date;

    
    public UserActivity(Long id, User belongsTo, Activity activity, int intensity, double steps, Date date) {
      this.id = id;
      this.belongsTo = belongsTo;
      this.activity = activity;
      this.intensity = intensity;
      this.steps = steps;
      this.date = date;
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