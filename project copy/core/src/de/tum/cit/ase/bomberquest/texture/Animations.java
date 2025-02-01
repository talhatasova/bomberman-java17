package de.tum.cit.ase.bomberquest.texture;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Contains all animation constants used in the game.
 * It is good practice to keep all textures and animations in constants to avoid loading them multiple times.
 * These can be referenced anywhere they are needed.
 */
public class Animations {
    
    /**
     * The animation for the character walking down.
     */
    public static final Animation<TextureRegion> CHARACTER_WALK_DOWN = new Animation<>(
            0.1f,
            SpriteSheet.CHARACTER.at(1, 1),
            SpriteSheet.CHARACTER.at(1, 2),
            SpriteSheet.CHARACTER.at(1, 3),
            SpriteSheet.CHARACTER.at(1, 4)
    );
    public static final Animation<TextureRegion> CHARACTER_WALK_UP = new Animation<>(
            0.1f,
            SpriteSheet.CHARACTER.at(3, 1),
            SpriteSheet.CHARACTER.at(3, 2),
            SpriteSheet.CHARACTER.at(3, 3),
            SpriteSheet.CHARACTER.at(3, 4)
    );
    public static final Animation<TextureRegion> CHARACTER_WALK_RIGHT = new Animation<>(
            0.1f,
            SpriteSheet.CHARACTER.at(2, 1),
            SpriteSheet.CHARACTER.at(2, 2),
            SpriteSheet.CHARACTER.at(2, 3),
            SpriteSheet.CHARACTER.at(2, 4)
    );
    public static final Animation<TextureRegion> CHARACTER_WALK_LEFT = new Animation<>(
            0.1f,
            SpriteSheet.CHARACTER.at(4, 1),
            SpriteSheet.CHARACTER.at(4, 2),
            SpriteSheet.CHARACTER.at(4, 3),
            SpriteSheet.CHARACTER.at(4, 4)
    );
    public static final Animation<TextureRegion> WALL_DESTRUCT = new Animation<>(
            0.1f,
            SpriteSheet.ORG_BOMBERMAN.at(4, 5),
            SpriteSheet.ORG_BOMBERMAN.at(4, 6),
            SpriteSheet.ORG_BOMBERMAN.at(4, 7),
            SpriteSheet.ORG_BOMBERMAN.at(4, 8),
            SpriteSheet.ORG_BOMBERMAN.at(4, 9),
            SpriteSheet.ORG_BOMBERMAN.at(4, 10),
            SpriteSheet.ORG_BOMBERMAN.at(4, 11)
    );
    public static final Animation<TextureRegion> BOMB_PLACED = new Animation<>(
            1f,
            SpriteSheet.ORG_BOMBERMAN.at(4, 1),
            SpriteSheet.ORG_BOMBERMAN.at(4, 2),
            SpriteSheet.ORG_BOMBERMAN.at(4, 3)
    );
    public static final Animation<TextureRegion> BOMB_EXPLODE = new Animation<>(
            0.25f,
            SpriteSheet.ORG_BOMBERMAN.at(7, 3),
            SpriteSheet.ORG_BOMBERMAN.at(7, 8),
            SpriteSheet.ORG_BOMBERMAN.at(12, 3),
            SpriteSheet.ORG_BOMBERMAN.at(12, 8)
    );
}
