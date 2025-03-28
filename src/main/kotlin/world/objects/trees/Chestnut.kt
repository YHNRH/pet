package world.objects.trees

import GraphicsExtender
import ImageHelper.Companion.apple_fructify
import ImageHelper.Companion.chestnut_full
import ImageHelper.Companion.chestnut_full_fall
import objects.Activity
import world.ChunkAndPoint
import world.objects.BuildingDirection
import world.objects.buildings.Building
import world.objects.mobs.SimpleMob
import java.awt.Image
import java.awt.event.ActionListener
import javax.swing.Timer
import kotlin.random.Random

class Chestnut (override var chunkAndPoint: ChunkAndPoint, override var width: Int = 6, override var height: Double = 12.0,

) : Tree {

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