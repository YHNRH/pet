package world.objects.mobs

import world.Chunk
import Point
import objects.Activity
import world.objects.Direction
import world.objects.DrawableObject
import world.objects.mobs.activityBehaviors.ActivityBehavior
import javax.swing.Timer

interface Mob : DrawableObject {
    fun move(destination: SimpleMob.ChunkAndPoint, chunks: HashMap<Point, Chunk>, objects: ArrayList<DrawableObject>, caller: ActivityBehavior?, activity: Activity)
    var direction: Direction
    var activity: Activity
    var behavior: ActivityBehavior?
//    val stepsForBlock: Int
    var step : Int
    val animSizes: HashMap<Activity,Int>

}