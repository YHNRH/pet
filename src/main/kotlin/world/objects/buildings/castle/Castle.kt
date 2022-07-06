package world.objects.buildings.castle

import GraphicsExtender
import ImageHelper.Companion.castle
import ImageHelper.Companion.castle_gate0_l
import ImageHelper.Companion.castle_gate0_r
import ImageHelper.Companion.castle_gate1_l
import ImageHelper.Companion.castle_gate1_r
import Point
import world.objects.BuildingDirection
import world.objects.DrawableObject
import world.objects.DrawableObjectPart
import world.objects.buildings.Building
import world.objects.mobs.SimpleMob
import java.awt.Image
import java.awt.image.BufferedImage

class Castle(override var chunkAndPoint: SimpleMob.ChunkAndPoint, override var width: Int = 8, override var height: Int = 16) : Building {
    override var direction: BuildingDirection = BuildingDirection.LEFT

    override fun setOccupiedBlocks() {
        for (x in (this.chunkAndPoint.point.getX())until (this.chunkAndPoint.point.getX()+width)){
            for (y in (this.chunkAndPoint.point.getY())until (this.chunkAndPoint.point.getY()+width)){
                this.occupiedBlocks.add(SimpleMob.ChunkAndPoint(chunkAndPoint.chunk, Point(x,y)))
            }
        }
    }
    val drawParts = ArrayList<DrawableObjectPart>()

    init {
        val gate0Imgs = HashMap<BuildingDirection, BufferedImage>()
        gate0Imgs.put(BuildingDirection.LEFT, castle_gate0_l)
        gate0Imgs.put(BuildingDirection.RIGHT, castle_gate0_r)

        val gate0OffsetX = HashMap<BuildingDirection, Int>()
        gate0OffsetX.put(BuildingDirection.LEFT, 20)
        gate0OffsetX.put(BuildingDirection.RIGHT, 160)


        val gate0OffsetY = HashMap<BuildingDirection, Int>()
        gate0OffsetY.put(BuildingDirection.LEFT, 15)
        gate0OffsetY.put(BuildingDirection.RIGHT, 0)


        val gate0 = DrawableObjectPart(gate0OffsetX, gate0OffsetY, gate0Imgs)

        val gate1Imgs = HashMap<BuildingDirection, BufferedImage>()
        gate1Imgs.put(BuildingDirection.LEFT, castle_gate1_l)
        gate1Imgs.put(BuildingDirection.RIGHT, castle_gate1_r)

        val gate1OffsetX = HashMap<BuildingDirection, Int>()
        gate1OffsetX.put(BuildingDirection.LEFT, 49)
        gate1OffsetX.put(BuildingDirection.RIGHT, 190)


        val gate1OffsetY = HashMap<BuildingDirection, Int>()
        gate1OffsetY.put(BuildingDirection.LEFT, 0)
        gate1OffsetY.put(BuildingDirection.RIGHT, 15)


        val gate1 = DrawableObjectPart(gate1OffsetX, gate1OffsetY, gate1Imgs)
        drawParts.add(gate0)
        drawParts.add(gate1)
    }

    override fun draw(ge: GraphicsExtender) {
        ge.drawBuilding(this, drawParts)
    }

    override fun getImage(): BufferedImage {
        return castle
    }

    override var occupiedBlocks: ArrayList<SimpleMob.ChunkAndPoint> = ArrayList()
}