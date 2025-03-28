package world.objects.trees

import world.objects.buildings.Building

interface Tree: Building {
    var state: TreeState
    val fallCount: Int
    var step: Int
}