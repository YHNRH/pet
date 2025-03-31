package world.objects.mobs.activityBehaviors

import Consts.Companion.restAnimSpeed
import objects.Activity
import world.Chunks
import world.objects.mobs.IMob
import world.objects.mobs.Woodcutter
import world.objects.trees.ITree
import world.objects.trees.TreeState
import java.awt.event.ActionListener
import javax.swing.Timer

class ChopTreeBehavior(nextActivityBehavior: ActivityBehavior?) : ActivityBehavior(nextActivityBehavior) {

    override fun performActivity(mob: IMob) {
        mob as Woodcutter
        if (mob.tree == null){
           mob.tree = Chunks.instance().chunks.get(mob.point.chunk)!!.getTrees()[0]
            if (mob.tree != null){
                val chop = ChopBehavior(nextActivityBehavior!!, mob.tree!!)
          //      WalkBehavior(chop, mob.tree!!.chunkAndPoint, {false}, Activity.WALK, {  }, 1).performActivity(mob)
            } else {
                nextActivity(mob)
            }
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

    private class ChopBehavior(nextActivityBehavior: ActivityBehavior, tree: ITree): ActivityBehavior(nextActivityBehavior){
        override fun performActivity(mob: IMob) {
            mob as Woodcutter
            mob.activity = Activity.CHOP_TREE
            mob.step = 0
            // Анимация проигрывается 5 раз
            var counter = 0


            val treeFall = ActionListener {
                if (mob.tree!!.step != mob.tree!!.fallCount-1){
                    mob.tree!!.step++
                } else {
                    (it.source as Timer).stop()
                    nextActivity(mob)

                }
            }


            val task = ActionListener {
                if (mob.step+1 != mob.animSizes[Activity.CHOP_TREE]){
                    mob.step++
                } else {
                    counter++
                    mob.step = 0
                    if (counter == 5){
                        mob.tree!!.state = TreeState.FALL
                        mob.tree!!.step = 0
                        val t = Timer(restAnimSpeed, treeFall)
                        t.start()
                        (it.source as Timer).stop()
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