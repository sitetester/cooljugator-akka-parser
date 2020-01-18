package Main

import akka.actor.{ActorSystem, Props}
import parser.{CommonVerbsParser, LanguagePersister}

import scala.language.postfixOps

case class LanguagePath(code: String, url: String)

object CommonVerbsParsingManagerTest extends App {

  val urls = List(
    LanguagePath("en", "https://cooljugator.com/en"),
    LanguagePath("ar", "https://cooljugator.com/ar"),
    LanguagePath("ru", "https://cooljugator.com/ru"),
    LanguagePath("es", "https://cooljugator.com/es"),
  )

  val system = ActorSystem("cooljugatorParsingSystem")
  val persister = system.actorOf(Props[LanguagePersister], name = "persister")

  urls.foreach(lp => {
    val languageParser = system.actorOf(Props(new CommonVerbsParser(persister)),
                                        name = "parser" + lp.code)
    println(languageParser)

    languageParser ! lp
  })
}
