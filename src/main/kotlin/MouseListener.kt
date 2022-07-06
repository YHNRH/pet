import Consts.Companion.blockHeight
import Consts.Companion.blockWidth
import Consts.Companion.chunkSize
import Consts.Companion.frameHeight
import Consts.Companion.frameWidth
import objects.Activity
import world.Chunks
import world.objects.DrawableObject
import world.objects.buildings.Building
import world.objects.mobs.SimpleMob
import java.awt.event.MouseEvent

class MouseListener(val drawer:Drawer) : java.awt.event.MouseListener {

    override fun mouseClicked(e: MouseEvent?) {
        if (e != null) {
            val x = e.x
            val y = e.y
            var calcY = -((y - drawer.camera.y - frameHeight/2))
//        val calcX = ((x + camera.x - frameWidth/2) - blockWidth/2) / blockWidth
            val calcX = ((x + drawer.camera.x - frameWidth/2) - blockWidth/2)
            val myCoordSysX = calcX + calcY*(blockWidth/ blockHeight)
            val myCoordSysY =  -(calcX/2 - calcY)
            println("calcx $calcX")
            println("calcY $calcY")
            println()
            println("myCoordSysX $myCoordSysX")
            println("myCoordSysY $myCoordSysY")
//        println("calcXWithOffset $calcXWithOffset")

            val chunkX = if (calcX / chunkSize < 0) 0 else myCoordSysX/ blockWidth / chunkSize
            val pointX = if (calcX % chunkSize < 0) 0 else myCoordSysX/ blockWidth % chunkSize
            val chunkY = if (calcY / chunkSize < 0) 0 else myCoordSysY/ blockHeight / chunkSize
            val pointY = if (calcY % chunkSize < 0) 0 else myCoordSysY/ blockHeight % chunkSize

            when (drawer.appState){
                Drawer.AppState.WALK -> {

                    SelectionHandler.selectedMobs.forEach{
                        val objects = ArrayList<DrawableObject>()
                        Chunks.instance().chunks.forEach{
                            objects.addAll(it.value.objects)
                        }
                        println("OBJECTS TO COLLISION CHECK SIZE ${objects.size}")
                        val chunk = Chunks.instance().chunks.get(Point(chunkX,chunkY))
                        val pointExist = chunk?.getNoncollisionObject(Point(pointX,pointY)) != null
//                        if (chunk != null && pointExist){
                            it.move(
                                SimpleMob.ChunkAndPoint(chunk!!, Point(pointX,pointY)),
                                Chunks.instance().chunks, objects, null, Activity.WALK)
//                        }
                    }

                }
                Drawer.AppState.BUILD -> {
                    Chunks.instance().chunks.get(BuilderHelper.getInstance().getObj()!!.chunkAndPoint.chunk.point)?.addBuilding(BuilderHelper.getInstance().getObj()!!)
                    BuilderHelper.getInstance().getAdditional()?.forEach {
                        if (it.obj is Building){
                            Chunks.instance().chunks.get(it.obj.chunkAndPoint.chunk.point)?.addBuilding(it.obj)
                        } else {
                            Chunks.instance().chunks.get(it.obj.chunkAndPoint.chunk.point)?.addNonCollision(it.obj)
                        }
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