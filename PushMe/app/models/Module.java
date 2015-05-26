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
    public String name;
    public String description;
    
    public static Finder<Long,Module> find = new Finder<Long,Module>(
        Long.class, Module.class
    ); 
    
    public static List<Module> all() {
        return find.all();
    }  
}