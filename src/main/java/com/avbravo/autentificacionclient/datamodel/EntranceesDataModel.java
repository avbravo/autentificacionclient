/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.datamodel;


import com.avbravo.autentificacionclient.entity.Entrancees;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author avbravo
 */
public class EntranceesDataModel extends ListDataModel<Entrancees> implements SelectableDataModel<Entrancees> {

    public EntranceesDataModel() {
    }

    public EntranceesDataModel(List<Entrancees> data) {
        super(data);
    }

    @Override
    public Entrancees getRowData(String rowKey) {
        List<Entrancees> list = (List<Entrancees>) getWrappedData();

        for (Entrancees entranceeso : list) {
            if (entranceeso.getIdentrancees().equals(rowKey)) {
                return entranceeso;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(Entrancees entranceeso) {
        return entranceeso.getIdentrancees();
    }

}
