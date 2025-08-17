package ua.in.sz.tomcat.websocket;

import jakarta.websocket.OnMessage;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

@Slf4j
@ServerEndpoint("/socket")
public class WebSocketEndpoint {
    @OnMessage
    public void handleTextMessage(String message, final Session session) {
        log.info("Received message: {}", message);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    log.info("Send message Push");
                    session.getBasicRemote().sendText("Push");
                } catch (IOException ex) {
                    log.error("Can not sent message: ", ex);
                }
            }
        };

        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 3 * 1000);
    }
}