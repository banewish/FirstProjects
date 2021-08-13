import java.math.BigInteger
import java.security.SecureRandom

object Form {

}
object RandomUtil extends App{
  private val random = SecureRandom.getInstanceStrong

  def alphanumeric(nrChars: Int = 24): String = {
    new BigInteger(nrChars * 5, random).toString(32)
  }
  print(alphanumeric())
}

