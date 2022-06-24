import java.awt.event.ActionListener
import javax.swing.Timer

class AnimationHelper{
    var index = 0

    init {
        val delay = 150 //milliseconds
        val taskPerformer = ActionListener {
            index++
            if (index == 100){
                index =0
            }
        }
        val timer = Timer(delay, taskPerformer)
        timer.start()
    }
    companion object {
        private var animationHelper: AnimationHelper? = null

        fun instance(): AnimationHelper{
            return if (animationHelper == null){
                animationHelper = AnimationHelper()
                animationHelper as AnimationHelper
            } else {
                animationHelper as AnimationHelper
            }
        }
    }
}