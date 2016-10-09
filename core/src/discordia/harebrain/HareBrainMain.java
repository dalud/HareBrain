package discordia.harebrain;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
		Gdx.gl.glClearColor(91/255f, 110/255f, 225/255f, 0);
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