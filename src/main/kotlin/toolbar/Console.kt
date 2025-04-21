package toolbar

import Consts.Companion.frameWidth
import java.awt.Color
import java.awt.Graphics
import java.awt.Rectangle
import javax.swing.JComponent

class Console : JComponent() {

    private val bgColor = Color(104,104,104,45)
    private val consoleWidth = frameWidth / 3
    private val consoleHeight = (consoleWidth * 0.6).toInt()

    private val history = ArrayList<Message>()

    val lineHeight = 15

    init {
        this.bounds = Rectangle(frameWidth-consoleWidth, 600, consoleWidth, consoleHeight)
        this.history.add(Message("Hello world!!!"))
    }

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)
        if (g != null) {
            g.color = bgColor
            g.fillRect(0, 0, consoleWidth, consoleHeight)

            var i = 0
            history.forEach {
                g.color = it.color
                g.drawString(it.text,5, (++i)*lineHeight)
            }
        }
    }

    class Message(val text: String, val color: Color = Color.WHITE)


}