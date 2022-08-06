package zoo;

import zoo.core.Controller;
import zoo.core.ControllerImpl;
import zoo.core.EngineImpl;
import zoo.io.ConsoleReader;
import zoo.io.ConsoleWriter;

public class Main {

    public static void main(String[] args) {
        Controller controller = new ControllerImpl();

        ConsoleReader reader = new ConsoleReader();
        ConsoleWriter writer = new ConsoleWriter();

        EngineImpl engine = new EngineImpl(reader, writer, controller);
        engine.run();
    }
}
