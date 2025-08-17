package ua.in.sz.tomcat.websocket;

import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

@Slf4j
@ServerEndpoint("/socket")
public class WebSocketEndpoint {
    private static final String TEXT = "With your consent, JetBrains may also use cookies and your IP address to collect " +
            "individual statistics and provide you with personalized offers and ads subject to the Privacy " +
            "Notice and the Terms of Use. JetBrains may use third-party services for this purpose. You can adjust or " +
            "withdraw your consent at any time by visiting the Opt-Out."
            ;

    @OnOpen
    public void handleTextMessage(final Session session) {
        log.info("Connected");

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    log.info("Send message");
                    session.getBasicRemote().sendText(TEXT);
                } catch (IOException ex) {
                    log.error("Can not sent message: ", ex);
                }
            }
        };

        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 1000, 1000);
    }
}