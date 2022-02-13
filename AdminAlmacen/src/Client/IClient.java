/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

/**
 * Version: 0.0.1 | Check changelog for a list of changes
 *
 * @author Equipo 5
 */
public interface IClient {

    /**
     * Attempts to establish a connection to the server
     *
     * @param address IP address of the server
     * @param port Port of the server
     * @param host Determines if the client is the host
     * @return Returns true if a connection was established to the server; false if the connection failed
     */
    boolean connect(String address, int port, boolean host);

    /**
     * Sends data to the server
     *
     * @param message The data to be sent to the server
     * @return Returns true if the message was successfully sent to the server; false if there was a problem sending the data
     */
    boolean send(Object message);

    /**
     * Starts listening and processes new data received from the server
     */
    void listen();

    /**
     * Closes the connection to the server
     */
    void close();

}
