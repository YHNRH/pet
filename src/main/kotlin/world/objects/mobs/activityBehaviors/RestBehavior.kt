package world.objects.mobs.activityBehaviors

import Consts.Companion.restAnimSpeed
import objects.Activity
import world.objects.mobs.IMob
import java.awt.event.ActionListener
import javax.swing.Timer

class RestBehavior(nextActivityBehavior: ActivityBehavior?) : ActivityBehavior(nextActivityBehavior)  {
    override fun performActivity(mob: IMob) {
        val task: ActionListener?
        var speed = restAnimSpeed
        if (mob.activity != Activity.REST){
            mob.activity = Activity.REST
            mob.step = 0

            task = ActionListener {
                if (mob.step+1 != mob.animSizes[Activity.REST]){
                    mob.step++
                } else {
                    (it.source as Timer).stop()
                    nextActivity(mob)
                }
            }
        } else {
            speed *= mob.animSizes[Activity.REST]!!
            task = ActionListener {
                (it.source as Timer).stop()
                nextActivity(mob)
            }
        }
        val t = Timer(speed, task)
        t.start()
    }
//    override fun performActivity(mob: Mob) {
////        mob.activity = Activity.STAND
////        val t = Timer(Random.nextInt(2000,5000)){
//        if (mob.direction == Direction.LEFT_BOTTOM){
//            (mob as Dog).rest(this)
//        } else {
//            nextActivity(mob)
//        }
////            (it.source as Timer).stop()
////        }
////        t.start()
//    }

    override fun nextActivity(mob: IMob) {
//        val task = ActionListener {
            nextActivityBehavior?.performActivity(mob)
//            (it.source as Timer).stop()
//        }
//        val t = Timer(Random.nextInt(3000,5000), task)
//        t.start()
    }

    override fun forceStop() {
        TODO("Not yet implemented")
    }
}