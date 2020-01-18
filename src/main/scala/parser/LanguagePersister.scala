package parser

import akka.actor.Actor
class LanguagePersister extends Actor {

  def receive: Receive = {

    case LanguageCommonVerbs(code, commonVerbs) => {
      println(s"$code: ${commonVerbs.length}")

      // TODO: persist to DB
    }

  }
}
