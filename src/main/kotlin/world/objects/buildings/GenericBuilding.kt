package world.objects.buildings

import GraphicsExtender
import world.ChunkAndPoint
import world.objects.BuildingDirection
import world.objects.mobs.SimpleMob
import java.awt.Image
import java.awt.image.BufferedImage

class GenericBuilding(
    override var chunkAndPoint: ChunkAndPoint,
    override var width: Int,
    override var height: Double,
    val image : BufferedImage
) : Building {
    override var direction: BuildingDirection = BuildingDirection.LEFT
    override fun setOccupiedBlocks() {
    }

    override fun draw(ge: GraphicsExtender) {
        ge.drawBuilding(this)
    }

    override fun getImage(): Image {
        return this.image
    }

    override var occupiedBlocks: ArrayList<ChunkAndPoint> = ArrayList()
}