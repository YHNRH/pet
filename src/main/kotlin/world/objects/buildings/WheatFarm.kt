package world.objects.buildings

import GraphicsExtender
import ImageHelper.Companion.wheatfarm
import Point
import world.objects.BuildingDirection
import world.objects.mobs.SimpleMob
import java.awt.Image

class WheatFarm(override var chunkAndPoint: SimpleMob.ChunkAndPoint, override var width: Int = 4, override var height: Int = 8,
) : Building {
    override var direction: BuildingDirection = BuildingDirection.LEFT

    override fun draw(ge: GraphicsExtender) {
        ge.drawBuilding(this)
    }
    override var occupiedBlocks: ArrayList<SimpleMob.ChunkAndPoint> = ArrayList()

//    init {

//    }

    override fun setOccupiedBlocks() {
        for (x in (this.chunkAndPoint.point.getX())until (this.chunkAndPoint.point.getX()+width)){
            for (y in (this.chunkAndPoint.point.getY())until (this.chunkAndPoint.point.getY()+width)){
                this.occupiedBlocks.add(SimpleMob.ChunkAndPoint(chunkAndPoint.chunk, Point(x,y)))
            }
        }
    }


    override fun getImage(): Image {
        return wheatfarm
    }
}