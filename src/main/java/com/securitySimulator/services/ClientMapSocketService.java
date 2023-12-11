package com.securitySimulator.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class ClientMapSocketService {
    public static Socket clientSocket;
    public static ServerSocket server;
    public static BufferedWriter out;

    public ClientMapSocketService() throws IOException {
        server = new ServerSocket(4004);
        log.info("waiting for someone to accept...");
        clientSocket = server.accept();
        log.info("client connected");
    }

    public void SendInfo(Object obj) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        String jsonString = objectMapper.writeValueAsString(obj);

        try {
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            out.write(jsonString);
            out.flush();

        } finally {
            clientSocket.close();
            out.close();
        }
    }

    public void StopConnection() throws IOException {
        server.close();
    }

}
