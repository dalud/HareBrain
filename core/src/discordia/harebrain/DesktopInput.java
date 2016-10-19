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

    public DesktopInput(Movement move){
        half = Gdx.graphics.getWidth()/2;
        dragSens = Gdx.graphics.getHeight()/20;
        this.move = move;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        AndroidInput.touch--;

        if(AndroidInput.ducker == pointer) {
            AndroidInput.ducked = false;
            move.direc = Movement.Direc.IDLE;
        }

        if(!AndroidInput.ducked) {
            if (AndroidInput.touch == 0) move.direc = Movement.Direc.IDLE;
            else if (screenX > half) move.direc = Movement.Direc.LEFT;
            else if (screenX < half) move.direc = Movement.Direc.RIGHT;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if(!AndroidInput.ducked) {
            if (screenY + dragSens < AndroidInput.initY) {
                move.jump();
            }
            else if(screenY - dragSens > AndroidInput.initY) {
                if(AndroidInput.initY != 0) { //hirveä purkka, mutta haluan päästä pelaamaan EVEä :D
                    AndroidInput.ducked = true;
                    AndroidInput.ducker = pointer;
                    System.out.println("drag duck, koska initY:" + AndroidInput.initY + " ja screenY:" + screenY);
                    move.direc = Movement.Direc.DUCK;
                }
            }
            AndroidInput.initY = screenY;
        }
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
            AndroidInput.ducked = true;
            move.direc = Movement.Direc.DUCK;
        }
        if(!AndroidInput.ducked) {
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
        if(keycode == Input.Keys.DOWN) AndroidInput.ducked = false;
        if(!AndroidInput.ducked) {
            if (keycode != Input.Keys.SPACE) move.direc = Movement.Direc.IDLE;
        }

        return false;
    }
    @Override
    public boolean keyTyped(char character) {
        return false;
    }
}
