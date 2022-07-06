import java.awt.*
import javax.swing.*

        fun main(args: Array<String>) {
            EventQueue.invokeLater(object : Runnable {
                override fun run() {
                    val frame = JFrame("Testing")
                    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
                    frame.isUndecorated = true
//                    frame.addKeyListener(KeyboardListener(null))
                    frame.addKeyListener(KeyboardListener(Camera.instance()))
//                    frame.addMouseListener(MouseListener(drawer))

                    frame.add(Drawer.instance())

                    frame.pack()
                    frame.isVisible = true
//                    frame.size = Dimension(frameWidth, frameHeight)
                }
            })
        }
