import java.awt.image.BufferedImage
import javax.imageio.ImageIO

class ImageHelper {
    companion object {
        val woodcutter_chop_log_size = 12
        const val woodcutter_chop_tree_size = 12
        const val woodcutter_rest_size  = 24
        val grass_tiles: Array<BufferedImage> = Array(8) {
            ImageIO.read(javaClass.getResource("tiles/grass/$it.png"))
        }

        val sand_tiles: Array<BufferedImage> = Array(16) {
            ImageIO.read(javaClass.getResource("tiles/sand/$it.png"))
        }

        val sea_tiles: Array<BufferedImage> = Array(42) {
            ImageIO.read(javaClass.getResource("tiles/sea/$it.png"))
        }

        val farmland_unprepared: Array<BufferedImage> = Array(4) {
            ImageIO.read(javaClass.getResource("tiles/farmland/unprepared/${it+1}.png"))
        }

        val farmland_prepared: Array<BufferedImage> = Array(4) {
            ImageIO.read(javaClass.getResource("tiles/farmland/prepared/${it+1}.png"))
        }

        val farmland_wheat_seeded: Array<BufferedImage> = Array(4) {
            ImageIO.read(javaClass.getResource("tiles/farmland/wheat_seeded/${it+1}.png"))
        }

        val farmland_wheat_halfgrown: Array<BufferedImage> = Array(4) {
            ImageIO.read(javaClass.getResource("tiles/farmland/wheat_halfgrown/${it+1}.png"))
        }

        val farmland_wheat_grown: Array<BufferedImage> = Array(4) {
            ImageIO.read(javaClass.getResource("tiles/farmland/wheat_grown/${it+1}.png"))
        }

        val farmland_wheat_cutted: Array<BufferedImage> = Array(4) {
            ImageIO.read(javaClass.getResource("tiles/farmland/wheat_cutted/${it+1}.png"))
        }

        val farmland_wheat_damaged: Array<BufferedImage> = Array(4) {
            ImageIO.read(javaClass.getResource("tiles/farmland/wheat_damaged/${it+1}.png"))
        }

        // ЭТО ТУПО!
        val farmland_wheat_sheaf: Array<BufferedImage> = Array(4) {
            ImageIO.read(javaClass.getResource("tiles/farmland/wheat_sheaf.png"))
        }

        val trodden_ground: BufferedImage = ImageIO.read(javaClass.getResource("tiles/trodden_ground.png"))

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

        val farmer_walk_bottom: Array<BufferedImage> = Array(8) {
            ImageIO.read(javaClass.getResource("farmer/bottom/walk/${it+1}.png"))
        }
        val farmer_walk_top: Array<BufferedImage> = Array(8) {

            ImageIO.read(javaClass.getResource("farmer/top/walk/${it+1}.png"))
        }
        val farmer_walk_left: Array<BufferedImage> = Array(8) {
            ImageIO.read(javaClass.getResource("farmer/left/walk/${it+1}.png"))
        }
        val farmer_walk_right: Array<BufferedImage> = Array(8) {
            ImageIO.read(javaClass.getResource("farmer/right/walk/${it+1}.png"))
        }
        val farmer_walk_right_top: Array<BufferedImage> = Array(8) {
            ImageIO.read(javaClass.getResource("farmer/right_top/walk/${it+1}.png"))
        }
        val farmer_walk_right_bottom: Array<BufferedImage> = Array(8) {
            ImageIO.read(javaClass.getResource("farmer/right_bottom/walk/${it+1}.png"))
        }
        val farmer_walk_left_top: Array<BufferedImage> = Array(8) {
            ImageIO.read(javaClass.getResource("farmer/left_top/walk/${it+1}.png"))
        }
        val farmer_walk_left_bottom: Array<BufferedImage> = Array(8) {
            ImageIO.read(javaClass.getResource("farmer/left_bottom/walk/${it+1}.png"))
        }

        val farmer_cultivate_bottom: Array<BufferedImage> = Array(8) {
            ImageIO.read(javaClass.getResource("farmer/bottom/cultivate/${it+1}.png"))
        }
        val farmer_cultivate_top: Array<BufferedImage> = Array(8) {

            ImageIO.read(javaClass.getResource("farmer/top/cultivate/${it+1}.png"))
        }
        val farmer_cultivate_left: Array<BufferedImage> = Array(8) {
            ImageIO.read(javaClass.getResource("farmer/left/cultivate/${it+1}.png"))
        }
        val farmer_cultivate_right: Array<BufferedImage> = Array(8) {
            ImageIO.read(javaClass.getResource("farmer/right/cultivate/${it+1}.png"))
        }
        val farmer_cultivate_right_top: Array<BufferedImage> = Array(8) {
            ImageIO.read(javaClass.getResource("farmer/right_top/cultivate/${it+1}.png"))
        }
        val farmer_cultivate_right_bottom: Array<BufferedImage> = Array(8) {
            ImageIO.read(javaClass.getResource("farmer/right_bottom/cultivate/${it+1}.png"))
        }
        val farmer_cultivate_left_top: Array<BufferedImage> = Array(8) {
            ImageIO.read(javaClass.getResource("farmer/left_top/cultivate/${it+1}.png"))
        }
        val farmer_cultivate_left_bottom: Array<BufferedImage> = Array(8) {
            ImageIO.read(javaClass.getResource("farmer/left_bottom/cultivate/${it+1}.png"))
        }
        
        val sow_size = 8

        val farmer_sow_bottom: Array<BufferedImage> = Array(sow_size) {
            ImageIO.read(javaClass.getResource("farmer/bottom/sow/${it+1}.png"))
        }
        val farmer_sow_top: Array<BufferedImage> = Array(sow_size) {

            ImageIO.read(javaClass.getResource("farmer/top/sow/${it+1}.png"))
        }
        val farmer_sow_left: Array<BufferedImage> = Array(sow_size) {
            ImageIO.read(javaClass.getResource("farmer/left/sow/${it+1}.png"))
        }
        val farmer_sow_right: Array<BufferedImage> = Array(sow_size) {
            ImageIO.read(javaClass.getResource("farmer/right/sow/${it+1}.png"))
        }
        val farmer_sow_right_top: Array<BufferedImage> = Array(sow_size) {
            ImageIO.read(javaClass.getResource("farmer/right_top/sow/${it+1}.png"))
        }
        val farmer_sow_right_bottom: Array<BufferedImage> = Array(sow_size) {
            ImageIO.read(javaClass.getResource("farmer/right_bottom/sow/${it+1}.png"))
        }
        val farmer_sow_left_top: Array<BufferedImage> = Array(sow_size) {
            ImageIO.read(javaClass.getResource("farmer/left_top/sow/${it+1}.png"))
        }
        val farmer_sow_left_bottom: Array<BufferedImage> = Array(sow_size) {
            ImageIO.read(javaClass.getResource("farmer/left_bottom/sow/${it+1}.png"))
        }
        
        val cut_size = 8
        val farmer_cut_bottom: Array<BufferedImage> = Array(cut_size) {
            ImageIO.read(javaClass.getResource("farmer/bottom/cut/${it+1}.png"))
        }
        val farmer_cut_top: Array<BufferedImage> = Array(cut_size) {

            ImageIO.read(javaClass.getResource("farmer/top/cut/${it+1}.png"))
        }
        val farmer_cut_left: Array<BufferedImage> = Array(cut_size) {
            ImageIO.read(javaClass.getResource("farmer/left/cut/${it+1}.png"))
        }
        val farmer_cut_right: Array<BufferedImage> = Array(cut_size) {
            ImageIO.read(javaClass.getResource("farmer/right/cut/${it+1}.png"))
        }
        val farmer_cut_right_top: Array<BufferedImage> = Array(cut_size) {
            ImageIO.read(javaClass.getResource("farmer/right_top/cut/${it+1}.png"))
        }
        val farmer_cut_right_bottom: Array<BufferedImage> = Array(cut_size) {
            ImageIO.read(javaClass.getResource("farmer/right_bottom/cut/${it+1}.png"))
        }
        val farmer_cut_left_top: Array<BufferedImage> = Array(cut_size) {
            ImageIO.read(javaClass.getResource("farmer/left_top/cut/${it+1}.png"))
        }
        val farmer_cut_left_bottom: Array<BufferedImage> = Array(cut_size) {
            ImageIO.read(javaClass.getResource("farmer/left_bottom/cut/${it+1}.png"))
        }

        val bear_wheat_size = 8
        val farmer_bear_wheat_bottom: Array<BufferedImage> = Array(bear_wheat_size) {
            ImageIO.read(javaClass.getResource("farmer/bottom/bear_wheat/${it+1}.png"))
        }
        val farmer_bear_wheat_top: Array<BufferedImage> = Array(bear_wheat_size) {

            ImageIO.read(javaClass.getResource("farmer/top/bear_wheat/${it+1}.png"))
        }
        val farmer_bear_wheat_left: Array<BufferedImage> = Array(bear_wheat_size) {
            ImageIO.read(javaClass.getResource("farmer/left/bear_wheat/${it+1}.png"))
        }
        val farmer_bear_wheat_right: Array<BufferedImage> = Array(bear_wheat_size) {
            ImageIO.read(javaClass.getResource("farmer/right/bear_wheat/${it+1}.png"))
        }
        val farmer_bear_wheat_right_top: Array<BufferedImage> = Array(bear_wheat_size) {
            ImageIO.read(javaClass.getResource("farmer/right_top/bear_wheat/${it+1}.png"))
        }
        val farmer_bear_wheat_right_bottom: Array<BufferedImage> = Array(bear_wheat_size) {
            ImageIO.read(javaClass.getResource("farmer/right_bottom/bear_wheat/${it+1}.png"))
        }
        val farmer_bear_wheat_left_top: Array<BufferedImage> = Array(bear_wheat_size) {
            ImageIO.read(javaClass.getResource("farmer/left_top/bear_wheat/${it+1}.png"))
        }
        val farmer_bear_wheat_left_bottom: Array<BufferedImage> = Array(bear_wheat_size) {
            ImageIO.read(javaClass.getResource("farmer/left_bottom/bear_wheat/${it+1}.png"))
        }

        val goods_wheat: Array<BufferedImage> = Array(32) {
            ImageIO.read(javaClass.getResource("buildings/palette_goods/wheat/${it+1}.png"))
        }

        val farmer_rest: Array<BufferedImage> = Array(12) {
            ImageIO.read(javaClass.getResource("farmer/rest/${it+1}.png"))
        }

        val woodcutter_walk_bottom: Array<BufferedImage> = Array(8) {
            ImageIO.read(javaClass.getResource("mobs/woodcutter/bottom/walk/${it+1}.png"))
        }
        val woodcutter_walk_top: Array<BufferedImage> = Array(8) {

            ImageIO.read(javaClass.getResource("mobs/woodcutter/top/walk/${it+1}.png"))
        }
        val woodcutter_walk_left: Array<BufferedImage> = Array(8) {
            ImageIO.read(javaClass.getResource("mobs/woodcutter/left/walk/${it+1}.png"))
        }
        val woodcutter_walk_right: Array<BufferedImage> = Array(8) {
            ImageIO.read(javaClass.getResource("mobs/woodcutter/right/walk/${it+1}.png"))
        }
        val woodcutter_walk_right_top: Array<BufferedImage> = Array(8) {
            ImageIO.read(javaClass.getResource("mobs/woodcutter/right_top/walk/${it+1}.png"))
        }
        val woodcutter_walk_right_bottom: Array<BufferedImage> = Array(8) {
            ImageIO.read(javaClass.getResource("mobs/woodcutter/right_bottom/walk/${it+1}.png"))
        }
        val woodcutter_walk_left_top: Array<BufferedImage> = Array(8) {
            ImageIO.read(javaClass.getResource("mobs/woodcutter/left_top/walk/${it+1}.png"))
        }
        val woodcutter_walk_left_bottom: Array<BufferedImage> = Array(8) {
            ImageIO.read(javaClass.getResource("mobs/woodcutter/left_bottom/walk/${it+1}.png"))
        }


        val woodcutter_chop_tree_bottom: Array<BufferedImage> = Array(woodcutter_chop_tree_size) {
            ImageIO.read(javaClass.getResource("mobs/woodcutter/bottom/chop_tree/${it+1}.png"))
        }
        val woodcutter_chop_tree_top: Array<BufferedImage> = Array(woodcutter_chop_tree_size) {
            ImageIO.read(javaClass.getResource("mobs/woodcutter/top/chop_tree/${it+1}.png"))
        }
        val woodcutter_chop_tree_left: Array<BufferedImage> = Array(woodcutter_chop_tree_size) {
            ImageIO.read(javaClass.getResource("mobs/woodcutter/left/chop_tree/${it+1}.png"))
        }
        val woodcutter_chop_tree_right: Array<BufferedImage> = Array(woodcutter_chop_tree_size) {
            ImageIO.read(javaClass.getResource("mobs/woodcutter/right/chop_tree/${it+1}.png"))
        }
        val woodcutter_chop_tree_right_top: Array<BufferedImage> = Array(woodcutter_chop_tree_size) {
            ImageIO.read(javaClass.getResource("mobs/woodcutter/right_top/chop_tree/${it+1}.png"))
        }
        val woodcutter_chop_tree_right_bottom: Array<BufferedImage> = Array(woodcutter_chop_tree_size) {
            ImageIO.read(javaClass.getResource("mobs/woodcutter/right_bottom/chop_tree/${it+1}.png"))
        }
        val woodcutter_chop_tree_left_top: Array<BufferedImage> = Array(woodcutter_chop_tree_size) {
            ImageIO.read(javaClass.getResource("mobs/woodcutter/left_top/chop_tree/${it+1}.png"))
        }
        val woodcutter_chop_tree_left_bottom: Array<BufferedImage> = Array(woodcutter_chop_tree_size) {
            ImageIO.read(javaClass.getResource("mobs/woodcutter/left_bottom/chop_tree/${it+1}.png"))
        }

        val woodcutter_chop_log_bottom: Array<BufferedImage> = Array(woodcutter_chop_log_size) {
            ImageIO.read(javaClass.getResource("mobs/woodcutter/bottom/chop_log/${it+1}.png"))
        }
        val woodcutter_chop_log_top: Array<BufferedImage> = Array(woodcutter_chop_log_size) {
            ImageIO.read(javaClass.getResource("mobs/woodcutter/top/chop_log/${it+1}.png"))
        }
        val woodcutter_chop_log_left: Array<BufferedImage> = Array(woodcutter_chop_log_size) {
            ImageIO.read(javaClass.getResource("mobs/woodcutter/left/chop_log/${it+1}.png"))
        }
        val woodcutter_chop_log_right: Array<BufferedImage> = Array(woodcutter_chop_log_size) {
            ImageIO.read(javaClass.getResource("mobs/woodcutter/right/chop_log/${it+1}.png"))
        }
        val woodcutter_chop_log_right_top: Array<BufferedImage> = Array(woodcutter_chop_log_size) {
            ImageIO.read(javaClass.getResource("mobs/woodcutter/right_top/chop_log/${it+1}.png"))
        }
        val woodcutter_chop_log_right_bottom: Array<BufferedImage> = Array(woodcutter_chop_log_size) {
            ImageIO.read(javaClass.getResource("mobs/woodcutter/right_bottom/chop_log/${it+1}.png"))
        }
        val woodcutter_chop_log_left_top: Array<BufferedImage> = Array(woodcutter_chop_log_size) {
            ImageIO.read(javaClass.getResource("mobs/woodcutter/left_top/chop_log/${it+1}.png"))
        }
        val woodcutter_chop_log_left_bottom: Array<BufferedImage> = Array(woodcutter_chop_log_size) {
            ImageIO.read(javaClass.getResource("mobs/woodcutter/left_bottom/chop_log/${it+1}.png"))
        }

        val woodcutter_rest: Array<BufferedImage> = Array(woodcutter_rest_size) {
            ImageIO.read(javaClass.getResource("mobs/woodcutter/rest/${it+1}.png"))
        }



        val peasant_walk_bottom: Array<BufferedImage> = Array(8) {
            ImageIO.read(javaClass.getResource("peasant/bottom/walk/${it+1}.png"))
        }
        val peasant_walk_top: Array<BufferedImage> = Array(8) {

            ImageIO.read(javaClass.getResource("peasant/top/walk/${it+1}.png"))
        }
        val peasant_walk_left: Array<BufferedImage> = Array(8) {
            ImageIO.read(javaClass.getResource("peasant/left/walk/${it+1}.png"))
        }
        val peasant_walk_right: Array<BufferedImage> = Array(8) {
            ImageIO.read(javaClass.getResource("peasant/right/walk/${it+1}.png"))
        }
        val peasant_walk_right_top: Array<BufferedImage> = Array(8) {
            ImageIO.read(javaClass.getResource("peasant/right_top/walk/${it+1}.png"))
        }
        val peasant_walk_right_bottom: Array<BufferedImage> = Array(8) {
            ImageIO.read(javaClass.getResource("peasant/right_bottom/walk/${it+1}.png"))
        }
        val peasant_walk_left_top: Array<BufferedImage> = Array(8) {
            ImageIO.read(javaClass.getResource("peasant/left_top/walk/${it+1}.png"))
        }
        val peasant_walk_left_bottom: Array<BufferedImage> = Array(8) {
            ImageIO.read(javaClass.getResource("peasant/left_bottom/walk/${it+1}.png"))
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

        val chestnut_full: Array<BufferedImage> = Array(13) {
            ImageIO.read(javaClass.getResource("trees/chestnut/full/${it+1}.png"))
        }

        val chestnut_full_fall: Array<BufferedImage> = Array(10) {
            ImageIO.read(javaClass.getResource("trees/chestnut/full_fall/${it+1}.png"))
        }

        val applefarm = ImageIO.read(javaClass.getResource("buildings/applefarm.png"))
        val cattlefarm = ImageIO.read(javaClass.getResource("buildings/cattlefarm.png"))
        val hopsfarm = ImageIO.read(javaClass.getResource("buildings/hopsfarm.png"))
        val wheatfarm = ImageIO.read(javaClass.getResource("buildings/wheatfarm.png"))
        val castle = ImageIO.read(javaClass.getResource("buildings/castle/castle.png"))
        val palette = ImageIO.read(javaClass.getResource("buildings/palette.png"))
        val woodcutter_hut = ImageIO.read(javaClass.getResource("buildings/woodcutter_hut.png"))

        val castle_gate0_l = ImageIO.read(javaClass.getResource("buildings/castle/gate0_l.png"))
        val castle_gate1_l = ImageIO.read(javaClass.getResource("buildings/castle/gate1_l.png"))
        val castle_gate0_r = ImageIO.read(javaClass.getResource("buildings/castle/gate0_r.png"))
        val castle_gate1_r = ImageIO.read(javaClass.getResource("buildings/castle/gate1_r.png"))

        val edge = ImageIO.read(javaClass.getResource("toolbar/edge.png"))
//        val edge_right = ImageIO.read(javaClass.getResource("toolbar/edge_right.png"))
        val treasurer_right = ImageIO.read(javaClass.getResource("toolbar/treasurer_right.png"))
        val toolbar = ImageIO.read(javaClass.getResource("toolbar/toolbar.png"))
        val treasurer_face_0 = ImageIO.read(javaClass.getResource("toolbar/treasurer_face_0.png"))

        val toolbar_applefarm: Array<BufferedImage> = Array(2) {
            ImageIO.read(javaClass.getResource("toolbar/applefarm/${it}.png"))
        }

        val toolbar_woodcutter_hut: Array<BufferedImage> = Array(2) {
            ImageIO.read(javaClass.getResource("toolbar/woodcutter_hut/${it}.png"))
        }

        val toolbar_castle: Array<BufferedImage> = Array(2) {
            ImageIO.read(javaClass.getResource("toolbar/castle/${it}.png"))
        }

        val toolbar_palette: Array<BufferedImage> = Array(2) {
            ImageIO.read(javaClass.getResource("toolbar/palette/${it}.png"))
        }

        val toolbar_cattlefarm: Array<BufferedImage> = Array(2) {
            ImageIO.read(javaClass.getResource("toolbar/cattlefarm/${it}.png"))
        }
        val toolbar_hopsfarm: Array<BufferedImage> = Array(2) {
            ImageIO.read(javaClass.getResource("toolbar/hopsfarm/${it}.png"))
        }
        val toolbar_wheatfarm: Array<BufferedImage> = Array(2) {
            ImageIO.read(javaClass.getResource("toolbar/wheatfarm/${it}.png"))
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