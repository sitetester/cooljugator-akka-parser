package parser

import java.util.Calendar

import akka.actor.{Actor, ActorRef}
import org.jsoup.Jsoup

case class LanguageCommonVerbs(code: String, commonVerbs: List[String])

class CommonVerbsParser(persister: ActorRef) extends Actor {

  val domain = "https://cooljugator.com/"

  def receive: Receive = {

    case (code, index) => {
      val url = domain + code
      println(s"Parsing $url at ${Calendar.getInstance().getTime}")
      val doc = Jsoup.connect(url).get
      val unParsedVerbs = doc.select("div.wordcloud ul li a")

      if (index == 0) println()
      println(s"Finished url: $url at ${Calendar.getInstance().getTime}")

      var commonVerbs = List[String]()
      unParsedVerbs.forEach(v => {
        commonVerbs :+= v.attr("href").split("/").last
      })

      commonVerbs.foreach(println(_))
      persister ! LanguageCommonVerbs(code.toString, commonVerbs)
    }

    case _ => println("huh?")
  }
}
