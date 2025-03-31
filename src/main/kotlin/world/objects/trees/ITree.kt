package world.objects.trees

import world.objects.buildings.IBuilding

interface ITree: IBuilding {
    var state: TreeState
    val fallCount: Int
    var step: Int
}