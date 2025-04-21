import Consts.Companion.cameraYInit
import Consts.Companion.zoomInit
import Consts.Companion.zoomMax
import Consts.Companion.zoomMin

class Camera {
    var x = 0
    var y = cameraYInit
    var zoom = zoomInit
    //    private constructor(x:Int, y: Int, zoom:Int) {}

//    @JvmName("setZoom1")
@JvmName("setZoom1")
fun setZoom(newZoom:Int){
        if (newZoom in zoomMin..zoomMax) {
            this.zoom = newZoom
        }
    }
    companion object {
        private var camera: Camera? = null

        fun instance(): Camera{
            return if (camera == null){
                camera = Camera()
                camera as Camera
            } else {
                camera as Camera
            }
        }
    }

}