package world.objects.mobs.activityBehaviors

import Consts.Companion.chunkSize
import world.MapPoint
import objects.Activity
import world.Chunks
import world.SimplePoint
import world.objects.IDrawableObject
import world.objects.mobs.IMob
import kotlin.random.Random

class RunBehavior(nextActivityBehavior: ActivityBehavior?) : ActivityBehavior(nextActivityBehavior)  {
    override fun performActivity(mob: IMob) {
        val activity = Activity.RUN
        var rndX = Random.nextInt(-10, 10)
        var rndY = Random.nextInt(-10, 10)
        var chunkX = 0
        var chunkY = 0
        if (mob.point.getX() + rndX >= chunkSize){
            rndX = (mob.point.getX() + rndX) % chunkSize
            chunkX++
        } else if (mob.point.getX() + rndX < 0){
            rndX += chunkSize + mob.point.getX()
            chunkX--
        } else {
            rndX += mob.point.getX()
        }

        if (mob.point.getY() + rndY >= chunkSize){
            rndY =  (mob.point.getY() + rndY) % chunkSize
            chunkY++
        } else if (mob.point.getY() + rndY < 0){
            rndY -= chunkSize + mob.point.getY()
            chunkY--
        } else {
            rndY += mob.point.getY()
        }
        val chunk = Chunks.instance().chunks.get(SimplePoint(chunkX,chunkY))
        val pointExist = chunk?.getNoncollisionObject(MapPoint(rndX,rndY, chunk)) != null
        if (chunk != null && pointExist){
            val objects = ArrayList<IDrawableObject>()
            Chunks.instance().chunks.forEach{
                objects.addAll(it.value.objects)
            }
      //      mob.move(
     //           SimpleMob.ChunkAndPoint(chunk, world.Point(rndX,rndY)),
    //            Chunks.instance().chunks, objects , this, activity)
        } else {
            performActivity(mob)
        }
    }

    override fun nextActivity(mob: IMob) {
        nextActivityBehavior?.performActivity(mob)
    }

    override fun forceStop() {
        TODO("Not yet implemented")
    }
}