package de.tum.cit.ase.bomberquest.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;
import de.tum.cit.ase.bomberquest.texture.Animations;
import de.tum.cit.ase.bomberquest.texture.Drawable;

/**
 * Represents the player character in the game.
 * The player has a hitbox, so it can collide with other objects in the game.
 */
public class Player implements Drawable {
    /** Total time elapsed since the game started. We use this for calculating the player movement and animating it. */
    private float elapsedTime;
    private final Body hitbox;
    private float xvel, yvel;
    private float speed;
    private Animation<TextureRegion> appearance;

    
    public Player(World world, float x, float y) {
        this.hitbox = createHitbox(world, x, y);
        this.xvel = 0f;
        this.yvel = 0f;
        this.speed = 3f;
        this.appearance = Animations.CHARACTER_WALK_DOWN;
    }
    
    /**
     * Creates a Box2D body for the player.
     * This is what the physics engine uses to move the player around and detect collisions with other bodies.
     * @param world The Box2D world to add the body to.
     * @param startX The initial X position.
     * @param startY The initial Y position.
     * @return The created body.
     */
    private Body createHitbox(World world, float startX, float startY) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(startX, startY);
        Body body = world.createBody(bodyDef);
        CircleShape circle = new CircleShape();
        circle.setRadius(0.3f);
        body.createFixture(circle, 1.0f);
        circle.dispose();
        body.setUserData(this);
        return body;
    }

    public void move() {
        this.xvel = 0f;
        this.yvel = 0f;
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            this.appearance = Animations.CHARACTER_WALK_UP;
            this.yvel += this.speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            this.appearance = Animations.CHARACTER_WALK_DOWN;
            this.yvel -= this.speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.appearance = Animations.CHARACTER_WALK_RIGHT;
            this.xvel += this.speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.appearance = Animations.CHARACTER_WALK_LEFT;
            this.xvel -= this.speed;
        }
        // normalisation of the speed if the movement is diagonal
        double totalVelocity = Math.sqrt(Math.pow(this.xvel, 2) + Math.pow(this.yvel, 2));
        if (totalVelocity > 0) {
            this.xvel *= (float) (this.speed/totalVelocity);
            this.yvel *= (float) (this.speed/totalVelocity);
        }
        this.hitbox.setLinearVelocity(this.xvel, this.yvel);
    }
    /**
     * Move the player around in a circle by updating the linear velocity of its hitbox every frame.
     * This doesn't actually move the player, but it tells the physics engine how the player should move next frame.
     * @param frameTime the time since the last frame.
     */
    public void tick(float frameTime) {
        this.elapsedTime += frameTime;
        // Make the player move in a circle with radius 2 tiles
        // You can change this to make the player move differently, e.g. in response to user input.
        // See Gdx.input.isKeyPressed() for keyboard input
        move();
//        float xVelocity = (float) Math.sin(this.elapsedTime) * 2;
//        float yVelocity = (float) Math.cos(this.elapsedTime) * 2;
//        this.hitbox.setLinearVelocity(xVelocity, yVelocity);
    }
    
    @Override
    public TextureRegion getCurrentAppearance() {
        // Get the frame of the walk down animation that corresponds to the current time.
        return this.appearance.getKeyFrame(this.elapsedTime, true);
    }
    
    @Override
    public float getX() {
        // The x-coordinate of the player is the x-coordinate of the hitbox (this can change every frame).
        return hitbox.getPosition().x;
    }
    
    @Override
    public float getY() {
        // The y-coordinate of the player is the y-coordinate of the hitbox (this can change every frame).
        return hitbox.getPosition().y;
    }
}
