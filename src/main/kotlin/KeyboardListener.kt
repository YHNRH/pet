import Consts.Companion.cameraStep
import Consts.Companion.zoomStep
import world.Chunk
import world.objects.BuildingDirection
import world.objects.mobs.SimpleMob
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class KeyboardListener(val camera: Camera): KeyListener {
    override fun keyTyped(e: KeyEvent?) {
        if (e?.keyCode == KeyEvent.VK_R) {
            println("R key typed");
        }
        if (e?.keyCode == KeyEvent.VK_LEFT) {
            println("Left key typed");
        }
    }

    override fun keyPressed(e: KeyEvent?) {
        if (e?.keyCode == KeyEvent.VK_D) {
            camera.x+= cameraStep
        }
        if (e?.keyCode == KeyEvent.VK_A) {
            camera.x-= cameraStep
        }

        if (e?.keyCode == KeyEvent.VK_W) {
            camera.y+= cameraStep
        }
        if (e?.keyCode == KeyEvent.VK_S) {
            camera.y-= cameraStep
        }

        if (e?.keyCode == KeyEvent.VK_E) {
            camera.setZoom(camera.zoom + zoomStep)
        }
        if (e?.keyCode == KeyEvent.VK_Q) {
            camera.setZoom(camera.zoom - zoomStep)
        }

        if (Drawer.instance().appState == Drawer.AppState.BUILD){
            if (e?.keyCode == KeyEvent.VK_R) {
                val obj = BuilderHelper.getInstance().getObj()!!
                if (obj.direction == BuildingDirection.LEFT ){
                    BuilderHelper.getInstance().setBuildingDirection(BuildingDirection.RIGHT)
                }  else {
                    BuilderHelper.getInstance().setBuildingDirection(BuildingDirection.LEFT)
                }


                val chunkX = obj.chunkAndPoint.chunk.point.getX()
                val chunkY = obj.chunkAndPoint.chunk.point.getY()
                val pointX = obj.chunkAndPoint.point.getX()
                val pointY = obj.chunkAndPoint.point.getY()
                BuilderHelper.getInstance().getAdditional()?.forEach {
                    val offset = it.offset[BuilderHelper.getInstance().getObj()!!.direction]!!
                    it.obj.chunkAndPoint = SimpleMob.ChunkAndPoint(
                        Chunk(Point(chunkX, chunkY)),
                        Point(pointX+offset.getX(), pointY+offset.getY())
                    )
                }

            }
        }
    }

    override fun keyReleased(e: KeyEvent?) {

    }
}