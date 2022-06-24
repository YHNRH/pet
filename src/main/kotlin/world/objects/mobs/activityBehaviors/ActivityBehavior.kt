package world.objects.mobs.activityBehaviors

import world.objects.mobs.Mob

interface ActivityBehavior {

    fun performActivity(mob: Mob)
    fun nextActivity(mob: Mob)
}