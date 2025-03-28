package managers

import world.objects.buildings.castle.Castle

class CastleManager {
    var castle: Castle? = null
    val farmManager: FarmManager = FarmManager(this)
    val woodcutterManager: WoodcutterManager = WoodcutterManager(this)
}