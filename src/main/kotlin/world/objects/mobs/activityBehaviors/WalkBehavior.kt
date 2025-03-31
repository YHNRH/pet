package world.objects.mobs.activityBehaviors

import AStar
import Consts.Companion.runSpeed
import Consts.Companion.walkSpeed
import world.MapPoint
import objects.Activity
import world.Chunk
import world.Chunks
import world.SimplePoint
import world.objects.Direction
import world.objects.IDrawableObject
import world.objects.mobs.IMob
import java.awt.event.ActionListener
import javax.swing.Timer

class WalkBehavior(
    nextActivityBehavior: ActivityBehavior?,
    var destination: MapPoint,
    private val collisionObjects: ArrayList<IDrawableObject>,
    var skip: () -> Boolean = { false },
    var activity: Activity = Activity.WALK,
    var afterFunction: () -> Unit = {},
   // private val removeLastElement: Int = 0
) : ActivityBehavior(nextActivityBehavior) {

    var timer: Timer? = null

    var t: Thread? = null

    override fun performActivity(mob: IMob) {
        if (!skip()) {
            timer?.stop()
            mob.activity = activity
            mob.step = 0


            val chunksX = if (mob.point.chunk.getX() >= destination.chunk.getX()) {
                mob.point.chunk.getX() downTo destination.chunk.getX()
            } else {
                mob.point.chunk.getX()..destination.chunk.getX()
            }

            val chunksY = if (mob.point.chunk.getY() >= destination.chunk.getY()) {
                mob.point.chunk.getY() downTo destination.chunk.getY()
            } else {
                mob.point.chunk.getY()..destination.chunk.getY()
            }


            val chunksToSearch = HashMap<MapPoint, Chunk>()

            chunksX.forEach { x ->
                chunksY.forEach { y ->
                    val foundedChunk = Chunks.instance().chunks.get(SimplePoint(x, y))
                    if (foundedChunk != null) {
                        chunksToSearch.put(MapPoint(x, y, foundedChunk), foundedChunk)
                    }
                }
            }
            println("chunksToSearch size " + chunksToSearch.size)
            t = Thread {
                try {
                    val shortestPath = getShortestPath(destination, mob,  collisionObjects/*),chunksToSearch*/)

                    mob.pathForDebugDraw.add(shortestPath)
                    var index = 0

                    val taskPerformer = ActionListener {
                        try {
                            if (mob.point.getX() > shortestPath[index].getX()) {

                                mob.direction = if (mob.point.getY() > shortestPath[index].getY()) {
                                    Direction.BOTTOM
                                } else if (mob.point.getY() < shortestPath[index].getY()) {
                                    Direction.LEFT
                                } else {
                                    Direction.LEFT_BOTTOM
                                }
                            } else if (mob.point.getX() < shortestPath[index].getX()) {

                                mob.direction = if (mob.point.getY() > shortestPath[index].getY()) {
                                    Direction.RIGHT

                                } else if (mob.point.getY() < shortestPath[index].getY()) {
                                    Direction.TOP
                                } else {
                                    Direction.RIGHT_TOP
                                }
                            } else {
                                mob.direction = if (mob.point.getY() > shortestPath[index].getY()) {
                                    Direction.RIGHT_BOTTOM
                                } else {
                                    Direction.LEFT_TOP
                                }
                            }
                            if (mob.step == mob.animSizes.get(activity)!! - 1) {
                                mob.point = shortestPath[index]
                                //mob.point.chunk = shortestPath[index].chunk
                                mob.occupiedBlocks.clear()
                                for (x in (mob.point.getX()) until (mob.point.getX() + mob.width / 2)) {
                                    for (y in (mob.point.getY()) until (mob.point.getY() + mob.width / 2)) {
                                        mob.occupiedBlocks.add(
                                            MapPoint(
                                                x, y, mob.point.chunk
                                            )
                                        )
                                    }
                                }
                                index++
                                mob.step = 0
                            } else {
                                mob.step++
                            }

                        } catch (e: Throwable) {
                            //e.printStackTrace()
                            timer?.stop()
                            mob.activity = Activity.STAND
                            afterFunction()

                            nextActivity(mob)
                            mob.step = 0
                            mob.pathForDebugDraw.clear()
                        }
                    }
                    val speed = when (mob.activity) {
                        Activity.WALK -> walkSpeed
                        Activity.RUN -> runSpeed
                        else -> walkSpeed
                    }
                    val delay = when (mob.activity) {
                        Activity.RUN -> speed / mob.animSizes[mob.activity]!!
                        Activity.WALK -> speed / mob.animSizes[mob.activity]!!
                        else -> speed / mob.animSizes[mob.activity]!!
                    }
                    timer = Timer(delay, taskPerformer)

                    timer?.start()
                } catch (e: Throwable) {
                    e.printStackTrace()
                    mob.activity = Activity.STAND
                    mob.step = 0
                    afterFunction()
                    nextActivity(mob)
                    mob.pathForDebugDraw.clear()
                }
            }
            t?.start()
        } else {
            nextActivity(mob)
        }
//            this.chunk = chunk
//            this.point = point

    }

    override fun forceStop(){
        this.timer?.stop()
        t?.interrupt()
    }

    //    override fun performActivity(mob: Mob) {
//        val activity = Activity.WALK
//        var rndX = Random.nextInt(-10, 10)
//        var rndY = Random.nextInt(-10, 10)
//        var chunkX = 0
//        var chunkY = 0
//        if (mob.chunkAndPoint.point.getX() + rndX >= chunkSize){
//            rndX = (mob.chunkAndPoint.point.getX() + rndX) % chunkSize
//            chunkX++
//        } else if (mob.chunkAndPoint.point.getX() + rndX < 0){
//            rndX += chunkSize + mob.chunkAndPoint.point.getX()
//            chunkX--
//        } else {
//            rndX += mob.chunkAndPoint.point.getX()
//        }
//
//        if (mob.chunkAndPoint.point.getY() + rndY >= chunkSize){
//            rndY =  (mob.chunkAndPoint.point.getY() + rndY) % chunkSize
//            chunkY++
//        } else if (mob.chunkAndPoint.point.getY() + rndY < 0){
//            rndY -= chunkSize + mob.chunkAndPoint.point.getY()
//            chunkY--
//        } else {
//            rndY += mob.chunkAndPoint.point.getY()
//        }
//        val chunk = Chunks.instance().chunks.get(world.Point(chunkX,chunkY))
//        val pointExist = chunk?.getNoncollisionObject(world.Point(rndX,rndY)) != null
//        if (chunk != null && pointExist){
//            val objects = ArrayList<DrawableObject>()
//            Chunks.instance().chunks.forEach{
//                objects.addAll(it.value.objects)
//            }
//            mob.move(SimpleMob.ChunkAndPoint(chunk, world.Point(rndX,rndY)),
//                Chunks.instance().chunks, objects , this, activity)
//        } else {
//            nextActivity(mob)
//        }
//    }
    private fun getShortestPath(
        destination: MapPoint,
        mob: IMob,
      //  removeLastElement: Int,
        collisionObjects: ArrayList<IDrawableObject>
    ): ArrayList<MapPoint> {
        val path = AStar().astar(mob.point, destination, collisionObjects)
        //path = path.dropLast(removeLastElement)

        val test = ArrayList<ArrayList<MapPoint>>()
        test.add(path)
        mob.pathForDebugDraw = test
        return path
    }

    override fun nextActivity(mob: IMob) {
        nextActivityBehavior?.performActivity(mob)
    }
}