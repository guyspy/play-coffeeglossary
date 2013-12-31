import play.*;
import play.jobs.*;
import play.test.*;
import play.mvc.*;
import java.util.*;
 
import models.*;
import utils.*; 

@OnApplicationStart
public class Bootstrap extends Job {
 
  public void doJob() {
    // Check if the database is empty
    if(User.count() == 0) {
       try {
         Logger.info("Bootstrap loading initial-data...");
         Fixtures.loadModels("initial-data.yml");
         Logger.info("initial-data loaded.");
       }
       catch (Exception ex) {
        Logger.warn(ex,"failed to load initial-data.yml");       
       }


    }

    
  }
 
}