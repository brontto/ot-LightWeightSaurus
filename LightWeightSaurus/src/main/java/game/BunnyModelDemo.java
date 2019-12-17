package game;

import engine.GameItem;
import engine.IGameLogic;
import engine.Window;
import graphics.Camera;
import graphics.Mesh;
import graphics.Renderer;
import graphics.Texture;
import utils.FileUtils;
import utils.OBJLoader;

public class BunnyModelDemo implements IGameLogic {

    private final Renderer renderer;

    private final Camera camera;

    private GameItem[] gameItems;

    public BunnyModelDemo() {
        this.renderer = new Renderer();
        this.camera = new Camera();
    }

    @Override
    public void init(Window window) throws Exception {
        renderer.init(window);
        Mesh mesh = OBJLoader.loadMesh("/models/bunny.obj");
        GameItem gameItem1 = new GameItem(mesh);
        gameItem1.setScale(0.5f);
        gameItem1.setPosition(0, -0.5f, -2);

        gameItems = new GameItem[]{gameItem1};

    }

    @Override
    public void update(float interval) {
        for (GameItem item: gameItems) {

            float rotation = item.getRotation().y + 1.5f;
            if (rotation > 360) {
                rotation = 0;
            }
            item.setRotation(0, rotation, 0);
        }
    }

    @Override
    public void render(Window window) {
        renderer.render(window, camera, gameItems);
    }

    @Override
    public void destroy() {
        renderer.destroy();
        for (GameItem item : gameItems) {
            item.getMesh().destroy();
        }
    }
}
