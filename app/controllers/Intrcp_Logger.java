package controllers;

import models.*;
import play.*;
import play.mvc.*;
import java.util.*;


public class Intrcp_Logger extends Controller {
  
  @Before(priority = 1)
  static void tstamp(){
    long t1 = Calendar.getInstance().getTimeInMillis();
    renderArgs.put("t1", ""+t1);
    //reveal all request params, useful for debugging
    for(String k : params.all().keySet()) {  
      Logger.info("params: "+k+"="+Arrays.toString(params.getAll(k)));
    }
  }

  @Before(priority = 10)
  static void putConnectedUser(){
    if (Security.isConnected()) {
      User user =  User.find("byEmail", Security.connected()).first();
      renderArgs.put("me", user); 
    }
  }

  @After
  static void logTime(){
    long t2 = Calendar.getInstance().getTimeInMillis();
    long t1 = Long.parseLong((String)renderArgs.get("t1")); 
    Logger.info( request.action +" "+ request.url +" successed." +" takes "+ (t2 - t1) +" ms");
  }
  
}