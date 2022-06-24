class Point(private val x: Int, private val y: Int) {


    fun getX(): Int {
        return x
    }

    fun getY(): Int {
        return y
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other !is Point) {
            return false
        }
        return this.x == other.x && this.y == other.y;
    }

    override fun hashCode(): Int {
        return x + 31 * y
    }

    override fun toString(): String {
        return "($x, $y)"
    }
}