package com.github.aycu9;

import com.github.aycu9.repository.InMemoryUserRepository;
import com.github.aycu9.server.ChessAPIServer;

/**
 * Created by Libra on 2018-05-12.
 */
public class Main {

    public static void main (String[]args){

        System.out.println("Starting chess server on Port 5000");
        ChessAPIServer server = new ChessAPIServer(5000, new InMemoryUserRepository());
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
