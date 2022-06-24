package world.objects.tiles

import GraphicsExtender
import ImageHelper.Companion.grass_tiles
import world.objects.DrawableObject
import world.objects.mobs.SimpleMob
import java.awt.Image
import kotlin.random.Random

class Grass(override var chunkAndPoint: SimpleMob.ChunkAndPoint, override var height: Int = 1, override var width: Int =1) :
    DrawableObject {
//    override fun draw(g: Graphics) {
//        g.drawImage(grass,x*blockWidth,frameHeight-y* blockHeight, null)
//        g.color = Color.RED
//        g.drawRect(x*Consts.blockWidth,frameHeight-y* blockHeight, blockWidth, blockHeight)
//    }
override var occupiedBlocks: ArrayList<SimpleMob.ChunkAndPoint> = ArrayList()

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