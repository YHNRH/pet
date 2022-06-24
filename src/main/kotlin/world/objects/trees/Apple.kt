package world.objects.trees

import GraphicsExtender
import ImageHelper.Companion.apple_fructify
import world.objects.buildings.Building
import world.objects.mobs.SimpleMob
import java.awt.Image

class Apple (override var chunkAndPoint: SimpleMob.ChunkAndPoint, override var width: Int = 4, override var height: Int = 8,
) : Building {
    val animCount = 13
    override fun draw(ge: GraphicsExtender) {
        ge.drawTree(this)
    }
    override var occupiedBlocks: ArrayList<SimpleMob.ChunkAndPoint> = ArrayList()

    init {
//        for (x in (this.chunkAndPoint.point.getX())until (this.chunkAndPoint.point.getX()+width/2)){
//            for (y in (this.chunkAndPoint.point.getY())until (this.chunkAndPoint.point.getY()+width/2)){
        this.occupiedBlocks.add(chunkAndPoint)
//            }
//        }
    }

    override fun getImage(): Image {
        return apple_fructify[AnimationHelper.instance().index % animCount]
    }
}