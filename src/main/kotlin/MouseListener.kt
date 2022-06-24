import java.awt.event.MouseEvent

class MouseListener(val drawer:Drawer) : java.awt.event.MouseListener {

    override fun mouseClicked(e: MouseEvent?) {
        if (e != null) {
            drawer.handleMouseClick(e.x,e.y)
        }
    }

    override fun mousePressed(e: MouseEvent?) {
//        TODO("Not yet implemented")
    }

    override fun mouseReleased(e: MouseEvent?) {
//        TODO("Not yet implemented")
    }

    override fun mouseEntered(e: MouseEvent?) {
        println(e)
    }

    override fun mouseExited(e: MouseEvent?) {
//        TODO("Not yet implemented")
    }
}