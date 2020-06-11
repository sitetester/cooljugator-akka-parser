package parser

import java.util.Calendar

import Main.LanguagePath
import akka.actor.{Actor, ActorRef}
import helper.HtmlCleanerHelper

import scala.io.Source

class AllVerbsParser(persister: ActorRef) extends Actor {

  def receive: Receive = {

    case LanguagePath(code, url) => {

      printf(s"\nReceived `$code` with $url at ${Calendar.getInstance().getTime}")

      val urlSource = Source.fromURL(url)
      val dirtyHtml = urlSource.mkString
      val html = HtmlCleanerHelper.cleanHtml(dirtyHtml)
      val xmlElement = scala.xml.XML.loadString(html)

      val divs = xmlElement \\ "div"
      divs.foreach(div => {
        val cls = div.attribute("class").getOrElse("").toString.trim
        if (cls == "ui segment stacked") {
          val aa = div \\ "a"
          printf(s"\n$code - ${aa.size} - ${Calendar.getInstance().getTime}")
        }
      })

      urlSource.close()
    }

    case _ => println("huh?")
  }
}
