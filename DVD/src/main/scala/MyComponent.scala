import java.awt.{Color, Graphics}

import javax.swing.JComponent

case class MyComponent(xAxis: Int, yAxis: Int, color: Color) extends JComponent {

  override def paint(g: Graphics): Unit = {
    g.setColor(color)
    g.fillRect(xAxis, yAxis, 10, 10)
    g.setColor(Color.BLACK)
    g.drawRect(xAxis, yAxis, 10, 10)
  }
}

object MyComponent {
  private val r = new scala.util.Random

  def generate(color: Color = Color.GREEN): MyComponent = {
    val r1 = r.nextInt(400) + 100
    val r2 = r.nextInt(400) + 100


    new MyComponent(r1, r2, color)
  }
}