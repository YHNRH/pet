package world.objects.tiles

import GraphicsExtender
import ImageHelper.Companion.grass_tiles
import ImageHelper.Companion.sand_tiles
import world.MapPoint
import world.objects.IDrawableObject
import java.awt.Color
import java.awt.Image
import kotlin.random.Random

class Sand(override var point: MapPoint, override var height: Double = 1.0, override var width: Int =1) :
    ITile {
    override val underlayColor: Color = Color(168,128,88)
//    override fun draw(g: Graphics) {
//        g.drawImage(grass,x*blockWidth,frameHeight-y* blockHeight, null)
//        g.color = Color.RED
//        g.drawRect(x*Consts.blockWidth,frameHeight-y* blockHeight, blockWidth, blockHeight)
//    }
override var occupiedBlocks: ArrayList<MapPoint> = ArrayList()

    init {
        occupiedBlocks.add(point)
    }
private var img:Image = sand_tiles[0]//[Random.nextInt(0, 16)]

    override fun draw(ge: GraphicsExtender){
//        ge.drawImage(grass,chunkX*chunkWidth+x*blockWidth,chunkY* chunkHeight -y* blockHeight)
//        ge.g.color = Color.RED
        ge.drawBlock(this)
    }

    override fun getImage(): Image {
    return img
    }



}