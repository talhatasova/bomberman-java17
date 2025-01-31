package de.tum.cit.ase.bomberquest.texture;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Contains all texture constants used in the game.
 * It is good practice to keep all textures and animations in constants to avoid loading them multiple times.
 * These can be referenced anywhere they are needed.
 */
public class Textures {
    
    public static final TextureRegion FLOWERS = SpriteSheet.BASIC_TILES.at(2, 5);
    public static final TextureRegion TREE = SpriteSheet.BASIC_TILES.at(10, 5);
    public static final TextureRegion GRASS = SpriteSheet.BASIC_TILES.at(3, 5);
    public static final TextureRegion CHEST = SpriteSheet.BASIC_TILES.at(5, 5);
    public static final TextureRegion DESTRUCTIBLE_WALL = SpriteSheet.BASIC_TILES.at(2, 8);
    public static final TextureRegion INDESTRUCTIBLE_WALL = SpriteSheet.BASIC_TILES.at(3, 8);
}
