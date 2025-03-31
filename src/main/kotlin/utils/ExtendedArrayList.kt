package utils

import Point
import world.objects.IDrawableObject

class ExtendedArrayList<E> : ArrayList<E>() {
    fun getByPoint(point: Point): E? {
        this.forEach{
            if ((it as IDrawableObject).chunkAndPoint.point == point)
                return it
        }
        return null
    }
}