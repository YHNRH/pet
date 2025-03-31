package world.objects.mobs

import objects.Activity
import world.MapPoint
import world.objects.Direction
import world.objects.IDrawableObject
import world.objects.mobs.activityBehaviors.ActivityBehavior

interface IMob : IDrawableObject {
   // fun move(destination: SimpleMob.ChunkAndPoint, chunks: HashMap<world.Point, Chunk>, objects: ArrayList<DrawableObject>, caller: ActivityBehavior?, activity: Activity)
    var pathForDebugDraw: ArrayList<ArrayList<MapPoint>>
    var direction: Direction
    var activity: Activity
    var behavior: ActivityBehavior?
//    val stepsForBlock: Int
    var step : Int
    val animSizes: HashMap<Activity,Int>

}