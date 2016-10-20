package discordia.harebrain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by dalud on 5.10.2016.
 */

public class DesktopInput implements InputProcessor {
    private Movement move;
    private int half, dragSens;
    public volatile static int touch, ducker;
    public volatile static float initY;
    public static volatile boolean ducked;

    public DesktopInput(Movement move){
        half = Gdx.graphics.getWidth()/2;
        dragSens = Gdx.graphics.getHeight()/20;
        this.move = move;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch++;
        //System.out.println(touch);

        if(screenY > Gdx.graphics.getHeight()/6*5 && screenX > Gdx.graphics.getWidth()/3 && screenY < Gdx.graphics.getWidth()/3*2) {
            ducked = true;
            ducker = pointer;
            initY = 0;
            move.direc = Movement.Direc.DUCK;
            System.out.println("touch duck");
        }

        if(!ducked) {
            if (screenX > Gdx.graphics.getWidth()/16*10 && move.direc != Movement.Direc.LEFT) move.direc = Movement.Direc.RIGHT;
            else if (screenY < Gdx.graphics.getWidth()/16*6 && move.direc != Movement.Direc.RIGHT)
                move.direc = Movement.Direc.LEFT;
            else if (touch > 1) {
                if (move.direc == Movement.Direc.RIGHT || move.direc == Movement.Direc.LEFT)
                    move.jump();
            }
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        touch--;

        if(ducker == pointer) {
            ducked = false;
            move.direc = Movement.Direc.IDLE;
        }

        if(!ducked) {
            if (touch == 0) move.direc = Movement.Direc.IDLE;
            else if (screenX > half) move.direc = Movement.Direc.LEFT;
            else if (screenX < half) move.direc = Movement.Direc.RIGHT;
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
