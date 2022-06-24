package world.objects.mobs.activityBehaviors

import objects.Activity
import world.objects.Direction
import world.objects.mobs.Dog
import world.objects.mobs.Mob
import java.awt.event.ActionListener
import javax.swing.Timer
import kotlin.random.Random

class RestBehavior(var nextActivityBehavior: ActivityBehavior?) : ActivityBehavior {
    override fun performActivity(mob: Mob) {
//        mob.activity = Activity.STAND
//        val t = Timer(Random.nextInt(2000,5000)){
        if (mob.direction == Direction.LEFT_BOTTOM){
            (mob as Dog).rest(this)
        } else {
            nextActivity(mob)
        }
//            (it.source as Timer).stop()
//        }
//        t.start()
    }

    override fun nextActivity(mob: Mob) {
//        val task = ActionListener {
            nextActivityBehavior?.performActivity(mob)
//            (it.source as Timer).stop()
//        }
//        val t = Timer(Random.nextInt(3000,5000), task)
//        t.start()
    }
}