package test.endtoend.composer;

import java.io.IOException;

public class FakeBotServer {

    private Process nodeProcess;

    public void start() throws IOException {
       try {
           ProcessBuilder pb = new ProcessBuilder("./start_server.sh");
           nodeProcess = pb.start();
           //nodeProcess.waitFor();
       } catch( Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void stop() {
        if (nodeProcess != null)
            nodeProcess.destroy();
    }

    public void hasRecievedTranslatedScript(String originalText) {
        //check the database for the translated conversation
    }
}
