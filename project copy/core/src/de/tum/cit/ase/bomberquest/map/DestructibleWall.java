package de.tum.cit.ase.bomberquest.map;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import de.tum.cit.ase.bomberquest.texture.Animations;
import de.tum.cit.ase.bomberquest.texture.Drawable;
import de.tum.cit.ase.bomberquest.texture.Textures;

public class DestructibleWall extends AbstractWall implements Drawable {
    private boolean destroyed;
    private boolean pendingRemoval;
    private float stateTime;

    private final float animationTime = 3f; // Duration of destruction animation

    public DestructibleWall(World world, float x, float y) {
        super(world, x, y);
        this.destroyed = false;
        this.pendingRemoval = false;
        this.stateTime = 0f;
    }

    public void tick(float frameTime, World world) {
        if (destroyed) {
            stateTime += frameTime;
            if (isAnimationComplete()) {
                removeHitbox(world);
            }
        }
    }

    public void destroy() {
        if (!destroyed) {
            this.destroyed = true;
            this.stateTime = 0f; // Reset animation time
        }
    }

    public boolean isAnimationComplete() {
        return destroyed && stateTime >= animationTime;
    }

    private void removeHitbox(World world) {
        if (hitbox != null && !pendingRemoval) {
            pendingRemoval = true;
            world.destroyBody(hitbox);
            hitbox = null; // Ensure hitbox is null to avoid further collision detection
        }
    }

    @Override
    public TextureRegion getCurrentAppearance() {
        return destroyed ? Animations.WALL_DESTRUCT.getKeyFrame(stateTime, false) : Textures.DESTRUCTIBLE_WALL;
    }

    public boolean isDestroyed() {return destroyed;}
}