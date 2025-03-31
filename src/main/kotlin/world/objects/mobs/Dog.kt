package world.objects.mobs

import GraphicsExtender
import ImageHelper.Companion.dog_pee
import ImageHelper.Companion.dog_rest
import ImageHelper.Companion.dog_run_bottom
import ImageHelper.Companion.dog_run_left
import ImageHelper.Companion.dog_run_left_bottom
import ImageHelper.Companion.dog_run_left_top
import ImageHelper.Companion.dog_run_right
import ImageHelper.Companion.dog_run_right_bottom
import ImageHelper.Companion.dog_run_right_top
import ImageHelper.Companion.dog_run_top
import ImageHelper.Companion.dog_walk_bottom
import ImageHelper.Companion.dog_walk_left
import ImageHelper.Companion.dog_walk_left_bottom
import ImageHelper.Companion.dog_walk_left_top
import ImageHelper.Companion.dog_walk_right
import ImageHelper.Companion.dog_walk_right_bottom
import ImageHelper.Companion.dog_walk_right_top
import ImageHelper.Companion.dog_walk_top
import objects.Activity
import world.MapPoint
import world.objects.Direction
import world.objects.mobs.activityBehaviors.ActivityBehavior
import java.awt.Image
import java.awt.event.ActionListener
import javax.swing.Timer

class Dog(chunkAndPoint: MapPoint, width: Int = 2, height: Double = 4.0, override var direction: Direction = Direction.BOTTOM,
          override var activity: Activity = Activity.STAND,
//             override val stepsForBlock: Int = 8,
          override var step: Int = 0
) : SimpleMob(chunkAndPoint, width, height) {
    override val animSizes = HashMap<Activity, Int>()
    val restAnimSpeed = 200
    val peeAnimSpeed = 200
    override fun draw(ge: GraphicsExtender) {
        super.draw(ge)
        ge.drawMob(this)
    }
    init {
        animSizes[Activity.WALK] = 16
        animSizes[Activity.RUN] = 14
        animSizes[Activity.REST] = 16
        animSizes[Activity.PEE] = 20
        animSizes[Activity.STAND] = 1

    }

    override var behavior: ActivityBehavior? = ActivityBehaviorHelper.dogBehavior()

    override fun getImage(): Image {
        when (activity){
            Activity.STAND ->
                return when (direction){
                    Direction.TOP -> dog_walk_top[3]
                    Direction.RIGHT_TOP -> dog_walk_right_top[3]
                    Direction.RIGHT -> dog_walk_right[3]
                    Direction.RIGHT_BOTTOM -> dog_walk_right_bottom[3]
                    Direction.BOTTOM -> dog_walk_bottom[3]
                    Direction.LEFT_BOTTOM -> dog_walk_left_bottom[3]
                    Direction.LEFT -> dog_walk_left[3]
                    Direction.LEFT_TOP -> dog_walk_left_top[3]
                }
            Activity.WALK -> return when (direction){
                Direction.TOP -> dog_walk_top[step]
                Direction.RIGHT_TOP -> dog_walk_right_top[step]
                Direction.RIGHT -> dog_walk_right[step]
                Direction.RIGHT_BOTTOM -> dog_walk_right_bottom[step]
                Direction.BOTTOM -> dog_walk_bottom[step]
                Direction.LEFT_BOTTOM -> dog_walk_left_bottom[step]
                Direction.LEFT -> dog_walk_left[step]
                Direction.LEFT_TOP -> dog_walk_left_top[step]
            }

            Activity.RUN -> return when (direction){
                Direction.TOP -> dog_run_top[step]
                Direction.RIGHT_TOP -> dog_run_right_top[step]
                Direction.RIGHT -> dog_run_right[step]
                Direction.RIGHT_BOTTOM -> dog_run_right_bottom[step]
                Direction.BOTTOM -> dog_run_bottom[step]
                Direction.LEFT_BOTTOM -> dog_run_left_bottom[step]
                Direction.LEFT -> dog_run_left[step]
                Direction.LEFT_TOP -> dog_run_left_top[step]
            }

            Activity.REST -> return dog_rest[step]
            Activity.PEE -> return dog_pee[step]

            else -> {
                return dog_walk_bottom[0]
            }
        }

    }

    fun rest(caller: ActivityBehavior?){
        this.activity = Activity.REST
        step = 0

        val task = ActionListener {
            if (step+1 != animSizes[this.activity]){
                step++
            } else {
                (it.source as Timer).stop()
                caller?.nextActivity(this)
            }
        }
        val t = Timer(restAnimSpeed, task)
        t.start()
    }

    fun pee(caller: ActivityBehavior?){
        this.activity = Activity.PEE
        step = 0

        val task = ActionListener {
            if (step+1 != animSizes[this.activity]){
                step++
            } else {
                (it.source as Timer).stop()
                caller?.nextActivity(this)
            }
        }
        val t = Timer(peeAnimSpeed, task)
        t.start()
    }



}