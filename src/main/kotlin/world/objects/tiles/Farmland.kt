package world.objects.tiles

import GraphicsExtender
import ImageHelper.Companion.farmland_prepared
import ImageHelper.Companion.farmland_unprepared

import ImageHelper.Companion.farmland_wheat_cutted
import ImageHelper.Companion.farmland_wheat_grown
import ImageHelper.Companion.farmland_wheat_halfgrown
import ImageHelper.Companion.farmland_wheat_seeded
import ImageHelper.Companion.farmland_wheat_sheaf
import world.ChunkAndPoint
import world.objects.IDrawableObject
import java.awt.Image
import java.awt.event.ActionListener
import javax.swing.Timer
import kotlin.random.Random

class Farmland (override var chunkAndPoint: ChunkAndPoint, override var height: Double = 1.0, override var width: Int = 1) :
    IDrawableObject {
    //    override fun draw(g: Graphics) {
//        g.drawImage(grass,x*blockWidth,frameHeight-y* blockHeight, null)
//        g.color = Color.RED
//        g.drawRect(x*Consts.blockWidth,frameHeight-y* blockHeight, blockWidth, blockHeight)
//    }
    var timer: Timer? = null
    var growingPhase = 0
    private var growingPhases = arrayOf(farmland_unprepared, farmland_prepared, farmland_wheat_seeded, farmland_wheat_halfgrown, farmland_wheat_grown,
                farmland_wheat_cutted, farmland_wheat_sheaf)
    override var occupiedBlocks: ArrayList<ChunkAndPoint> = ArrayList()

    private var img:Image = growingPhases[growingPhase][Random.nextInt(0, 4)]

    override fun draw(ge: GraphicsExtender) {
//        ge.drawImage(grass,chunkX*chunkWidth+x*blockWidth,chunkY* chunkHeight -y* blockHeight)
//        ge.g.color = Color.RED
        ge.drawBlock(this)
    }

    override fun getImage(): Image {
        return img
    }

    fun nextPhase(inc: Int = 1){
        println("growingPhase do $growingPhase")
        if (growingPhase >= growingPhases.size-2){
            growingPhase = 0
        } else {
            growingPhase+= inc
        }
        println("growingPhase posle $growingPhase")
        val rnd = Random.nextInt(0, 4)
        img = growingPhases[growingPhase][rnd]

        if (growingPhase == 2){
            startGrowing()
        }
        height = when(growingPhase){
            3 -> 1.5
            4 -> 2.0
            else -> 1.0
        }
    }

    fun startGrowing(){
        val taskPerformer = ActionListener {
            nextPhase()
            if (growingPhase == 4){
                timer?.stop()
            }
        }
        timer = Timer(Random.nextInt(5,10) * 1000, taskPerformer)

        timer?.start()
    }
}