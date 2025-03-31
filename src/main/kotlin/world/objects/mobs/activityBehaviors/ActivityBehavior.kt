package world.objects.mobs.activityBehaviors

import world.objects.mobs.IMob

abstract class ActivityBehavior( var nextActivityBehavior: ActivityBehavior? ) {

    abstract fun performActivity(mob: IMob)
    abstract  fun nextActivity(mob: IMob)

    abstract fun forceStop()
}