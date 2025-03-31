package world.objects.buildings

import Point
import world.objects.BuildingDirection
import world.objects.IDrawableObject

class AdditionalBuilding(val obj: IDrawableObject, val offset: HashMap<BuildingDirection,Point>) {
}