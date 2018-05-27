package com.github.aycu9.server;

import com.github.aycu9.repository.UserRepository;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;


public class ChessAPIServer {
    private final int port;
    private HttpServer server;
    private final Gson gson;
    private final UserRepository userRepository;

    public ChessAPIServer(int port, UserRepository userRepository) {
        this.port = port;
        this.gson = new Gson();
        this.userRepository = userRepository;
    }

    public void start() throws Exception {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/register", this::registerUser);
        server.start();
    }

    public void stop() {
        if (server != null) {
            server.stop(0);// no delay
        }
    }

    private void registerUser(HttpExchange httpExchange) throws IOException {
        httpExchange.sendResponseHeaders(200, 0);
        NewUser newUser = gson.fromJson(new InputStreamReader(httpExchange.getRequestBody()), NewUser.class);
        String uuid = userRepository.assignUUID(newUser.name);
        httpExchange.getResponseBody().write(uuid.getBytes());
        httpExchange.getResponseBody().close();
    }

}
