package parser

import java.util.Calendar

import Main.LanguagePath
import akka.actor.{Actor, ActorRef}

import scala.io.Source

case class LanguageCommonVerbs(code: String, commonVerbs: List[String])

class AllVerbsParser(persister: ActorRef) extends Actor {

  // currently Jsoup is not parsing data from BIG page, will check later
  /*def receive: Receive = {

    case LanguagePath(code, url) => {

      println(s"Received $code with $url at ${Calendar.getInstance().getTime}")
      val doc = Jsoup.connect(url.toString).get
      val unParsedVerbs = doc.select("div.ui segment stacked ul li a")
      println(s"Finished url: $url at ${Calendar.getInstance().getTime}")

      var commonVerbs = List[String]()
      unParsedVerbs.forEach(v => {
        commonVerbs :+= v.attr("href").split("/").last
      })

      persister ! LanguageCommonVerbs(code.toString, commonVerbs)
    }

    case _ => println("huh?")
  }*/

  def receive: Receive = {

    case LanguagePath(code, url) => {

      println(s"Received $code with $url at ${Calendar.getInstance().getTime}")
      val urlSource = Source.fromURL(url)
      val html = urlSource.mkString

      // TODO: parse in some other way
      // <div class ="ui segment stacked">
      urlSource.close()

    }

    case _ => println("huh?")
  }
}
