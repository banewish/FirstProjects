object ssasgeyo extends App {

  class implicitS(val error: String, val aboba: Int) {


  }
//  val implicitss = new implicitS("sfs", ???)
//  print(implicitss)

  def factorial(n: Int): Long ={
    if (n == 1) 1
    else n * factorial(n-1)

  }
  factorial(12)


}