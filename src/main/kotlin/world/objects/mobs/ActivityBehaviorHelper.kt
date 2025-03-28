package world.objects.mobs

import world.objects.mobs.activityBehaviors.*

class ActivityBehaviorHelper {

    companion object{
        fun dogBehavior(): ActivityBehavior {
            val run  = RunBehavior(null)
//            val stand  = StandBehavior(walk)
            val rest  = RestBehavior(run)
            val pee  = PeeBehavior(rest)
        //    val walk  = WalkBehavior(pee)

            run.nextActivityBehavior = rest//walk
            return run
        }
    }
}