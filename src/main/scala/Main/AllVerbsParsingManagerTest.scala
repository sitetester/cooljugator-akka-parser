package Main

import akka.actor.{ActorSystem, Props}
import parser.{AllVerbsParser, LanguagePersister}

import scala.language.postfixOps

case class LanguagePath(code: String, url: String)

object AllVerbsParsingManagerTest extends App {

  val urls = List(
    LanguagePath("en", "https://cooljugator.com/en/list/10"),
    /*LanguagePath("ar", "https://cooljugator.com/ar"),
    LanguagePath("ru", "https://cooljugator.com/ru"),
    LanguagePath("es", "https://cooljugator.com/es"),*/
  )

  val system = ActorSystem("cooljugatorParsingSystem")
  val persister = system.actorOf(Props[LanguagePersister], name = "persister")

  urls.foreach(lp => {
    val languageParser = system.actorOf(Props(new AllVerbsParser(persister)),
                                        name = "parser" + lp.code)
    println(languageParser)

    languageParser ! lp
  })
}
