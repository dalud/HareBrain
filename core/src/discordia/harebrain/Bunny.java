package discordia.harebrain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Dalud on 3.10.2016.
 */

public class Bunny {
    public TextureRegion currentFrame;
    OrthographicCamera cam;
    float frameT, stateTime;
    private enum State {    RIGHT,
        LEFT,
        JUMP,
        DUCK    }
    private State state;

    public Bunny(OrthographicCamera cam){
        this.cam = cam;
        frameT = .2f;
        stateTime = 0;
        state = State.RIGHT;

    }
    public void draw(SpriteBatch batch){
        batch.draw(currentFrame, cam.position.x-(currentFrame.getRegionWidth()/2), cam.position.y-(currentFrame.getRegionHeight()/2));
    }
    public void move(){
        if(Gdx.input.isTouched()){
            if(Gdx.input.getX() > 640){
                state = State.RIGHT;
                this.anim(1);
            }
            else if(Gdx.input.getX() < 640){
                state = State.LEFT;
                this.anim(2);
            }
        }
        else {
            if(state == State.RIGHT) this.anim(0);
            else if(state == State.LEFT) this.anim(3);
        }
    }

    public void anim(int state) {

        int frame_cols = 3;
        int frame_rows = 1;
        float tick = Gdx.graphics.getDeltaTime();


        Animation anim;
        Texture animSheet = new Texture("Bunny/pupu_sit.png");
        TextureRegion[] animFrames;

        if(state==0) animSheet = new Texture("Bunny/pupu_sit.png");
        if(state==1) animSheet = new Texture("Bunny/pupu_hopRight.png");
        if(state==2) animSheet = new Texture("Bunny/pupu_hopLeft.png");
        if(state==3) animSheet = new Texture("Bunny/pupu_sitLeft.png");

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