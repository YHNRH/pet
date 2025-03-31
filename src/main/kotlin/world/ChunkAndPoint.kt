package world

import Point

@Deprecated("Добавить chunk в класс Point")
class ChunkAndPoint(var chunk: Chunk, var point: Point){

    override fun toString(): String {
        return chunk.toString().plus(" ").plus(point.toString())
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other !is ChunkAndPoint) {
            return false
        }
        return this.point.getX() == other.point.getX() && this.point.getY() == other.point.getY() && this.chunk.point.getX() == other.chunk.point.getX() && this.chunk.point.getY() == other.chunk.point.getY()
    }

    override fun hashCode(): Int {
        var result = chunk.hashCode()
        result = 31 * result + point.hashCode()
        return result
    }
}