import ImageHelper.Companion.woodcutter_hut
import managers.CastleManager
import world.Chunks
import world.SimplePoint
import world.objects.IDrawableObject
import world.objects.buildings.IBuilding
import world.objects.buildings.GenericBuilding
import world.objects.buildings.Palette
import world.objects.buildings.WheatFarm
import world.objects.buildings.castle.Castle
import world.objects.mobs.SimpleMob
import world.objects.mobs.activityBehaviors.WalkBehavior
import java.awt.event.MouseEvent

class MouseListener(private val drawer:Drawer, private val castleManager: CastleManager) : java.awt.event.MouseListener {

    override fun mouseClicked(e: MouseEvent?) {
        if (e != null) {

            val point = drawer.getPointByMouseXY(e.x, e.y)

            when (drawer.appState){
                Drawer.AppState.WALK -> {

                    SelectionHandler.selectedMobs.forEach{
                        val objects = ArrayList<IDrawableObject>()
                        Chunks.instance().chunks.forEach{
                            objects.addAll(it.value.objects)
                        }
                        println("OBJECTS TO COLLISION CHECK SIZE ${objects.size}")
                        val chunk = Chunks.instance().chunks.get(SimplePoint(0,0))//chunkX,chunkY))
                        val pointExist = chunk?.getNoncollisionObject(point) != null
                        (it as SimpleMob).addBehavior(WalkBehavior(null, point, objects))
                        //  it.move(
                        //       SimpleMob.ChunkAndPoint(chunk!!, world.Point(pointX,pointY)),
                        //        Chunks.instance().chunks, objects, null, Activity.WALK)
                    }

                }
                Drawer.AppState.BUILD -> {
                    val obj = BuilderHelper.getInstance().getObj()!!
                    Chunks.instance().chunks.get(obj.point.chunk)?.addBuilding(obj)
                    BuilderHelper.getInstance().getAdditional()?.forEach {
                        if (it.obj is IBuilding){
                            Chunks.instance().chunks.get(it.obj.point.chunk)?.addBuilding(it.obj)
                        } else {
                            Chunks.instance().chunks.get(it.obj.point.chunk)?.addNonCollision(it.obj)
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
                        is GenericBuilding   -> {
                            when(obj.image){
                                woodcutter_hut -> castleManager.woodcutterManager.createWorker(obj)
                            }
                        }
                        is Palette      -> castleManager.castle!!.palettes.add(obj)
                    }
                    drawer.appState = Drawer.AppState.WALK
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