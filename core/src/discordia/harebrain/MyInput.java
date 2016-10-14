package discordia.harebrain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;

/**
 * Created by dalud on 5.10.2016.
 */

public class MyInput implements InputProcessor {
    private Movement move;
    private int half, touch;

    public MyInput(Movement move){
        half = Gdx.graphics.getWidth()/2;
        touch = 0;
        this.move = move;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch++;
        if(screenX > half) move.direc = Movement.Direc.RIGHT;
        else move.direc = Movement.Direc.LEFT;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        touch--;
        if(touch == 0) move.direc = Movement.Direc.IDLE;
        else if(screenX > half) move.direc = Movement.Direc.LEFT;
        else if(screenX < half) move.direc = Movement.Direc.RIGHT;
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

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            move.direc = Movement.Direc.RIGHT;
        }
        if(keycode == Input.Keys.LEFT) {
            move.direc = Movement.Direc.LEFT;
        }
        if(keycode == Input.Keys.SPACE) {
            move.jump();
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode != Input.Keys.SPACE) move.direc = Movement.Direc.IDLE;

        return false;
    }
    @Override
    public boolean keyTyped(char character) {
        return false;
    }
}
