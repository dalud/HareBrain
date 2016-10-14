package discordia.harebrain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by dalud on 8.10.2016.
 */

public class Fourest {
    private Texture layer0, layer1, layer2, layer3;
    private Sprite l0, l1, l2, l3;
    private int l0X, l0Y, l2X, l2Y;
    //private float speed = Movement.SPEED;

    private Bunny bunny;
    public Body body;

    public Fourest(Bunny bunny, World world) {
        this.bunny = bunny;
        layer0 = new Texture(Gdx.files.internal("Fourest/fL0.png"));
        l0 = new Sprite(layer0);
        l0.setSize(48, 9);
        l0.setPosition(-16, -4.5f);
        layer1 = new Texture(Gdx.files.internal("Fourest/fL1.png"));
        l1 = new Sprite(layer1);
        l1.setSize(48, 9);
        l1.setPosition(-16, -4.5f);
        layer2 = new Texture(Gdx.files.internal("Fourest/fL2.png"));
        l2 = new Sprite(layer2);
        l2.setSize(48, 9);
        l2.setPosition(-16, -4.5f);
        //layer3 = new Texture(Gdx.files.internal("Fourest/fL3.png"));

        l0X = -layer0.getWidth();
        l2X = -layer2.getWidth()/2;
        l0Y = l2Y = -Gdx.graphics.getHeight()/2;

        //physics
        BodyDef groundDef = new BodyDef();
        groundDef.position.set(-16, -4);
        body = world.createBody(groundDef);
        PolygonShape box = new PolygonShape();
        box.setAsBox(32, 1);
        body.createFixture(box, 0);
        box.dispose();
    }
    public void draw(SpriteBatch batch) {

        //batch.draw(layer3, -layer3.getWidth()/2, -Gdx.graphics.getHeight()/2);
        l2.draw(batch);

        /*if(bunny.state == Bunny.State.HOP_RIGHT) {
            l0.translate(-speed*1.33f, 0);
            l2.translate(+speed*.66f, 0);
        }
        else if(bunny.state == Bunny.State.HOP_LEFT) {
            l0.translate(+speed*1.33f, 0);
            l2.translate(-speed*.66f, 0);
        }*/

        l1.draw(batch);
        l0.draw(batch);

    }
}
