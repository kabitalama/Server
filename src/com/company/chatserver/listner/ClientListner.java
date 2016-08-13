/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.chatserver.listner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author Kabi
 */
public class ClientListner extends Thread {

    private Socket socket;
    private Client client;
    private ClientHandler handler;

    public ClientListner(Socket socket, ClientHandler handler) {
        this.socket = socket;
        this.handler = handler;
        /**
         *  
         * 
         * { {
         *
         * }
         * } else{ handler.broadcastMessage(Client.getUserName() + "Says >"
         * +msg){
         *
         * }
         * }
         * }
         * handler.broadcastMessage(client.getUserName() "Says >" +msg); }
         * }catch(IOException ioe){ System.out.println("..."); } }
         *
         */

    }

    @Override
    public void run() {
        try {
            PrintStream ps = new PrintStream(socket.getOutputStream());
            ps.println("Welcome to Server");
            ps.println("Enter your Name");
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String name = reader.readLine();
            System.out.println("Hello" + name);
            
            Client client = new Client(name, socket);
            client = new Client(name, socket);
            handler.addClient(client);
            handler.broadcastMessage(client.getUserName() + "has joined the room");
            
            while (!isInterrupted()){
                String msg = reader.readLine();
                String[] tokens=msg.split(";;");
                if(tokens[0].equalsIgnoreCase("pm")){
                    if(tokens.length>2){
                        handler.privateMessage(tokens[1],"PM from " +client.getUserName() + ">"+tokens[2]);
                }
            }else{
               handler.broadcastMessage(client.getUserName()+ "Says >" +msg);
            }
            }
        }catch (IOException ioe) {

        }
    }
}
