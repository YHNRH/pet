package world.objects.tiles

import GraphicsExtender
import ImageHelper.Companion.grass_tiles
import world.ChunkAndPoint
import world.objects.IDrawableObject
import java.awt.Image
import kotlin.random.Random

class Grass(override var chunkAndPoint: ChunkAndPoint, override var height: Double = 1.0, override var width: Int =1) :
    IDrawableObject {
//    override fun draw(g: Graphics) {
//        g.drawImage(grass,x*blockWidth,frameHeight-y* blockHeight, null)
//        g.color = Color.RED
//        g.drawRect(x*Consts.blockWidth,frameHeight-y* blockHeight, blockWidth, blockHeight)
//    }
override var occupiedBlocks: ArrayList<ChunkAndPoint> = ArrayList()

    init {
        occupiedBlocks.add(chunkAndPoint)
    }
private var img:Image = grass_tiles[Random.nextInt(0, 8)]

    override fun draw(ge: GraphicsExtender){
//        ge.drawImage(grass,chunkX*chunkWidth+x*blockWidth,chunkY* chunkHeight -y* blockHeight)
//        ge.g.color = Color.RED
        ge.drawBlock(this)
    }

    override fun getImage(): Image {
    return img
    }



}