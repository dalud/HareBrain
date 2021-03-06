package discordia.harebrain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import javafx.geometry.Rectangle2D;

/**
 * Created by dalud on 3.10.2016.
 */

public class Bunny{
    private Texture animSheet, sitRight, hopRight, hopLeft, sitLeft;
    private TextureRegion currentFrame, jumpRight, jumpLeft, fallRight, fallLeft, duckRight, duckLeft;
    public Sprite frame;
    OrthographicCamera cam;
    float frameT, stateTime;
    public Body body;
    enum State {    SIT_RIGHT,
                    SIT_LEFT,
                    HOP_RIGHT,
                    HOP_LEFT,
                    JUMP_RIGHT,
                    JUMP_LEFT,
                    FALL_RIGHT,
                    FALL_LEFT,
                    DUCK_RIGHT,
                    DUCK_LEFT   }
    State state;

    public Bunny(OrthographicCamera cam, World world){

        this.cam = cam;

        frameT = .15f;
        stateTime = 0;
        state = State.SIT_RIGHT;

        sitRight = new Texture("Bunny/pupu_sit.png");
        hopRight = new Texture("Bunny/pupu_hopRight.png");
        hopLeft = new Texture("Bunny/pupu_hopLeft.png");
        sitLeft = new Texture("Bunny/pupu_sitLeft.png");
        jumpRight = new TextureRegion(new Texture("Bunny/pupu_jump.png"));
        jumpLeft = new TextureRegion(jumpRight);
        jumpLeft.flip(true, false);
        fallRight = new TextureRegion(new Texture("Bunny/pupu_fall.png"));
        fallLeft = new TextureRegion(fallRight);
        fallLeft.flip(true, false);
        duckRight = new TextureRegion(new Texture("Bunny/pupu_duck.png"));
        duckLeft = new TextureRegion(duckRight);
        duckLeft.flip(true, false);

        currentFrame = new TextureRegion(sitRight, sitRight.getWidth()/3, sitRight.getHeight());
        frame = new Sprite(currentFrame);
        frame.setSize(2, 2);

        //physics testing
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(0, 5);
        body = world.createBody(def);
        PolygonShape gon = new PolygonShape();
        gon.setAsBox(1, 1);

        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = gon;
        fixDef.density = 1;
        fixDef.friction = .7f;
        fixDef.restitution = 0;
        Fixture fix = body.createFixture(fixDef);
        gon.dispose();

    }
    public void draw(SpriteBatch batch){
        frame.setPosition(body.getPosition().x-1, body.getPosition().y-1);
        frame.setRegion(currentFrame);
        frame.draw(batch);
    }

    public void anim() {
        int frame_cols = 3;
        int frame_rows = 1;
        float tick = Gdx.graphics.getDeltaTime();

        Animation anim;

        TextureRegion[] animFrames;

        if(state == State.SIT_RIGHT) animSheet = sitRight;
        else if(state == State.HOP_RIGHT) animSheet = hopRight;
        else if(state == State.HOP_LEFT) animSheet = hopLeft;
        else if(state == State.SIT_LEFT) animSheet = sitLeft;

        TextureRegion[][] tmp = TextureRegion.split(animSheet, animSheet.getWidth()/frame_cols, animSheet.getHeight()/frame_rows);
        animFrames = new TextureRegion[frame_cols * frame_rows];
        int index = 0;
        for (int i = 0; i < frame_rows; i++) {
            for (int j = 0; j < frame_cols; j++) {
                animFrames[index++] = tmp[i][j];
            }
        }
        anim = new Animation(frameT, animFrames);
        stateTime += tick;

        //kokeile switchiä, dummy!
        if(state == State.JUMP_RIGHT) currentFrame = jumpRight;
        else if(state == State.JUMP_LEFT) currentFrame = jumpLeft;
        else if(state == State.FALL_RIGHT) currentFrame = fallRight;
        else if(state == State.FALL_LEFT) currentFrame = fallLeft;
        else if(state == State.DUCK_RIGHT) currentFrame = duckRight;
        else if(state == State.DUCK_LEFT) currentFrame = duckLeft;
        else currentFrame = anim.getKeyFrame(stateTime, true);
    }
}