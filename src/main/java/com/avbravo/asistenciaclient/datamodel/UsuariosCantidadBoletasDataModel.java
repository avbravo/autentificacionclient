/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.asistenciaclient.datamodel;


import com.avbravo.asistenciaclient.entity.Boletas;
import com.avbravo.autentificacion.viewentity.UsuariosCantidadBoletas;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author avbravo
 */
public class UsuariosCantidadBoletasDataModel extends ListDataModel<UsuariosCantidadBoletas> implements SelectableDataModel<UsuariosCantidadBoletas> {

    public UsuariosCantidadBoletasDataModel() {
    }

    public UsuariosCantidadBoletasDataModel(List<UsuariosCantidadBoletas> data) {
        super(data);
    }

    @Override
    public UsuariosCantidadBoletas getRowData(String rowKey) {
        List<UsuariosCantidadBoletas> list = (List<UsuariosCantidadBoletas>) getWrappedData();

        for (UsuariosCantidadBoletas usuariosCantidadBoletaso : list) {
            if (usuariosCantidadBoletaso.getNombreUsuario().equals(rowKey)) {
                return usuariosCantidadBoletaso;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(UsuariosCantidadBoletas usuariosCantidadBoletaso) {
        return usuariosCantidadBoletaso.getNombreUsuario();
    }

}
