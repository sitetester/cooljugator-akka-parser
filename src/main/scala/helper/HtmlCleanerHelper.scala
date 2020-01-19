package helper

import org.htmlcleaner.{HtmlCleaner, PrettyXmlSerializer}

object HtmlCleanerHelper {

  def cleanHtml(dirtyHtml: String): String = {

    val cleaner = new HtmlCleaner
    val props = cleaner.getProperties
    props.setOmitComments(true)
    val rootTagNode = cleaner.clean(dirtyHtml)

    // use the getAsString method on an XmlSerializer class
    val xmlSerializer = new PrettyXmlSerializer(props)
    xmlSerializer.getAsString(rootTagNode)

  }
}
