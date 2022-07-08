package toolbar

import BuilderHelper
import Drawer
import ImageHelper.Companion.edge
import ImageHelper.Companion.grass_tiles
import ImageHelper.Companion.toolbar_applefarm
import ImageHelper.Companion.toolbar_castle
import ImageHelper.Companion.toolbar_cattlefarm
import ImageHelper.Companion.toolbar_hopsfarm
import ImageHelper.Companion.toolbar_palette
import ImageHelper.Companion.toolbar_wheatfarm
import Point
import world.Chunk
import world.objects.BuildingDirection
import world.objects.buildings.*
import world.objects.buildings.castle.Castle
import world.objects.mobs.SimpleMob
import world.objects.tiles.Farmland
import world.objects.tiles.TroddenGround
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
                        BuilderHelper.getInstance().setBuilding(AppleFarm(SimpleMob.ChunkAndPoint(Chunk(Point(0,0)), Point(0,0))))
                    }
                    toolbar_wheatfarm -> {
                        drawer.appState = Drawer.AppState.BUILD
                        val farmlands = ArrayList<AdditionalBuilding>()
                        for (x in 2 downTo -4){
                            for (y in 0 downTo -4){
                                if ((x-2) % 3 != 0 ){
                                    val offset = HashMap<BuildingDirection, Point>()
                                    offset.put(BuildingDirection.LEFT, Point(x, y))
                                    offset.put(BuildingDirection.RIGHT, Point(-3,0))
                                    val farmland = AdditionalBuilding(Farmland(SimpleMob.ChunkAndPoint(Chunk(Point(0,0)), Point(0,0))), offset)
                                    farmlands.add(farmland)
                                    }
                                }
                        }

                        BuilderHelper.getInstance().setBuilding(WheatFarm(SimpleMob.ChunkAndPoint(Chunk(Point(0,0)), Point(0,0))), farmlands)
                    }
                    toolbar_hopsfarm -> {
                        drawer.appState = Drawer.AppState.BUILD
                        BuilderHelper.getInstance().setBuilding(HopsFarm(SimpleMob.ChunkAndPoint(Chunk(Point(0,0)), Point(0,0))))
                    }
                    toolbar_cattlefarm -> {
                        drawer.appState = Drawer.AppState.BUILD
                        BuilderHelper.getInstance().setBuilding(CattleFarm(SimpleMob.ChunkAndPoint(Chunk(Point(0,0)), Point(0,0))))
                    }

                    toolbar_castle -> {
                        drawer.appState = Drawer.AppState.BUILD
                        val offset = HashMap<BuildingDirection, Point>()
                        offset.put(BuildingDirection.LEFT, Point(0,-4))
                        offset.put(BuildingDirection.RIGHT, Point(-4,0))
                        val pallete = AdditionalBuilding(Palette(SimpleMob.ChunkAndPoint(Chunk(Point(0,0)), Point(0,0))), offset)

                        val offset_TG = HashMap<BuildingDirection, Point>()
                        offset_TG.put(BuildingDirection.LEFT, Point(-6,0))
                        offset_TG.put(BuildingDirection.RIGHT, Point(0,-6))
                        val troddenGround = AdditionalBuilding(TroddenGround(SimpleMob.ChunkAndPoint(Chunk(Point(0,0)), Point(0,0))), offset_TG)

                        val offset_CF = HashMap<BuildingDirection, Point>()
                        offset_CF.put(BuildingDirection.LEFT, Point(-4,2))
                        offset_CF.put(BuildingDirection.RIGHT, Point(2,-4))
                        val campfire = AdditionalBuilding(Campfire(SimpleMob.ChunkAndPoint(Chunk(Point(0,0)), Point(0,0))), offset_CF)

                        BuilderHelper.getInstance().setBuilding(
                            Castle(SimpleMob.ChunkAndPoint(Chunk(Point(0,0)), Point(0,0))),
                            arrayListOf(pallete, troddenGround)//, campfire)
                        )
                    }

                    toolbar_palette -> {
                        drawer.appState = Drawer.AppState.BUILD
                        BuilderHelper.getInstance().setBuilding(Palette(SimpleMob.ChunkAndPoint(Chunk(Point(0,0)), Point(0,0))))
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