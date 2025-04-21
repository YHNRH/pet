import toolbar.Console
import java.awt.*
import javax.swing.*

        fun main(args: Array<String>) {
            EventQueue.invokeLater(object : Runnable {
                override fun run() {
                    val frame = JFrame("Testing")
                    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
                    frame.isUndecorated = true


                    frame.addKeyListener(KeyboardListener(Camera.instance()))
                    frame.add(Drawer.instance())
                    Drawer.instance().add(Console())

                    frame.pack()
                    frame.isVisible = true
                }
            })
        }
