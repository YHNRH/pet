package world.objects.mobs.activityBehaviors

import Consts.Companion.restAnimSpeed
import objects.Activity
import world.objects.mobs.IMob
import world.objects.mobs.Woodcutter
import world.objects.trees.ITree
import java.awt.event.ActionListener
import javax.swing.Timer

class ChopLogBehavior(nextActivityBehavior: ActivityBehavior?) : ActivityBehavior(nextActivityBehavior) {

    override fun performActivity(mob: IMob) {
        mob as Woodcutter
            if (mob.tree != null) {
//                val chop =
                    ChopBehavior(nextActivityBehavior!!, mob.tree!!).performActivity(mob)
//                WalkBehavior(chop, mob.tree!!.chunkAndPoint, { false }, Activity.WALK, { }, 1).performActivity(mob)
            } else {
                nextActivity(mob)
            }
        }


    override fun nextActivity(mob: IMob) {
        nextActivityBehavior?.performActivity(mob)
    }

    override fun forceStop() {
        TODO("Not yet implemented")
    }

    private class ChopBehavior(nextActivityBehavior: ActivityBehavior, tree: ITree) :
        ActivityBehavior(nextActivityBehavior) {
        override fun performActivity(mob: IMob) {
            mob as Woodcutter
            mob.activity = Activity.CHOP_LOG
            mob.step = 0
            // Анимация проигрывается 5 раз
            var counter = 0


            val task = ActionListener {
                if (mob.step + 1 != mob.animSizes[Activity.CHOP_LOG]) {
                    mob.step++
                } else {
                    counter++
                    mob.step = 0
                    if (counter == 5) {
                        (it.source as Timer).stop()
                        nextActivity(mob)
                    }
                }
            }
            val t = Timer(restAnimSpeed, task)
            t.start()
        }

        override fun nextActivity(mob: IMob) {
            nextActivityBehavior?.performActivity(mob)
        }

        override fun forceStop() {
            TODO("Not yet implemented")
        }

    }
}
