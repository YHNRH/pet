package world

import Consts.Companion.chunkSize
import Consts.Companion.debugDraw
import GraphicsExtender
import Point
import world.objects.DrawableObject
import world.objects.buildings.Building
import world.objects.mobs.Mob
import world.objects.tiles.Grass
import world.objects.mobs.SimpleMob

class Chunk(val point: Point) {
    private var nonCollissionObjects = HashMap<Point, DrawableObject>()
//    private var mobs = HashMap<Point, objects.mobs.Mob>()
//    val objects = HashMap<Point, DrawableObject>()
    val objects = ArrayList<DrawableObject>()
    fun addNonCollision(obj: DrawableObject){
        if (obj.chunkAndPoint.point.getX()>= chunkSize){
            println("Выход за размеры чанка")
            return
        }
        nonCollissionObjects.put(Point(obj.chunkAndPoint.point.getX(), obj.chunkAndPoint.point.getY()), obj)
    }

    fun getNoncollisionObject(key: Point): DrawableObject? {
        return nonCollissionObjects.get(key)
    }

//    fun addMob(obj: objects.mobs.Mob){
//        if (obj.point.getX()>= chunkSize){
//            println("Выход за размеры чанка")
//            return
//        }
//        mobs.put(Point(obj.point.getX(),obj.point.getY()), obj)
//    }

    fun draw(ge: GraphicsExtender){
        nonCollissionObjects.forEach{
            it.value.draw(ge)
        }
        objects.sortByDescending { it.chunkAndPoint.point.getY()  }
        objects.sortByDescending { it.chunkAndPoint.point.getX()  }
        objects.forEach{
            it.draw(ge)
        }
//        println()

//            .forEach{
//            it.value.draw(ge)
//        }
//        mobs.forEach{
//            it.value.drawByChunkPosition(ge,point.getX(), point.getY())
//        }
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
                        c.addNonCollision(Grass(SimpleMob.ChunkAndPoint(c, Point(x, y))))
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