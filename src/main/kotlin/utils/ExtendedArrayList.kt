package utils

import world.MapPoint
import world.objects.IDrawableObject

class ExtendedArrayList<E> : ArrayList<E>() {
    fun getByPoint(point: MapPoint): E? {
        this.forEach{
            if ((it as IDrawableObject).point == point)
                return it
        }
        return null
    }
}