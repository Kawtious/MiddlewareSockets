/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import Datos.ProductoDTO;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Version: 0.0.1 | Check changelog for a list of changes
 *
 * It is recommended to run this program with JDK 17
 *
 * @author Equipo 5
 */
public class ServerThread implements Runnable {

    private AtomicReference<ProductoDTO> productoDTO = new AtomicReference<>();

    private AtomicReference<ObjectInputStream> input = new AtomicReference<>();
    private AtomicReference<ObjectOutputStream> output = new AtomicReference<>();
    private Object incomingMessage;

    private List<ServerThread> threads = Collections.synchronizedList(new ArrayList<>());
    private List<ProductoDTO> productoList = Collections.synchronizedList(new ArrayList<>());
    private ServerProtocol socketProtocol;
    protected Socket socket;

    /**
     *
     * @param socket
     * @param threads
     * @throws IOException
     */
    public ServerThread(Socket socket, List<ServerThread> threads) throws IOException {
        this.socket = socket;
        this.socketProtocol = new ServerProtocol();

        this.threads = threads;

        this.output.set(new ObjectOutputStream(socket.getOutputStream()));
        getOutput().flush();
        this.input.set(new ObjectInputStream(socket.getInputStream()));
    }

    /**
     *
     */
    @Override
    public void run() {
        while (true) {
            try {
                incomingMessage = getInput().readObject();

                Object command = socketProtocol.processData(incomingMessage);

                if (command == SocketMessage.ADD_PRODUCT) {
                    System.out.println("[Server/ServerThread] Processing data: " + incomingMessage);

                    this.productoDTO.set((ProductoDTO) incomingMessage);

                    this.productoList.add(this.productoDTO.get());

                    sendToEveryone(productoList);

                }
            } catch (IOException | ClassNotFoundException ex) {
                break;
            }
        }
    }

    /**
     *
     * @return
     */
    private ObjectInputStream getInput() {
        return this.input.get();
    }

    /**
     *
     * @return
     */
    private ObjectOutputStream getOutput() {
        return this.output.get();
    }

    /**
     *
     * @param message
     */
    private void sendToEveryone(Object message) {
        System.out.println("[Server/ServerThread] Sending data to all threads...");
        threads.forEach(thread -> {
            try {
                thread.getOutput();
                System.out.println("[Server/ServerThread] Sending data to: " + thread);
                System.out.println("[Server/ServerThread] Data: " + message.getClass() + " " + message);
                thread.getOutput().writeObject(message);
                thread.getOutput().flush();
            } catch (IOException ex) {
                System.out.println("[Exception] Exception at: " + getClass() + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "\n" + ex.getMessage());
            }
        });
    }
}
