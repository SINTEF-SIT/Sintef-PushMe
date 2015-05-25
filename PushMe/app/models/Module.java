package models;

import java.util.List;

import javax.persistence.*;

import play.db.ebean.*;
import play.db.ebean.Model.Finder;

import com.avaje.ebean.*;

@Entity
public class Module extends Model {

    @Id
    public long id;
    public String description;
    public int clickCounter;
    @ManyToOne
    public User belongsTo;
    
    public static Finder<Long,Module> find = new Finder<Long,Module>(
        Long.class, Module.class
    ); 
    
    public static List<Module> all() {
        return find.all();
    }
    
    public static String update(Long id, Module module) {
        module.update((Object)id);
        return ("Your profile has been updated");
        }    
}