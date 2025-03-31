package world.objects.mobs.activityBehaviors

import world.MapPoint
import world.objects.mobs.IMob
import world.objects.tiles.Farmland

class SowBehavior(
    nextActivityBehavior: ActivityBehavior?,
    var destination: MapPoint,
    var skip: () -> Boolean = { false },
    var farmland: Farmland
) : ActivityBehavior(nextActivityBehavior) {

    override fun performActivity(mob: IMob) {
        if (!skip() && destination != mob.point) {
        //    WalkBehavior(this, destination, { false }, Activity.SOW).performActivity(mob)
        } else {
            nextActivity(mob)
        }
    }

    override fun nextActivity(mob: IMob) {
        if (!skip()) {
            farmland.nextPhase()
        }
        nextActivityBehavior?.performActivity(mob)
    }

    override fun forceStop() {
        TODO("Not yet implemented")
    }
}