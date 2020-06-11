package Main

import akka.actor.{ActorSystem, Props}
import parser.{CommonVerbsParser, LanguagePersister}

import scala.language.postfixOps

case class LanguagePath(code: String, url: String)

object CommonVerbsParsingDemo extends App {

  val urls = List("en", "ar", "ru", "lt", "af")

  val system = ActorSystem("cooljugatorParsingSystem")
  val persister = system.actorOf(Props[LanguagePersister], name = "persister")

  println()

  for ((code, index) <- urls.zipWithIndex) {
    val name = "parser" + code
    val languageParser = system.actorOf(Props(new CommonVerbsParser(persister)), name)

    languageParser ! (code, index)
  }
}
