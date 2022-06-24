import java.awt.image.BufferedImage
import javax.imageio.ImageIO

class ImageHelper {
    companion object {
        val grass_tiles: Array<BufferedImage> = Array(8) {
            ImageIO.read(javaClass.getResource("tiles/grass/$it.png"))
        }

        val campfire: Array<BufferedImage> = Array(18) {
            ImageIO.read(javaClass.getResource("buildings/campfire/$it.png"))
        }

        val knight_standing_bottom: BufferedImage = ImageIO.read(javaClass.getResource("knight/standing/bottom.png"))
        val knight_standing_top: BufferedImage = ImageIO.read(javaClass.getResource("knight/standing/top.png"))
        val knight_standing_left: BufferedImage = ImageIO.read(javaClass.getResource("knight/standing/left.png"))
        val knight_standing_right: BufferedImage = ImageIO.read(javaClass.getResource("knight/standing/right.png"))
        val knight_standing_right_top: BufferedImage = ImageIO.read(javaClass.getResource("knight/standing/right_top.png"))
        val knight_standing_right_bottom: BufferedImage = ImageIO.read(javaClass.getResource("knight/standing/right_bottom.png"))
        val knight_standing_left_top: BufferedImage = ImageIO.read(javaClass.getResource("knight/standing/left_top.png"))
        val knight_standing_left_bottom: BufferedImage = ImageIO.read(javaClass.getResource("knight/standing/left_bottom.png"))

        val knight_move_bottom: Array<BufferedImage> = Array(4) {
            ImageIO.read(javaClass.getResource("knight/move/bottom_$it.png"))
        }
        val knight_move_top: Array<BufferedImage> = Array(4) {

            ImageIO.read(javaClass.getResource("knight/move/top_$it.png"))
        }
        val knight_move_left: Array<BufferedImage> = Array(4) {
            ImageIO.read(javaClass.getResource("knight/move/left_$it.png"))
        }
        val knight_move_right: Array<BufferedImage> = Array(4) {
            ImageIO.read(javaClass.getResource("knight/move/right_$it.png"))
        }
        val knight_move_right_top: Array<BufferedImage> = Array(4) {
            ImageIO.read(javaClass.getResource("knight/move/right_top_$it.png"))
        }
        val knight_move_right_bottom: Array<BufferedImage> = Array(4) {
            ImageIO.read(javaClass.getResource("knight/move/right_bottom_$it.png"))
        }
        val knight_move_left_top: Array<BufferedImage> = Array(4) {
            ImageIO.read(javaClass.getResource("knight/move/left_top_$it.png"))
        }
        val knight_move_left_bottom: Array<BufferedImage> = Array(4) {
            ImageIO.read(javaClass.getResource("knight/move/left_bottom_$it.png"))
        }



        val archer_walk_bottom: Array<BufferedImage> = Array(8) {
            ImageIO.read(javaClass.getResource("archer/bottom/walk/${it+1}.png"))
        }
        val archer_walk_top: Array<BufferedImage> = Array(8) {

            ImageIO.read(javaClass.getResource("archer/top/walk/${it+1}.png"))
        }
        val archer_walk_left: Array<BufferedImage> = Array(8) {
            ImageIO.read(javaClass.getResource("archer/left/walk/${it+1}.png"))
        }
        val archer_walk_right: Array<BufferedImage> = Array(8) {
            ImageIO.read(javaClass.getResource("archer/right/walk/${it+1}.png"))
        }
        val archer_walk_right_top: Array<BufferedImage> = Array(8) {
            ImageIO.read(javaClass.getResource("archer/right_top/walk/${it+1}.png"))
        }
        val archer_walk_right_bottom: Array<BufferedImage> = Array(8) {
            ImageIO.read(javaClass.getResource("archer/right_bottom/walk/${it+1}.png"))
        }
        val archer_walk_left_top: Array<BufferedImage> = Array(8) {
            ImageIO.read(javaClass.getResource("archer/left_top/walk/${it+1}.png"))
        }
        val archer_walk_left_bottom: Array<BufferedImage> = Array(8) {
            ImageIO.read(javaClass.getResource("archer/left_bottom/walk/${it+1}.png"))
        }


        val archer_rest_0: Array<BufferedImage> = Array(16) {
            ImageIO.read(javaClass.getResource("archer/rest/rest_0/${it+1}.png"))
        }


        val dog_walk_bottom: Array<BufferedImage> = Array(16) {
            ImageIO.read(javaClass.getResource("dog/bottom/walk/${it+1}.png"))
        }
        val dog_walk_top: Array<BufferedImage> = Array(16) {

            ImageIO.read(javaClass.getResource("dog/top/walk/${it+1}.png"))
        }
        val dog_walk_left: Array<BufferedImage> = Array(16) {
            ImageIO.read(javaClass.getResource("dog/left/walk/${it+1}.png"))
        }
        val dog_walk_right: Array<BufferedImage> = Array(16) {
            ImageIO.read(javaClass.getResource("dog/right/walk/${it+1}.png"))
        }
        val dog_walk_right_top: Array<BufferedImage> = Array(16) {
            ImageIO.read(javaClass.getResource("dog/right_top/walk/${it+1}.png"))
        }
        val dog_walk_right_bottom: Array<BufferedImage> = Array(16) {
            ImageIO.read(javaClass.getResource("dog/right_bottom/walk/${it+1}.png"))
        }
        val dog_walk_left_top: Array<BufferedImage> = Array(16) {
            ImageIO.read(javaClass.getResource("dog/left_top/walk/${it+1}.png"))
        }
        val dog_walk_left_bottom: Array<BufferedImage> = Array(16) {
            ImageIO.read(javaClass.getResource("dog/left_bottom/walk/${it+1}.png"))
        }

        val dog_run_bottom: Array<BufferedImage> = Array(14) {
            ImageIO.read(javaClass.getResource("dog/bottom/run/${it+1}.png"))
        }
        val dog_run_top: Array<BufferedImage> = Array(14) {

            ImageIO.read(javaClass.getResource("dog/top/run/${it+1}.png"))
        }
        val dog_run_left: Array<BufferedImage> = Array(14) {
            ImageIO.read(javaClass.getResource("dog/left/run/${it+1}.png"))
        }
        val dog_run_right: Array<BufferedImage> = Array(14) {
            ImageIO.read(javaClass.getResource("dog/right/run/${it+1}.png"))
        }
        val dog_run_right_top: Array<BufferedImage> = Array(14) {
            ImageIO.read(javaClass.getResource("dog/right_top/run/${it+1}.png"))
        }
        val dog_run_right_bottom: Array<BufferedImage> = Array(14) {
            ImageIO.read(javaClass.getResource("dog/right_bottom/run/${it+1}.png"))
        }
        val dog_run_left_top: Array<BufferedImage> = Array(14) {
            ImageIO.read(javaClass.getResource("dog/left_top/run/${it+1}.png"))
        }
        val dog_run_left_bottom: Array<BufferedImage> = Array(14) {
            ImageIO.read(javaClass.getResource("dog/left_bottom/run/${it+1}.png"))
        }

        val dog_rest: Array<BufferedImage> = Array(16) {
            ImageIO.read(javaClass.getResource("dog/rest/rest_0/${it+1}.png"))
        }

        val dog_pee: Array<BufferedImage> = Array(20) {
            ImageIO.read(javaClass.getResource("dog/pee/${it+1}.png"))
        }

        val apple_fructify: Array<BufferedImage> = Array(13) {
            ImageIO.read(javaClass.getResource("trees/apple/fructify/${it+1}.png"))
        }

        val applefarm = ImageIO.read(javaClass.getResource("buildings/applefarm_old.png"))

        val edge = ImageIO.read(javaClass.getResource("toolbar/edge.png"))
//        val edge_right = ImageIO.read(javaClass.getResource("toolbar/edge_right.png"))
        val treasurer_right = ImageIO.read(javaClass.getResource("toolbar/treasurer_right.png"))
        val toolbar = ImageIO.read(javaClass.getResource("toolbar/toolbar.png"))
        val treasurer_face_0 = ImageIO.read(javaClass.getResource("toolbar/treasurer_face_0.png"))

        val toolbar_applefarm: Array<BufferedImage> = Array(2) {
            ImageIO.read(javaClass.getResource("toolbar/applefarm/${it}.png"))
        }

        val bottombar_apple: Array<BufferedImage> = Array(3) {
            ImageIO.read(javaClass.getResource("toolbar/bottombar/apple/${it+1}.png"))
        }

        val bottombar_hummer: Array<BufferedImage> = Array(3) {
            ImageIO.read(javaClass.getResource("toolbar/bottombar/hummer/${it+1}.png"))
        }

        val bottombar_shield: Array<BufferedImage> = Array(3) {
            ImageIO.read(javaClass.getResource("toolbar/bottombar/shield/${it+1}.png"))
        }

        val bottombar_house: Array<BufferedImage> = Array(3) {
            ImageIO.read(javaClass.getResource("toolbar/bottombar/house/${it+1}.png"))
        }

        val bottombar_hook: Array<BufferedImage> = Array(3) {
            ImageIO.read(javaClass.getResource("toolbar/bottombar/hook/${it+1}.png"))
        }

        val bottombar_tower: Array<BufferedImage> = Array(3) {
            ImageIO.read(javaClass.getResource("toolbar/bottombar/tower/${it+1}.png"))
        }

        val sidebar_key: Array<BufferedImage> = Array(2) {
            ImageIO.read(javaClass.getResource("toolbar/sidebar/key_${it+1}.png"))
        }

        val sidebar_exit: Array<BufferedImage> = Array(2) {
            ImageIO.read(javaClass.getResource("toolbar/sidebar/exit_${it+1}.png"))
        }

        val sidebar_info: Array<BufferedImage> = Array(2) {
            ImageIO.read(javaClass.getResource("toolbar/sidebar/info_${it+1}.png"))
        }

        val sidebar_back: Array<BufferedImage> = Array(3) {
            ImageIO.read(javaClass.getResource("toolbar/sidebar/back_${it+1}.png"))
        }
    }
}