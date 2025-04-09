import Consts.Companion.timeScale

class DayNightTimer {

    private var isPaused = false
    private var rawTime: Float = 0f // В секундах
    private var lastUpdate = 0L
    val secondsPerHour = 60f

    companion object {
        private var dayTimer: DayNightTimer? = null

        fun instance(): DayNightTimer{
            return if (dayTimer == null){
                dayTimer = DayNightTimer()
                dayTimer as DayNightTimer
            } else {
                dayTimer as DayNightTimer
            }
        }
    }

    val currentTime: String
        get() {
            val totalHours = rawTime / secondsPerHour
            val hours = (totalHours % 24).toInt()
            val minutes = ((totalHours % 1) * 60).toInt()
            return "%02d:%02d".format(hours, minutes)
        }

    fun update() {
        if (isPaused) return

        val now = System.currentTimeMillis()
        if (lastUpdate == 0L)
            lastUpdate = now
        val delta = (now - lastUpdate) / 1000f
        rawTime += delta * timeScale
        lastUpdate = now
    }

    fun getDayProgress(): Float {
        return (rawTime % (24 * secondsPerHour)) / (24 * secondsPerHour)
    }

    fun togglePause() {
        isPaused = !isPaused
        if (!isPaused) lastUpdate = System.currentTimeMillis()
    }
}