package models;
 
import java.util.*;
import javax.persistence.*;
import play.db.jpa.*;
import play.data.validation.*;


@Entity
public class Term extends Model {

  //-----account
  @Required
  @Unique
  public String word;

  public long shrubId; //term id on coffeeshrub website

  @Lob
  @Column(length=60000)
  @MaxSize(6000)
  public String content;

  @Lob
  @Column(length=60000)
  @MaxSize(6000)
  public String seeAlso;



  public String toString(){
    return "["+shrubId+"] "+word;
  }




}