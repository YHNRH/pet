package world.objects.mobs.activityBehaviors

import world.objects.Direction
import world.objects.mobs.Dog
import world.objects.mobs.Mob

class PeeBehavior(var nextActivityBehavior: ActivityBehavior?) : ActivityBehavior {
    override fun performActivity(mob: Mob) {
        if (mob.direction == Direction.LEFT_TOP){
            (mob as Dog).pee(this)
        } else {
            nextActivity(mob)
        }
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