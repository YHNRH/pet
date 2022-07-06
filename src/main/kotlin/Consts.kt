class Consts{
    companion object {
        val blockWidth = 30 //50
        val blockHeight = 15 //50
        val chunkSize = 50
        val chunkWidth = chunkSize* blockWidth
        val chunkHeight = chunkSize* blockHeight

        const val frameWidth = 1000
        const val frameHeight = 1000
        const val cameraStep = 15

        const val zoomMax = 20
        const val zoomMin = 1

        const val zoomStep = 1

        const val debugDraw = false

        const val walkSpeed = 700
        const val runSpeed = 400
    }
}
