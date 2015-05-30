package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import play.db.ebean.*;

@Entity
public class Trophy extends Model {
	
	//Trophies are handled in the UserActivityController
	@Id
	public int trophy_number;
	public int points;
	public String description;
	public Date endDate;
	@ManyToOne
	public User user;
	
	
    public static Finder<Long,Trophy> find = new Finder<Long,Trophy>(
            Long.class, Trophy.class
        );
    
    public static List<Trophy> all() {
    	List<Trophy> trophies = find.all();
        return trophies;
    }
    
    public static List<Trophy> userTrophies(User user) {
    	List<Trophy> trophies = all();
    	List<Trophy> userTrophies = new ArrayList<Trophy>();
    	for (Trophy ut: trophies) {
    		if (ut.user.equals(user))
    			userTrophies.add(ut);
    	}
        return userTrophies;
    }
    
    public static void createTrophy(int points, String description, Date endDate, User user) {
    	Trophy trophy = new Trophy();
    	trophy.points = points;
    	trophy.description = description;
    	trophy.endDate = endDate;
    	trophy.user = user;
    	trophy.save();
    }
    
}
