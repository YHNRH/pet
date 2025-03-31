package world.objects

import GraphicsExtender
import world.ChunkAndPoint
import java.awt.Image

interface IDrawableObject {
    var chunkAndPoint: ChunkAndPoint
    fun draw(ge: GraphicsExtender)
    fun getImage() : Image


    var width: Int
    var height: Double
    var occupiedBlocks: ArrayList<ChunkAndPoint>

}