package world.objects.trees

import GraphicsExtender
import ImageHelper.Companion.apple_fructify
import world.objects.BuildingDirection
import world.objects.buildings.Building
import world.objects.mobs.SimpleMob
import java.awt.Image

class Apple (override var chunkAndPoint: SimpleMob.ChunkAndPoint, override var width: Int = 4, override var height: Int = 8,
) : Building {
    val animCount = 13
    override var direction: BuildingDirection = BuildingDirection.LEFT

    override fun draw(ge: GraphicsExtender) {
        ge.drawTree(this)
    }

    override fun setOccupiedBlocks() {
        this.occupiedBlocks.add(chunkAndPoint)
    }

    override var occupiedBlocks: ArrayList<SimpleMob.ChunkAndPoint> = ArrayList()


    override fun getImage(): Image {
        return apple_fructify[AnimationHelper.instance().index % animCount]
    }
}