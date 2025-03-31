package world.objects.trees

import GraphicsExtender
import ImageHelper.Companion.apple_fructify
import world.MapPoint
import world.objects.BuildingDirection
import java.awt.Image

class Apple (override var point: MapPoint, override var width: Int = 4, override var height: Double = 8.0,

             ) :
    ITree
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
        this.occupiedBlocks.add(MapPoint(point.getX()+1, point.getY(), point.chunk))
    }

    override var occupiedBlocks: ArrayList<MapPoint> = ArrayList()


    override fun getImage(): Image {
        return apple_fructify[AnimationHelper.instance().index % animCount]
    }
}