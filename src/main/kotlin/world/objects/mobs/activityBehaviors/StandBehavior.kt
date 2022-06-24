package world.objects.mobs.activityBehaviors

import objects.Activity
import world.Chunks
import world.objects.Direction
import world.objects.DrawableObject
import world.objects.mobs.Mob
import javax.swing.Timer
import kotlin.random.Random
import kotlin.reflect.typeOf


class StandBehavior(var nextActivityBehavior: ActivityBehavior?) : ActivityBehavior {
    override fun performActivity(mob: Mob) {
//        mob.activity = Activity.STAND
//        val t = Timer(Random.nextInt(2000,5000)){
            nextActivity(mob)
//            (it.source as Timer).stop()
//        }
//        t.start()
    }

    override fun nextActivity(mob: Mob) {
        nextActivityBehavior?.performActivity(mob)
    }
}