package world.objects.mobs

import GraphicsExtender
import ImageHelper.Companion.woodcutter_chop_log_bottom
import ImageHelper.Companion.woodcutter_chop_log_left
import ImageHelper.Companion.woodcutter_chop_log_left_bottom
import ImageHelper.Companion.woodcutter_chop_log_left_top
import ImageHelper.Companion.woodcutter_chop_log_right
import ImageHelper.Companion.woodcutter_chop_log_right_bottom
import ImageHelper.Companion.woodcutter_chop_log_right_top
import ImageHelper.Companion.woodcutter_chop_log_size
import ImageHelper.Companion.woodcutter_chop_log_top
import ImageHelper.Companion.woodcutter_chop_tree_bottom
import ImageHelper.Companion.woodcutter_chop_tree_left
import ImageHelper.Companion.woodcutter_chop_tree_left_bottom
import ImageHelper.Companion.woodcutter_chop_tree_left_top
import ImageHelper.Companion.woodcutter_chop_tree_right
import ImageHelper.Companion.woodcutter_chop_tree_right_bottom
import ImageHelper.Companion.woodcutter_chop_tree_right_top
import ImageHelper.Companion.woodcutter_chop_tree_size
import ImageHelper.Companion.woodcutter_chop_tree_top
import ImageHelper.Companion.woodcutter_rest
import ImageHelper.Companion.woodcutter_rest_size
import ImageHelper.Companion.woodcutter_walk_bottom
import ImageHelper.Companion.woodcutter_walk_left
import ImageHelper.Companion.woodcutter_walk_left_bottom
import ImageHelper.Companion.woodcutter_walk_left_top
import ImageHelper.Companion.woodcutter_walk_right
import ImageHelper.Companion.woodcutter_walk_right_bottom
import ImageHelper.Companion.woodcutter_walk_right_top
import ImageHelper.Companion.woodcutter_walk_top
import objects.Activity
import world.MapPoint
import world.objects.Direction
import world.objects.mobs.activityBehaviors.ActivityBehavior
import world.objects.trees.ITree
import java.awt.Image

class Woodcutter(chunkAndPoint: MapPoint, width: Int = 3, height: Double = 6.0, override var direction: Direction = Direction.BOTTOM,
                 override var activity: Activity = Activity.STAND,
                 override var step: Int = 0
) : SimpleMob(chunkAndPoint, width, height) {

    var tree: ITree? = null

    override fun draw(ge: GraphicsExtender) {
        super.draw(ge)
        ge.drawMob(this)
    }

    override var behavior: ActivityBehavior? = null
    override val animSizes: HashMap<Activity, Int> = HashMap()

    init {
        animSizes[Activity.REST] = woodcutter_rest_size
        animSizes[Activity.STAND] = 1
        animSizes[Activity.WALK] = 8
        animSizes[Activity.CHOP_TREE] = woodcutter_chop_tree_size
        animSizes[Activity.CHOP_LOG] = woodcutter_chop_log_size
    }

    override fun getImage(): Image {
        when (activity) {
            Activity.STAND ->
                return when (direction) {
                    Direction.TOP -> woodcutter_walk_top[2]
                    Direction.RIGHT_TOP -> woodcutter_walk_right_top[2]
                    Direction.RIGHT -> woodcutter_walk_right[2]
                    Direction.RIGHT_BOTTOM -> woodcutter_walk_right_bottom[2]
                    Direction.BOTTOM -> woodcutter_walk_bottom[2]
                    Direction.LEFT_BOTTOM -> woodcutter_walk_left_bottom[2]
                    Direction.LEFT -> woodcutter_walk_left[2]
                    Direction.LEFT_TOP -> woodcutter_walk_left_top[2]
                }

            Activity.WALK -> return when (direction) {
                Direction.TOP -> woodcutter_walk_top[step]
                Direction.RIGHT_TOP -> woodcutter_walk_right_top[step]
                Direction.RIGHT -> woodcutter_walk_right[step]
                Direction.RIGHT_BOTTOM -> woodcutter_walk_right_bottom[step]
                Direction.BOTTOM -> woodcutter_walk_bottom[step]
                Direction.LEFT_BOTTOM -> woodcutter_walk_left_bottom[step]
                Direction.LEFT -> woodcutter_walk_left[step]
                Direction.LEFT_TOP -> woodcutter_walk_left_top[step]
            }

            Activity.CHOP_TREE -> return when (direction) {
                Direction.TOP -> woodcutter_chop_tree_top[step]
                Direction.RIGHT_TOP -> woodcutter_chop_tree_right_top[step]
                Direction.RIGHT -> woodcutter_chop_tree_right[step]
                Direction.RIGHT_BOTTOM -> woodcutter_chop_tree_right_bottom[step]
                Direction.BOTTOM -> woodcutter_chop_tree_bottom[step]
                Direction.LEFT_BOTTOM -> woodcutter_chop_tree_left_bottom[step]
                Direction.LEFT -> woodcutter_chop_tree_left[step]
                Direction.LEFT_TOP -> woodcutter_chop_tree_left_top[step]
            }
            Activity.CHOP_LOG -> return when (direction) {
                Direction.TOP -> woodcutter_chop_log_top[step]
                Direction.RIGHT_TOP -> woodcutter_chop_log_right_top[step]
                Direction.RIGHT -> woodcutter_chop_log_right[step]
                Direction.RIGHT_BOTTOM -> woodcutter_chop_log_right_bottom[step]
                Direction.BOTTOM -> woodcutter_chop_log_bottom[step]
                Direction.LEFT_BOTTOM -> woodcutter_chop_log_left_bottom[step]
                Direction.LEFT -> woodcutter_chop_log_left[step]
                Direction.LEFT_TOP -> woodcutter_chop_log_left_top[step]
            }
            Activity.REST -> return woodcutter_rest[step]

            else -> {
                return woodcutter_walk_bottom[0]
            }
        }

    }
}

