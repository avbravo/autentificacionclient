/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.autentificacion.viewentity;

import com.avbravo.jmoordb.pojos.JmoordbResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Soporte-UTICA
 */
public class UsuariosCantidadBoletas {

    private String nombreUsuario;
    private int cantidadBoletas;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getCantidadBoletas() {
        return cantidadBoletas;
    }

    public void setCantidadBoletas(int cantidadBoletas) {
        this.cantidadBoletas = cantidadBoletas;
    }

    public UsuariosCantidadBoletas(String nombreUsuario, int cantidadBoletas) {
        this.nombreUsuario = nombreUsuario;
        this.cantidadBoletas = cantidadBoletas;
    }

    public UsuariosCantidadBoletas() {
        this.nombreUsuario = "";
        this.cantidadBoletas = 0;
    }

    public UsuariosCantidadBoletas(JmoordbResult origen) {
        if (origen != null) {

            if (origen.get("cantidad") != null) {
                try {
                    this.nombreUsuario = origen.get("_id");
                    this.cantidadBoletas = Integer.parseInt(origen.get("cantidad"));

                } catch (Exception e) {
                    System.out.println("Error " + e.getMessage());
                }
            }
        }
    }

    public UsuariosCantidadBoletas(JmoordbEntity origen) {
        if (origen != null) {

            try {
                this.nombreUsuario = origen.getIdData();
                this.cantidadBoletas = Integer.parseInt(origen.getValueData());

            } catch (Exception e) {
                System.out.println("Error " + e.getMessage());
            }

        }
    }

    public Optional<UsuariosCantidadBoletas> jmoorToUsuariosCantidad(JmoordbResult origen) {
        UsuariosCantidadBoletas destino = new UsuariosCantidadBoletas();
        Optional<UsuariosCantidadBoletas> valor = Optional.empty();
        if (origen != null) {

            if (origen.get("cantidad") != null) {
                try {
                    destino.nombreUsuario = origen.get("_id");
                    destino.cantidadBoletas = Integer.parseInt(origen.get("cantidad"));
                    valor = Optional.ofNullable(destino);
                } catch (Exception e) {
                    System.out.println("Error " + e.getMessage());
                }
            }
        }
        return valor;
    }

    public Optional<List<UsuariosCantidadBoletas>> listajmoorToUsuariosCantidad(List<JmoordbResult> listaOrigen) {
        Optional<List<UsuariosCantidadBoletas>> valor = Optional.empty();
        List<UsuariosCantidadBoletas> elementos = new ArrayList<>();
        if (listaOrigen != null && !listaOrigen.isEmpty()) {
            listaOrigen.forEach((elemento) -> {
                try {
                    elementos.add(new UsuariosCantidadBoletas(elemento.get("_id"), Integer.parseInt(elemento.get("cantidad"))));
                } catch (Exception e) {
                    System.out.println("Error al agregar valores a la lista");
                }
            });
            valor = Optional.ofNullable(elementos);
        }
        return valor;
    }

}
