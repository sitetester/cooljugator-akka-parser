package parser

import java.util.Calendar

import Main.LanguagePath
import akka.actor.{Actor, ActorRef}
import org.jsoup.Jsoup

case class LanguageCommonVerbs(code: String, commonVerbs: List[String])

class CommonVerbsParser(persister: ActorRef) extends Actor {

  def receive: Receive = {

    case LanguagePath(code, url) => {

      println(s"Received $code with $url at ${Calendar.getInstance().getTime}")
      val doc = Jsoup.connect(url.toString).get
      val unParsedVerbs = doc.select("div.wordcloud ul li a")
      println(s"Finished url: $url at ${Calendar.getInstance().getTime}")

      var commonVerbs = List[String]()
      unParsedVerbs.forEach(v => {
        commonVerbs :+= v.attr("href").split("/").last
      })

      persister ! LanguageCommonVerbs(code.toString, commonVerbs)
    }

    case _ => println("huh?")
  }
}
