package utils;

import play.*;
import play.libs.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import org.apache.commons.lang3.StringUtils;


public class WebScraper {

  public static String getMetaTag(Document document, String attr) {
    Elements elements = document.select("meta[name=" + attr + "]");
    for (Element element : elements) {
      final String s = element.attr("content");
      if (s != null) return s;
    }
    elements = document.select("meta[property=" + attr + "]");
    for (Element element : elements) {
      final String s = element.attr("content");
      if (s != null) return s;
    }
    return "";
  }

  public static Map scrap(String url){
    Map result = new HashMap();
    try {
      WS.HttpResponse resp = WS.url(url).setHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.95 Safari/537.36").followRedirects(true).get();

        Document doc = Jsoup.parse(resp.getString());
        String word = doc.select("dt").text();
        String desc = doc.select("p").first().text();
        String seeAlso = doc.select("span.glossary-related").html();
        result.put("desc", desc);
        result.put("word", word);
        result.put("seeAlso", seeAlso);

      
    } catch (Exception e){
      Logger.warn(e, "[WebScraper] WebScraper.scrap() faild with exception");
    }
    return result;
  }

  

}
