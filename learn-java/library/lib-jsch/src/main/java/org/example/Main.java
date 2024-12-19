package org.example;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Main {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 2222;

        String user = "postgres";
        String password = "postgres";

        StringBuilder commands = new StringBuilder();
        // create database dump
//        commands.append("rm -rf ETSE-0001;");
//        commands.append("mkdir ETSE-0001;");
//        commands.append("cd ETSE-0001;");
//        commands.append("export PGPASSWORD='postgres';");
//        commands.append("pg_dump -F t database_name > database_name.tar;");
//        commands.append("cd ~;");
//        commands.append("ls -l;");

        // restore database dump
        commands.append("cd ETSE-0001;");
        commands.append("export PGPASSWORD='postgres';");
        commands.append("psql -c 'create database database_name';");
        commands.append("pg_restore -Ft -d database_name < database_name.tar;");

        try {
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");

            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();
            log.info("Connected");

            Channel channel = session.openChannel("exec");
            ChannelExec channelExec = (ChannelExec) channel;
            channelExec.setCommand(commands.toString());
            channel.setInputStream(null);
            channelExec.setErrStream(System.err);

            InputStream in = channel.getInputStream();
            channel.connect();
            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) {
                        break;
                    }
                    log.info(new String(tmp, 0, i));
                }

                if (channel.isClosed()) {
                    log.info("exit-status: " + channel.getExitStatus());
                    break;
                }

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception ignore) {
                }
            }
            channel.disconnect();
            session.disconnect();
            log.info("DONE");
        } catch (Exception e) {
            log.error("Can not execute", e);
        }
    }
}