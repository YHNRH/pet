package world.objects.buildings

import world.objects.BuildingDirection
import world.objects.DrawableObject

interface Building: DrawableObject {
    var direction: BuildingDirection
    fun setOccupiedBlocks()
}