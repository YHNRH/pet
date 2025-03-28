package world.objects

import GraphicsExtender
import world.ChunkAndPoint
import world.objects.mobs.SimpleMob
import java.awt.Image
import java.awt.image.BufferedImage

interface DrawableObject {
    var chunkAndPoint: ChunkAndPoint
    fun draw(ge: GraphicsExtender)
    fun getImage() : Image


    var width: Int
    var height: Double
    var occupiedBlocks: ArrayList<ChunkAndPoint>

}