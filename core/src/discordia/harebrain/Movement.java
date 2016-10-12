package discordia.harebrain;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by dalud on 8.10.2016.
 */

public class Movement {
    private Bunny bunny;
    private OrthographicCamera cam;
    static float SPEED = .03f;
    static float SCALE = SPEED*20;
    private Vector2 velo, pos;
    static float MAX_VELOCITY = 1.3f;


    public Movement(Bunny bunny, OrthographicCamera cam){
        this.bunny = bunny;
        this.cam = cam;

    }

    public void move(){
        velo = bunny.body.getLinearVelocity();
        pos = bunny.body.getPosition();

        if(bunny.state == Bunny.State.HOP_RIGHT) {
            bunny.frame.translate(SPEED, 0);
            if(velo.x < MAX_VELOCITY) bunny.body.applyLinearImpulse(SCALE, 0, pos.x, pos.y, true);
            cam.translate(SPEED, 0, 0);
        }
        else if(bunny.state == Bunny.State.HOP_LEFT) {
            bunny.frame.translate(-SPEED, 0);
            if(velo.x > -MAX_VELOCITY) bunny.body.applyLinearImpulse(-SCALE, 0, pos.x, pos.y, true);
            cam.translate(-SPEED, 0, 0);
        }
        System.out.println(velo);
    }
}
