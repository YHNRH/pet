package world.objects.buildings

import GraphicsExtender
import ImageHelper.Companion.hopsfarm
import Point
import world.ChunkAndPoint
import world.objects.BuildingDirection
import java.awt.Image

class HopsFarm(override var chunkAndPoint: ChunkAndPoint, override var width: Int = 2, override var height: Double = 4.0,
) : IBuilding {
    override var direction: BuildingDirection = BuildingDirection.LEFT

    override fun draw(ge: GraphicsExtender) {
        ge.drawBuilding(this)
    }
    override var occupiedBlocks: ArrayList<ChunkAndPoint> = ArrayList()

//    init {

//    }

    override fun setOccupiedBlocks() {
        for (x in (this.chunkAndPoint.point.getX())until (this.chunkAndPoint.point.getX()+width)){
            for (y in (this.chunkAndPoint.point.getY())until (this.chunkAndPoint.point.getY()+width)){
                this.occupiedBlocks.add(ChunkAndPoint(chunkAndPoint.chunk, Point(x,y)))
            }
        }
    }


    override fun getImage(): Image {
        return hopsfarm
    }
}