package toolbar

import BuilderHelper
import Drawer
import ImageHelper.Companion.toolbar_applefarm
import ImageHelper.Companion.toolbar_castle
import ImageHelper.Companion.toolbar_cattlefarm
import ImageHelper.Companion.toolbar_hopsfarm
import ImageHelper.Companion.toolbar_palette
import ImageHelper.Companion.toolbar_wheatfarm
import ImageHelper.Companion.toolbar_woodcutter_hut
import ImageHelper.Companion.woodcutter_hut
import world.MapPoint
import world.Chunk
import world.Chunks
import world.SimplePoint
import world.objects.BuildingDirection
import world.objects.buildings.*
import world.objects.buildings.castle.Castle
import world.objects.tiles.Farmland
import world.objects.tiles.TroddenGround
import java.awt.Color
import java.awt.Graphics
import java.awt.Rectangle
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.image.BufferedImage
import javax.swing.JPanel


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
                        BuilderHelper.getInstance().setBuilding(AppleFarm(MapPoint(0,0, Chunk(0,0))))
                    }
                    toolbar_wheatfarm -> {
                        drawer.appState = Drawer.AppState.BUILD
                        val farmlands = ArrayList<AdditionalBuilding>()
                        for (x in 2 downTo -4){
                            for (y in -1 downTo -4){
                                if ((x-2) % 3 != 0 ){
                                    val offset = HashMap<BuildingDirection, MapPoint>()
                                    offset.put(BuildingDirection.LEFT, MapPoint(x-2, y, Chunk(0,0)))
                                    offset.put(BuildingDirection.RIGHT, MapPoint(-3,0, Chunk(0,0)))
                                    val farmland = AdditionalBuilding(Farmland(MapPoint(0,0, Chunk(0,0))), offset)
                                    farmlands.add(farmland)
                                    }
                                }
                        }

                        BuilderHelper.getInstance().setBuilding(WheatFarm(MapPoint(0,0, Chunk(0,0))), farmlands)
                    }
                    toolbar_hopsfarm -> {
                        drawer.appState = Drawer.AppState.BUILD
                        BuilderHelper.getInstance().setBuilding(HopsFarm(MapPoint(0,0, Chunk(0,0))))
                    }
                    toolbar_cattlefarm -> {
                        drawer.appState = Drawer.AppState.BUILD
                        BuilderHelper.getInstance().setBuilding(CattleFarm(MapPoint(0,0, Chunk(0,0))))
                    }

                    toolbar_castle -> {
                        drawer.appState = Drawer.AppState.BUILD
                        val offset = HashMap<BuildingDirection, MapPoint>()
                        offset.put(BuildingDirection.LEFT, MapPoint(0,-4, Chunk(0,0)))
                        offset.put(BuildingDirection.RIGHT, MapPoint(-4,0, Chunk(0,0)))
                        val pallete = AdditionalBuilding(Palette(MapPoint(0,0, Chunk(0,0))), offset)

                        val offset_TG = HashMap<BuildingDirection, MapPoint>()
                        offset_TG.put(BuildingDirection.LEFT, MapPoint(-6,0, Chunk(0,0)))
                        offset_TG.put(BuildingDirection.RIGHT, MapPoint(0,-6, Chunk(0,0)))
                        val troddenGround = AdditionalBuilding(TroddenGround(MapPoint(0,0, Chunk(0,0))), offset_TG)

                        val offset_CF = HashMap<BuildingDirection, MapPoint>()
                        offset_CF.put(BuildingDirection.LEFT, MapPoint(-4,2, Chunk(0,0)))
                        offset_CF.put(BuildingDirection.RIGHT, MapPoint(2,-4, Chunk(0,0)))
                        val campfire = AdditionalBuilding(Campfire(MapPoint(0,0, Chunk(0,0))), offset_CF)

                        BuilderHelper.getInstance().setBuilding(
                            Castle(MapPoint(0,0, Chunks.instance().chunks[SimplePoint(0,0)]!!)),
                            arrayListOf(pallete, troddenGround)//, campfire)
                        )
                    }

                    toolbar_palette -> {
                        drawer.appState = Drawer.AppState.BUILD
                        BuilderHelper.getInstance().setBuilding(Palette(MapPoint(0,0, Chunk(0,0))))
                    }

                    toolbar_woodcutter_hut -> {
                        drawer.appState = Drawer.AppState.BUILD
                        BuilderHelper.getInstance().setBuilding(GenericBuilding(MapPoint(0,0, Chunk(0,0)),3,6.0,
                            woodcutter_hut
                        ))
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