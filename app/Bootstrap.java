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

    if (Term.count() == 0) {
      try{
        for (int i = 231; i <= 744; i++ ) {
          Map termMap = WebScraper.scrap("http://www.coffeeshrub.com/shrub/glossary/term/"+i);
          //Logger.info("result="+termMap);
          
          String word = (String)termMap.get("word");
          Term term = Term.find("byWord", word).first();
          if (term == null) {
            term = new Term();
            term.word = word;
            term.shrubId = i;
            term.content = (String)termMap.get("desc");
            term.seeAlso = (String)termMap.get("seeAlso");
            term.save();
            Logger.info("word "+word+" is saved.");
          } 
        }
      } catch (Exception e){
        Logger.warn(e, "failed to populate the database");
      }
    }

    
  }
 
}