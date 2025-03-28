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
import ImageHelper.Companion.bear_wheat_size
import ImageHelper.Companion.cut_size
import ImageHelper.Companion.farmer_bear_wheat_bottom
import ImageHelper.Companion.farmer_bear_wheat_left
import ImageHelper.Companion.farmer_bear_wheat_left_bottom
import ImageHelper.Companion.farmer_bear_wheat_left_top
import ImageHelper.Companion.farmer_bear_wheat_right
import ImageHelper.Companion.farmer_bear_wheat_right_bottom
import ImageHelper.Companion.farmer_bear_wheat_right_top
import ImageHelper.Companion.farmer_bear_wheat_top
import ImageHelper.Companion.farmer_cultivate_bottom
import ImageHelper.Companion.farmer_cultivate_left
import ImageHelper.Companion.farmer_cultivate_left_bottom
import ImageHelper.Companion.farmer_cultivate_left_top
import ImageHelper.Companion.farmer_cultivate_right
import ImageHelper.Companion.farmer_cultivate_right_bottom
import ImageHelper.Companion.farmer_cultivate_right_top
import ImageHelper.Companion.farmer_cultivate_top
import ImageHelper.Companion.farmer_cut_bottom
import ImageHelper.Companion.farmer_cut_left
import ImageHelper.Companion.farmer_cut_left_bottom
import ImageHelper.Companion.farmer_cut_left_top
import ImageHelper.Companion.farmer_cut_right
import ImageHelper.Companion.farmer_cut_right_bottom
import ImageHelper.Companion.farmer_cut_right_top
import ImageHelper.Companion.farmer_cut_top
import ImageHelper.Companion.farmer_rest
import ImageHelper.Companion.farmer_sow_bottom
import ImageHelper.Companion.farmer_sow_left
import ImageHelper.Companion.farmer_sow_left_bottom
import ImageHelper.Companion.farmer_sow_left_top
import ImageHelper.Companion.farmer_sow_right
import ImageHelper.Companion.farmer_sow_right_bottom
import ImageHelper.Companion.farmer_sow_right_top
import ImageHelper.Companion.farmer_sow_top
import ImageHelper.Companion.farmer_walk_bottom
import ImageHelper.Companion.farmer_walk_left
import ImageHelper.Companion.farmer_walk_left_bottom
import ImageHelper.Companion.farmer_walk_left_top
import ImageHelper.Companion.farmer_walk_right
import ImageHelper.Companion.farmer_walk_right_bottom
import ImageHelper.Companion.farmer_walk_right_top
import ImageHelper.Companion.farmer_walk_top
import ImageHelper.Companion.sow_size
import objects.Activity
import world.ChunkAndPoint
import world.objects.Direction
import world.objects.mobs.activityBehaviors.ActivityBehavior
import java.awt.Image

class Farmer(chunkAndPoint: ChunkAndPoint, width: Int = 3, height: Double = 6.0, override var direction: Direction = Direction.BOTTOM,
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
        animSizes[Activity.CULTIVATE] = 8
        animSizes[Activity.BEAR_WHEAT] = bear_wheat_size
        animSizes[Activity.SOW] = sow_size
        animSizes[Activity.CUT] = cut_size
        animSizes[Activity.STAND] = 1
        animSizes[Activity.REST] = 12
    }
    override fun getImage(): Image {
        when (activity){
            Activity.STAND ->
                return when (direction){
                    Direction.TOP -> farmer_walk_top[3]
                    Direction.RIGHT_TOP -> farmer_walk_right_top[3]
                    Direction.RIGHT -> farmer_walk_right[3]
                    Direction.RIGHT_BOTTOM -> farmer_walk_right_bottom[3]
                    Direction.BOTTOM -> farmer_walk_bottom[3]
                    Direction.LEFT_BOTTOM -> farmer_walk_left_bottom[3]
                    Direction.LEFT -> farmer_walk_left[3]
                    Direction.LEFT_TOP -> farmer_walk_left_top[3]
                }
            Activity.WALK -> return when (direction){
                Direction.TOP -> farmer_walk_top[step]
                Direction.RIGHT_TOP -> farmer_walk_right_top[step]
                Direction.RIGHT -> farmer_walk_right[step]
                Direction.RIGHT_BOTTOM -> farmer_walk_right_bottom[step]
                Direction.BOTTOM -> farmer_walk_bottom[step]
                Direction.LEFT_BOTTOM -> farmer_walk_left_bottom[step]
                Direction.LEFT -> farmer_walk_left[step]
                Direction.LEFT_TOP -> farmer_walk_left_top[step]
            }
            Activity.CULTIVATE -> return when (direction){
                Direction.TOP -> farmer_cultivate_top[step]
                Direction.RIGHT_TOP -> farmer_cultivate_right_top[step]
                Direction.RIGHT -> farmer_cultivate_right[step]
                Direction.RIGHT_BOTTOM -> farmer_cultivate_right_bottom[step]
                Direction.BOTTOM -> farmer_cultivate_bottom[step]
                Direction.LEFT_BOTTOM -> farmer_cultivate_left_bottom[step]
                Direction.LEFT -> farmer_cultivate_left[step]
                Direction.LEFT_TOP -> farmer_cultivate_left_top[step]
            }
            Activity.SOW -> return when (direction){
                Direction.TOP -> farmer_sow_top[step]
                Direction.RIGHT_TOP -> farmer_sow_right_top[step]
                Direction.RIGHT -> farmer_sow_right[step]
                Direction.RIGHT_BOTTOM -> farmer_sow_right_bottom[step]
                Direction.BOTTOM -> farmer_sow_bottom[step]
                Direction.LEFT_BOTTOM -> farmer_sow_left_bottom[step]
                Direction.LEFT -> farmer_sow_left[step]
                Direction.LEFT_TOP -> farmer_sow_left_top[step]
            }
            Activity.CUT -> return when (direction){
                Direction.TOP -> farmer_cut_top[step]
                Direction.RIGHT_TOP -> farmer_cut_right_top[step]
                Direction.RIGHT -> farmer_cut_right[step]
                Direction.RIGHT_BOTTOM -> farmer_cut_right_bottom[step]
                Direction.BOTTOM -> farmer_cut_bottom[step]
                Direction.LEFT_BOTTOM -> farmer_cut_left_bottom[step]
                Direction.LEFT -> farmer_cut_left[step]
                Direction.LEFT_TOP -> farmer_cut_left_top[step]
            }
            Activity.BEAR_WHEAT -> return when (direction){
                Direction.TOP -> farmer_bear_wheat_top[step]
                Direction.RIGHT_TOP -> farmer_bear_wheat_right_top[step]
                Direction.RIGHT -> farmer_bear_wheat_right[step]
                Direction.RIGHT_BOTTOM -> farmer_bear_wheat_right_bottom[step]
                Direction.BOTTOM -> farmer_bear_wheat_bottom[step]
                Direction.LEFT_BOTTOM -> farmer_bear_wheat_left_bottom[step]
                Direction.LEFT -> farmer_bear_wheat_left[step]
                Direction.LEFT_TOP -> farmer_bear_wheat_left_top[step]
            }
            Activity.REST -> return farmer_rest[step]

            else -> {
                return farmer_walk_bottom[0]
            }
        }

    }


}