package world.objects.tiles

import world.objects.IDrawableObject
import java.awt.Color

interface ITile: IDrawableObject {
    val underlayColor: Color
}