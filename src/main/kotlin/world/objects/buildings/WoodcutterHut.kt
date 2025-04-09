package world.objects.buildings

import GraphicsExtender
import ImageHelper.Companion.woodcutter_hut
import Point
import world.ChunkAndPoint
import world.objects.BuildingDirection
import java.awt.image.BufferedImage

class WoodcutterHut(override var chunkAndPoint: ChunkAndPoint, override var width: Int = 2, override var height: Double = 4.0,
) : Building {
    override fun draw(ge: GraphicsExtender) {
        ge.drawBuilding(this)
    }
    override var occupiedBlocks: ArrayList<ChunkAndPoint> = ArrayList()
    override var direction: BuildingDirection = BuildingDirection.LEFT

//    init {

//    }

    override fun setOccupiedBlocks() {
        for (x in (this.chunkAndPoint.point.getX()+1)until (this.chunkAndPoint.point.getX()+width)){
            for (y in (this.chunkAndPoint.point.getY()+1)until (this.chunkAndPoint.point.getY()+width)){
                this.occupiedBlocks.add(ChunkAndPoint(chunkAndPoint.chunk, Point(x,y)))
            }
        }
    }


    override fun getImage(): BufferedImage {
        return woodcutter_hut
    }
}