package world.objects

import GraphicsExtender
import world.objects.mobs.SimpleMob
import java.awt.Image
import java.awt.image.BufferedImage

interface DrawableObject {
    var chunkAndPoint: SimpleMob.ChunkAndPoint
    fun draw(ge: GraphicsExtender)
    fun getImage() : Image
    var width: Int
    var height: Int
    var occupiedBlocks: ArrayList<SimpleMob.ChunkAndPoint>

}