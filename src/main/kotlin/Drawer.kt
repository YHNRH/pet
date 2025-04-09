import Consts.Companion.blockHeight
import Consts.Companion.blockWidth
import Consts.Companion.chunkSize
import Consts.Companion.frameHeight
import Consts.Companion.frameWidth
import Consts.Companion.zoomMin
import ImageHelper.Companion.bottombar_apple
import ImageHelper.Companion.bottombar_hook
import ImageHelper.Companion.bottombar_house
import ImageHelper.Companion.bottombar_hummer
import ImageHelper.Companion.bottombar_shield
import ImageHelper.Companion.bottombar_tower
import ImageHelper.Companion.edge
import ImageHelper.Companion.sidebar_back
import ImageHelper.Companion.sidebar_exit
import ImageHelper.Companion.sidebar_info
import ImageHelper.Companion.sidebar_key
import ImageHelper.Companion.toolbar
import ImageHelper.Companion.toolbar_applefarm
import ImageHelper.Companion.toolbar_castle
import ImageHelper.Companion.toolbar_cattlefarm
import ImageHelper.Companion.toolbar_hopsfarm
import ImageHelper.Companion.toolbar_palette
import ImageHelper.Companion.toolbar_wheatfarm
import ImageHelper.Companion.toolbar_woodcutter_hut
import ImageHelper.Companion.treasurer_face_0
import ImageHelper.Companion.treasurer_right
import managers.CastleManager
import managers.FarmManager
import toolbar.ToolbarButton
import java.awt.geom.Area
import world.Chunk
import world.ChunkAndPoint
import world.Chunks
import world.objects.buildings.AppleFarm
import world.objects.buildings.Campfire
import world.objects.mobs.*
import world.objects.trees.Apple
import world.objects.trees.Chestnut
import java.awt.*
import java.awt.event.ActionListener
import java.awt.event.MouseEvent
import java.awt.event.MouseMotionListener
import javax.swing.JComponent
import javax.swing.SwingUtilities
import javax.swing.Timer
import kotlin.math.abs
import kotlin.math.sin


class Drawer : JComponent(), MouseMotionListener {
    var camera: Camera = Camera.instance()
    private val dayTimer  = DayNightTimer.instance()
    var appState: AppState = AppState.WALK
    companion object {
        private var drawer: Drawer? = null

        fun instance(): Drawer{
            return if (drawer == null){
                drawer = Drawer()
                drawer as Drawer
            } else {
                drawer as Drawer
            }
        }
    }
    init {
        //camera.x = 0// frameWidth/2 + blockWidth/2
        //camera.y = 0//frameHeight/2 //250
        //camera.setZoom(zoomMin)
        val castleManager = CastleManager()
        val testChunk = Chunk.grassChunk(0,0)
        val testMob = Woodcutter(ChunkAndPoint(testChunk,Point(0,0)))
        val dog = Dog(ChunkAndPoint(testChunk,Point(6,1)))
        val campfire = Campfire(ChunkAndPoint(testChunk,Point(9,3)))
        val apple = Apple(ChunkAndPoint(testChunk, Point(12, 3)))
        val chestnut = Chestnut(ChunkAndPoint(testChunk,Point(25,5)))
        val appleFarm = AppleFarm(ChunkAndPoint(testChunk,Point(12,8)))
//        val secondMob = SimpleMob(SimpleMob.ChunkAndPoint(testChunk,Point(3,5)),3 , 3)
//        val thirdMob = objects.mobs.SimpleMob(objects.mobs.SimpleMob.ChunkAndPoint(testChunk,Point(4,5)), Point(0,0))
//        val fourthMob = objects.mobs.SimpleMob(objects.mobs.SimpleMob.ChunkAndPoint(testChunk,Point(5,4)), Point(0,0))
//        val a1 = objects.mobs.SimpleMob(objects.mobs.SimpleMob.ChunkAndPoint(testChunk,Point(5,3)), Point(0,0))
//        val a2 = objects.mobs.SimpleMob(objects.mobs.SimpleMob.ChunkAndPoint(testChunk,Point(5,2)), Point(0,0))
//        val a3 = objects.mobs.SimpleMob(objects.mobs.SimpleMob.ChunkAndPoint(testChunk,Point(5,1)), Point(0,0))
//        val a4 = objects.mobs.SimpleMob(objects.mobs.SimpleMob.ChunkAndPoint(testChunk,Point(5,0)), Point(0,0))
//        val a5 = objects.mobs.SimpleMob(objects.mobs.SimpleMob.ChunkAndPoint(testChunk,Point(5,5)), Point(0,0))
//        val a6 = objects.mobs.SimpleMob(objects.mobs.SimpleMob.ChunkAndPoint(testChunk,Point(0,5)), Point(0,0))
//        val a7 = objects.mobs.SimpleMob(objects.mobs.SimpleMob.ChunkAndPoint(testChunk,Point(1,5)), Point(0,0))
//        addMob(secondMob)
//        addMob(thirdMob)
//        addMob(fourthMob)
//        addMob(a1)
//        addMob(a2)
//        addMob(a3)
//        addMob(a4)
//        addMob(a5)
//        addMob(a6)
//        addMob(a7)

//        addMob(a1)

        addChunk(testChunk)

        testChunk.addMob(testMob)
        testChunk.addMob(dog)
        dog.behavior?.performActivity(dog)
        testChunk.addBuilding(campfire)
        testChunk.addBuilding(apple)
        testChunk.addBuilding(chestnut)
        testChunk.addBuilding(appleFarm)

//        addChunk(world.Chunk.grassChunk(1,0))
//        addChunk(world.Chunk.grassChunk(2,0))
//        addChunk(world.Chunk.grassChunk(3,0))
////
////        addChunk(world.Chunk.grassChunk(0,1))
//        addChunk(world.Chunk.grassChunk(1,1))
//        addChunk(world.Chunk.grassChunk(2,1))
//        addChunk(world.Chunk.grassChunk(3,1))
//
////        addChunk(world.Chunk.grassChunk(0,2))
////        addChunk(world.Chunk.grassChunk(1,2))
//        addChunk(world.Chunk.grassChunk(2,2))
//        addChunk(world.Chunk.grassChunk(3,2))
        SelectionHandler.selectedMobs.add(testMob)
//        SelectionHandler.selectedMobs.add(dog)

        Chunks.instance().chunks.forEach{
            it.value.objects.forEach{
                if (it is Mob){
//                    it.behavior?.performActivity(it)
                }
            }
        }
//        SelectionHandler.selectedMobs.add(dog)
        SwingUtilities.invokeLater {
            add(ToolbarButton(this, bottombar_tower, 133, frameHeight-35, 30, 35))
            add(ToolbarButton(this, bottombar_hummer, 169, frameHeight-35, 30, 35))
            add(ToolbarButton(this, bottombar_apple, 207, frameHeight-35, 30, 35))
            add(ToolbarButton(this, bottombar_house, 242, frameHeight-35, 30, 35))
            add(ToolbarButton(this, bottombar_shield, 280, frameHeight-35, 30, 35))
            add(ToolbarButton(this, bottombar_hook, 317, frameHeight-35, 30, 35))
            add(ToolbarButton(this, sidebar_back, 625, frameHeight-32, 29, 22))
            add(ToolbarButton(this, sidebar_exit, 625, frameHeight - 26 - 32, 29, 21))
            add(ToolbarButton(this, sidebar_info, 625, frameHeight - 60 - 30, 29, 28))
            add(ToolbarButton(this, sidebar_key, 625, frameHeight-100-28, 29, 36))

            val btns = arrayOf(toolbar_woodcutter_hut, toolbar_applefarm, toolbar_wheatfarm, toolbar_cattlefarm, toolbar_hopsfarm, toolbar_castle, toolbar_palette)
            for (i in btns.indices){
                val btn = ToolbarButton(this, btns[i], 140 + (i*100), frameHeight-45 - 61, 76, 61)
                add(btn)
            }
            addMouseMotionListener(this)
            addMouseListener(MouseListener(this, castleManager))



            val delay = 16 //milliseconds

            val taskPerformer = ActionListener {
                repaint()
            }
            Timer(delay, taskPerformer).start()
           }
    }

    override fun mouseDragged(e: MouseEvent) {
//        val g = graphics
//        g.color = Color.BLUE
//        g.fillOval(e.getX(), e.getY(), 20, 20)
    }

    override fun mouseMoved(e: MouseEvent?) {
        if (e != null) {
            if (appState == AppState.BUILD) {
                val point = getPointByMouseXY(e.x, e.y)
                val chunkPoint = Point(0,0)//mockup
                val chunk = Chunks.instance().chunks[chunkPoint]!!
                BuilderHelper.getInstance().setBuildingChunkAndPoint(
                    ChunkAndPoint(chunk, point)
                )
                BuilderHelper.getInstance().getAdditional()?.forEach {
                    val offset = it.offset[BuilderHelper.getInstance().getObj()!!.direction]!!
                    it.obj.chunkAndPoint = ChunkAndPoint(
                        chunk,
                        Point(point.getX() + offset.getX(), point.getY() + offset.getY())
                    )
                }
                //println(SimpleMob.ChunkAndPoint(Chunk(Point(chunkX, chunkY)), Point(pointX, pointY)))
            }
        }
    }


    //frameWidth/2, frameHeight/2,0)

    override fun getPreferredSize(): Dimension {
        return Dimension(frameWidth, frameHeight)
    }


    override fun paintComponent(g: Graphics) {
        val ge = GraphicsExtender(g as Graphics2D, camera)
//        ge.drawRect(camera.x - frameWidth/2, camera.y + frameHeight/2, 10,10)

        dayTimer.update()
        Chunks.instance().chunks.forEach{
            it.value.draw(ge)
        }

        if (appState == AppState.BUILD){
            BuilderHelper.getInstance().getAdditional()?.forEach {
                it.obj.draw(ge)
            }
            BuilderHelper.getInstance().getObj()?.draw(ge)
        }
        drawTimeVisuals(ge)
        drawToolbar(g)
//        g.dispose()
//        if (Consts.debugDraw){
//            ge.drawLine(camera.x-10, camera.y, camera.x+10, camera.y) //??
//            ge.drawLine(camera.x, camera.y-10, camera.x, camera.y+10) //??
//        }
        super.paintComponent(g)
    }

    private fun drawTimeVisuals(ge: GraphicsExtender){

        val progress = dayTimer.getDayProgress()
        val lightIntensity = abs(sin(progress * Math.PI.toFloat())) // Синусоида для плавности
        val alpha = 1f - lightIntensity // Ночь темнее
        ge.g.color = Color(0f, 0f, 0f, alpha * 0.7f) // Затемнение

        // Вырезаем овал из клипа
        val area = Area(Rectangle(0, 0, frameWidth, frameHeight))
        //area.subtract(Area(Ellipse2D.Double(100.0, 100.0, 100.0, 100.0)))

        Chunks.instance().chunks.forEach{
            it.value.getLightsShapes(ge).forEach {
                area.subtract(it)
            }
        }

        ge.g.clip = area

        ge.g.fillRect(0, 0, frameHeight, frameHeight)
        ge.g.clip = null

        Chunks.instance().chunks.forEach{
        //    it.value.drawLights(ge)
        }

        ge.g.color = Color(1f, 1f, 1f, 1f) // Сброс цвета
        ge.g.drawString(dayTimer.currentTime, 10, 30)
    }
    fun drawToolbar(g: Graphics){
        g.run {
            drawImage(edge, 0, frameHeight-128, 112, 128, null)
            drawImage(toolbar, 112, frameHeight-150, 800, 150, null)
            drawImage(treasurer_face_0, 1040-96 -128, frameHeight-150-50, 110, 110, null)
            drawImage(treasurer_right, 1040 - 128, frameHeight-200, 112, 200, null)
//            //        g.drawImage(edge, 1152, frameHeight-128, 112, 128, null)
//
//            drawImage(bottombar_tower[0], 133, frameHeight-35, 30, 35, null)
//            drawImage(bottombar_hummer[0], 169, frameHeight-35, 30, 35, null)
//            drawImage(bottombar_apple[0], 207, frameHeight-35, 30, 35, null)
//            drawImage(bottombar_house[0], 242, frameHeight-35, 30, 35, null)
//            drawImage(bottombar_shield[0], 280, frameHeight-35, 30, 35, null)
//            drawImage(bottombar_hook[0], 317, frameHeight-35, 30, 35, null)
//
//            drawImage(sidebar_back[0], 625, frameHeight-32, 29, 22, null)
//            drawImage(sidebar_exit[0], 625, frameHeight - 26 - 32, 29, 21, null)
//            drawImage(sidebar_info[0], 625, frameHeight - 60 - 30, 29, 28, null)
//            drawImage(sidebar_key[0], 625, frameHeight-100-28, 29, 36, null)
        }
//
//        for (i in 0..4){
//            g.drawImage(toolbar_applefarm[0], 140 + (i*100), frameHeight-45 - 61, 76, 61, null)
//        }

    }

    fun addChunk(c: Chunk){
        Chunks.instance().chunks[Point(c.point.getX(),c.point.getY())] = c
    }



    fun handleMouseClick(x:Int, y:Int){
//        println(y)
//        println("Camera y ".plus(camera.y))

//        chunkX*chunkWidth+x*blockWidth-(y * blockWidth/2),chunkY* chunkHeight + y* blockHeight - (y * blockHeight/2)


//        val calcX = (x + camera.x - frameWidth/2)/ blockWidth
//        val calcY = (y - camera.y - frameHeight/2) / -blockHeight

    }

    fun getPointByMouseXY(x: Int,y: Int): Point{
        var calcY = -((y / camera.zoom - camera.y  - frameHeight))  // Вычитаем FrameHeight для того, чтобы инвертировать y
        val calcX = ((x + camera.x * camera.zoom ) - blockWidth*camera.zoom/2) / camera.zoom
        val myCoordSysX = calcX + calcY*(blockWidth/ blockHeight)
        val myCoordSysY =  -(calcX/2 - calcY)
        val chunkX = if (calcX / chunkSize < 0) 0 else myCoordSysX/ (blockWidth*camera.zoom) / chunkSize
        val pointX = if (calcX % chunkSize < 0) 0 else myCoordSysX/ (blockWidth) % chunkSize
        val chunkY = if (calcY / chunkSize < 0) 0 else myCoordSysY/ (blockHeight*camera.zoom) / chunkSize
        val pointY = if (calcY % chunkSize < 0) 0 else myCoordSysY/ (blockHeight) % chunkSize

        return Point(pointX, pointY)
    }

    enum class AppState {
        BUILD, WALK
    }
}
