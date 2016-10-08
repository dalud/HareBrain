package discordia.harebrain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Käyttäjä on 8.10.2016.
 */

public class Fourest {
    private Texture layer1, layer2, layer3;

    public Fourest() {
        layer1 = new Texture(Gdx.files.internal("Fourest/fL1.png"));
        layer2 = new Texture(Gdx.files.internal("Fourest/fL2.png"));
    }
    public void draw(SpriteBatch batch) {
        batch.draw(layer2, -layer2.getWidth()/2, -Gdx.graphics.getHeight()/2);
        batch.draw(layer1, -layer1.getWidth()/2, -Gdx.graphics.getHeight()/2);
    }
}
