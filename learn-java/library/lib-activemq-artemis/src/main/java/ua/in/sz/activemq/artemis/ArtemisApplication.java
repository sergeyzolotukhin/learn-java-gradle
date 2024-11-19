package ua.in.sz.activemq.artemis;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.artemis.api.core.TransportConfiguration;
import org.apache.activemq.artemis.api.jms.ActiveMQJMSClient;
import org.apache.activemq.artemis.api.jms.JMSFactoryType;
import org.apache.activemq.artemis.core.remoting.impl.netty.NettyConnectorFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

@SuppressWarnings("ALL")
@Slf4j
public class ArtemisApplication {
    public static void main(String[] args) throws Exception {
        TransportConfiguration transportConfiguration = new TransportConfiguration(NettyConnectorFactory.class.getName());

        ConnectionFactory cf = ActiveMQJMSClient.createConnectionFactoryWithoutHA(JMSFactoryType.CF, transportConfiguration);

        Queue orderQueue = ActiveMQJMSClient.createQueue("OrderQueue");

        Connection connection = cf.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        MessageProducer producer = session.createProducer(orderQueue);
        MessageConsumer consumer = session.createConsumer(orderQueue);

        connection.start();

        TextMessage message = session.createTextMessage("This is an order");
        producer.send(message);

        TextMessage receivedMessage = (TextMessage) consumer.receive();
        log.info("Got order: {}", receivedMessage.getText());
    }
}
