package world.objects.mobs.activityBehaviors

import world.objects.mobs.IMob


class StandBehavior(nextActivityBehavior: ActivityBehavior?) : ActivityBehavior(nextActivityBehavior) {
    override fun performActivity(mob: IMob) {
//        mob.activity = Activity.STAND
//        val t = Timer(Random.nextInt(2000,5000)){
            nextActivity(mob)
//            (it.source as Timer).stop()
//        }
//        t.start()
    }

    override fun nextActivity(mob: IMob) {
        nextActivityBehavior?.performActivity(mob)
    }

    override fun forceStop() {
        TODO("Not yet implemented")
    }
}