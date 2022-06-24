package world

import Point

class Chunks {
    val chunks = HashMap<Point, Chunk>()

    private constructor()

    companion object{
        private var instance: Chunks? = null
        fun instance(): Chunks {
            if (instance == null){
                this.instance = Chunks()
            }
            return instance!!
        }
    }
}