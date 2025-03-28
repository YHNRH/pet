package world.objects.mobs.activityBehaviors

import objects.Activity
import world.objects.buildings.Palette
import world.objects.buildings.Goods
import world.objects.mobs.Mob
import world.objects.tiles.Farmland

class BearWheatBehavior(nextActivityBehavior: ActivityBehavior?,
                        var palette: Palette,
                        var farmland: Farmland) : ActivityBehavior(nextActivityBehavior) {

    override fun performActivity(mob: Mob) {
        if (farmland.growingPhase == 6 ){//&& destination != mob.chunkAndPoint) {
      //      val toPalette = WalkBehavior(nextActivityBehavior, palette.chunkAndPoint, {false}, Activity.BEAR_WHEAT, {palette.updateGoods(Goods.WHEAT,1)})
     //       WalkBehavior(toPalette, farmland.chunkAndPoint, {false}, Activity.WALK,{farmland.nextPhase()}).performActivity(mob)
        } else {
            nextActivity(mob)
        }
    }

    override fun nextActivity(mob: Mob) {
        nextActivityBehavior?.performActivity(mob)
    }

    override fun forceStop() {
        TODO("Not yet implemented")
    }
}