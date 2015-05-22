package controllers

import play.api.mvc._
import com.typesafe.plugin._
import play.api.Play.current

object MailController extends Controller {

  def sendEmail() = Action {
    val mail = use[MailerPlugin].email
    mail.setSubject("mailer")
    mail.setRecipient("ErikBugge <embugge@hotmail.com>","embugge@hotmail.com")
    //or use a list
    //mail.setBcc(List("Dummy <example@example.org>", "Dummy2 <example@example.org>"):_*)
    mail.setFrom("PushMe - SINTEFF <pushme@gmail.com>")
    //adds attachment
    //mail.addAttachment("attachment.pdf", new File("/some/path/attachment.pdf"))
    //adds inline attachment from byte array
    //val data: Array[Byte] = "data".getBytes
    //mail.addAttachment("data.txt", data, "text/plain", "A simple file", EmailAttachment.INLINE)
    //sends html
    //mail.sendHtml("<html>html</html>" )
    //sends text/text
    mail.send( "Hello, this is a mail from PushMe - SINTEF.")
    //sends both text and html
    //mail.send( "text", "<html>html</html>")
    Ok("Hello")
  }
}