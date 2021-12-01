/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.system.asistenciaclient.datamodel;


import com.avbravo.system.asistenciaclient.entity.Boletas;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author avbravo
 */
public class BoletasDataModel extends ListDataModel<Boletas> implements SelectableDataModel<Boletas> {

    public BoletasDataModel() {
    }

    public BoletasDataModel(List<Boletas> data) {
        super(data);
    }

    @Override
    public Boletas getRowData(String rowKey) {
        List<Boletas> list = (List<Boletas>) getWrappedData();

        for (Boletas boletaso : list) {
            if (boletaso.getIdboleta().equals(rowKey)) {
                return boletaso;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(Boletas boletas) {
        return boletas.getIdboleta();
    }

}
