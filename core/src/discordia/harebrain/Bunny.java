package discordia.harebrain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Dalud on 3.10.2016.
 */

public class Bunny {
    private Texture animSheet, sitRight, hopRight, hopLeft, sitLeft;
    public TextureRegion currentFrame;
    OrthographicCamera cam;
    InputProcessor input;
    float frameT, stateTime;
    enum State {    SIT_RIGHT,
                            SIT_LEFT,
                            HOP_RIGHT,
                            HOP_LEFT,
                            JUMP,
                            DUCK    }
    State state;

    public Bunny(OrthographicCamera cam){
        this.cam = cam;
        input = new MyInput(this);
        Gdx.input.setInputProcessor(input);
        frameT = .2f;
        stateTime = 0;
        state = State.SIT_RIGHT;

        sitRight = new Texture("Bunny/pupu_sit.png");
        hopRight = new Texture("Bunny/pupu_hopRight.png");
        hopLeft = new Texture("Bunny/pupu_hopLeft.png");
        sitLeft = new Texture("Bunny/pupu_sitLeft.png");
    }
    public void draw(SpriteBatch batch){
        batch.draw(currentFrame, cam.position.x-(currentFrame.getRegionWidth()/2), cam.position.y-(currentFrame.getRegionHeight()/2));
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
        currentFrame = anim.getKeyFrame(stateTime, true);
    }
}