package controllers;
 
import play.*;
import play.mvc.*;
import controllers.*;
import models.*; 

@Check("admin")
@With(Secure.class)
public class Tags extends CRUD {    
}