package com.github.aycu9;

import com.github.aycu9.repository.InMemoryUserRepository;
import com.github.aycu9.server.ChessAPIServer;

/**
 * Created by Libra on 2018-05-12.
 */
public class Main {

    public static void main (String[]args){

        int port = 5000;
        String portString = System.getenv("PORT");
        System.out.println("Starting chess server on Port " + portString);
        try{
            port = Integer.parseInt(portString);
        }catch (NumberFormatException ignored){

        }
        ChessAPIServer server = new ChessAPIServer(port, new InMemoryUserRepository());
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
