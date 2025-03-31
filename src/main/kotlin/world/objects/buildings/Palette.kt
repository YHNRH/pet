package world.objects.buildings

import Consts.Companion.blockHeight
import Consts.Companion.blockWidth
import GraphicsExtender
import ImageHelper.Companion.goods_wheat
import ImageHelper.Companion.palette
import Point
import world.ChunkAndPoint
import world.objects.BuildingDirection
import world.objects.DrawableObjectPart
import java.awt.Image
import java.awt.image.BufferedImage
import java.lang.Exception

class Palette(override var chunkAndPoint: ChunkAndPoint, override var width: Int = 4, override var height: Double = 4.0,
) : IBuilding {
    override var direction: BuildingDirection = BuildingDirection.LEFT
    val drawParts = ArrayList<DrawableObjectPart>()
    val goods:ArrayList<PalleteGood> = ArrayList()
    override fun draw(ge: GraphicsExtender) {
        ge.drawBuilding(this, drawParts)
    }
    override var occupiedBlocks: ArrayList<ChunkAndPoint> = ArrayList()

    init {
        goods.add(PalleteGood(Goods.WHEAT,3))
        goods.add(PalleteGood(Goods.WHEAT,3))
        goods.add(PalleteGood(Goods.WHEAT,3))
        goods.add(PalleteGood(Goods.WHEAT,3))
        refreshDrawparts()
    }

    fun updateGoods(type: Goods, number: Int){
        goods.forEach{
            if (it.type == type && it.amount < 32 ){
                it.amount.plus(number)
                refreshDrawparts()
                return
            }
        }
        if (goods.size<=4){
            goods.add(PalleteGood(type,number))
            refreshDrawparts()
            return
        }

        throw Exception("ERROR")
//        if (goods.containsKey(type)){
//            goods[type] = goods[type]!!.plus(number)
//        } else {
//            goods[type] = number
//        }
    }

    fun isFree(type: Goods, number: Int) : Boolean{
        if (number > 0){
            if (goods.size<4) return true
            goods.forEach{
                if (it.type == type && it.amount+number in 0..32) return true
            }
        }
        return false
    }

    fun refreshDrawparts(){
        drawParts.clear()
        val y_p = goods.size/2
        val x_p = goods.size-y_p
        var x = 1
        var y = 0
        for (i in 1..(x_p+y_p)){
                val imgs = HashMap<BuildingDirection, BufferedImage>()
                imgs.put(BuildingDirection.LEFT, goods_wheat[goods[i-1].amount])
                imgs.put(BuildingDirection.RIGHT, goods_wheat[goods[i-1].amount])

                val offsetX = HashMap<BuildingDirection, Int>()
                offsetX.put(BuildingDirection.LEFT, blockWidth * x)
                offsetX.put(BuildingDirection.RIGHT, 0)


                val offsetY = HashMap<BuildingDirection, Int>()
                offsetY.put(BuildingDirection.LEFT, 10 - blockHeight * y + 3*blockHeight/2)
                offsetY.put(BuildingDirection.RIGHT, 5)


                val wheat1 = DrawableObjectPart(offsetX, offsetY, imgs)
                drawParts.add(wheat1)
                if (x==2) x = 0
                else x++
                if (y==1 && x == 0)  y = 1
                else y++
            }
    }

    override fun setOccupiedBlocks() {
        for (x in (this.chunkAndPoint.point.getX())until (this.chunkAndPoint.point.getX()+width)){
            for (y in (this.chunkAndPoint.point.getY())until (this.chunkAndPoint.point.getY()+width)){
                this.occupiedBlocks.add(ChunkAndPoint(chunkAndPoint.chunk, Point(x,y)))
            }
        }
    }




    override fun getImage(): Image {
        return palette
    }


    class PalleteGood(val type:Goods, var amount: Int)
}