/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Version: 0.0.1 | Check changelog for a list of changes
 *
 * It is recommended to run this program with JDK 17
 *
 * @author Equipo 5
 */
public class Server {

    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    private static List<ServerThread> threads = Collections.synchronizedList(new ArrayList<>());

    protected ServerProtocol serverProtocol = new ServerProtocol();
    private Socket socket;
    public static ServerSocket serverSocket;

    /**
     *
     */
    public Server() {
        startServer(4444);
    }

    /**
     *
     * @throws IOException
     */
    public void addClient() throws IOException {
        System.out.println("New connection incoming from: " + socket.getInetAddress());

        ServerThread serverThread = new ServerThread(socket, threads);

        threadPoolExecutor.submit(serverThread);

        threads.add(serverThread);

        System.out.println("[Server] " + threads.size() + " thread" + (threads.size() == 1 ? "" : "s") + " currently running.");
        StringBuilder threadList = new StringBuilder(128).append("[");

        for (int i = 0; i < threads.size(); i++) {
            threadList.append(threads.get(i)).append(", ");
        }

        threadList.append("]");

        System.out.println("Threads: " + threadList.toString());
    }

    /**
     *
     * @param port
     */
    public final void startServer(int port) {
        try {
            serverSocket = new ServerSocket(port); // start the server

            System.out.println("Server initialized at port: " + port);

            threadPoolExecutor.submit(() -> {
                while (!serverSocket.isClosed()) {
                    try {
                        socket = serverSocket.accept();

                        try {

                            addClient();
                        } catch (IOException ex) {
                            System.out.println("[Exception] Exception at: "
                                    + getClass() + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName()
                                    + "\n" + ex.getMessage());
                        }
                    } catch (IOException ex) {
                        // Server closes
                    }
                }
            });
        } catch (IOException ex) {
            System.out.println("[Exception] Exception at: "
                    + getClass() + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName()
                    + "\n" + ex.getMessage());
        }
    }
}
