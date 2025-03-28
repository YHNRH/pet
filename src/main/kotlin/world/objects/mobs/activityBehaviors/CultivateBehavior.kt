package world.objects.mobs.activityBehaviors

import Consts.Companion.restAnimSpeed
import objects.Activity
import world.objects.mobs.Mob
import world.objects.tiles.Farmland
import java.awt.event.ActionListener
import javax.swing.Timer

class CultivateBehavior(nextActivityBehavior: ActivityBehavior?, private var farmland: Farmland, var skip: ()-> Boolean = {false}) : ActivityBehavior(nextActivityBehavior)  {
    override fun performActivity(mob: Mob) {
        if (!skip()){
            mob.activity = Activity.CULTIVATE
            mob.step = 0

            val task = ActionListener {
                if (mob.step+1 != mob.animSizes[Activity.CULTIVATE]){
                    mob.step++
                } else {
                    farmland.nextPhase()
                    (it.source as Timer).stop()
                    nextActivity(mob)
                }
            }
            val t = Timer(restAnimSpeed, task)
            t.start()
        } else {nextActivity(mob)}
    }

    override fun nextActivity(mob: Mob) {
        nextActivityBehavior?.performActivity(mob)
    }

    override fun forceStop() {
        TODO("Not yet implemented")
    }
}