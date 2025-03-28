package world.objects.mobs.activityBehaviors

import Consts.Companion.restAnimSpeed
import objects.Activity
import world.objects.mobs.Mob
import java.awt.event.ActionListener
import javax.swing.Timer

class RestContiniousBehavior(nextActivityBehavior: ActivityBehavior?) : ActivityBehavior(nextActivityBehavior)  {
    override fun performActivity(mob: Mob) {
        val task: ActionListener?
        var speed = restAnimSpeed
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
        val t = Timer(speed, task)
        t.start()
    }

    override fun nextActivity(mob: Mob) {
        nextActivityBehavior?.performActivity(mob)
    }

    override fun forceStop() {
        TODO("Not yet implemented")
    }
}