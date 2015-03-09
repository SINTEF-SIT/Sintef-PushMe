package models;

import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
public class ActivityLevel extends Model {

    @Id
    public int id;
    public int level;
    public String description;

    public ActivityLevel(int id, int level, String description){
        this.id = id;
        this.level = level;
        this.description = description;
    }

    public static Finder<Long,ActivityLevel> find = new Finder<Long,ActivityLevel>(
            Long.class, ActivityLevel.class
    );

    public static List<ActivityLevel> all() {
        return find.all();
    }

    public static void create(ActivityLevel userinfo) {
        userinfo.save();
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }
}
