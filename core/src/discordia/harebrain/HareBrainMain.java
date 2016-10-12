package discordia.harebrain;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;

public class HareBrainMain extends ApplicationAdapter {
	SpriteBatch batch;
	OrthographicCamera cam;


	static int resoX = 1280;
	static int resoY = 720;

	private Bunny bunny;
	private Fourest level;
	private Movement move;

	@Override
	public void create () {


		batch = new SpriteBatch();
		cam = new OrthographicCamera(resoX, resoY);
		bunny = new Bunny(cam);
		level = new Fourest(bunny);
		move = new Movement(bunny, cam);

		cam.position.set(0, 0, 0);
		cam.update();

	}

	@Override
	public void render () {


		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(cam.combined);
		cam.update();

		bunny.anim();
		move.move();

		batch.begin();
		level.draw(batch);
		bunny.draw(batch);
		batch.end();


	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}