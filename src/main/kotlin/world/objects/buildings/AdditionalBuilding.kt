package world.objects.buildings

import Point
import world.objects.BuildingDirection
import world.objects.DrawableObject

class AdditionalBuilding(val obj: DrawableObject, val offset: HashMap<BuildingDirection,Point>) {
}