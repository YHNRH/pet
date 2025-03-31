package world.objects.buildings

import GraphicsExtender
import ImageHelper.Companion.applefarm
import world.MapPoint
import world.objects.BuildingDirection
import java.awt.Image

class AppleFarm(override var point: MapPoint, override var width: Int = 4, override var height: Double = 8.0,
) : IBuilding {
    override var direction: BuildingDirection = BuildingDirection.LEFT

    override fun draw(ge: GraphicsExtender) {
        ge.drawBuilding(this)
    }
    override var occupiedBlocks: ArrayList<MapPoint> = ArrayList()

//    init {

//    }

    override fun setOccupiedBlocks() {
        for (x in (this.point.getX())until (this.point.getX()+width)){
            for (y in (this.point.getY())until (this.point.getY()+width)){
                this.occupiedBlocks.add(MapPoint(x,y, point.chunk))
            }
        }
    }


    override fun getImage(): Image {
        return applefarm
    }
}