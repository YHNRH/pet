package world.objects.mobs.activityBehaviors

import AStar
import Consts.Companion.runSpeed
import Consts.Companion.walkSpeed
import Point
import objects.Activity
import world.Chunk
import world.ChunkAndPoint
import world.Chunks
import world.objects.Direction
import world.objects.DrawableObject
import world.objects.mobs.Mob
import java.awt.event.ActionListener
import javax.swing.Timer

class WalkBehavior(
    nextActivityBehavior: ActivityBehavior?,
    var destination: ChunkAndPoint,
    var skip: () -> Boolean = { false },
    var activity: Activity = Activity.WALK,
    var afterFunction: () -> Unit = {},
    private val removeLastElement: Int = 0
) : ActivityBehavior(nextActivityBehavior) {

    var timer: Timer? = null

    var t: Thread? = null

    override fun performActivity(mob: Mob) {
        if (!skip()) {
            timer?.stop()
            mob.activity = activity
            mob.step = 0


            val chunksX = if (mob.chunkAndPoint.chunk.point.getX() >= destination.chunk.point.getX()) {
                mob.chunkAndPoint.chunk.point.getX() downTo destination.chunk.point.getX()
            } else {
                mob.chunkAndPoint.chunk.point.getX()..destination.chunk.point.getX()
            }

            val chunksY = if (mob.chunkAndPoint.chunk.point.getY() >= destination.chunk.point.getY()) {
                mob.chunkAndPoint.chunk.point.getY() downTo destination.chunk.point.getY()
            } else {
                mob.chunkAndPoint.chunk.point.getY()..destination.chunk.point.getY()
            }


            val chunksToSearch = HashMap<Point, Chunk>()

            chunksX.forEach { x ->
                chunksY.forEach { y ->
                    val foundedChunk = Chunks.instance().chunks.get(Point(x, y))
                    if (foundedChunk != null) {
                        chunksToSearch.put(Point(x, y), foundedChunk)
                    }
                }
            }
            println("chunksToSearch size " + chunksToSearch.size)
            t = Thread {
                try {
                    val shortestPath = getShortestPath(destination, mob, removeLastElement/*),chunksToSearch*/)

                    mob.pathForDebugDraw.add(shortestPath)
                    var index = 0

                    val taskPerformer = ActionListener {
                        try {
                            if (mob.chunkAndPoint.point.getX() > shortestPath[index].point.getX()) {

                                mob.direction = if (mob.chunkAndPoint.point.getY() > shortestPath[index].point.getY()) {
                                    Direction.BOTTOM
                                } else if (mob.chunkAndPoint.point.getY() < shortestPath[index].point.getY()) {
                                    Direction.LEFT
                                } else {
                                    Direction.LEFT_BOTTOM
                                }
                            } else if (mob.chunkAndPoint.point.getX() < shortestPath[index].point.getX()) {

                                mob.direction = if (mob.chunkAndPoint.point.getY() > shortestPath[index].point.getY()) {
                                    Direction.RIGHT

                                } else if (mob.chunkAndPoint.point.getY() < shortestPath[index].point.getY()) {
                                    Direction.TOP
                                } else {
                                    Direction.RIGHT_TOP
                                }
                            } else {
                                mob.direction = if (mob.chunkAndPoint.point.getY() > shortestPath[index].point.getY()) {
                                    Direction.RIGHT_BOTTOM
                                } else {
                                    Direction.LEFT_TOP
                                }
                            }
                            if (mob.step == mob.animSizes.get(activity)!! - 1) {
                                mob.chunkAndPoint.point = shortestPath[index].point
                                mob.chunkAndPoint.chunk = shortestPath[index].chunk
                                mob.occupiedBlocks.clear()
                                for (x in (mob.chunkAndPoint.point.getX()) until (mob.chunkAndPoint.point.getX() + mob.width / 2)) {
                                    for (y in (mob.chunkAndPoint.point.getY()) until (mob.chunkAndPoint.point.getY() + mob.width / 2)) {
                                        mob.occupiedBlocks.add(
                                            ChunkAndPoint(
                                                mob.chunkAndPoint.chunk,
                                                Point(x, y)
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
//        val chunk = Chunks.instance().chunks.get(Point(chunkX,chunkY))
//        val pointExist = chunk?.getNoncollisionObject(Point(rndX,rndY)) != null
//        if (chunk != null && pointExist){
//            val objects = ArrayList<DrawableObject>()
//            Chunks.instance().chunks.forEach{
//                objects.addAll(it.value.objects)
//            }
//            mob.move(SimpleMob.ChunkAndPoint(chunk, Point(rndX,rndY)),
//                Chunks.instance().chunks, objects , this, activity)
//        } else {
//            nextActivity(mob)
//        }
//    }
    private fun getShortestPath(
        destination: ChunkAndPoint,
        mob: Mob,
        removeLastElement: Int
    ): ArrayList<ChunkAndPoint> {
        var path = AStar().astar(mob.chunkAndPoint, destination)
        path = path.dropLast(removeLastElement) as ArrayList<ChunkAndPoint>
        val test = ArrayList<ArrayList<ChunkAndPoint>>()
        test.add(path)
        mob.pathForDebugDraw = test
        return path
    }

    override fun nextActivity(mob: Mob) {
        nextActivityBehavior?.performActivity(mob)
    }
}