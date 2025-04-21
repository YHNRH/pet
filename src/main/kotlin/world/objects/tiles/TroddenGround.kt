package world.objects.tiles

import GraphicsExtender
import ImageHelper.Companion.trodden_ground
import world.MapPoint
import world.objects.IDrawableObject
import java.awt.Color
import java.awt.Image

class TroddenGround(override var point: MapPoint, override var height: Double = 6.0, override var width: Int = 6) :
    ITile {

    override val underlayColor: Color = Color(96,120,56)
    //    override fun draw(g: Graphics) {
//        g.drawImage(grass,x*blockWidth,frameHeight-y* blockHeight, null)
//        g.color = Color.RED
//        g.drawRect(x*Consts.blockWidth,frameHeight-y* blockHeight, blockWidth, blockHeight)
//    }
    override var occupiedBlocks: ArrayList<MapPoint> = ArrayList()


    override fun draw(ge: GraphicsExtender) {
//        ge.drawImage(grass,chunkX*chunkWidth+x*blockWidth,chunkY* chunkHeight -y* blockHeight)
//        ge.g.color = Color.RED
        ge.drawBlock(this)
    }

    override fun getImage(): Image {
        return trodden_ground
    }
}