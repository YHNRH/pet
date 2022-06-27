package toolbar

import BuilderHelper
import Drawer
import ImageHelper.Companion.edge
import ImageHelper.Companion.grass_tiles
import ImageHelper.Companion.toolbar_applefarm
import Point
import world.Chunk
import world.objects.buildings.AppleFarm
import world.objects.buildings.Campfire
import world.objects.mobs.SimpleMob
import java.awt.Color
import java.awt.Graphics
import java.awt.Rectangle
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.awt.image.BufferedImage
import javax.swing.JPanel
import javax.swing.UIManager


class ToolbarButton(val drawer: Drawer,private val imgArray: Array<BufferedImage>, private val x_: Int, private val y_: Int, private val width_: Int, private val height_: Int) : JPanel() {
    var img = imgArray[0]
    init {
        this.bounds = Rectangle(x_, y_, width_, height_)
        background = Color(0,0,0,0)
        addMouseListener(object : MouseAdapter() {
            override fun mouseEntered(evt: MouseEvent) {
                img = imgArray[1]
            }

            override fun mouseExited(evt: MouseEvent) {
                img = imgArray[0]
            }
            override fun mouseClicked(e : MouseEvent) {
                when(imgArray){
                    toolbar_applefarm -> {
                        drawer.appState = Drawer.AppState.BUILD
                        BuilderHelper.getInstance().obj = AppleFarm(SimpleMob.ChunkAndPoint(Chunk(Point(0,0)), Point(0,0)))
                    }
                }
            }
            })
    }

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)
        g?.drawImage(img, 0,0,width,height, null)
    }
}