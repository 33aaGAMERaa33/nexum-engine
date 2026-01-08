package io.nexum;

import io.nexum.channel.ApplicationProcess;
import io.nexum.models.Size;
import io.nexum.render.Java2DWindow;

import static io.nexum.Engine.LOGGER;

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
