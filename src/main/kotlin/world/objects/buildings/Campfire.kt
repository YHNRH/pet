package world.objects.buildings

import AnimationHelper
import GraphicsExtender
import ImageHelper.Companion.campfire
import world.objects.mobs.SimpleMob
import java.awt.Image

class Campfire(override var chunkAndPoint: SimpleMob.ChunkAndPoint, override var width: Int = 1, override var height: Int = 2,
) : Building {
    val animCount = 18
    override fun setOccupiedBlocks() {
                this.occupiedBlocks.add(chunkAndPoint)
    }

    override fun draw(ge: GraphicsExtender) {
        ge.drawBuilding(this)
    }
    override var occupiedBlocks: ArrayList<SimpleMob.ChunkAndPoint> = ArrayList()

//    init {
////        for (x in (this.chunkAndPoint.point.getX())until (this.chunkAndPoint.point.getX()+width/2)){
////            for (y in (this.chunkAndPoint.point.getY())until (this.chunkAndPoint.point.getY()+width/2)){
//                this.occupiedBlocks.add(chunkAndPoint)
////            }
////        }
//    }

    override fun getImage(): Image {
        return campfire[AnimationHelper.instance().index % animCount]
    }
}