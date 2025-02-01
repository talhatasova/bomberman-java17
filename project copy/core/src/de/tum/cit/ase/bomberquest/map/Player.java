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

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the player character in the game.
 * The player has a hitbox, so it can collide with other objects in the game.
 */
public class Player implements Drawable {
    private final World world;
    /** Total time elapsed since the game started. We use this for calculating the player movement and animating it. */
    private float elapsedTime;
    private final Body hitbox;
    private float xvel, yvel;
    private float speed;
    private Animation<TextureRegion> appearance;

    private List<Bomb> bombs;

    
    public Player(World world, float x, float y) {
        this.world = world;
        this.hitbox = createHitbox(world, x, y);
        this.xvel = 0f;
        this.yvel = 0f;
        this.speed = 3f;
        this.appearance = Animations.CHARACTER_WALK_DOWN;
        this.bombs = new ArrayList<>();
    }

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

    public void placeBomb() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) { // Prevents rapid placement
            float bombX = Math.round(getX()); // Round to grid coordinates
            float bombY = Math.round(getY());
            this.bombs.add(Bomb.createBomb(world, bombX, bombY, this));
        }
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

    public void tick(float frameTime) {
        this.elapsedTime += frameTime;
        move();
        placeBomb();
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
