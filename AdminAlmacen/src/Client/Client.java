/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Datos.Producto;
import Datos.ProductoDTO;
import Datos.Venta;
import Datos.VentaDTO;
import Presentacion.IUpdate;
import Server.Server;
import java.io.IOException;
import java.net.ServerSocket;

/**
 * Version: 0.0.1 | Check changelog for a list of changes
 *
 * @author Equipo 5
 */
public class Client implements IClient {

    private ClientSocket clientSocket;

    /**
     * Call for a new client socket to be created
     *
     * @param updatable The JFrame which will be updated accordingly to the received data from the server
     */
    public Client(IUpdate updatable) {
        this.clientSocket = ClientSocket.getInstance(updatable);
    }

    /**
     * Determine if a server is already running on the same network
     *
     * This prevents problems while attempting to host a new server while another one is running on the same network
     *
     * @param port Server port to be checked
     * @return Returns true if a server was found
     * @throws IOException Throws an Input/Output Exception if the client is unable to interact with the server
     */
    private static boolean isServerRunning(int port) throws IOException {
        try ( ServerSocket server = new ServerSocket(port)) {
            return false;
        } catch (java.net.BindException e) {
            return true;
        }
    }

    /**
     * Attempts to establish a connection to the server
     *
     * @param address IP address of the server
     * @param port Port of the server
     * @param host Determines if the client is the host
     * @return Returns true if a connection was established to the server; false if the connection failed
     */
    @Override
    public boolean connect(String address, int port, boolean host) {
        try {
            if (host) {
                if (!isServerRunning(port)) {
                    Server server = new Server();
                    System.out.println("[Server] Hosting new server at " + address + ":" + port);
                    clientSocket.connect(address, port);
                    System.out.println("[Server] Server created successfully!");
                    return true;

                } else {
                    System.out.println("[Server] Could not host a new server. Is another server already being hosted on the same network?");
                    return false;

                }
            } else {
                System.out.println("[Client] Connecting to " + address + ":" + port);
                clientSocket.connect(address, port);
                System.out.println("[Client] Connection successful!");
                return true;

            }
        } catch (IOException ex) {
            System.out.println("[Client] " + "Could not connect to server. Connection either was rejected, or server is not running");
            return false;
        }
    }

    /**
     * Sends data to the server
     *
     * @param message The data to be sent to the server
     * @return Returns true if the message was successfully sent to the server; false if there was a problem sending the data
     */
    @Override
    public boolean send(Object message) {
        try {
            if (message instanceof Venta venta) {

                VentaDTO ventaDTO = new VentaDTO(venta.getProducto(), venta.getQuantity());
                System.out.println("[Client] " + "Sending data to server: " + ventaDTO);
                clientSocket.send(ventaDTO);
                return true;

            } else if (message instanceof Producto producto) {
                ProductoDTO productoDTO = new ProductoDTO(producto.getName(), producto.getCost());
                System.out.println("[Client] " + "Sending data to server: " + productoDTO);
                clientSocket.send(productoDTO);
                return true;
            }
        } catch (IOException ex) {
            System.out.println("[Exception] Exception at: " + getClass() + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName()
                    + "\n" + ex.getMessage());
        }

        return false;
    }

    /**
     * Starts listening and processes new data received from the server
     */
    @Override
    public void listen() {
        try {
            clientSocket.listen();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("[Exception] Exception at: " + getClass() + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName()
                    + "\n" + ex.getMessage() + "\n"
                    + "[Client] Couldn't connect to server. Is server running?"
            );
        }
    }

    /**
     * Closes the connection to the server
     */
    @Override
    public void close() {
        clientSocket.close();
    }
}
