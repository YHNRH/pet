package world.objects.trees

import GraphicsExtender
import ImageHelper.Companion.chestnut_full
import ImageHelper.Companion.chestnut_full_fall
import world.ChunkAndPoint
import world.objects.BuildingDirection
import java.awt.Image

class Chestnut (override var chunkAndPoint: ChunkAndPoint, override var width: Int = 6, override var height: Double = 12.0,

) : ITree {

    override var direction = BuildingDirection.LEFT


    override var state: TreeState = TreeState.FULL
    override val fallCount: Int = 10
    override var step = 0
    override fun setOccupiedBlocks() {
        occupiedBlocks.add(chunkAndPoint)
    }

    val animCount = 13

    override fun draw(ge: GraphicsExtender) {
        ge.drawTree(this)
    }


    override var occupiedBlocks: ArrayList<ChunkAndPoint> = ArrayList()


    override fun getImage(): Image {
        when(state){
            TreeState.FULL -> return chestnut_full[AnimationHelper.instance().index % animCount]
            TreeState.FALL -> return chestnut_full_fall[step]
        }
    }
}