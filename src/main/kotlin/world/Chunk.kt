package world

import Consts.Companion.chunkSize
import Consts.Companion.debugDraw
import GraphicsExtender
import utils.ExtendedArrayList
import world.objects.IDrawableObject
import world.objects.buildings.IBuilding
import world.objects.mobs.IMob
import world.objects.tiles.Grass
import world.objects.trees.ITree

class Chunk(x: Int, y: Int) : Point(x, y) {
    private var nonCollissionObjects = HashMap<MapPoint, IDrawableObject>()
//    private var mobs = HashMap<world.Point, objects.mobs.Mob>()
//    val objects = HashMap<world.Point, DrawableObject>()
    val objects = ExtendedArrayList<IDrawableObject>()
    fun addNonCollision(obj: IDrawableObject){
        if (obj.point.getX()>= chunkSize){
            println("Выход за размеры чанка")
            return
        }
        for (x in 0 until obj.width){
            for (y in 0 until obj.height.toInt()){
                nonCollissionObjects.remove(MapPoint(obj.point.getX()+x,obj.point.getY()+y, this))
            }
        }
        nonCollissionObjects.put(MapPoint(obj.point.getX(), obj.point.getY(), this), obj)
    }

    fun getNoncollisionObject(key: MapPoint): IDrawableObject? {
        return nonCollissionObjects.get(key)
    }

    fun getTrees(): List<ITree> {
        return objects.filterIsInstance<ITree>()
    }

//    fun addMob(obj: objects.mobs.Mob){
//        if (obj.point.getX()>= chunkSize){
//            println("Выход за размеры чанка")
//            return
//        }
//        mobs.put(world.Point(obj.point.getX(),obj.point.getY()), obj)
//    }

    fun draw(ge: GraphicsExtender){
        for (x in chunkSize downTo  0){
            for (y in chunkSize downTo 0){
                val p = MapPoint(x,y, this)
                nonCollissionObjects.get(p)?.draw(ge)
//                objects.getByPoint(p)?.draw(ge)
            }
        }




        // По идее, для объектов, которые занимают много блоков (например здания), надо смотреть не chunkAndPoint, а (наверное) блок из occupiedBlock с бОльшим X и меньшим Y
        objects.sortByDescending { it.occupiedBlocks.minByOrNull { it.getY() }?.getY() }
        objects.sortByDescending { it.occupiedBlocks.maxByOrNull { it.getX() }?.getX()  }


        objects.forEach{
            it.draw(ge)
        }
        if (debugDraw){
//            ge.g.color = Color.BLUE
//            ge.drawRect(point.getX()*chunkWidth,point.getY()* chunkHeight , chunkWidth, chunkHeight)
        }
    }

    companion object{
        fun grassChunk(x: Int,y: Int): Chunk {
            val c = Chunk(x,y)
            for ( x in 0..chunkSize-1){
                for (y in 0..chunkSize-1){
                    if (x>=y){
                        c.addNonCollision(Grass(MapPoint(x, y, c)))
                    }
                }
            }
            return c
        }
    }

    private fun addObject(obj: IDrawableObject){
        objects.add(obj)
//        objects.put(obj.chunkAndPoint.point,obj)
    }

    fun addBuilding(building: IBuilding){
        building.setOccupiedBlocks()
        addObject(building)
    }

    fun addMob(mob: IMob){
        addObject(mob)
    }
}