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
import objects.Activity
import world.objects.Direction
import world.objects.mobs.activityBehaviors.ActivityBehavior
import java.awt.Image

class Archer(chunkAndPoint: ChunkAndPoint, width: Int = 2, height: Int = 4, override var direction: Direction = Direction.BOTTOM,
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
                    Direction.TOP -> archer_walk_top[3]
                    Direction.RIGHT_TOP -> archer_walk_right_top[3]
                    Direction.RIGHT -> archer_walk_right[3]
                    Direction.RIGHT_BOTTOM -> archer_walk_right_bottom[3]
                    Direction.BOTTOM -> archer_walk_bottom[3]
                    Direction.LEFT_BOTTOM -> archer_walk_left_bottom[3]
                    Direction.LEFT -> archer_walk_left[3]
                    Direction.LEFT_TOP -> archer_walk_left_top[3]
                }
            Activity.WALK -> return when (direction){
                Direction.TOP -> archer_walk_top[step]
                Direction.RIGHT_TOP -> archer_walk_right_top[step]
                Direction.RIGHT -> archer_walk_right[step]
                Direction.RIGHT_BOTTOM -> archer_walk_right_bottom[step]
                Direction.BOTTOM -> archer_walk_bottom[step]
                Direction.LEFT_BOTTOM -> archer_walk_left_bottom[step]
                Direction.LEFT -> archer_walk_left[step]
                Direction.LEFT_TOP -> archer_walk_left_top[step]
            }

            else -> {
                return archer_walk_bottom[0]
            }
        }

    }


}