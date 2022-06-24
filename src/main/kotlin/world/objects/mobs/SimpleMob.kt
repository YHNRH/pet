package world.objects.mobs

import world.Chunk
import Consts.Companion.chunkSize
import Consts.Companion.debugDraw
import Consts.Companion.runSpeed
import Consts.Companion.walkSpeed
import GraphicsExtender
import Point
import objects.Activity
import world.objects.Direction
import world.objects.DrawableObject
import world.objects.mobs.activityBehaviors.ActivityBehavior
import java.awt.event.ActionListener
import javax.swing.Timer
import kotlin.math.abs

abstract class SimpleMob(override var chunkAndPoint: ChunkAndPoint,
                         override var width: Int,
                         override var height: Int
) : Mob {
    var timer: Timer? = null
    var pathForDebugDraw: ArrayList<ArrayList<ChunkAndPoint>> = ArrayList()
    override var occupiedBlocks: ArrayList<ChunkAndPoint> = ArrayList()

    init {
        for (x in (this.chunkAndPoint.point.getX())until (this.chunkAndPoint.point.getX()+width/2)){
            for (y in (this.chunkAndPoint.point.getY())until (this.chunkAndPoint.point.getY()+width/2)){
                this.occupiedBlocks.add(ChunkAndPoint(chunkAndPoint.chunk, Point(x, y)))
            }
        }
    }

    override fun move(
        chunkAndPoint: ChunkAndPoint,
        chunks: HashMap<Point, Chunk>,
        objects: ArrayList<DrawableObject>,
        caller: ActivityBehavior?,
        activity: Activity
    ) {
        timer?.stop()
        this.activity = activity
            step = 0


            val chunksX =if (this.chunkAndPoint.chunk.point.getX() >= chunkAndPoint.chunk.point.getX()){
                this.chunkAndPoint.chunk.point.getX()downTo chunkAndPoint.chunk.point.getX()
            } else {
                this.chunkAndPoint.chunk.point.getX()..chunkAndPoint.chunk.point.getX()
            }

            val chunksY =if (this.chunkAndPoint.chunk.point.getY() >= chunkAndPoint.chunk.point.getY()){
                this.chunkAndPoint.chunk.point.getY()downTo chunkAndPoint.chunk.point.getY()
            } else {
                this.chunkAndPoint.chunk.point.getY()..chunkAndPoint.chunk.point.getY()
            }


            val chunksToSearch = HashMap<Point, Chunk>()

            chunksX.forEach{ x ->
                chunksY.forEach{y ->
                    val foundedChunk =  chunks.get(Point(x, y))
                    if (foundedChunk != null){
                        chunksToSearch.put(Point(x, y), foundedChunk)
                    }
                }
            }
            println("chunksToSearch size " + chunksToSearch.size)
            val t = Thread{
                try{
                    val shortestPath = getShortestPath(chunkAndPoint,chunksToSearch, objects)

                pathForDebugDraw.add(shortestPath)
                var index = 0

                val taskPerformer = ActionListener {
                    try{
                        if (this.chunkAndPoint.point.getX() > shortestPath[index].point.getX()){

                            direction = if (this.chunkAndPoint.point.getY() > shortestPath[index].point.getY()){
                                Direction.BOTTOM
                            } else if (this.chunkAndPoint.point.getY() < shortestPath[index].point.getY()){
                                Direction.LEFT
                            } else {
                                Direction.LEFT_BOTTOM
                            }
                        } else if (this.chunkAndPoint.point.getX() < shortestPath[index].point.getX()){

                            direction = if (this.chunkAndPoint.point.getY() > shortestPath[index].point.getY()){
                                Direction.RIGHT

                            } else if (this.chunkAndPoint.point.getY() < shortestPath[index].point.getY()){
                                Direction.TOP
                            } else {
                                Direction.RIGHT_TOP
                            }
                        } else {
                            direction = if (this.chunkAndPoint.point.getY() > shortestPath[index].point.getY()){
                                Direction.RIGHT_BOTTOM
                            } else {
                                Direction.LEFT_TOP
                            }
                        }
                        if (step == animSizes.get(activity)!!-1){
                            this.chunkAndPoint.point = shortestPath[index].point
                            this.chunkAndPoint.chunk = shortestPath[index].chunk
                            this.occupiedBlocks.clear()
                            for (x in (this.chunkAndPoint.point.getX())until(this.chunkAndPoint.point.getX()+width/2)){
                                for (y in (this.chunkAndPoint.point.getY())until(this.chunkAndPoint.point.getY()+width/2)){
                                    this.occupiedBlocks.add(ChunkAndPoint(this.chunkAndPoint.chunk, Point(x,y)))
                                }
                            }
                            index++
                            step = 0
                        } else {
                            step++
                        }

                    } catch (e : Throwable){
                        e.printStackTrace()
                        timer?.stop()
                        this.activity = Activity.STAND

                        caller?.nextActivity(this)
                        step = 0
                        this.pathForDebugDraw.clear()
                    }
                }
                val speed = when(this.activity){
                    Activity.WALK -> walkSpeed
                    Activity.RUN -> runSpeed
                    else  -> walkSpeed
                }
                    val delay = when (activity){
                        Activity.RUN -> speed/ animSizes[activity]!!
                        Activity.WALK -> speed/ animSizes[activity]!!
                        else -> speed/ animSizes[activity]!!
                    }
                timer = Timer(delay, taskPerformer)

                timer?.start()
                } catch (e: Throwable){
                    e.printStackTrace()
                    this.activity = Activity.STAND
                    step = 0
                    caller?.performActivity(this)
                    this.pathForDebugDraw.clear()
                }
            }
            t.start()
//            this.chunk = chunk
//            this.point = point

        }

//        fun availableMoves_old(chunk: world.Chunk, point: Point, chunks: HashMap<Point, world.Chunk>): ArrayList<Point> {
//            var moves = ArrayList<Point>()
//            if (point.getX()>0){ //влево
//                moves.add(Point(point.getX()-1, point.getY()))
//            }
//
//            if (point.getX()>0 && point.getY()< chunkSize-1){ //влево вверх
//                moves.add(Point(point.getX()-1, point.getY() + 1))
//            }
//
//
//            if (point.getX()>0 && point.getY()>0){ //влево вниз
//                moves.add(Point(point.getX()-1, point.getY() - 1))
//            }
//
//            if (point.getX()<chunkSize-1){ //вправо
//                moves.add(Point(point.getX()+1, point.getY()))
//            }
//
//            if (point.getX()<chunkSize-1 && point.getY()< chunkSize-1){ //вправо вверх
//                moves.add(Point(point.getX()+1, point.getY()+1))
//            }
//
//            if (point.getX()<chunkSize-1 && point.getY()>0){ //вправо вниз
//                moves.add(Point(point.getX()+1, point.getY()-1))
//            }
//
//
//            if (point.getY()>0){ // вниз
//                moves.add(Point(point.getX(), point.getY()-1))
//            }
//
//
//            if (point.getY()< chunkSize-1){ // вверх
//                moves.add(Point(point.getX(), point.getY()+1))
//            }
//            return moves
//        }

    fun availableMoves(chunkAndPoint: ChunkAndPoint, chunks: HashMap<Point, Chunk>, mobs: ArrayList<DrawableObject>): ArrayList<ChunkAndPoint> {
        var moves = ArrayList<ChunkAndPoint>()
        if (chunkAndPoint.point.getX()>0){ //влево
            moves.add(
                ChunkAndPoint(chunkAndPoint.chunk,
                    Point(chunkAndPoint.point.getX()-1, chunkAndPoint.point.getY())
                )
            )
        } else if (chunks[Point(chunkAndPoint.chunk.point.getX() - 1, chunkAndPoint.chunk.point.getY())] != null){
            moves.add(
                ChunkAndPoint(chunks[Point(chunkAndPoint.chunk.point.getX()-1, chunkAndPoint.chunk.point.getY())]!!,
                    Point(
                chunkSize-1, chunkAndPoint.point.getY())
                )
            )
        }

        if (chunkAndPoint.point.getX()>0 && chunkAndPoint.point.getY()< chunkSize-1){ //влево вверх
            moves.add(
                ChunkAndPoint(chunkAndPoint.chunk,
                    Point(chunkAndPoint.point.getX()-1, chunkAndPoint.point.getY() + 1)
                )
            )
        } else if (chunks[Point(chunkAndPoint.chunk.point.getX() - 1, chunkAndPoint.chunk.point.getY())] != null && chunkAndPoint.point.getY()< chunkSize-1 && chunkAndPoint.point.getX() == 0){ // влево вверх в левый чанк
            moves.add(
                ChunkAndPoint(chunks[Point(chunkAndPoint.chunk.point.getX()-1, chunkAndPoint.chunk.point.getY())]!!,
                    Point(
                chunkSize-1, chunkAndPoint.point.getY() + 1)
                )
            )
        } else if (chunks[Point(chunkAndPoint.chunk.point.getX(), chunkAndPoint.chunk.point.getY() + 1)] != null && chunkAndPoint.point.getY() == chunkSize-1 && chunkAndPoint.point.getX() > 0) { // влево вверх в верхний чанк
            moves.add(
                ChunkAndPoint(chunks[Point(chunkAndPoint.chunk.point.getX(), chunkAndPoint.chunk.point.getY()+1)]!!,
                    Point(
                chunkAndPoint.point.getX()-1, 0)
                )
            )
        }
        else if (chunks[Point(chunkAndPoint.chunk.point.getX() - 1, chunkAndPoint.chunk.point.getY() + 1)] != null && chunkAndPoint.point.getY() == chunkSize-1 && chunkAndPoint.point.getX() == 0) { // влево вверх в левый верхний чанк
            moves.add(
                ChunkAndPoint(chunks[Point(chunkAndPoint.chunk.point.getX()-1, chunkAndPoint.chunk.point.getY()+1)]!!,
                    Point(
                chunkSize-1, 0)
                )
            )
        }

        if (chunkAndPoint.point.getX()>0 && chunkAndPoint.point.getY()>0){ //влево вниз
            moves.add(
                ChunkAndPoint(chunkAndPoint.chunk,
                    Point(chunkAndPoint.point.getX()-1, chunkAndPoint.point.getY() - 1)
                )
            )
        } else if (chunks[Point(chunkAndPoint.chunk.point.getX() - 1, chunkAndPoint.chunk.point.getY())] != null && chunkAndPoint.point.getY()>0){ // влево вниз в левый чанк
            moves.add(
                ChunkAndPoint(chunks[Point(chunkAndPoint.chunk.point.getX()-1, chunkAndPoint.chunk.point.getY())]!!,
                    Point(
                chunkSize-1, chunkAndPoint.point.getY() - 1)
                )
            )
        } else if (false){} // влево вниз в нижний чанк
         else if (false){} // влево вниз в левый нижний чанк




        if (chunkAndPoint.point.getX()<chunkSize-1){ //вправо
            moves.add(
                ChunkAndPoint(chunkAndPoint.chunk,
                    Point(chunkAndPoint.point.getX()+1, chunkAndPoint.point.getY())
                )
            )
        } else if (chunks[Point(chunkAndPoint.chunk.point.getX() + 1, chunkAndPoint.chunk.point.getY())] != null){
            moves.add(
                ChunkAndPoint(chunks[Point(chunkAndPoint.chunk.point.getX()+1, chunkAndPoint.chunk.point.getY())]!!,
                    Point(0,chunkAndPoint.point.getY())
                )
            )
        }


        if (chunkAndPoint.point.getX()<chunkSize-1 && chunkAndPoint.point.getY()< chunkSize-1){ //вправо вверх
            moves.add(
                ChunkAndPoint(chunkAndPoint.chunk,
                    Point(chunkAndPoint.point.getX()+1, chunkAndPoint.point.getY()+1)
                )
            )
        } else if (chunks[Point(chunkAndPoint.chunk.point.getX() + 1, chunkAndPoint.chunk.point.getY())] != null && chunkAndPoint.point.getY()< chunkSize-1){ //вправо вверх в правый чанк
            moves.add(
                ChunkAndPoint(chunks[Point(chunkAndPoint.chunk.point.getX()+1, chunkAndPoint.chunk.point.getY())]!!,
                    Point(
                0, chunkAndPoint.point.getY()+1)
                )
            )
        } else if (false ){ // вправо вверх в верхний чанк
        }
        else if (false ){ }// вправо вверх в правый верхний чанк


        if (chunkAndPoint.point.getX()<chunkSize-1 && chunkAndPoint.point.getY()>0){ //вправо вниз
            moves.add(
                ChunkAndPoint(chunkAndPoint.chunk,
                    Point(chunkAndPoint.point.getX()+1, chunkAndPoint.point.getY()-1)
                )
            )
        } else if (chunks[Point(chunkAndPoint.chunk.point.getX() + 1, chunkAndPoint.chunk.point.getY())] != null && chunkAndPoint.point.getY()>0){ //вправо вниз в правый чанк
            moves.add(
                ChunkAndPoint(chunks[Point(chunkAndPoint.chunk.point.getX()+1, chunkAndPoint.chunk.point.getY())]!!,
                    Point(
                0, chunkAndPoint.point.getY()-1)
                )
            )
        } else if (false){ // вправо вниз в нижний чанк
        } else if (false){ // вправо вниз в правый нижний чанк
        }




        if (chunkAndPoint.point.getY()>0){ // вниз
            moves.add(
                ChunkAndPoint(chunkAndPoint.chunk,
                    Point(chunkAndPoint.point.getX(), chunkAndPoint.point.getY()-1)
                )
            )
        } else if (chunks[Point(chunkAndPoint.chunk.point.getX(), chunkAndPoint.chunk.point.getY() - 1)] != null){
            moves.add(
                ChunkAndPoint(chunks[Point(chunkAndPoint.chunk.point.getX(), chunkAndPoint.chunk.point.getY()-1)]!!,
                    Point(chunkAndPoint.point.getX(), chunkSize-1)
                )
            )

        }


        if (chunkAndPoint.point.getY()< chunkSize-1){ // вверх
            moves.add(
                ChunkAndPoint(chunkAndPoint.chunk,
                    Point(chunkAndPoint.point.getX(), chunkAndPoint.point.getY()+1)
                )
            )
        } else if (chunks[Point(chunkAndPoint.chunk.point.getX(), chunkAndPoint.chunk.point.getY() + 1)] != null){
            moves.add(
                ChunkAndPoint(chunks[Point(chunkAndPoint.chunk.point.getX(), chunkAndPoint.chunk.point.getY()+1)]!!,
                    Point(chunkAndPoint.point.getX(), 0)
                )
            )
        }

        val collisionMoves = ArrayList<ChunkAndPoint>()
        moves.forEach{move ->

            val occupiedSize = ArrayList<ChunkAndPoint>()
            for (x in (move.point.getX())until(move.point.getX()+width/2)){
                for (y in (move.point.getY())until(move.point.getY()+width/2)){
                    occupiedSize.add(ChunkAndPoint(move.chunk, Point(x,y)))
                }
            }
//
//            for (x in move.point.getX()..move.point.getX() + width-1){
//                for (y in move.point.getY()..move.point.getY() + height-1){
//                    occupiedSize.add(ChunkAndPoint(move.chunk, Point(x, y)));
//                }
//            }
            // Необходима доработка коллизии по чанкам
            mobs.forEach {mob ->
                if (mob!= this){

//                    val mobOccupiedSize = ArrayList<ChunkAndPoint>()

//                    for (x in mob.chunkAndPoint.point.getX() until mob.chunkAndPoint.point.getX() + width){
//                        for (y in mob.chunkAndPoint.point.getY() until mob.chunkAndPoint.point.getY() + height){
//                            mobOccupiedSize.add(ChunkAndPoint(mob.chunkAndPoint.chunk, Point(x, y)));
//                        }
//                    }

                    occupiedSize.forEach { point ->
                        mob.occupiedBlocks.forEach {
                            mobPoint ->
                            if (point.equals(mobPoint)){
                                collisionMoves.add(move)
                            }
                        }
                    }
                }
            }
        }
        moves.removeAll(collisionMoves)
        return moves
    }


    fun getOptimalMove(path:ArrayList<ChunkAndPoint>, chunks: HashMap<Point, Chunk>, destination: ChunkAndPoint, mobs:ArrayList<DrawableObject> ): ArrayList<ChunkAndPoint> {
        val availableMoves = availableMoves(path.last(), chunks, mobs)
        val optimalMoves = ArrayList<ChunkAndPoint>()
        if (availableMoves.contains(destination)){
            optimalMoves.add(destination)
//            println("optimalMoves size ${optimalMoves.size}")
            return optimalMoves
        }
        path.forEach{ // Удаляем повторяющиеся
            if (availableMoves.contains(it)){
                availableMoves.remove(it)
            }
        }
        var lowestAbsX = 999999
        var lowestAbsY = 999999
        availableMoves.forEach{move ->
            if (abs(destination.point.getX() + destination.chunk.point.getX() * chunkSize - (move.point.getX() + move.chunk.point.getX()  * chunkSize)) < lowestAbsX){
                lowestAbsX = abs(destination.point.getX() + destination.chunk.point.getX() * chunkSize - (move.point.getX() + move.chunk.point.getX()  * chunkSize))
            }
        }

        availableMoves.forEach{move ->
            if (abs(destination.point.getY() + destination.chunk.point.getY() * chunkSize - (move.point.getY() + move.chunk.point.getY()  * chunkSize)) < lowestAbsY){
                lowestAbsY = abs(destination.point.getY() + destination.chunk.point.getY() * chunkSize - (move.point.getY() + move.chunk.point.getY()  * chunkSize))
            }
        }


        availableMoves.forEach{move ->
            if (abs(destination.point.getX() + destination.chunk.point.getX() * chunkSize - (move.point.getX() + move.chunk.point.getX()  * chunkSize))-1  <= lowestAbsX
                &&
                abs(destination.point.getY() + destination.chunk.point.getY() * chunkSize - (move.point.getY() + move.chunk.point.getY()  * chunkSize))-1  <= lowestAbsY){
                optimalMoves.add(move)
            }
        }
//        println("lowestAbsX $lowestAbsX")
//        println("lowestAbsY $lowestAbsY")
//        println("optimalMoves size ${optimalMoves.size}")
        if (optimalMoves.size == 0){
            return availableMoves // На крайняк
        }
        return optimalMoves

    }



    fun getShortestPath(destination: ChunkAndPoint, chunks: HashMap<Point, Chunk>, mobs:ArrayList<DrawableObject>): ArrayList<ChunkAndPoint> {
        val timeStart = System.currentTimeMillis()
        var pathes = ArrayList<ArrayList<ChunkAndPoint>>()
        val firstPoint = ArrayList<ChunkAndPoint>()
        firstPoint.add(this.chunkAndPoint)
        getOptimalMove(firstPoint ,chunks, destination, mobs).forEach{
            val path = ArrayList<ChunkAndPoint>()
            path.add(it)
            if (it == destination){
                return path
            }
            pathes.add(path)
        }

        while(true){
            val pathesAppend = ArrayList<ArrayList<ChunkAndPoint>>()
            val pathesDelete = ArrayList<ArrayList<ChunkAndPoint>>()
            pathes.forEach{ path ->
//                val availableMoves = availableMoves(path.last(), chunks)
//                try{
//                    availableMoves.remove(path.get(path.size-2)) // Чтобы не шел в точку, откуда только что ушел
//                } catch (e:java.lang.IndexOutOfBoundsException){
//                    println("Не могу получить ${path.size-2} элемент массива path")
//                }

//                path.forEach{ // Удаляем повторяющиеся
//                    if (availableMoves.contains(it)){
//                        availableMoves.remove(it)
//                    }
//                }
                val optimalMoves = getOptimalMove(path, chunks, destination, mobs)
                if (optimalMoves.size>0){
                    if (optimalMoves.contains(destination)){
                        path.add(destination)
                        println("time: ".plus(System.currentTimeMillis().minus(timeStart)))
                       return path
                    }


                    optimalMoves.forEach {
                        if (it != optimalMoves.first()){
                            val newPath = ArrayList<ChunkAndPoint>()
                            path.toCollection(newPath)
                            newPath.add(it)
                            pathesAppend.add(newPath)
                        }
                    }

                    path.add(optimalMoves.first())
                } else { // Нет ни одного возможного хода (или не повторяющегося)
                    pathesDelete.add(path)
                    println(path.toString())
                }
            }

            val samePathesToAppend = ArrayList<ArrayList<ChunkAndPoint>>()
            for (i in 0..pathesAppend.size-1){
                for (j in i..pathesAppend.size-1){
                    if (i != j){
                        if (pathesAppend.get(i).last() == pathesAppend.get(j).last()){
                            samePathesToAppend.add(pathesAppend.get(j))
                        }
                    }
                }
            }

            for (i in 0..pathesAppend.size-1){
                for (j in 0..pathes.size-1){
                    if (i != j){
                        if (pathesAppend.get(i).last() == pathes.get(j).last()){
                            samePathesToAppend.add(pathesAppend.get(i))
                        }
                    }
                }
            }

            pathesAppend.removeAll(samePathesToAppend)
            pathes.addAll(pathesAppend)
            pathes.removeAll(pathesDelete)
//d

//            if (pathes.size> 50) {
                val samePathes = ArrayList<ArrayList<ChunkAndPoint>>()
                for (i in 0..pathes.size-1){
                    for (j in i..pathes.size-1){
                        if (i != j){
                            if (pathes.get(i).last() == pathes.get(j).last()){
                                samePathes.add(pathes.get(j))
                            }
                        }
                    }
                }
                pathes.removeAll(samePathes)
                println("pathes.size ${pathes.size}")
//            }
//
//                var lowestAbsX = 999999
//                var lowestAbsY = 999999
//                pathes.forEach { path ->
//                    if (abs(destination.point.getX() + destination.chunk.point.getX() * chunkSize - (path.last().point.getX() + path.last().chunk.point.getX() * chunkSize)) < lowestAbsX) {
//                        lowestAbsX =
//                            abs(destination.point.getX() + destination.chunk.point.getX() * chunkSize - (path.last().point.getX() + path.last().chunk.point.getX() * chunkSize))
//                    }
//
//                    if (abs(destination.point.getY() + destination.chunk.point.getY() * chunkSize - (path.last().point.getY() + path.last().chunk.point.getY() * chunkSize)) < lowestAbsY) {
//                        lowestAbsY =
//                            abs(destination.point.getY() + destination.chunk.point.getY() * chunkSize - (path.last().point.getY() + path.last().chunk.point.getY() * chunkSize))
//                    }
//                }
//
//
//
//                pathes.forEach { path ->
//                    if (abs(destination.point.getX() + destination.chunk.point.getX() * chunkSize - (path.last().point.getX() + path.last().chunk.point.getX() * chunkSize)) - 1 <= lowestAbsX
//                        &&
//                        abs(destination.point.getY() + destination.chunk.point.getY() * chunkSize - (path.last().point.getY() + path.last().chunk.point.getY() * chunkSize)) - 1 <= lowestAbsY
//                    ) {
//                        optimizedPathes.add(path)
//                    }
//                }


//                availableMoves.forEach{move ->
//                    if (abs(destination.point.getX() + destination.chunk.point.getX() * chunkSize - (move.point.getX() + move.chunk.point.getX()  * chunkSize))-1  <= lowestAbsX
//                        &&
//                        abs(destination.point.getY() + destination.chunk.point.getY() * chunkSize - (move.point.getY() + move.chunk.point.getY()  * chunkSize))-1  <= lowestAbsY){
//                        optimalMoves.add(move)
//                    }
            if (pathes.size == 0){
                throw java.lang.Error("Нет пути")
            }
            this.pathForDebugDraw = pathes
//            Thread.sleep(10)
        }
    }


    override fun draw(ge: GraphicsExtender) {
        if (debugDraw){
            ge.drawPolyline(pathForDebugDraw)
        }
//        ge.drawMob(troll, this.chunkAndPoint.point.getX(),this.chunkAndPoint.point.getY(),this.chunkAndPoint.chunk.point.getX(),this.chunkAndPoint.chunk.point.getY(), width, height)
    }

    class ChunkAndPoint(var chunk: Chunk, var point: Point){

        override fun toString(): String {
            return chunk.toString().plus(" ").plus(point.toString())
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }
            if (other !is ChunkAndPoint) {
                return false
            }
            return this.point.getX() == other.point.getX() && this.point.getY() == other.point.getY() && this.chunk.point.getX() == other.chunk.point.getX() && this.chunk.point.getY() == other.chunk.point.getY()
        }

        override fun hashCode(): Int {
            var result = chunk.hashCode()
            result = 31 * result + point.hashCode()
            return result
        }
    }



}