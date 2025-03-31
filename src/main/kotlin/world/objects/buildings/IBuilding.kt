package world.objects.buildings

import world.objects.BuildingDirection
import world.objects.IDrawableObject

interface IBuilding: IDrawableObject {
    var direction: BuildingDirection
    fun setOccupiedBlocks()
}