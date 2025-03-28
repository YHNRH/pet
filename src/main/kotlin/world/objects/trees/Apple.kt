package world.objects.trees

import GraphicsExtender
import ImageHelper.Companion.apple_fructify
import Point
import world.ChunkAndPoint
import world.objects.BuildingDirection
import world.objects.buildings.Building
import world.objects.mobs.SimpleMob
import java.awt.Image

class Apple (override var chunkAndPoint: ChunkAndPoint, override var width: Int = 4, override var height: Double = 8.0,

) :
    Tree
{
    override var state = TreeState.FULL
    override val fallCount = 1
    override var step = 1


    val animCount = 13
    override var direction: BuildingDirection = BuildingDirection.LEFT

    override fun draw(ge: GraphicsExtender) {
        ge.drawTree(this)
    }

    override fun setOccupiedBlocks() {
        this.occupiedBlocks.add(ChunkAndPoint(chunkAndPoint.chunk, Point(chunkAndPoint.point.getX()+1, chunkAndPoint.point.getY())))
    }

    override var occupiedBlocks: ArrayList<ChunkAndPoint> = ArrayList()


    override fun getImage(): Image {
        return apple_fructify[AnimationHelper.instance().index % animCount]
    }
}