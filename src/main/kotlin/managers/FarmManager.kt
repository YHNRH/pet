package managers

import Point
import world.ChunkAndPoint
import world.Chunks

import world.objects.buildings.WheatFarm
import world.objects.mobs.Farmer
import world.objects.mobs.activityBehaviors.*
import world.objects.tiles.Farmland

class FarmManager(val castleManager: CastleManager) {
    fun createWorker(farm: WheatFarm) {
        val farmer = Farmer(
            ChunkAndPoint(
            castleManager.castle!!.chunkAndPoint.chunk, Point(
                    castleManager.castle!!.chunkAndPoint.point.getX()-2,castleManager.castle!!.chunkAndPoint.point.getY()+2)
            )
        )
        Chunks.instance().chunks.get(castleManager.castle?.chunkAndPoint?.chunk?.point)?.addMob(farmer)
       // val toFarm =    WalkBehavior(null,farm.chunkAndPoint)
        //val toCastle =  WalkBehavior(toFarm,castle!!.chunkAndPoint)
        val rest =      RestBehavior(null)

        var buff : ActivityBehavior?  = null
        farm.farmlands.forEach {
            val cultivate = CultivateBehavior(null, it as Farmland) { it.growingPhase != 0 && it.growingPhase != 5  }
//            val walk = WalkBehavior(cultivate, it.chunkAndPoint, afterFunction = { it.growingPhase != 0 && it.growingPhase != 5  })
//
//            // Внесение первого действия
//            if (buff == null){
//                farmer.addBehavior(walk)
//                // Замыкаем
//                rest.nextActivityBehavior = walk
//                //toFarm.nextActivityBehavior = walk
//            } else {
//                buff?.nextActivityBehavior = walk
//            }
//            buff = cultivate

        }
       // buff?.nextActivityBehavior = WalkBehavior(rest,farm.chunkAndPoint)

        var sowBuff : ActivityBehavior? = null

        // Засеивание
        farm.farmlands.forEach {
            val sow = SowBehavior(null, it.chunkAndPoint, { (it as Farmland).growingPhase != 1 }, it as Farmland)
            if (buff?.nextActivityBehavior == null){
                buff?.nextActivityBehavior = sow
            } else {
                sowBuff?.nextActivityBehavior = sow
            }
            sowBuff = sow
        }

        var cutBuff : ActivityBehavior?  = null

        // Скашивание

        farm.farmlands.forEach {
            val cut = CutBehavior(null, it as Farmland) { it.growingPhase != 4 }
//            val walk = WalkBehavior(cut, it.chunkAndPoint, { it.growingPhase != 4 })
//
//            if (sowBuff?.nextActivityBehavior == null){
//                sowBuff?.nextActivityBehavior = walk
//            } else {
//                cutBuff?.nextActivityBehavior = walk
//            }
//            cutBuff = cut
        }

        var bearBuff : ActivityBehavior?  = null

        // Относить на склад

//        farm.farmlands.forEach {
//            // Надо засунуть это внутрь toSheaf, Потому что здесь неоднозначная функция skip
//            val toPalette   = WalkBehavior(null, castle!!.palettes[0].chunkAndPoint, {(it as Farmland).growingPhase != 6}, Activity.BEAR_WHEAT)
//            val toSheaf     = WalkBehavior(toPalette,it.chunkAndPoint, {(it as Farmland).growingPhase != 6}, Activity.WALK, {(it as Farmland).nextPhase()})
//
//            if (cutBuff?.nextActivityBehavior == null){
//                cutBuff?.nextActivityBehavior = toSheaf
//            } else {
//                bearBuff?.nextActivityBehavior = toSheaf
//            }
//            bearBuff = toPalette
//        }

        farm.farmlands.forEach {
            val bearWheat  = BearWheatBehavior(null,castleManager.castle!!.palettes[0],
                it as Farmland)

            if (cutBuff?.nextActivityBehavior == null){
                cutBuff?.nextActivityBehavior = bearWheat
            } else {
                bearBuff?.nextActivityBehavior = bearWheat
            }
            bearBuff = bearWheat
        }

                                                                            // Пропускаем если уже на ферме. Это нужно, чтобы не менялось activity
       // bearBuff?.nextActivityBehavior = WalkBehavior(rest,farm.chunkAndPoint, {farmer.chunkAndPoint == farm.chunkAndPoint})







        //sowBuff?.nextActivityBehavior = WalkBehavior(rest,farm.chunkAndPoint)
       // castle!!.chunkAndPoint.chunk.addMob(Farmer(SimpleMob.ChunkAndPoint(castle!!.chunkAndPoint.chunk, Point(castle!!.chunkAndPoint.point.getX()+5,castle!!.chunkAndPoint.point.getY()+5))))
    }
}