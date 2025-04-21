class Consts{
    companion object {
        val blockWidth = 30 //50
        const val blockHeight = 15 //50
        val chunkSize = 100
        val chunkWidth = chunkSize* blockWidth
        val chunkHeight = chunkSize* blockHeight

        const val frameWidth = 1000
        const val frameHeight = 1000
        const val cameraSpeed = 2
        const val cameraStep = blockHeight * cameraSpeed
        const val cameraYInit = -600

        const val zoomMax = 10
        const val zoomMin = 1
        const val zoomInit = 2

        const val zoomStep = 1

        const val debugDraw = true

        const val walkSpeed = 700
        const val runSpeed = 400
        val restAnimSpeed = 100//200

    }
}
