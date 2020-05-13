/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.datamodel;


import com.avbravo.autentificacionclient.entity.Usuario;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author avbravo
 */
public class UsuarioDataModel extends ListDataModel<Usuario> implements SelectableDataModel<Usuario> {

    public UsuarioDataModel() {
    }

    public UsuarioDataModel(List<Usuario> data) {
        super(data);
    }

    @Override
    public Usuario getRowData(String rowKey) {
        List<Usuario> list = (List<Usuario>) getWrappedData();

        for (Usuario usero : list) {
            if (usero.getUsername().equals(rowKey)) {
                return usero;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(Usuario usero) {
        return usero.getUsername();
    }

}
