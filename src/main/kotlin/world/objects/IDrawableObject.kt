package world.objects

import GraphicsExtender
import world.MapPoint
import java.awt.Image

interface IDrawableObject {
    var point: MapPoint
    fun draw(ge: GraphicsExtender)
    fun getImage() : Image


    var width: Int
    var height: Double
    var occupiedBlocks: ArrayList<MapPoint>

}