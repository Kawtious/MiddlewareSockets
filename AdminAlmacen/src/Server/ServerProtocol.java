/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import Datos.ProductoDTO;
import Datos.VentaDTO;

/**
 * Version: 0.0.1 | Check changelog for a list of changes
 *
 * It is recommended to run this program with JDK 17
 *
 * @author Equipo 5
 */
public class ServerProtocol {

    public ServerProtocol() {
    }

    /**
     *
     * @param incomingMessage
     * @return
     */
    public Object processData(Object incomingMessage) {

        if (incomingMessage instanceof ProductoDTO) {
            System.out.println("[Server/ServerProtocol] Received message from client: " + incomingMessage);
            return SocketMessage.ADD_PRODUCT;
        } else if (incomingMessage instanceof VentaDTO) {
            System.out.println("[Server/ServerProtocol] Received message from client: " + incomingMessage);
            return SocketMessage.ADD_VENTA;
        }

        return null;
    }
}
