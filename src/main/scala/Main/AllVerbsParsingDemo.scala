package Main

import akka.actor.{ActorSystem, Props}
import parser.{AllVerbsParser, LanguagePersister}

import scala.language.postfixOps

object AllVerbsParsingDemo extends App {

  val urls = List(
    LanguagePath("en", "https://cooljugator.com/en/list/all"),
    LanguagePath("lt", "https://cooljugator.com/lt/list/all"),
    LanguagePath("ar", "https://cooljugator.com/ar/list/all"),
    LanguagePath("ru", "https://cooljugator.com/ru/list/all"),
    LanguagePath("es", "https://cooljugator.com/es/list/all"),
    LanguagePath("af", "https://cooljugator.com/af/list/index"),
    LanguagePath("tr", "https://cooljugator.com/tr/list/all"),
  )

  val system = ActorSystem("cooljugatorParsingSystem")
  val persister = system.actorOf(Props[LanguagePersister], name = "persister")

  urls.foreach(lp => {

    val name = "parser" + lp.code
    val languageParser = system.actorOf(Props(new AllVerbsParser(persister)), name)

    println(languageParser)

    languageParser ! lp
  })
}
