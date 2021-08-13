package Actor
object Test2 extends App {

  val seqOfInts: Seq[Int] = Seq(1, 2, 3, 4, 5, 6)

  val mapExample = seqOfInts.map(v => v + 1)  // что случилось
  println("mapExample " + mapExample)
  println


  val filterExample = seqOfInts.filter(v => v >= 3) // что случилось
  println("filterExample " + filterExample)
  println


  val seqOfOptionInts: Seq[Option[Int]] = Seq(Some(1), Some(2), None, Some(4), None, Some(6))
  val flattenExample: Seq[Int] = seqOfOptionInts.flatten // что случилось(flatten убирает none и оставляет Some)
  println("flattenExample " + flattenExample)
  println


  val mapExample2 = seqOfOptionInts.map { v =>
    v match {
      case Some(chislo) => chislo + 1
      case None => None
    }

  }  // что случилось
  println("mapExample2 " + mapExample2)
  println


  val flatmapExample = seqOfOptionInts.flatten // что случилось. тут должно гореть желтым и интелиджа подсказывает заменить этот код на другой
  println("flatmapExample " + flatmapExample)
}
object Test3 extends App {
  val seqNames = Seq("Rodion", "Aminia", "Other")
  println(seqNames)

  def getNickName(name: String): String = {
    name match {
      case "Rodion" => "Nayder"
      case "Aminia" => "Mitsaki"
      case _ => "Jopa"
    }
  }

  val seqNickName = seqNames.map(v => v -> getNickName(v))
  println(seqNickName)
  val mapNameToNickName = seqNickName.toMap
  println(mapNameToNickName)
}
object Test4 extends App {
  val seqInts = Seq(1, 2, 3)

  val res1 = seqInts.map(v => v + 1)
  val res2 = seqInts.map(_ + 1)


  val res3 = for {
    v <- seqInts
  } yield v + 1

  println(res1)
  println(res2)
  println(res3)
}
object Test5 extends App{
  val seqOption: Seq[Option[Int]] = Seq(Some(1),None,Some(3),None,Some(5),None,Some(7),None,Some(9),None)
  val seqFlatten: Seq[Int] = seqOption.flatten
  println("seqOptionExample " + seqFlatten)

}