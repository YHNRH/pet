package world

import Consts.Companion.chunkSize
import Consts.Companion.debugDraw
import GraphicsExtender
import Point
import utils.ExtendedArrayList
import world.objects.DrawableObject
import world.objects.buildings.Building
import world.objects.mobs.Mob
import world.objects.tiles.Grass
import world.objects.mobs.SimpleMob
import world.objects.tiles.Farmland
import world.objects.trees.Tree

class Chunk(val point: Point) {
    private var nonCollissionObjects = HashMap<Point, DrawableObject>()
//    private var mobs = HashMap<Point, objects.mobs.Mob>()
//    val objects = HashMap<Point, DrawableObject>()
    val objects = ExtendedArrayList<DrawableObject>()
    fun addNonCollision(obj: DrawableObject){
        if (obj.chunkAndPoint.point.getX()>= chunkSize){
            println("Выход за размеры чанка")
            return
        }
        for (x in 0 until obj.width){
            for (y in 0 until obj.height.toInt()){
                nonCollissionObjects.remove(Point(obj.chunkAndPoint.point.getX()+x,obj.chunkAndPoint.point.getY()+y))
            }
        }
        nonCollissionObjects.put(Point(obj.chunkAndPoint.point.getX(), obj.chunkAndPoint.point.getY()), obj)
    }

    fun getNoncollisionObject(key: Point): DrawableObject? {
        return nonCollissionObjects.get(key)
    }

    fun getTrees(): List<Tree> {
        return objects.filterIsInstance<Tree>()
    }

//    fun addMob(obj: objects.mobs.Mob){
//        if (obj.point.getX()>= chunkSize){
//            println("Выход за размеры чанка")
//            return
//        }
//        mobs.put(Point(obj.point.getX(),obj.point.getY()), obj)
//    }

    fun draw(ge: GraphicsExtender){
        for (x in chunkSize downTo  0){
            for (y in chunkSize downTo 0){
                val p = Point(x,y)
                nonCollissionObjects.get(p)?.draw(ge)
//                objects.getByPoint(p)?.draw(ge)
            }
        }




        // По идее, для объектов, которые занимают много блоков (например здания), надо смотреть не chunkAndPoint, а (наверное) блок из occupiedBlock с бОльшим X и меньшим Y
        objects.sortByDescending { it.occupiedBlocks.minByOrNull { it.point.getY() }?.point?.getY() }
        objects.sortByDescending { it.occupiedBlocks.maxByOrNull { it.point.getX() }?.point?.getX()  }


        objects.forEach{
            it.draw(ge)
        }
        if (debugDraw){
//            ge.g.color = Color.BLUE
//            ge.drawRect(point.getX()*chunkWidth,point.getY()* chunkHeight , chunkWidth, chunkHeight)
        }
    }

    override fun toString(): String {
        return "world.Chunk (${point.getX()}, ${point.getY()})"
    }

    companion object{
        fun grassChunk(x: Int,y: Int): Chunk {
            val c = Chunk(Point(x,y))
            for ( x in 0..chunkSize-1){
                for (y in 0..chunkSize-1){
                    if (x>=y){
                        c.addNonCollision(Grass(ChunkAndPoint(c, Point(x, y))))
                    }
                }
            }
            return c
        }
    }

    private fun addObject(obj: DrawableObject){
        objects.add(obj)
//        objects.put(obj.chunkAndPoint.point,obj)
    }

    fun addBuilding(building: Building){
        building.setOccupiedBlocks()
        addObject(building)
    }

    fun addMob(mob: Mob){
        addObject(mob)
    }
}