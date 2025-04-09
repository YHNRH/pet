package managers

import Point
import world.ChunkAndPoint
import world.Chunks
import world.objects.buildings.GenericBuilding
import world.objects.buildings.WoodcutterHut
import world.objects.mobs.SimpleMob
import world.objects.mobs.Woodcutter
import world.objects.mobs.activityBehaviors.ChopLogBehavior
import world.objects.mobs.activityBehaviors.ChopTreeBehavior
import world.objects.mobs.activityBehaviors.RestContiniousBehavior
import world.objects.mobs.activityBehaviors.WalkBehavior

class WoodcutterManager(val castleManager: CastleManager) {
    fun createWorker(hut: WoodcutterHut) {
        val woodcutter = Woodcutter(
            ChunkAndPoint(
                castleManager.castle!!.chunkAndPoint.chunk, Point(
                    castleManager.castle!!.chunkAndPoint.point.getX()-2,castleManager.castle!!.chunkAndPoint.point.getY()+2)
            )
        )
        Chunks.instance().chunks[castleManager.castle?.chunkAndPoint?.chunk?.point]?.addMob(woodcutter)
        val toHut =    WalkBehavior(null,hut.chunkAndPoint)
        //val toCastle =  WalkBehavior(toHut,castleManager.castle!!.chunkAndPoint)

        val chopLog = ChopLogBehavior(toHut)
        val chopTree = ChopTreeBehavior(chopLog)

        val rest =      RestContiniousBehavior(chopTree)
        toHut.nextActivityBehavior = rest

        woodcutter.addBehavior(rest)


    }
}