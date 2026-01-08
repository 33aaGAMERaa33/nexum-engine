package io.nexum;

import io.nexum.engine.Engine;
import io.nexum.engine.channel.ApplicationProcess;
import io.nexum.engine.models.Size;
import io.nexum.engine.render.Java2DWindow;

import static io.nexum.engine.Engine.LOGGER;

public class TestMain {
    public static void main(String[] args) {
        try {
            final Engine nexum = Engine.initialize(120, new Size(800, 600));
            final Java2DWindow window = new Java2DWindow(nexum.getScreenSize());

            window.prepare(nexum);
            nexum.start(ApplicationProcess.build());
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("TestMain", e.toString());
        }
    }
}
