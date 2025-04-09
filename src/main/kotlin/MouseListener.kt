import Consts.Companion.blockHeight
import Consts.Companion.blockWidth
import Consts.Companion.chunkSize
import Consts.Companion.frameHeight
import ImageHelper.Companion.woodcutter_hut
import managers.CastleManager
import managers.FarmManager
import world.ChunkAndPoint
import world.Chunks
import world.objects.DrawableObject
import world.objects.buildings.*
import world.objects.buildings.castle.Castle
import world.objects.mobs.SimpleMob
import world.objects.mobs.activityBehaviors.WalkBehavior
import java.awt.event.MouseEvent

class MouseListener(private val drawer:Drawer, private val castleManager: CastleManager) : java.awt.event.MouseListener {

    override fun mouseClicked(e: MouseEvent?) {
        if (e != null) {
            when (e.button){
                1 -> { //ЛКМ
                    val point = drawer.getPointByMouseXY(e.x, e.y)
                    when (drawer.appState){
                        Drawer.AppState.WALK -> {

                            SelectionHandler.selectedMobs.forEach{
                                val chunk = Chunks.instance().chunks.get(Point(0,0))//chunkX,chunkY))
                                val pointExist = chunk?.getNoncollisionObject(point) != null
                                (it as SimpleMob).addBehavior(WalkBehavior(null, ChunkAndPoint(chunk!!, point)))
                                //  it.move(
                                //       SimpleMob.ChunkAndPoint(chunk!!, Point(pointX,pointY)),
                                //        Chunks.instance().chunks, objects, null, Activity.WALK)
                            }

                        }
                        Drawer.AppState.BUILD -> {
                            val obj = BuilderHelper.getInstance().getObj()!!
                            Chunks.instance().chunks[obj.chunkAndPoint.chunk.point]?.addBuilding(obj)
                            BuilderHelper.getInstance().getAdditional()?.forEach {
                                if (it.obj is Building){
                                    Chunks.instance().chunks[it.obj.chunkAndPoint.chunk.point]?.addBuilding(it.obj)
                                } else {
                                    Chunks.instance().chunks[it.obj.chunkAndPoint.chunk.point]?.addNonCollision(it.obj)
                                }
                                when (obj){
                                    is WheatFarm ->
                                        obj.farmlands.add(it.obj)

                                    is Castle ->{
                                        if (it.obj is Palette)
                                            obj.palettes.add(it.obj)
                                    }
                                }
                            }
                            when(obj){
                                is Castle       -> castleManager.castle = obj
                                is WheatFarm    -> castleManager.farmManager.createWorker(obj)
                                is WoodcutterHut -> {
                                    castleManager.woodcutterManager.createWorker(obj)
                                }
                                is Palette      -> castleManager.castle!!.palettes.add(obj)
                            }
                            drawer.appState = Drawer.AppState.WALK
                        }
                    }
                }
                3 -> { //ПКМ
                    when (drawer.appState){
                        Drawer.AppState.BUILD -> {
                            drawer.appState = Drawer.AppState.WALK
                        }
                    }
                }
            }
        }
    }

    override fun mousePressed(e: MouseEvent?) {
//        TODO("Not yet implemented")
    }

    override fun mouseReleased(e: MouseEvent?) {
//        TODO("Not yet implemented")
    }

    override fun mouseEntered(e: MouseEvent?) {
        println(e)
    }

    override fun mouseExited(e: MouseEvent?) {
//        TODO("Not yet implemented")
    }
}