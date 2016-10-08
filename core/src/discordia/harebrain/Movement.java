package discordia.harebrain;


import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by dalud on 8.10.2016.
 */

public class Movement {
    private Bunny bunny;
    private OrthographicCamera cam;
    private int speed;

    public Movement(Bunny bunny, OrthographicCamera cam){
        this.bunny = bunny;
        this.cam = cam;
        speed = 3;
    }

    public void move(){
        if(bunny.state == Bunny.State.HOP_RIGHT) cam.translate(speed, 0, 0);
        else if(bunny.state == Bunny.State.HOP_LEFT) cam.translate(-speed, 0, 0);
    }
}
