package discordia.harebrain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Dalud on 15.10.2016.
 */

public class AndroidInput implements GestureDetector.GestureListener{
    public volatile static Integer touch;
    private int half;
    public volatile static float initY;
    private Movement move;


    public AndroidInput(Movement move) {
        this.move = move;
        half = Gdx.graphics.getWidth()/2;
        touch = 0;
    }
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        touch++;
        System.out.println(touch);
        initY = y;
        if(x > half && move.direc != Movement.Direc.LEFT) move.direc = Movement.Direc.RIGHT;
        else if(x < half && move.direc != Movement.Direc.RIGHT) move.direc = Movement.Direc.LEFT;
        else if(touch > 1) {
            if( move.direc == Movement.Direc.RIGHT || move.direc == Movement.Direc.LEFT)move.jump();
        }

        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        initY = y;
        if(y < Gdx.graphics.getHeight()/3) move.jump();
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
