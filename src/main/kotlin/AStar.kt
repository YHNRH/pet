import world.ChunkAndPoint
import world.Chunks
import world.objects.DrawableObject
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

class AStar {



    private fun reconstructPath(start: Node, goal: Node): ArrayList<ChunkAndPoint> {
        var currentNode : Node? = goal // поиск начинается от финиша
        val res = ArrayList<ChunkAndPoint>()
        while (currentNode != start && currentNode != null){
            res.add(currentNode.point) // Добавить вершину в карту
            currentNode = currentNode.cameFrom
        }
        res.reverse()
        return res
    }

    private fun distBetween(x: Node, it: Node): Int {
        return 10
    }

    private fun heuristicCostEstimate(start: Point, goal: Point): Double {
        return sqrt( (abs(start.getX()-goal.getX()).toDouble()).pow(2) + (abs(start.getY()-goal.getY()).toDouble()).pow(2))
    }

    private fun unclosedNeighbors(x: Node, collisionObjects: List<Node>): ArrayList<Node> {
        val res = ArrayList<Node>()
        res.add(Node(ChunkAndPoint(x.point.chunk,Point(x.point.point.getX()+1, x.point.point.getY()+1))))
        res.add(Node(ChunkAndPoint(x.point.chunk,Point(x.point.point.getX()+1, x.point.point.getY()))))
        res.add(Node(ChunkAndPoint(x.point.chunk,Point(x.point.point.getX()+1, x.point.point.getY()-1))))
        res.add(Node(ChunkAndPoint(x.point.chunk,Point(x.point.point.getX(), x.point.point.getY()+1))))
        res.add(Node(ChunkAndPoint(x.point.chunk,Point(x.point.point.getX(), x.point.point.getY()-1))))
        res.add(Node(ChunkAndPoint(x.point.chunk,Point(x.point.point.getX()-1, x.point.point.getY()+1))))
        res.add(Node(ChunkAndPoint(x.point.chunk,Point(x.point.point.getX()-1, x.point.point.getY()))))
        res.add(Node(ChunkAndPoint(x.point.chunk,Point(x.point.point.getX()-1, x.point.point.getY()-1))))

        res.removeAll(collisionObjects)

        return res
    }

    fun astar(s :ChunkAndPoint, g : ChunkAndPoint): ArrayList<ChunkAndPoint> {


        val start = Node(s)
        val goal = Node(g)
        val closed = ArrayList<Node>()


        val collisionObjects = Chunks.instance().chunks[Point(0,0)]?.objects?.toList()
        val occupiedNodes = ArrayList<Node>()
        collisionObjects?.forEach { t -> occupiedNodes.addAll(t.occupiedBlocks.map { Node(it) }) }

        //Заполняем свойства вершины start
        start.g = 0.0   // g(x). Стоимость пути от начальной вершины. У start g(x) = 0
        start.h = heuristicCostEstimate(start.point.point, goal.point.point) // Эвристическая оценка расстояние до цели. h(x)
        start.f = start.g + start.h      // f(x) = g(x) + h(x)

        val open = ArrayList<Node>()
        open.add(start)


        while (open.size > 0) {
            val x = minF(open)
            if (x.point.point == goal.point.point){
                return reconstructPath(start, x) //заполняем карту path_map
            }

            open.remove(x)
            closed.add(x)


            val neighbors = unclosedNeighbors(x, occupiedNodes)
            neighbors.removeAll(closed.toSet())
            neighbors.forEach {
                if (!closed.contains(it)){
                    val tentativeGScore = x.g+distBetween(x, it)  // Вычисляем g(it) через x для обрабатываемого соседа
                    val tentativeIsBetter: Boolean

                    if (!open.contains(it)) {
                                open.add(it)
                                tentativeIsBetter = true
                    } else {               // Сосед был в открытом списке, а значит мы уже знаем его g(it), h(it) и f(it)
                        tentativeIsBetter = tentativeGScore < it.g
                    }
                        // Обновление свойств соседа.
                     if (tentativeIsBetter){
                            it.cameFrom  = x //Вершина с которой мы пришли. Используется для реконструкции пути.
                            it.g  = tentativeGScore
                            it.h  = heuristicCostEstimate(it.point.point, goal.point.point)
                            it.f  = it.g+it.h
                    }
                        // Обратите внимание, что если происходит обновление свойств - значит it(сосед x)
                        // так или иначе находится в openset.
                        // Т.е. при следующей итерации внешнего цикла из openset будет извлечена вершина с наименьшей оценкой f(x)
                        // Не исключено, что она окажется соседом нашего x, которого мы только что добавили.
                        // В общем это самая важная особенность алгоритма А*
                    }    
                }

        }

//        return failure //управление передаётся сюда когда openset пуст, а goal не найден (путь найти не удалось)
//
//
//        while (open.size > 0){
//            var curr = min_f(open)
//            if (curr == end) return 0
//            open.remove(curr)
//            closed.add(curr)
//            unclosed_neighbors(curr).forEach {
//                var temp_g
//                if (!open.contains(it) || temp_g < g[it]){
//                    from
//                    f[neighbor]
//                }
//                if ( !open.contains(it)) {
//                    open.add(it)
//                }
//              }
//            }
        throw Exception()

    }


    class Node(var point: ChunkAndPoint) {
        var g: Double = 0.0
        var h: Double = 0.0
        var f: Double = 0.0
        var cameFrom : Node? = null

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }
            if (other !is Node) {
                return false
            }
            return this.point.point.getX() == other.point.point.getX() && this.point.point.getY() == other.point.point.getY();
        }

    }

    private fun minF(open: ArrayList<Node>): Node {
        return open.minByOrNull { it.f }!!
    }


}