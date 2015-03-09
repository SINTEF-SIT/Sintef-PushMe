package models;

import java.sql.Timestamp;
import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

import java.util.*;

@Entity
public class Userinformation extends Model {

    @Id
    public int id;
    public String name;
    public Date dob;
    public int weight;
    public int height;
    public String gender;
    public String current_al;
    public String target_al;
    @ManyToOne
    public User belongsTo;

    public Userinformation(String name,Date dob, int weight, int height, String gender, String current_al, String target_al, User belongsTo){
        this.name = name;
        this.dob = dob;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.current_al = current_al;
        this.target_al = target_al;
        this.belongsTo = belongsTo;
    }

    public static Finder<Long,Userinformation> find = new Finder(
            Long.class, Userinformation.class
    );
    public static List<Userinformation> all() {
        return find.all();
    }

    public static void create(Userinformation userinfo) {
        userinfo.save();
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }

}