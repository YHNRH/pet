package world.objects.mobs.activityBehaviors

import objects.Activity
import world.Chunk
import world.ChunkAndPoint
import world.Chunks
import world.objects.Direction
import world.objects.mobs.Mob
import world.objects.mobs.SimpleMob
import world.objects.tiles.Farmland
import javax.swing.Timer

class SowBehavior(
    nextActivityBehavior: ActivityBehavior?,
    var destination: ChunkAndPoint,
    var skip: () -> Boolean = { false },
    var farmland: Farmland
) : ActivityBehavior(nextActivityBehavior) {

    override fun performActivity(mob: Mob) {
        if (!skip() && destination != mob.chunkAndPoint) {
        //    WalkBehavior(this, destination, { false }, Activity.SOW).performActivity(mob)
        } else {
            nextActivity(mob)
        }
    }

    override fun nextActivity(mob: Mob) {
        if (!skip()) {
            farmland.nextPhase()
        }
        nextActivityBehavior?.performActivity(mob)
    }

    override fun forceStop() {
        TODO("Not yet implemented")
    }
}