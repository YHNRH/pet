package world.objects.buildings

import AnimationHelper
import GraphicsExtender
import ImageHelper.Companion.campfire
import world.ChunkAndPoint
import world.objects.BuildingDirection
import java.awt.Image

class Campfire(override var chunkAndPoint: ChunkAndPoint, override var width: Int = 1, override var height: Double = 2.0,
) : IBuilding {
    override var direction: BuildingDirection = BuildingDirection.LEFT

    val animCount = 18
    override fun setOccupiedBlocks() {
                this.occupiedBlocks.add(chunkAndPoint)
    }

    override fun draw(ge: GraphicsExtender) {
        ge.drawBuilding(this)
    }
    override var occupiedBlocks: ArrayList<ChunkAndPoint> = ArrayList()

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