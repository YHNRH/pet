import Consts.Companion.blockHeight
import Consts.Companion.blockWidth
import Consts.Companion.chunkHeight
import Consts.Companion.chunkWidth
import Consts.Companion.debugDraw
import Consts.Companion.frameHeight
import Consts.Companion.frameWidth
import objects.Activity
import world.ChunkAndPoint
import world.objects.Direction
import world.objects.DrawableObject
import world.objects.DrawableObjectPart
import world.objects.buildings.Building
import world.objects.mobs.Mob
import world.objects.mobs.SimpleMob
import java.awt.BasicStroke
import java.awt.Color
import java.awt.Graphics2D
import java.awt.Image
import java.awt.image.BufferedImage


class GraphicsExtender(val g: Graphics2D, val camera: Camera) {

    private fun drawImage(img: Image, x: Int, y: Int,
                          width: Int? = null, height: Int? = null){
        if (width != null && height != null){
            g.drawImage(img,(x - camera.x)*camera.zoom,// + frameWidth/2,
                                        (frameHeight - y  - height + camera.y)*camera.zoom,// - frameHeight/2,
                                     width*camera.zoom,
                                    height*camera.zoom,null)
        } else {
            g.drawImage(img,(x - camera.x)*camera.zoom,// + frameWidth/2,
                (frameHeight - y  - (img as BufferedImage).height + camera.y)*camera.zoom,// - frameHeight/2,
                img.width * camera.zoom,
                img.height * camera.zoom,
                null)
        }
    }

    fun drawRect(x: Int, y: Int,
                         width: Int, height: Int){
        g.drawRect((x - camera.x)*camera.zoom + frameWidth/2,(frameHeight - y - height + camera.y)*camera.zoom - frameHeight/2,width*camera.zoom,height*camera.zoom)
    }

    fun drawString(string: String, x: Int, y: Int,
                 yOffset: Int){
        g.drawString(string,(x - camera.x)*camera.zoom ,//+ frameWidth/2,
            (frameHeight - y - yOffset + camera.y)*camera.zoom )//- frameHeight/2)
    }

    fun fillRect(x: Int, y: Int,
                 width: Int, height: Int){
        g.fillRect((x - camera.x)*camera.zoom + frameWidth/2,(frameHeight - y - height + camera.y)*camera.zoom - frameHeight/2,width*camera.zoom,height*camera.zoom)
    }


    fun drawLine(x: Int, y: Int,
                 x1: Int, y1: Int){
        g.drawLine((x - camera.x)*camera.zoom,// + frameWidth/2,
                    (frameHeight - y  + camera.y)*camera.zoom,// - frameHeight/2,
                    (x1 - camera.x)*camera.zoom ,//+ frameWidth/2,
                    (frameHeight - y1 + camera.y)*camera.zoom// - frameHeight/2
        )
    }

    fun drawPolyline(array: ArrayList<ArrayList<ChunkAndPoint>>){
        g.color = Color.GREEN
        g.stroke = BasicStroke(3f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)

        array.forEach {
            for (i in 0..it.size-2){
                drawLine(
                    (it.get(i).chunk.point.getX()*chunkWidth/2) - (it.get(i).chunk.point.getY()* chunkWidth/2 )+ it.get(i).point.getX()*blockWidth-(it.get(i).point.getY() * blockWidth/2) -( it.get(i).point.getX() * blockWidth/2)+ blockWidth/2,
                    (it.get(i).chunk.point.getX()*chunkHeight/2) + (it.get(i).chunk.point.getY()* chunkHeight/2 ) + it.get(i).point.getY()* blockHeight - (it.get(i).point.getY() * blockHeight/2) + blockHeight/2 + ( it.get(i).point.getX() * blockHeight/2) ,
                    (it.get(i+1).chunk.point.getX()*chunkWidth/2) - (it.get(i + 1).chunk.point.getY()* chunkWidth/2 )+ it.get(i + 1).point.getX()*blockWidth-(it.get(i+1).point.getY() * blockWidth/2) -( it.get(i+1).point.getX() * blockWidth/2) + blockWidth/2,
                    (it.get(i +1).chunk.point.getX()*chunkHeight/2) + (it.get(i + 1 ).chunk.point.getY()* chunkHeight/2 ) + it.get(i+1).point.getY()* blockHeight - (it.get(i+1).point.getY() * blockHeight/2) + blockHeight/2 + ( it.get(i+1).point.getX() * blockHeight/2),
                )
            }
        }
    }

    fun drawBlock( obj: DrawableObject){
        val x = obj.chunkAndPoint.point.getX()
        val y = obj.chunkAndPoint.point.getY()
        val chunkX = obj.chunkAndPoint.chunk.point.getX()
        val chunkY = obj.chunkAndPoint.chunk.point.getY()

        var drawx = -(blockWidth*obj.width)/2 + blockWidth /2 + (chunkX*chunkWidth/2) - (chunkY* chunkWidth/2 )+x*blockWidth-(y * blockWidth/2) -(x * blockWidth/2)
//        var drawy = -blockHeight* obj.width/2 + y* blockHeight - (y * blockHeight/2) + (x * blockHeight/2) + (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 ) + blockHeight/2
        var drawy = when (obj.width ) {
            1 -> -(blockHeight* obj.width/2) + y* blockHeight - (y * blockHeight/2) + (x * blockHeight/2) + (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 ) + blockHeight/2
            2 -> -(blockHeight* obj.width/2) + y* blockHeight - (y * blockHeight/2) + (x * blockHeight/2) + (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 ) + blockHeight/2
            3 -> blockWidth/2-(blockHeight* obj.width/2) + y* blockHeight - (y * blockHeight/2) + (x * blockHeight/2) + (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 ) + blockHeight/2
            4 -> blockWidth/4*3-(blockHeight* obj.width/2) + y* blockHeight - (y * blockHeight/2) + (x * blockHeight/2) + (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 ) + blockHeight/2
            6 -> blockWidth*5/4-(blockHeight* obj.width/2) + y* blockHeight - (y * blockHeight/2) + (x * blockHeight/2) + (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 ) + blockHeight/2
            8 -> 60-(blockHeight* obj.width/2) + y* blockHeight - (y * blockHeight/2) + (x * blockHeight/2) + (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 ) + blockHeight/2
            else -> -blockHeight* obj.width/2 + y* blockHeight - (y * blockHeight/2) + (x * blockHeight/2) + (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 ) + blockHeight/2
        }
//        g.color = Color.YELLOW
//        g.fillRect((drawx - camera.x)*camera.zoom + frameWidth/2,(frameHeight - drawy  - blockHeight * obj.height + camera.y)*camera.zoom - frameHeight/2,
//            blockWidth * obj.width, blockHeight * obj.height)
        drawImage(obj.getImage(), drawx,
            drawy,
            blockWidth * obj.width, (blockHeight * obj.height).toInt())


        if (debugDraw){

            //Отрисовка границ блока
            g.color = Color.RED

            drawLine((chunkX*chunkWidth/2) - (chunkY* chunkWidth/2 )+x*blockWidth-(y * blockWidth/2) -(x * blockWidth/2),
                (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 ) + y* blockHeight - (y * blockHeight/2) + blockHeight/2 + (x * blockHeight/2),
                (chunkX*chunkWidth/2) - (chunkY* chunkWidth/2 )+x*blockWidth-(y * blockWidth/2) + blockWidth/2  -(x * blockWidth/2),
                (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 ) + y* blockHeight - (y * blockHeight/2) + (x * blockHeight/2)
            )

            drawLine(
                (chunkX*chunkWidth/2) - (chunkY* chunkWidth/2 )+x*blockWidth-(y * blockWidth/2) + blockWidth/2  -(x * blockWidth/2),
                (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 ) + y* blockHeight - (y * blockHeight/2) + (x * blockHeight/2),
                (chunkX*chunkWidth/2) - (chunkY* chunkWidth/2 )+x*blockWidth-(y * blockWidth/2) + blockWidth  -(x * blockWidth/2),
                (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 ) + y* blockHeight - (y * blockHeight/2) + blockHeight/2 + (x * blockHeight/2)

            )

            drawLine(
                (chunkX*chunkWidth/2) - (chunkY* chunkWidth/2 )+x*blockWidth-(y * blockWidth/2) + blockWidth  -(x * blockWidth/2),
                (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 )+ y* blockHeight - (y * blockHeight/2) + blockHeight/2 + (x * blockHeight/2),
                (chunkX*chunkWidth/2) - (chunkY* chunkWidth/2 )+x*blockWidth-(y * blockWidth/2) + blockWidth/2  -(x * blockWidth/2),
                (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 )+ y* blockHeight - (y * blockHeight/2) + blockHeight + (x * blockHeight/2)
                )

            drawLine(
                (chunkX*chunkWidth/2) - (chunkY* chunkWidth/2 )+x*blockWidth-(y * blockWidth/2) + blockWidth/2  -(x * blockWidth/2),
                (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 )+ y* blockHeight - (y * blockHeight/2) + blockHeight + (x * blockHeight/2),
                (chunkX*chunkWidth/2) - (chunkY* chunkWidth/2 )+x*blockWidth-(y * blockWidth/2)  -(x * blockWidth/2),
                (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 ) + y* blockHeight - (y * blockHeight/2) + blockHeight/2 + (x * blockHeight/2)
            )

            g.color = Color.BLACK

            drawString("$x,$y",
                (chunkX*chunkWidth/2) - (chunkY* chunkWidth/2 )+x*blockWidth-(y * blockWidth/2) -(x * blockWidth/2) + blockWidth/4,
                (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 )+ y* blockHeight - (y * blockHeight/2)+ (x * blockHeight/2)  + blockHeight/2 ,
                1
            )

//            drawRect(chunkX*chunkWidth + x*blockWidth, chunkY* chunkHeight + y* blockHeight, blockWidth, blockHeight)
        }
    }


    fun drawBuilding( obj: Building, drawParts: ArrayList<DrawableObjectPart>? = null){
        val x = obj.chunkAndPoint.point.getX()
        val y = obj.chunkAndPoint.point.getY()
        val chunkX = obj.chunkAndPoint.chunk.point.getX()
        val chunkY = obj.chunkAndPoint.chunk.point.getY()

        var drawx = -(blockWidth*obj.width)/2 + blockWidth /2 + (chunkX*chunkWidth/2) - (chunkY* chunkWidth/2 )+x*blockWidth-(y * blockWidth/2) -(x * blockWidth/2)
//        var drawy = -blockHeight* obj.width/2 + y* blockHeight - (y * blockHeight/2) + (x * blockHeight/2) + (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 ) + blockHeight/2
        var drawy = when (obj.width ) {
            1 -> -(blockHeight* obj.width/2) + y* blockHeight - (y * blockHeight/2) + (x * blockHeight/2) + (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 ) + blockHeight/2
            2 -> -(blockHeight* obj.width/2) + y* blockHeight - (y * blockHeight/2) + (x * blockHeight/2) + (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 ) + blockHeight //  /2
            3 -> blockWidth/2-(blockHeight* obj.width/2) + y* blockHeight - (y * blockHeight/2) + (x * blockHeight/2) + (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 ) + blockHeight/2
            4 -> blockWidth/4*3-(blockHeight* obj.width/2) + y* blockHeight - (y * blockHeight/2) + (x * blockHeight/2) + (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 ) + blockHeight/2
            6 -> blockWidth*5/4-(blockHeight* obj.width/2) + y* blockHeight - (y * blockHeight/2) + (x * blockHeight/2) + (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 ) + blockHeight/2
            8 -> 60-(blockHeight* obj.width/2) + y* blockHeight - (y * blockHeight/2) + (x * blockHeight/2) + (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 ) + blockHeight/2
            else -> -blockHeight* obj.width/2 + y* blockHeight - (y * blockHeight/2) + (x * blockHeight/2) + (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 ) + blockHeight/2
        }
//        g.color = Color.YELLOW
//        g.fillRect((drawx - camera.x)*camera.zoom + frameWidth/2,(frameHeight - drawy  - blockHeight * obj.height + camera.y)*camera.zoom - frameHeight/2,
//            blockWidth * obj.width, blockHeight * obj.height)
        drawImage(obj.getImage(), drawx,
            drawy,
            blockWidth * obj.width, (blockHeight * obj.height).toInt())

        /*
        START drawParts
         */
        drawParts?.forEach {
            drawImage(it.getImage(obj.direction),drawx + it.getOffsetX(obj.direction),
                drawy + it.getOffsetY(obj.direction))
        }

        /*
        END drawParts
         */

        if (debugDraw){
            g.color = Color.RED
            obj.occupiedBlocks.forEach {
                val x = it.point.getX()
                val y = it.point.getY()
                val chunkX = it.chunk.point.getX()
                val chunkY = it.chunk.point.getY()
                drawLine((chunkX*chunkWidth/2) - (chunkY* chunkWidth/2 )+x*blockWidth-(y * blockWidth/2) -(x * blockWidth/2),
                    (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 ) + y* blockHeight - (y * blockHeight/2) + blockHeight/2 + (x * blockHeight/2),
                    (chunkX*chunkWidth/2) - (chunkY* chunkWidth/2 )+x*blockWidth-(y * blockWidth/2) + blockWidth/2  -(x * blockWidth/2),
                    (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 ) + y* blockHeight - (y * blockHeight/2) + (x * blockHeight/2)
                )

                drawLine(
                    (chunkX*chunkWidth/2) - (chunkY* chunkWidth/2 )+x*blockWidth-(y * blockWidth/2) + blockWidth/2  -(x * blockWidth/2),
                    (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 ) + y* blockHeight - (y * blockHeight/2) + (x * blockHeight/2),
                    (chunkX*chunkWidth/2) - (chunkY* chunkWidth/2 )+x*blockWidth-(y * blockWidth/2) + blockWidth  -(x * blockWidth/2),
                    (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 ) + y* blockHeight - (y * blockHeight/2) + blockHeight/2 + (x * blockHeight/2)

                )

                drawLine(
                    (chunkX*chunkWidth/2) - (chunkY* chunkWidth/2 )+x*blockWidth-(y * blockWidth/2) + blockWidth  -(x * blockWidth/2),
                    (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 )+ y* blockHeight - (y * blockHeight/2) + blockHeight/2 + (x * blockHeight/2),
                    (chunkX*chunkWidth/2) - (chunkY* chunkWidth/2 )+x*blockWidth-(y * blockWidth/2) + blockWidth/2  -(x * blockWidth/2),
                    (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 )+ y* blockHeight - (y * blockHeight/2) + blockHeight + (x * blockHeight/2)
                )

                drawLine(
                    (chunkX*chunkWidth/2) - (chunkY* chunkWidth/2 )+x*blockWidth-(y * blockWidth/2) + blockWidth/2  -(x * blockWidth/2),
                    (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 )+ y* blockHeight - (y * blockHeight/2) + blockHeight + (x * blockHeight/2),
                    (chunkX*chunkWidth/2) - (chunkY* chunkWidth/2 )+x*blockWidth-(y * blockWidth/2)  -(x * blockWidth/2),
                    (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 ) + y* blockHeight - (y * blockHeight/2) + blockHeight/2 + (x * blockHeight/2)
                )
            }


//            drawRect(chunkX*chunkWidth + x*blockWidth, chunkY* chunkHeight + y* blockHeight, blockWidth, blockHeight)
        }
    }

    fun drawTree( obj: DrawableObject){
        val x = obj.chunkAndPoint.point.getX()
        val y = obj.chunkAndPoint.point.getY()
        val chunkX = obj.chunkAndPoint.chunk.point.getX()
        val chunkY = obj.chunkAndPoint.chunk.point.getY()

        var calcx = -(blockWidth* obj.width)/2 + blockWidth + (chunkX*chunkWidth/2) - (chunkY* chunkWidth/2 )+x*blockWidth-(y * blockWidth/2) -(x * blockWidth/2)
        var calcy = -blockHeight* obj.width/2 + y* blockHeight - (y * blockHeight/2) + (x * blockHeight/2) + (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 )

//        drawImage(obj.getImage(),(chunkX*chunkWidth/2) - (chunkY* chunkWidth/2 )+x*blockWidth-(y * blockWidth/2) -(x * blockWidth/2),
////                -(x+chunkX* chunkSize),//смещение на пиксель ,
//            y* blockHeight - (y * blockHeight/2) + (x * blockHeight/2) + (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 ),
////            -(y+chunkY* chunkSize),//смещение на пиксель
//            blockWidth * obj.width, blockHeight * obj.height)

//        var calcx = (chunkX*chunkWidth/2) - (chunkY* chunkWidth/2 )+x*blockWidth-(y * blockWidth/2) -(x * blockWidth/2)
//        var calcy = blockHeight*3/2+   (x * blockHeight/2) + (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 )
//        if (obj.height != 2.0){
//            calcy+= (blockHeight * (obj.height/2) - blockHeight*6).toInt()
//        }
//        if (obj.width != 1){
//            calcx -= blockWidth/2 * (obj.width/2) + blockWidth/2
//        }
        drawImage(obj.getImage(), calcx,
            calcy,
            blockWidth * obj.width, (blockHeight * obj.height).toInt())



        if (debugDraw){
            g.color = Color.RED
            obj.occupiedBlocks.forEach {
                val x = it.point.getX()
                val y = it.point.getY()
                val chunkX = it.chunk.point.getX()
                val chunkY = it.chunk.point.getY()
                drawLine(
                    (chunkX * chunkWidth / 2) - (chunkY * chunkWidth / 2) + x * blockWidth - (y * blockWidth / 2) - (x * blockWidth / 2),
                    (chunkX * chunkHeight / 2) + (chunkY * chunkHeight / 2) + y * blockHeight - (y * blockHeight / 2) + blockHeight / 2 + (x * blockHeight / 2),
                    (chunkX * chunkWidth / 2) - (chunkY * chunkWidth / 2) + x * blockWidth - (y * blockWidth / 2) + blockWidth / 2 - (x * blockWidth / 2),
                    (chunkX * chunkHeight / 2) + (chunkY * chunkHeight / 2) + y * blockHeight - (y * blockHeight / 2) + (x * blockHeight / 2)
                )

                drawLine(
                    (chunkX * chunkWidth / 2) - (chunkY * chunkWidth / 2) + x * blockWidth - (y * blockWidth / 2) + blockWidth / 2 - (x * blockWidth / 2),
                    (chunkX * chunkHeight / 2) + (chunkY * chunkHeight / 2) + y * blockHeight - (y * blockHeight / 2) + (x * blockHeight / 2),
                    (chunkX * chunkWidth / 2) - (chunkY * chunkWidth / 2) + x * blockWidth - (y * blockWidth / 2) + blockWidth - (x * blockWidth / 2),
                    (chunkX * chunkHeight / 2) + (chunkY * chunkHeight / 2) + y * blockHeight - (y * blockHeight / 2) + blockHeight / 2 + (x * blockHeight / 2)

                )

                drawLine(
                    (chunkX * chunkWidth / 2) - (chunkY * chunkWidth / 2) + x * blockWidth - (y * blockWidth / 2) + blockWidth - (x * blockWidth / 2),
                    (chunkX * chunkHeight / 2) + (chunkY * chunkHeight / 2) + y * blockHeight - (y * blockHeight / 2) + blockHeight / 2 + (x * blockHeight / 2),
                    (chunkX * chunkWidth / 2) - (chunkY * chunkWidth / 2) + x * blockWidth - (y * blockWidth / 2) + blockWidth / 2 - (x * blockWidth / 2),
                    (chunkX * chunkHeight / 2) + (chunkY * chunkHeight / 2) + y * blockHeight - (y * blockHeight / 2) + blockHeight + (x * blockHeight / 2)
                )

                drawLine(
                    (chunkX * chunkWidth / 2) - (chunkY * chunkWidth / 2) + x * blockWidth - (y * blockWidth / 2) + blockWidth / 2 - (x * blockWidth / 2),
                    (chunkX * chunkHeight / 2) + (chunkY * chunkHeight / 2) + y * blockHeight - (y * blockHeight / 2) + blockHeight + (x * blockHeight / 2),
                    (chunkX * chunkWidth / 2) - (chunkY * chunkWidth / 2) + x * blockWidth - (y * blockWidth / 2) - (x * blockWidth / 2),
                    (chunkX * chunkHeight / 2) + (chunkY * chunkHeight / 2) + y * blockHeight - (y * blockHeight / 2) + blockHeight / 2 + (x * blockHeight / 2)
                )
            }
//            drawRect(chunkX*chunkWidth + x*blockWidth, chunkY* chunkHeight + y* blockHeight, blockWidth, blockHeight)
        }
    }


    fun drawMob(mob: Mob){
        val chunkX = mob.chunkAndPoint.chunk.point.getX()
        val chunkY = mob.chunkAndPoint.chunk.point.getY()
        val x = mob.chunkAndPoint.point.getX()
        val y = mob.chunkAndPoint.point.getY()
        if (debugDraw){
            g.color = Color.BLACK
            drawString(mob.chunkAndPoint.toString()
                .plus("Occupied blocks ${mob.occupiedBlocks.first().point} - ${mob.occupiedBlocks.last().point}"),
                mob.chunkAndPoint.chunk.point.getX()*chunkWidth + mob.chunkAndPoint.point.getX()*blockWidth - (blockWidth*mob.width)/2 , (mob.chunkAndPoint.chunk.point.getY()* chunkHeight + mob.chunkAndPoint.point.getY()* blockHeight - (blockHeight*mob.height)/2).toInt(), (blockHeight*mob.height).toInt())
            drawLine((chunkX*chunkWidth/2) - (chunkY* chunkWidth/2 )+x*blockWidth-(y * blockWidth/2) -(x * blockWidth/2),
                (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 ) + y* blockHeight - (y * blockHeight/2) + blockHeight/2 + (x * blockHeight/2),
                (chunkX*chunkWidth/2) - (chunkY* chunkWidth/2 )+x*blockWidth-(y * blockWidth/2) + blockWidth/2  -(x * blockWidth/2),
                (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 ) + y* blockHeight - (y * blockHeight/2) + (x * blockHeight/2)
            )

            drawLine(
                (chunkX*chunkWidth/2) - (chunkY* chunkWidth/2 )+x*blockWidth-(y * blockWidth/2) + blockWidth/2  -(x * blockWidth/2),
                (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 ) + y* blockHeight - (y * blockHeight/2) + (x * blockHeight/2),
                (chunkX*chunkWidth/2) - (chunkY* chunkWidth/2 )+x*blockWidth-(y * blockWidth/2) + blockWidth  -(x * blockWidth/2),
                (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 ) + y* blockHeight - (y * blockHeight/2) + blockHeight/2 + (x * blockHeight/2)

            )

            drawLine(
                (chunkX*chunkWidth/2) - (chunkY* chunkWidth/2 )+x*blockWidth-(y * blockWidth/2) + blockWidth  -(x * blockWidth/2),
                (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 )+ y* blockHeight - (y * blockHeight/2) + blockHeight/2 + (x * blockHeight/2),
                (chunkX*chunkWidth/2) - (chunkY* chunkWidth/2 )+x*blockWidth-(y * blockWidth/2) + blockWidth/2  -(x * blockWidth/2),
                (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 )+ y* blockHeight - (y * blockHeight/2) + blockHeight + (x * blockHeight/2)
            )

            drawLine(
                (chunkX*chunkWidth/2) - (chunkY* chunkWidth/2 )+x*blockWidth-(y * blockWidth/2) + blockWidth/2  -(x * blockWidth/2),
                (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 )+ y* blockHeight - (y * blockHeight/2) + blockHeight + (x * blockHeight/2),
                (chunkX*chunkWidth/2) - (chunkY* chunkWidth/2 )+x*blockWidth-(y * blockWidth/2)  -(x * blockWidth/2),
                (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 ) + y* blockHeight - (y * blockHeight/2) + blockHeight/2 + (x * blockHeight/2)
            )

        }
//        drawImage(mob.getImage(),mob.chunkAndPoint.chunk.point.getX()*chunkWidth + mob.chunkAndPoint.point.getX()*blockWidth - (blockWidth*mob.width)/2, mob.chunkAndPoint.chunk.point.getY()* chunkHeight + mob.chunkAndPoint.point.getY()* blockHeight - (blockHeight*mob.height)/2, blockWidth*mob.width, blockHeight*mob.height)

        var drawx = -(blockWidth*mob.width)/2 + blockWidth /2 + (chunkX*chunkWidth/2) - (chunkY* chunkWidth/2 )+x*blockWidth-(y * blockWidth/2) -(x * blockWidth/2)
        var drawy = -blockHeight* mob.width/2 + y* blockHeight - (y * blockHeight/2) + (x * blockHeight/2) + (chunkX*chunkHeight/2) + (chunkY* chunkHeight/2 )
        if (mob.activity in setOf(Activity.RUN, Activity.WALK, Activity.SOW, Activity.BEAR_WHEAT) ){
            when(mob.direction){
                Direction.TOP -> drawy+= blockHeight*mob.step/mob.animSizes[mob.activity]!!
                Direction.RIGHT_TOP -> {
                    drawx+= blockWidth*mob.step/mob.animSizes.get(mob.activity)!!/2
                    drawy+= blockHeight*mob.step/mob.animSizes.get(mob.activity)!!/2
                }
                Direction.RIGHT -> drawx+= blockWidth*mob.step/mob.animSizes.get(mob.activity)!!
                Direction.RIGHT_BOTTOM -> {
                    drawx+= blockWidth*mob.step/mob.animSizes.get(mob.activity)!!/2
                    drawy-= blockHeight*mob.step/mob.animSizes.get(mob.activity)!!/2
                }
                Direction.BOTTOM -> drawy-= blockHeight*mob.step/mob.animSizes.get(mob.activity)!!
                Direction.LEFT_BOTTOM -> {
                    drawx-= blockWidth*mob.step/mob.animSizes.get(mob.activity)!!/2
                    drawy-= blockHeight*mob.step/mob.animSizes.get(mob.activity)!!/2
                }
                Direction.LEFT -> drawx-= blockWidth*mob.step/mob.animSizes.get(mob.activity)!!
                Direction.LEFT_TOP -> {
                    drawx-= blockWidth*mob.step/mob.animSizes.get(mob.activity)!!/2
                    drawy+= blockHeight*mob.step/mob.animSizes.get(mob.activity)!!/2
                }
            }
        }


        drawImage(mob.getImage(),
            drawx,
            drawy,
            blockWidth*mob.width, (blockHeight*mob.height).toInt())
    }


}