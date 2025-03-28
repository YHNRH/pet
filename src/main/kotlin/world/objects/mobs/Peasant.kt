package world.objects.mobs

import GraphicsExtender
import ImageHelper.Companion.archer_walk_bottom
import ImageHelper.Companion.archer_walk_left
import ImageHelper.Companion.archer_walk_left_bottom
import ImageHelper.Companion.archer_walk_left_top
import ImageHelper.Companion.archer_walk_right
import ImageHelper.Companion.archer_walk_right_bottom
import ImageHelper.Companion.archer_walk_right_top
import ImageHelper.Companion.archer_walk_top
import ImageHelper.Companion.farmer_walk_bottom
import ImageHelper.Companion.farmer_walk_left
import ImageHelper.Companion.farmer_walk_left_bottom
import ImageHelper.Companion.farmer_walk_left_top
import ImageHelper.Companion.farmer_walk_right
import ImageHelper.Companion.farmer_walk_right_bottom
import ImageHelper.Companion.farmer_walk_right_top
import ImageHelper.Companion.farmer_walk_top
import ImageHelper.Companion.peasant_walk_bottom
import ImageHelper.Companion.peasant_walk_left
import ImageHelper.Companion.peasant_walk_left_bottom
import ImageHelper.Companion.peasant_walk_left_top
import ImageHelper.Companion.peasant_walk_right
import ImageHelper.Companion.peasant_walk_right_bottom
import ImageHelper.Companion.peasant_walk_right_top
import ImageHelper.Companion.peasant_walk_top
import objects.Activity
import world.ChunkAndPoint
import world.objects.Direction
import world.objects.mobs.activityBehaviors.ActivityBehavior
import java.awt.Image

class Peasant(chunkAndPoint: ChunkAndPoint, width: Int = 2, height: Double = 4.0, override var direction: Direction = Direction.BOTTOM,
              override var activity: Activity = Activity.STAND,
//             override val stepsForBlock: Int = 8,
              override var step: Int = 0
) : SimpleMob(chunkAndPoint, width, height) {


    override fun draw(ge: GraphicsExtender) {
        super.draw(ge)
        ge.drawMob(this)
    }

    override var behavior: ActivityBehavior? = null
    override val animSizes: HashMap<Activity, Int> = HashMap()
    init {
        animSizes[Activity.WALK] = 8
        animSizes[Activity.RUN] = 8
        animSizes[Activity.STAND] = 1
    }
    override fun getImage(): Image {
        when (activity){
            Activity.STAND ->
                return when (direction){
                    Direction.TOP -> peasant_walk_top[3]
                    Direction.RIGHT_TOP -> peasant_walk_right_top[3]
                    Direction.RIGHT -> peasant_walk_right[3]
                    Direction.RIGHT_BOTTOM -> peasant_walk_right_bottom[3]
                    Direction.BOTTOM -> peasant_walk_bottom[3]
                    Direction.LEFT_BOTTOM -> peasant_walk_left_bottom[3]
                    Direction.LEFT -> peasant_walk_left[3]
                    Direction.LEFT_TOP -> peasant_walk_left_top[3]
                }
            Activity.WALK -> return when (direction){
                Direction.TOP -> peasant_walk_top[step]
                Direction.RIGHT_TOP -> peasant_walk_right_top[step]
                Direction.RIGHT -> peasant_walk_right[step]
                Direction.RIGHT_BOTTOM -> peasant_walk_right_bottom[step]
                Direction.BOTTOM -> peasant_walk_bottom[step]
                Direction.LEFT_BOTTOM -> peasant_walk_left_bottom[step]
                Direction.LEFT -> peasant_walk_left[step]
                Direction.LEFT_TOP -> peasant_walk_left_top[step]
            }

            else -> {
                return peasant_walk_bottom[0]
            }
        }

    }


}