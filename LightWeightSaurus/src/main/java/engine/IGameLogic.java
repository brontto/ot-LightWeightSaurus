package engine;

import engine.Window;

public interface IGameLogic {
    void init() throws Exception;

    void update(float interval);

    void render(Window window);

    void destroy();
}
