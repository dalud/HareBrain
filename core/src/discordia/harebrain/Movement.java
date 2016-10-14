package discordia.harebrain;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;


/**
 * Created by dalud on 8.10.2016.
 */

public class Movement {
    private Bunny bunny;
    private OrthographicCamera cam;
    private volatile Vector2 velo, pos;
    private float speed, maxSpeed, jump, jumpMagic, camSpeed;
    private volatile Bunny.State idle;
    private volatile boolean airborne;
    enum Direc {
                RIGHT,
                LEFT, IDLE,
                        }
    public Direc direc;

    public Movement(Bunny bunny, OrthographicCamera cam){
        this.bunny = bunny;
        this.cam = cam;
        speed = 1;
        maxSpeed = 1;
        jump = 20;
        jumpMagic = .1111098416149f;
        camSpeed = .05f;
        idle = Bunny.State.SIT_RIGHT;
        direc = Direc.IDLE;
        cam.position.set(0, 0, 0);
    }

    public void move(){
        velo = bunny.body.getLinearVelocity();
        pos = bunny.body.getPosition();

        if(velo.y < jumpMagic && velo.y > -jumpMagic) airborne = false;

        if(direc == Direc.IDLE) {
            bunny.body.setLinearVelocity(0, velo.y);
            bunny.state = idle;
        }
        if(velo.y < -1) {
            airborne = true;
            if(idle == Bunny.State.SIT_LEFT) {
                bunny.state = Bunny.State.FALL_LEFT;
                idle = Bunny.State.SIT_LEFT;
            }
            else {
                bunny.state = Bunny.State.FALL_RIGHT;
                idle = Bunny.State.SIT_RIGHT;
            }
        }
        if(velo.y > 1) {
            if(idle == Bunny.State.SIT_LEFT) {
                bunny.state = Bunny.State.JUMP_LEFT;
                idle = Bunny.State.SIT_LEFT;
            }
            else {
                bunny.state = Bunny.State.JUMP_RIGHT;
                idle = Bunny.State.SIT_RIGHT;
            }

        }
        if(!airborne) {
            if (direc == Direc.RIGHT) {
                bunny.state = Bunny.State.HOP_RIGHT;
                idle = Bunny.State.SIT_RIGHT;
                if (velo.x < maxSpeed) bunny.body.applyLinearImpulse(speed, 0, pos.x, pos.y, true);
            }
            if (direc == Direc.LEFT) {
                bunny.state = Bunny.State.HOP_LEFT;
                idle = Bunny.State.SIT_LEFT;
                if (velo.x > -maxSpeed) bunny.body.applyLinearImpulse(-speed, 0, pos.x, pos.y, true);
            }
        }
        cam.position.set(pos.x, 0, 0);
        /*System.out.println(velo);
        System.out.println(bunny.state);*/
        //System.out.println(direc);
    }

    public void jump() {
        System.out.println("JUMPING");
        airborne = true;
        bunny.body.applyLinearImpulse(velo.x, jump, pos.x, pos.y, true);
        if(idle == Bunny.State.SIT_LEFT) {
            bunny.state = Bunny.State.JUMP_LEFT;
            idle = Bunny.State.SIT_LEFT;
        }
        else if(idle == Bunny.State.SIT_RIGHT) {
            bunny.state = Bunny.State.JUMP_RIGHT;
            idle = Bunny.State.SIT_RIGHT;
        }
    }
}