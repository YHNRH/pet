package world.objects.buildings

import GraphicsExtender
import world.MapPoint
import world.objects.BuildingDirection
import java.awt.Image
import java.awt.image.BufferedImage

class GenericBuilding(
    override var point: MapPoint,
    override var width: Int,
    override var height: Double,
    val image : BufferedImage
) : IBuilding {
    override var direction: BuildingDirection = BuildingDirection.LEFT
    override fun setOccupiedBlocks() {
    }

    override fun draw(ge: GraphicsExtender) {
        ge.drawBuilding(this)
    }

    override fun getImage(): Image {
        return this.image
    }

    override var occupiedBlocks: ArrayList<MapPoint> = ArrayList()
}