import world.objects.buildings.Building

class BuilderHelper {

    var obj: Building? = null
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