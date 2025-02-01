package de.tum.cit.ase.bomberquest.map;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import de.tum.cit.ase.bomberquest.texture.Animations;
import de.tum.cit.ase.bomberquest.texture.Drawable;

import java.util.ArrayList;
import java.util.List;

public class Bomb implements Drawable {
    private final World world;
    private final Body hitbox;
    private float x;
    private float y;
    private boolean exploded;
    private float animationTime;
    private float explosionTime;
    private int blastRadius;
    private final Drawable owner; // Generic owner to track who placed the bomb
    private static List<Bomb> bombs = new ArrayList<>();
    private static List<Bomb> bombsToRemove = new ArrayList<>();

    public Bomb(World world, float x, float y, Drawable owner) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.blastRadius = 2;
        this.hitbox = null;
        this.owner = owner;
        this.exploded = false;
        this.animationTime = 1f;
        this.explosionTime = 3f;
        createHitbox(world, x, y);
        bombs.add(this);
    }

    public static List<Bomb> getBombs() {
        return bombs;
    }

    public static void addToRemoveBombs(Bomb bomb){
        bombsToRemove.add(bomb);
    }
    public static void removeBombs(){
        bombs.removeAll(bombsToRemove);
    }

    public static Bomb createBomb(World world, float x, float y, Drawable owner) {
        if (!isOccupied(x, y)) {
            Bomb newBomb = new Bomb(world, x, y, owner);
            bombs.add(newBomb);
            return newBomb;
        }
        return null;
    }

    private static boolean isOccupied(float x, float y) {
        for (Bomb existingBomb : bombs) {
            if (existingBomb.x == x && existingBomb.y == y) {
                return true;
            }
        }
        return false;
    }

    public boolean isAnimationComplete() {
        return exploded && animationTime <= 0;
    }

    public void tick(float frameTime){
        if (!exploded) {
            this.explosionTime -= frameTime;
            if (this.explosionTime <= 0) { this.explode(); }
        } else if (!isAnimationComplete()){
            this.animationTime -= frameTime;
        }
    }

    private void explode(){
        this.exploded = true;
        this.explosionTime = 0;
    }

    private void createHitbox(World world, float x, float y) {
        // TODO: Implement hitbox creation logic using Box2D
    }

    public Drawable getOwner() {
        return this.owner;
    }

    @Override
    public TextureRegion getCurrentAppearance() {
        if (!exploded) {
        return Animations.BOMB_PLACED.getKeyFrame(3-explosionTime, false);}
        else if (!isAnimationComplete()) {
            return Animations.BOMB_EXPLODE.getKeyFrame(1-animationTime, false);
        }
        else {return null;}
    }

    @Override
    public float getX() {return x;}

    @Override
    public float getY() {return y;}

    private static class ExplosionTiles {

    }
}



