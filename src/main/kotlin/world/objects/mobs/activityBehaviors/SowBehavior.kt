package world.objects.mobs.activityBehaviors

import world.ChunkAndPoint
import world.objects.mobs.IMob
import world.objects.tiles.Farmland

class SowBehavior(
    nextActivityBehavior: ActivityBehavior?,
    var destination: ChunkAndPoint,
    var skip: () -> Boolean = { false },
    var farmland: Farmland
) : ActivityBehavior(nextActivityBehavior) {

    override fun performActivity(mob: IMob) {
        if (!skip() && destination != mob.chunkAndPoint) {
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