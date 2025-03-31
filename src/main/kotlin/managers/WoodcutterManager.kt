package managers

import world.Chunks
import world.MapPoint
import world.Point
import world.objects.buildings.GenericBuilding
import world.objects.mobs.Woodcutter
import world.objects.mobs.activityBehaviors.ChopLogBehavior
import world.objects.mobs.activityBehaviors.ChopTreeBehavior
import world.objects.mobs.activityBehaviors.RestContiniousBehavior

class WoodcutterManager(val castleManager: CastleManager) {
    fun createWorker(hut: GenericBuilding) {
        val woodcutter = Woodcutter(
            MapPoint(castleManager.castle!!.point.getX()-2,castleManager.castle!!.point.getY()+2, castleManager.castle!!.point.chunk)
        )
        Chunks.instance().chunks[castleManager.castle?.point?.chunk as Point]?.addMob(woodcutter)
        //val toHut =    WalkBehavior(null,hut.chunkAndPoint)
        //val toCastle =  WalkBehavior(toHut,castleManager.castle!!.chunkAndPoint)

        val chopLog = ChopLogBehavior(null)
        val chopTree = ChopTreeBehavior(chopLog)

        val rest =      RestContiniousBehavior(chopTree)
        chopLog.nextActivityBehavior = rest

        woodcutter.addBehavior(rest)


    }
}