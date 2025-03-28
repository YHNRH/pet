import world.ChunkAndPoint
import world.objects.BuildingDirection
import world.objects.buildings.AdditionalBuilding
import world.objects.buildings.Building
import world.objects.mobs.SimpleMob

class BuilderHelper {

    private var obj: Building? = null
    private var additional: ArrayList<AdditionalBuilding>? = null

    fun getObj(): Building? {
        return obj
    }

    fun getAdditional(): ArrayList<AdditionalBuilding>? {
        return additional
    }

    fun setBuildingChunkAndPoint(cap: ChunkAndPoint){
        this.obj?.chunkAndPoint = cap
    }

    fun setBuildingDirection(direction: BuildingDirection){
        this.obj?.direction = direction
    }

    fun setBuilding(obj: Building, additional: ArrayList<AdditionalBuilding>? = null){
        this.obj = obj
        this.additional = additional
    }
    companion object{
        private var instance: BuilderHelper? = null
        fun getInstance(): BuilderHelper {
            if (instance == null){
                instance = BuilderHelper()
            }
            return instance!!
        }
    }
}