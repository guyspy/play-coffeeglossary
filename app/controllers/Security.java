package controllers;
 
import models.*;
import play.Play;
import play.Logger;
import play.libs.*;
 
public class Security extends Secure.Security {
  
  static boolean authenticate(String username, String password) {
    return User.connect(username, Codec.hexSHA1(password)) != null;
  }

  static void onDisconnected() {
    Application.index();
  }

  static boolean check(String profile) {
    User user = User.find("email is ? ", connected()).first();
     if("admin".equals(profile)) {
      return user.isAdmin;
    }
    return false;
  }
    
}