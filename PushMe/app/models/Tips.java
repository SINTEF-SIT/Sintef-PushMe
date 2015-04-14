package models;

import java.util.Collections;
import java.util.List;

import javax.persistence.*;

import play.db.ebean.*;
import play.db.ebean.Model.Finder;

import com.avaje.ebean.*;

@Entity
public class Tips extends Model {
	
	@Id
	public int tip_number;
	public String tip;
	
    public static Finder<Long,Tips> find = new Finder<Long,Tips>(
            Long.class, Tips.class
        );
    
    public static List<Tips> all() {
    	List<Tips> tips = find.all();
    	Collections.shuffle(tips);
        return tips;
    }
}
