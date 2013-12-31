package controllers;

import play.*;
import play.mvc.*;
import play.libs.*;

import java.util.*;

import models.*;
import utils.*;

@With(Secure.class)
public class Application extends Controller {

    public static void index() {
        for (int i = 231; i <= 234; i++ ) {
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
        
    }

    public static void term(long shrubId){
    	Term term = Term.find("byShrubId", shrubId).first();
    	notFoundIfNull(term);
    	render(term);
    }

}