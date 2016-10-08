package discordia.harebrain;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by dalud on 5.10.2016.
 */

public class MyInput implements InputProcessor {
    private Bunny bunny;

    public MyInput(Bunny bunny){
                this.bunny = bunny;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(screenX > 640) {
            bunny.state = Bunny.State.HOP_RIGHT;
        }
        else {
            bunny.state = Bunny.State.HOP_LEFT;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(screenX > 640) bunny.state = Bunny.State.SIT_RIGHT;
        else bunny.state = Bunny.State.SIT_LEFT;
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
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }
}
