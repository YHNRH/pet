package utils

import Point
import world.objects.DrawableObject

class ExtendedArrayList<E> : ArrayList<E>() {
    fun getByPoint(point: Point): E? {
        this.forEach{
            if ((it as DrawableObject).chunkAndPoint.point == point)
                return it
        }
        return null
    }
}