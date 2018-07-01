package com.github.aycu9.server;

import com.github.aycu9.data.Team;
import com.github.aycu9.data.User;
import com.github.aycu9.repository.UserRepository;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;


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
        server.createContext("/host", this::hostGame);
        server.createContext("/host_list", this::getListOfHosts);
        server.createContext("/start_game", this::startGame);
        server.createContext("/get_user", this::getUser);
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

    private void hostGame(HttpExchange httpExchange) throws IOException {
        httpExchange.sendResponseHeaders(200, 0);
        HostGameRequest hostGameRequest = gson.fromJson(new InputStreamReader(httpExchange.getRequestBody()), HostGameRequest.class);
        userRepository.hostGame(hostGameRequest.uuid, new Team(hostGameRequest.team));
        httpExchange.getResponseBody().close();
    }

    private void getListOfHosts(HttpExchange httpExchange) throws IOException {
        httpExchange.sendResponseHeaders(200, 0);
        List<User> usersHosting = userRepository.getListOfHosts();
        List<Host> hostList = new ArrayList<>();
        for (User user : usersHosting) {
            hostList.add(new Host(user));
        }
        String hostListJson = gson.toJson(hostList);
        httpExchange.getResponseBody().write(hostListJson.getBytes());
        httpExchange.getResponseBody().close();
    }

    private void startGame (HttpExchange httpExchange) throws IOException {
        httpExchange.sendResponseHeaders(200, 0);
        StartGameRequest startGameRequest = gson.fromJson(new InputStreamReader(httpExchange.getRequestBody()), StartGameRequest.class);
        userRepository.startGame(startGameRequest.hostUuid, startGameRequest.otherUuid);
        httpExchange.getResponseBody().close();
    }

    private void getUser (HttpExchange httpExchange) throws IOException {
        httpExchange.sendResponseHeaders(200, 0);
        GetUserRequest getUserRequest = gson.fromJson(new InputStreamReader(httpExchange.getRequestBody()), GetUserRequest.class);
        User user = userRepository.getUser(getUserRequest.uuid);
        String userJson = gson.toJson(user);
        httpExchange.getResponseBody().write(userJson.getBytes());
        httpExchange.getResponseBody().close();
    }

}
