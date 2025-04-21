package utils

import kotlin.math.pow
import kotlin.random.Random

class PerlinNoise (seed: Int, private val boundary: Int = 10) {
    private var gradientRange = 0f
    private val auxiliaryBytes: ByteArray
    private val gradientVectors: Array<FloatArray>
    private val d0: FloatArray
    private val d1: FloatArray
    private val d2: FloatArray
    private val d3: FloatArray

    init {
        gradientRange = 5.5f
        gradientVectors = arrayOf(
            floatArrayOf(gradientRange, 0f),
            floatArrayOf(-gradientRange, 0f),
            floatArrayOf(0f, gradientRange),
            floatArrayOf(0f, -gradientRange)
        )
        java.util.Random().nextBytes(ByteArray(512).also { auxiliaryBytes = it })
        d0 = FloatArray(2)
        d1 = FloatArray(2)
        d2 = FloatArray(2)
        d3 = FloatArray(2)
    }

    fun calc(x: Float, y: Float, octaves: Int, persistence: Float): Float {
        var x = x
        var y = y
        var octaves = octaves
        var amplitude = 1f
        var max = 0f
        var result = 0f
        while (octaves-- > 0) {
            max += amplitude
            result += calc(x, y) * amplitude
            amplitude *= persistence
            x *= 2f
            y *= 2f
        }
        return result / max
    }

    /*
   * Main noise function.
   * */
    private fun calc(x: Float, y: Float): Float {
        /*
     * The top left corner of the square.
     * */
        val tLX = Math.floor(x.toDouble()).toInt()
        val tLY = Math.floor(y.toDouble()).toInt()

        /*
     * The local position of the point in the current
     * square.
     * */
        var dX = x - tLX
        var dY = y - tLY

        /*
     * Calculation of vectors emanating from the vertices
     * of a square to a point.
     * */d0[0] = dX
        d0[1] = dY
        d1[0] = dX - 1
        d1[1] = dY
        d2[0] = dX
        d2[1] = dY - 1
        d3[0] = dX - 1
        d3[1] = dY - 1

        /*
     * Calculation of gradient vectors for each of
     * the vertices.
     * */
        val g0 = gradientVector(tLX, tLY)
        val g1 = gradientVector(tLX + 1, tLY)
        val g2 = gradientVector(tLX, tLY + 1)
        val g3 = gradientVector(tLX + 1, tLY + 1)

        /*
     * Dot product of gradient vectors by relative vectors.
     * */
        val q0 = dotProduct(d0, g0)
        val q1 = dotProduct(d1, g1)
        val q2 = dotProduct(d2, g2)
        val q3 = dotProduct(d3, g3)
        dX = quinticCurve(dX)
        dY = quinticCurve(dY)
        val l1 = linearInterpolation(q0, q1, dX)
        val l2 = linearInterpolation(q2, q3, dX)
        return linearInterpolation(l1, l2, dY)
    }

    private fun gradientVector(x: Int, y: Int): FloatArray {
        val hash = x * 234231123 xor y * 234231123 + 234231123
        val aux = auxiliaryBytes[hash and auxiliaryBytes.size - 1].toInt()
        return gradientVectors[aux and gradientVectors.size - 1]
    }

    private fun dotProduct(a: FloatArray, b: FloatArray): Float {
        return a[0] * b[0] + a[1] * b[1]
    }

    private fun linearInterpolation(a: Float, b: Float, t: Float): Float {
        return a + (b - a) * t
    }

    private fun quinticCurve(t: Float): Float {
        return t * t * t * (t * (t * 6 - 15) + 10)
    }
}