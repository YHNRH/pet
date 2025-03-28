package world.objects.buildings

import GraphicsExtender
import ImageHelper.Companion.wheatfarm
import Point
import world.ChunkAndPoint
import world.objects.BuildingDirection
import world.objects.DrawableObject
import world.objects.mobs.SimpleMob
import world.objects.tiles.Farmland
import java.awt.Image

class WheatFarm(override var chunkAndPoint: ChunkAndPoint, override var width: Int = 3, override var height: Double = 6.0,
) : Building {
    override var direction: BuildingDirection = BuildingDirection.LEFT
    val farmlands  = ArrayList<DrawableObject>()
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
        return wheatfarm
    }
}