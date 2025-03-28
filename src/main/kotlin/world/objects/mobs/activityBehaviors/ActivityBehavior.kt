package world.objects.mobs.activityBehaviors

import world.objects.mobs.Mob

abstract class ActivityBehavior( var nextActivityBehavior: ActivityBehavior? ) {

    abstract fun performActivity(mob: Mob)
    abstract  fun nextActivity(mob: Mob)

    abstract fun forceStop()
}