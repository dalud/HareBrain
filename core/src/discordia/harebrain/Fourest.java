package discordia.harebrain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by dalud on 8.10.2016.
 */

public class Fourest {
    private Texture layer0, layer1, layer2, layer3;
    private int l0X, l0Y, l2X, l2Y;

    private Bunny bunny;

    public Fourest(Bunny bunny) {
        this.bunny = bunny;
        layer0 = new Texture(Gdx.files.internal("Fourest/fL0.png"));
        layer1 = new Texture(Gdx.files.internal("Fourest/fL1.png"));
        layer2 = new Texture(Gdx.files.internal("Fourest/fL2.png"));
        //layer3 = new Texture(Gdx.files.internal("Fourest/fL3.png"));

        l0X = -layer0.getWidth();
        l2X = -layer2.getWidth()/2;
        l0Y = l2Y = -Gdx.graphics.getHeight()/2;
    }
    public void draw(SpriteBatch batch) {

        //batch.draw(layer3, -layer3.getWidth()/2, -Gdx.graphics.getHeight()/2);
        batch.draw(layer2, l2X, l2Y);

        if(bunny.state == Bunny.State.HOP_RIGHT) {
            l0X-=2;
            l2X++;
        }
        else if(bunny.state == Bunny.State.HOP_LEFT) {
            l0X+=2;
            l2X--;
        }

        batch.draw(layer1, -layer1.getWidth()/2, -Gdx.graphics.getHeight()/2);
        batch.draw(layer0, l0X, l0Y);
        batch.draw(layer0, l0X+layer0.getWidth(), l0Y);
    }
}
