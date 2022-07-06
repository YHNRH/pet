package world.objects.tiles

import GraphicsExtender
import ImageHelper.Companion.trodden_ground
import world.objects.BuildingDirection
import world.objects.DrawableObject
import world.objects.buildings.Building
import world.objects.mobs.SimpleMob
import java.awt.Image
import kotlin.random.Random

class TroddenGround(override var chunkAndPoint: SimpleMob.ChunkAndPoint, override var height: Int = 8, override var width: Int = 8) :
    DrawableObject {
    //    override fun draw(g: Graphics) {
//        g.drawImage(grass,x*blockWidth,frameHeight-y* blockHeight, null)
//        g.color = Color.RED
//        g.drawRect(x*Consts.blockWidth,frameHeight-y* blockHeight, blockWidth, blockHeight)
//    }
    override var occupiedBlocks: ArrayList<SimpleMob.ChunkAndPoint> = ArrayList()


    override fun draw(ge: GraphicsExtender) {
//        ge.drawImage(grass,chunkX*chunkWidth+x*blockWidth,chunkY* chunkHeight -y* blockHeight)
//        ge.g.color = Color.RED
        ge.drawBlock(this)
    }

    override fun getImage(): Image {
        return trodden_ground
    }
}