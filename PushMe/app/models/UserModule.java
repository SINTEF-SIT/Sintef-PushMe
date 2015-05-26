package models;

import java.util.List;

import javax.persistence.*;

import play.db.ebean.*;
import play.db.ebean.Model.Finder;

import com.avaje.ebean.*;

@Entity
public class UserModule extends Model {

    @Id
    public long id;
    public int clickCounter;
    @ManyToOne
    public User user;
    @ManyToOne
    public Module module;
    
    public static Finder<Long,UserModule> find = new Finder<Long,UserModule>(
        Long.class, UserModule.class
    ); 
    
    public static List<UserModule> all() {
        return find.all();
    }
    
    public static String update(Long id, UserModule module) {
        module.update((Object)id);
        return ("Your profile has been updated");
        }    
}