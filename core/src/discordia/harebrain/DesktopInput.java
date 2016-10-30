package discordia.harebrain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by dalud on 5.10.2016.
 */

public class DesktopInput implements InputProcessor {
    private Movement move;
    private int width, height, touchR, touchL, dragSens;
    public volatile static int touch, ducker;
    public volatile static float initY;
    public static volatile boolean ducked;
    private Movement.Direc intent;

    public DesktopInput(Movement move){
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        //SCREENBLOCKS FOR CONTROLS
        touchR = width/16*10;
        touchL = width/16*6;

        //SENSITIVITY FOR DRAG EVENTS
        dragSens = Gdx.graphics.getHeight()/20;

        this.move = move;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch++;
        //System.out.println(touch);

        //TOUCH DUCKING
        if(screenY>height/9*7.5 && screenX>touchL && screenX<touchR) {
            ducked = true;
            ducker = pointer;
            initY = 0;
            move.direc = Movement.Direc.DUCK;
            //System.out.println("touch duck");
        }

        //BASIC MOVE LEFT&RIGHT (sets intent to move)
        if (screenX>touchR && move.direc!=Movement.Direc.LEFT) intent = Movement.Direc.RIGHT;
        if (screenX<touchL && move.direc!=Movement.Direc.RIGHT) intent = Movement.Direc.LEFT;

        //TOUCH JUMP WHILE MOVING
        if (touch > 1) {
            if (move.direc == Movement.Direc.RIGHT || move.direc == Movement.Direc.LEFT) {
                move.jump();
                //System.out.println("touch jump");
            }
        }

        //SINGLE TOUCH JUMP
        if(screenY<height/9*4 && screenX<touchR && screenX>touchL) move.jump();

        //MOVE IF NOT DUCKED
        if(!ducked /*&& (screenX<touchL || screenX>touchR)*/) move.direc = intent;

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        touch--;
        System.out.println(touch);

        if(ducked && ducker == pointer) {
            ducked = false;
            move.direc = intent;
        }

        if(!ducked) {
            if (touch == 0) move.direc = Movement.Direc.IDLE;
            else {
                if (screenX > touchR) move.direc = Movement.Direc.LEFT;
                if (screenX < touchL) move.direc = Movement.Direc.RIGHT;
            }
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {

        if(keycode == Input.Keys.DOWN) {
            ducked = true;
            move.direc = Movement.Direc.DUCK;
        }
        if(!ducked) {
            if (keycode == Input.Keys.SPACE) {
                move.jump();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                move.direc = Movement.Direc.RIGHT;
            }
            if (keycode == Input.Keys.LEFT) {
                move.direc = Movement.Direc.LEFT;
            }
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

        if(keycode == Input.Keys.DOWN) ducked = false;

        if(!ducked) {
            if (keycode != Input.Keys.SPACE) move.direc = Movement.Direc.IDLE;
        }

        return false;
    }
    @Override
    public boolean keyTyped(char character) {
        return false;
    }
}
