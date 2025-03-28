package world.objects.mobs.activityBehaviors

import Consts.Companion.chunkSize
import Point
import objects.Activity
import world.Chunks
import world.objects.Direction
import world.objects.DrawableObject
import world.objects.mobs.Mob
import world.objects.mobs.SimpleMob
import kotlin.random.Random

class RunBehavior(nextActivityBehavior: ActivityBehavior?) : ActivityBehavior(nextActivityBehavior)  {
    override fun performActivity(mob: Mob) {
        val activity = Activity.RUN
        var rndX = Random.nextInt(-10, 10)
        var rndY = Random.nextInt(-10, 10)
        var chunkX = 0
        var chunkY = 0
        if (mob.chunkAndPoint.point.getX() + rndX >= chunkSize){
            rndX = (mob.chunkAndPoint.point.getX() + rndX) % chunkSize
            chunkX++
        } else if (mob.chunkAndPoint.point.getX() + rndX < 0){
            rndX += chunkSize + mob.chunkAndPoint.point.getX()
            chunkX--
        } else {
            rndX += mob.chunkAndPoint.point.getX()
        }

        if (mob.chunkAndPoint.point.getY() + rndY >= chunkSize){
            rndY =  (mob.chunkAndPoint.point.getY() + rndY) % chunkSize
            chunkY++
        } else if (mob.chunkAndPoint.point.getY() + rndY < 0){
            rndY -= chunkSize + mob.chunkAndPoint.point.getY()
            chunkY--
        } else {
            rndY += mob.chunkAndPoint.point.getY()
        }
        val chunk = Chunks.instance().chunks.get(Point(chunkX,chunkY))
        val pointExist = chunk?.getNoncollisionObject(Point(rndX,rndY)) != null
        if (chunk != null && pointExist){
            val objects = ArrayList<DrawableObject>()
            Chunks.instance().chunks.forEach{
                objects.addAll(it.value.objects)
            }
      //      mob.move(
     //           SimpleMob.ChunkAndPoint(chunk, Point(rndX,rndY)),
    //            Chunks.instance().chunks, objects , this, activity)
        } else {
            performActivity(mob)
        }
    }

    override fun nextActivity(mob: Mob) {
        nextActivityBehavior?.performActivity(mob)
    }

    override fun forceStop() {
        TODO("Not yet implemented")
    }
}