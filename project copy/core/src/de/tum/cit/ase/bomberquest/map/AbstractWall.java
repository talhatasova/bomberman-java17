package de.tum.cit.ase.bomberquest.map;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public abstract class AbstractWall {
    protected final float x;
    protected final float y;
    protected Body hitbox;

    public AbstractWall(World world, float x, float y) {
        this.x = x;
        this.y = y;
        this.hitbox = createHitbox(world, x, y);
    }

    protected Body createHitbox(World world, float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x, y);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f, 0.5f);

        Body body = world.createBody(bodyDef);
        body.createFixture(shape, 1.0f);
        shape.dispose();
        body.setUserData(this);

        return body;
    }

    public float getX() { return x; }
    public float getY() { return y; }
}
