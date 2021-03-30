import java.awt.Color

object Main extends App {

  val frame = JFrameFactory.create

  // добавляем много
  val components = Seq()
  components.foreach { comp =>
    frame.add(comp)
    frame.revalidate()


  }
  val Intro= Seq(MyComponent(30,60, Color.BLUE),MyComponent(30,70, Color.BLUE),MyComponent(40,80, Color.BLUE),MyComponent(40,90, Color.BLUE),MyComponent(50,100, Color.BLUE),MyComponent(50,110, Color.BLUE),MyComponent(60,90, Color.BLUE),MyComponent(70,100, Color.BLUE),MyComponent(70,110, Color.BLUE),MyComponent(80,90, Color.BLUE),MyComponent(80,80, Color.BLUE),MyComponent(90,70, Color.BLUE),MyComponent(90,60, Color.BLUE),MyComponent(110,60, Color.BLUE),MyComponent(120,60, Color.BLUE),MyComponent(130,60, Color.BLUE),MyComponent(110,70, Color.BLUE),MyComponent(110,80, Color.BLUE),MyComponent(110,90, Color.BLUE),MyComponent(110,100, Color.BLUE)
    ,MyComponent(110,110, Color.BLUE),MyComponent(120,110, Color.BLUE),MyComponent(130,110, Color.BLUE),MyComponent(110,90, Color.BLUE),MyComponent(120,90, Color.BLUE),MyComponent(130,90, Color.BLUE),MyComponent(150,60, Color.BLUE),MyComponent(150,70, Color.BLUE),MyComponent(150,80, Color.BLUE),MyComponent(150,90, Color.BLUE),MyComponent(150,100, Color.BLUE),MyComponent(150,110, Color.BLUE),MyComponent(160,110, Color.BLUE),MyComponent(170,110, Color.BLUE),MyComponent(200,60, Color.BLUE),MyComponent(210,60, Color.BLUE),MyComponent(190,70, Color.BLUE),MyComponent(190,80, Color.BLUE),MyComponent(190,90, Color.BLUE),MyComponent(190,100, Color.BLUE),MyComponent(200,110, Color.BLUE),MyComponent(210,110, Color.BLUE),MyComponent(240,60, Color.BLUE),MyComponent(250,60, Color.BLUE),MyComponent(230,70, Color.BLUE),MyComponent(230,80, Color.BLUE),MyComponent(230,90, Color.BLUE),MyComponent(230,100, Color.BLUE),MyComponent(240,110, Color.BLUE),MyComponent(250,110, Color.BLUE),MyComponent(260,100, Color.BLUE),MyComponent(260,90, Color.BLUE),MyComponent(260,80, Color.BLUE),MyComponent(260,70, Color.BLUE),MyComponent(280,60, Color.BLUE),MyComponent(280,70, Color.BLUE)
  ,MyComponent(280,80, Color.BLUE),MyComponent(280,90, Color.BLUE),MyComponent(280,100, Color.BLUE),MyComponent(280,110, Color.BLUE),MyComponent(290,70, Color.BLUE),MyComponent(300,80, Color.BLUE),MyComponent(310,70, Color.BLUE),MyComponent(320,60, Color.BLUE),MyComponent(320,70, Color.BLUE),MyComponent(320,80, Color.BLUE),MyComponent(320,90, Color.BLUE),MyComponent(320,100, Color.BLUE),MyComponent(320,110, Color.BLUE),MyComponent(340,60, Color.BLUE),MyComponent(350,60, Color.BLUE),MyComponent(360,60, Color.BLUE),MyComponent(340,70, Color.BLUE),MyComponent(340,80, Color.BLUE),MyComponent(340,90, Color.BLUE),MyComponent(340,100, Color.BLUE),MyComponent(340,110, Color.BLUE),MyComponent(350,90, Color.BLUE),MyComponent(360,90, Color.BLUE),MyComponent(350,110, Color.BLUE),MyComponent(360,110, Color.BLUE))

  Intro.foreach { comp =>
    frame.add(comp)
    frame.revalidate()


  }
}













