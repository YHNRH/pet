package world.objects.mobs.activityBehaviors

import Consts.Companion.restAnimSpeed
import objects.Activity
import world.objects.mobs.IMob
import world.objects.tiles.Farmland
import java.awt.event.ActionListener
import javax.swing.Timer
import kotlin.random.Random

class CutBehavior (nextActivityBehavior: ActivityBehavior?,
                   private var farmland: Farmland,
                   var skip: ()-> Boolean = {false})  : ActivityBehavior(nextActivityBehavior) {

    override fun performActivity(mob: IMob) {
        if (!skip()){
            mob.activity = Activity.CUT
            mob.step = 0

            val task = ActionListener {
                if (mob.step+1 != mob.animSizes[mob.activity]){
                    mob.step++
                } else {
                    farmland.nextPhase(Random.nextInt(1,3))
                    (it.source as Timer).stop()
                    nextActivity(mob)
                }
            }
            val t = Timer(restAnimSpeed, task)
            t.start()
        } else {nextActivity(mob)}
    }

    override fun nextActivity(mob: IMob) {
        nextActivityBehavior?.performActivity(mob)
    }

    override fun forceStop() {
        TODO("Not yet implemented")
    }
}