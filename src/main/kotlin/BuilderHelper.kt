import world.MapPoint
import world.objects.BuildingDirection
import world.objects.buildings.AdditionalBuilding
import world.objects.buildings.IBuilding

class BuilderHelper {

    private var obj: IBuilding? = null
    private var additional: ArrayList<AdditionalBuilding>? = null

    fun getObj(): IBuilding? {
        return obj
    }

    fun getAdditional(): ArrayList<AdditionalBuilding>? {
        return additional
    }

    fun setBuildingPoint(point: MapPoint){
        this.obj?.point = point
    }

    fun setBuildingDirection(direction: BuildingDirection){
        this.obj?.direction = direction
    }

    fun setBuilding(obj: IBuilding, additional: ArrayList<AdditionalBuilding>? = null){
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