/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Datos.Venta;
import Presentacion.IUpdate;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import javax.swing.SwingUtilities;

/**
 * Version: 0.0.1 | Check changelog for a list of changes
 *
 * @author Equipo 5
 */
public class ClientSocket {

    private ExecutorService clientSocketExecutor;

    private Socket clientSocket;
    private AtomicReference<ObjectInputStream> clientSocketInput = new AtomicReference<>();
    private AtomicReference<ObjectOutputStream> clientSocketOutput = new AtomicReference<>();
    private IUpdate updater;
    private Object receivedObject;

    private static ClientSocket instance;

    /**
     *
     * @param updater
     */
    private ClientSocket(IUpdate updater) {
        this.updater = updater;
    }

    /**
     *
     * @param updater
     * @return
     */
    public static ClientSocket getInstance(IUpdate updater) {
        if (instance == null) {
            instance = new ClientSocket(updater);
        } else {
            instance.updater = updater;
        }
        return instance;
    }

    /**
     *
     * @param address
     * @param port
     * @throws IOException
     */
    public void connect(String address, int port) throws IOException {
        clientSocket = new Socket(address, port);
        clientSocketOutput.set(new ObjectOutputStream(clientSocket.getOutputStream()));
        clientSocketOutput.get().flush();
        clientSocketInput.set(new ObjectInputStream(clientSocket.getInputStream()));
    }

    /**
     *
     * @param mensaje
     * @throws IOException
     */
    public void send(Object mensaje) throws IOException {
        clientSocketOutput.get();
        System.out.println("[Client/ClientSocket] Sending data to server: " + mensaje.getClass());
        clientSocketOutput.get().writeObject(mensaje);
        clientSocketOutput.get().flush();
    }

    /**
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void listen() throws IOException, ClassNotFoundException {
        clientSocketExecutor = Executors.newSingleThreadExecutor();
        clientSocketExecutor.submit(() -> {
            while (!clientSocket.isClosed()) {
                try {
                    receivedObject = clientSocketInput.get().readObject();

                    if (receivedObject instanceof Venta) {

                    }

                    showChanges();
                } catch (IOException | ClassNotFoundException ex) {
                    break;
                }
            }
        });
    }

    /**
     *
     */
    public void close() {
        try {
            clientSocketInput.get().close();
            clientSocketOutput.get().close();
            clientSocket.close();
            System.out.println("[Client/ClientSocket] Client socket closed");
            clientSocketExecutor.shutdownNow();
        } catch (IOException ex) {
            System.out.println("[Exception] Exception at: "
                    + getClass() + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName()
                    + ex.getMessage());
        }
    }

    /**
     *
     */
    private synchronized void showChanges() {
        SwingUtilities.invokeLater(() -> {
            updater.updateClient(receivedObject);
        });
    }
}
