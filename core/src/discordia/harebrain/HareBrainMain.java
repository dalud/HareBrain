package discordia.harebrain;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class HareBrainMain extends ApplicationAdapter {
	public static Movement move;
	World world;
	Box2DDebugRenderer debug;

	SpriteBatch batch;
	OrthographicCamera cam;


	static int resoX = 16;
	static int resoY = 9;

	private Bunny bunny;
	private Fourest level;
	private InputMultiplexer inputs;
	private InputProcessor andrIn, deskIn;


	@Override
	public void create () {
		world = new World(new Vector2(0, -10), true);
		debug = new Box2DDebugRenderer();

		batch = new SpriteBatch();
		cam = new OrthographicCamera(resoX, resoY);
		bunny = new Bunny(cam, world);
		level = new Fourest(bunny, world);
		move = new Movement(bunny, cam);
		andrIn = new GestureDetector(new AndroidInput(move));
		deskIn = new DesktopInput(move);
		inputs = new InputMultiplexer(deskIn, andrIn);
		Gdx.input.setInputProcessor(inputs);

		cam.position.set(0, 0, 0);
		cam.update();

	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(cam.combined);
		cam.update();

		move.move();
		bunny.anim();

		batch.begin();
		level.draw(batch);
		bunny.draw(batch);
		batch.end();

		//debug.render(world, cam.combined);
		world.step(1/45f, 6, 2);
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}