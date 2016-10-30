package discordia.harebrain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
    private float camSpeed, initX, updateX;

    private OrthographicCamera cam;
    public Body body;

    public Fourest(OrthographicCamera cam, World world) {
        this.cam = cam;
        layer0 = new Texture(Gdx.files.internal("Fourest/fL0.png"));
        l0 = new Sprite(layer0);
        l0.setSize(48, 9);
        l0.setPosition(-24, -4.5f);
        layer1 = new Texture(Gdx.files.internal("Fourest/fL1.png"));
        l1 = new Sprite(layer1);
        l1.setSize(48, 9);
        l1.setPosition(-24, -4.5f);
        layer2 = new Texture(Gdx.files.internal("Fourest/fL2.png"));
        l2 = new Sprite(layer2);
        l2.setSize(48, 9);
        l2.setPosition(-24, -4.5f);
        //layer3 = new Texture(Gdx.files.internal("Fourest/fL3.png"));

        camSpeed = .02f;
        initX = cam.position.x;

        //physics
        BodyDef groundDef = new BodyDef();
        groundDef.position.set(-32, -4);
        body = world.createBody(groundDef);
        PolygonShape box = new PolygonShape();
        box.setAsBox(64, 1);
        body.createFixture(box, 0);
        box.dispose();
    }
    public void draw(SpriteBatch batch) {

        updateX = cam.position.x;
        //batch.draw(layer3, -layer3.getWidth()/2, -Gdx.graphics.getHeight()/2);
        l2.draw(batch);

            if(updateX > initX+camSpeed) {
                l0.translate(-camSpeed, 0);
                l2.translate(+camSpeed, 0);
            }
            else if(updateX+camSpeed < initX) {
                l0.translate(+camSpeed, 0);
                l2.translate(-camSpeed, 0);
            }

        l1.draw(batch);
        l0.draw(batch);

        initX = cam.position.x;
    }
}
