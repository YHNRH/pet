package world.objects.buildings

import world.objects.DrawableObject

interface Building: DrawableObject {

    fun setOccupiedBlocks()
}