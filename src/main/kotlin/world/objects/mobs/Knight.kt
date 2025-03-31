package world.objects.mobs

import GraphicsExtender
import ImageHelper.Companion.knight_move_bottom
import ImageHelper.Companion.knight_move_left
import ImageHelper.Companion.knight_move_left_bottom
import ImageHelper.Companion.knight_move_left_top
import ImageHelper.Companion.knight_move_right
import ImageHelper.Companion.knight_move_right_bottom
import ImageHelper.Companion.knight_move_right_top
import ImageHelper.Companion.knight_move_top
import ImageHelper.Companion.knight_standing_bottom
import ImageHelper.Companion.knight_standing_left
import ImageHelper.Companion.knight_standing_left_bottom
import ImageHelper.Companion.knight_standing_left_top
import ImageHelper.Companion.knight_standing_right
import ImageHelper.Companion.knight_standing_right_bottom
import ImageHelper.Companion.knight_standing_right_top
import ImageHelper.Companion.knight_standing_top
import world.MapPoint
import objects.Activity
import world.objects.Direction
import world.objects.mobs.activityBehaviors.ActivityBehavior
import java.awt.Image

class Knight(chunkAndPoint: MapPoint, width: Int = 2, height: Double = 4.0, override var direction: Direction = Direction.BOTTOM,
             override var activity: Activity = Activity.STAND,
//             override val stepsForBlock: Int = 4,
             override var step: Int = 0
) : SimpleMob(chunkAndPoint, width, height) {
    override var occupiedBlocks: ArrayList<MapPoint> = ArrayList()
    override var behavior: ActivityBehavior? = null
    override val animSizes: HashMap<Activity, Int>
        get() = TODO("Not yet implemented")

    init {
        for (x in (this.point.getX())until (this.point.getX()+width/2)){
            for (y in (this.point.getY())until (this.point.getY()+width/2)){
                this.occupiedBlocks.add(MapPoint(x,y, chunkAndPoint.chunk))
            }
        }
    }


    override fun draw(ge: GraphicsExtender) {
        super.draw(ge)
        ge.drawMob(this)
    }

    override fun getImage(): Image{
        when (activity){
          Activity.STAND ->  when (direction){
                Direction.TOP -> return knight_standing_top
                Direction.RIGHT_TOP -> return knight_standing_right_top
                Direction.RIGHT -> return knight_standing_right
                Direction.RIGHT_BOTTOM -> return knight_standing_right_bottom
                Direction.BOTTOM -> return knight_standing_bottom
                Direction.LEFT_BOTTOM -> return knight_standing_left_bottom
                Direction.LEFT -> return knight_standing_left
                Direction.LEFT_TOP -> return knight_standing_left_top
            }
          Activity.WALK ->  when (direction){
                Direction.TOP -> return knight_move_top[step]
                Direction.RIGHT_TOP -> return knight_move_right_top[step]
                Direction.RIGHT -> return knight_move_right[step]
                Direction.RIGHT_BOTTOM -> return knight_move_right_bottom[step]
                Direction.BOTTOM -> return knight_move_bottom[step]
                Direction.LEFT_BOTTOM -> return knight_move_left_bottom[step]
                Direction.LEFT -> return knight_move_left[step]
                Direction.LEFT_TOP -> return knight_move_left_top[step]
            }

            else -> {
                return knight_standing_bottom
            }
        }

    }


}