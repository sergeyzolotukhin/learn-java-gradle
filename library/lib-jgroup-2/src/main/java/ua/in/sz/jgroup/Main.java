package ua.in.sz.jgroup;

import lombok.extern.slf4j.Slf4j;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ObjectMessage;
import org.jgroups.Receiver;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Slf4j
public class Main implements Receiver {
    JChannel channel;
    String user_name=System.getProperty("user.name", "n/a");

    public static void main(String[] args) throws Exception {
        new Main().start();
    }

    private void start() throws Exception {
        channel = new JChannel();
        channel.setReceiver(this);
        channel.connect("ChatCluster");
        eventLoop();
        channel.close();
    }

    private void eventLoop() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                System.out.print("> ");
                System.out.flush();
                String line = in.readLine().toLowerCase();
                if (line.startsWith("quit") || line.startsWith("exit")) {
                    break;
                }

                line = "[" + user_name + "] " + line;
                Message msg = new ObjectMessage(null, line);
                channel.send(msg);
            } catch (Exception e) {
                log.error("Error: ", e);
            }
        }
    }

    @Override
    public void receive(Message msg) {
        System.out.println(": " + msg.getObject());
    }
}