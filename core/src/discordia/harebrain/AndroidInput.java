package discordia.harebrain;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by dalud on 15.10.2016.
 */

public class AndroidInput implements GestureDetector.GestureListener{
    private Movement move;

    public AndroidInput(Movement move) {
        this.move = move;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {

        /*if(!DesktopInput.ducked) {
            if (y<Gdx.graphics.getHeight()/9*6 && x<Gdx.graphics.getWidth()/16*9 && x>Gdx.graphics.getWidth()/16*7) move.jump();
        }*/
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}