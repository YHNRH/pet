package world.objects

import java.awt.Image
import java.awt.image.BufferedImage

class DrawableObjectPart(private val offsetX: HashMap<BuildingDirection, Int>, private val offsetY: HashMap<BuildingDirection, Int>,
                         private val images: HashMap<BuildingDirection, BufferedImage>) {
    fun getImage(direction: BuildingDirection) : BufferedImage {
        return images.get(direction)!!
    }

    fun getOffsetX(direction: BuildingDirection) : Int {
        return offsetX.get(direction)!!
    }

    fun getOffsetY(direction: BuildingDirection) : Int {
        return offsetY.get(direction)!!
    }
}