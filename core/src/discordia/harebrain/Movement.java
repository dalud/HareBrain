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
    private float speed, jump, jumpX, jumpVeloX;
    public static float maxSpeed;
    private volatile Bunny.State idle;
    private volatile boolean airborne;
    public enum Direc {
                        RIGHT,
                        LEFT,
                        IDLE,
                        DUCK
                                }
    public Direc direc;

    public Movement(Bunny bunny, OrthographicCamera cam) {
        this.bunny = bunny;
        this.cam = cam;
        speed = 1;
        maxSpeed = 2;
        jump = 20;
        idle = Bunny.State.SIT_RIGHT;
        direc = Direc.IDLE;
        cam.position.set(0, 0, 0);
    }

    public void move() {
        velo = bunny.body.getLinearVelocity();
        pos = bunny.body.getPosition();

        if (velo.y == 0) airborne = false;

        if (direc == Direc.IDLE) {
            bunny.body.setLinearVelocity(velo.x, velo.y);
            bunny.state = idle;
        }
        if (velo.y < -.1f) {
            airborne = true;
            if (idle == Bunny.State.SIT_LEFT) {
                bunny.state = Bunny.State.FALL_LEFT;
                idle = Bunny.State.SIT_LEFT;
            } else {
                bunny.state = Bunny.State.FALL_RIGHT;
                idle = Bunny.State.SIT_RIGHT;
            }
        }
        if (velo.y > 1) {
            if (idle == Bunny.State.SIT_LEFT) {
                bunny.state = Bunny.State.JUMP_LEFT;
                idle = Bunny.State.SIT_LEFT;
            } else {
                bunny.state = Bunny.State.JUMP_RIGHT;
                idle = Bunny.State.SIT_RIGHT;
            }

        }
        if (!airborne) {
            if (direc == Direc.RIGHT) {
                bunny.state = Bunny.State.HOP_RIGHT;
                idle = Bunny.State.SIT_RIGHT;
                if (velo.x < maxSpeed) bunny.body.applyLinearImpulse(speed, 0, pos.x, pos.y, true);
            }
            if (direc == Direc.LEFT) {
                bunny.state = Bunny.State.HOP_LEFT;
                idle = Bunny.State.SIT_LEFT;
                if (velo.x > -maxSpeed)
                    bunny.body.applyLinearImpulse(-speed, 0, pos.x, pos.y, true);
            }
            if (direc == Direc.DUCK) {
                if(idle == Bunny.State.SIT_RIGHT) bunny.state = Bunny.State.DUCK_RIGHT;
                else bunny.state = Bunny.State.DUCK_LEFT;
            }
        }
        cam.position.set(pos.x, 0, 0);
    }

    public void jump() {
        if (direc == Direc.RIGHT) {
            jumpX = pos.x + .05f;
            jumpVeloX = velo.x;
        }
        else if (direc == Direc.LEFT) {
            jumpX = pos.x - .05f;
            jumpVeloX = velo.x;
        }
        else if(direc == Direc.IDLE) {
            if (idle == Bunny.State.SIT_RIGHT) {
                jumpX = pos.x;
                jumpVeloX = 1f;
            } else if(idle == Bunny.State.SIT_LEFT) {
                jumpX = pos.x;
                jumpVeloX = -1f;
            }
        }

        if (!airborne) {
            airborne = true;
            bunny.body.applyLinearImpulse(jumpVeloX, jump, jumpX, pos.y, true);
            if (idle == Bunny.State.SIT_LEFT) {
                bunny.state = Bunny.State.JUMP_LEFT;
                idle = Bunny.State.SIT_LEFT;
            } else if (idle == Bunny.State.SIT_RIGHT) {
                bunny.state = Bunny.State.JUMP_RIGHT;
                idle = Bunny.State.SIT_RIGHT;
            }
        }
    }
}