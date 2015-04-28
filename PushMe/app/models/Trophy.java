package models;

import java.util.List;

import javax.persistence.*;

import play.db.ebean.*;

@Entity
public class Trophy extends Model {
	
	@Id
	public int trophy_number;
	public int points;
	public String description;
	@ManyToOne
	public User belongsTo;
	
    public static Finder<Long,Trophy> find = new Finder<Long,Trophy>(
            Long.class, Trophy.class
        );
    
    public static List<Trophy> all() {
    	List<Trophy> trophies = find.all();
        return trophies;
    }
    
    
    public static void addTrophy(Trophy trophy) {
    	trophy.save();
    }
}
