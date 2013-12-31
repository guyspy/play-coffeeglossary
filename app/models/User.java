package models;
 
import java.util.*;
import javax.persistence.*;
import play.db.jpa.*;
import play.data.validation.*;



@Entity
public class User extends Model {

  //-----account
  @Required
  @Unique
  @Email
  public String email;

  public String password;

  public boolean isAdmin;


  //-------major login method
  public static User connect(String email, String password) {
    User user = User.find("byEmailAndPassword", email, password).first();
    return user;
  }

}