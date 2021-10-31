package models

import play.api.libs.json.{Json, OFormat}


case class PasswordHash(password_hash: String) {
  implicit val passwordFormat: OFormat[PasswordHash] = Json.format[PasswordHash]

  def passwordHash(s: String): String = {
    import java.security.MessageDigest
    import java.math.BigInteger
    val md = MessageDigest.getInstance("MD5")
    val digest = md.digest(s.getBytes)
    val bigInt = new BigInteger(1,digest)
    val hashedString = bigInt.toString(16)
    hashedString
  }
}


