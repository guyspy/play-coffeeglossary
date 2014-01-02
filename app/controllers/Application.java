package controllers;

import play.*;
import play.mvc.*;
import play.libs.*;

import java.util.*;

import models.*;
import utils.*;

@With({Secure.class, Intrcp_Logger.class})
public class Application extends Controller {

    public static void index() {
        render();
    }

    public static void term(long shrubId){
    	Term term = Term.find("byShrubId", shrubId).first();
    	notFoundIfNull(term);
    	render(term);
    }

    public static void wordlist(String term){
    	List<Term> all = Term.find("word like ? ", "%"+term+"%").fetch();
    	List<Map> result = new ArrayList<Map>();
    	for (Term t : all ) {
    		Map m = new HashMap();
    		m.put("label", t.word);
    		m.put("value", t.word);
    		m.put("word", t.word);
    		m.put("word_zh", t.word_zh);
    		m.put("shrubId", t.shrubId);
    		result.add(m);
    	}
    	renderJSON(result);
    }

    public static void list(String startLetter){
        String startLetterLow = startLetter.toLowerCase();
        String startLetterUp = startLetter.toUpperCase();
        List<Term> results = Term.find("word like ? or word like ?", startLetterLow+"%", startLetterUp+"%").fetch();
        String query = startLetter;
        render(query, results);
    }

    public static void tag(String tag){
        List<Term> results = Term.find("select distinct t from Term t join t.tags as tg where tg.name = ? ",tag).fetch();
        String query = tag;
        renderTemplate("Application/list.html", results, query);
    }


}