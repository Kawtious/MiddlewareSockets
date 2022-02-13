/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import java.io.Serializable;

/**
 * Version: 0.0.1 | Check changelog for a list of changes
 *
 * It is recommended to run this program with JDK 17
 *
 * @author Equipo 5
 */
public class VentaDTO implements Serializable {

    private Producto producto;
    private int quantity;

    /**
     *
     */
    public VentaDTO() {
    }

    public VentaDTO(Producto producto, int quantity) {
        this.producto = producto;
        this.quantity = quantity;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
