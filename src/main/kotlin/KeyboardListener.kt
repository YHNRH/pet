import Consts.Companion.cameraStep
import Consts.Companion.zoomStep
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class KeyboardListener(val camera: Camera): KeyListener {
    override fun keyTyped(e: KeyEvent?) {
        if (e?.keyCode == KeyEvent.VK_RIGHT) {
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
    }

    override fun keyReleased(e: KeyEvent?) {

    }
}