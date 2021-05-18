/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.datamodel;

import com.avbravo.autentificacionclient.entity.History;
import com.avbravo.autentificacionclient.entity.User;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author avbravo
 */
public class HistoryDataModel extends ListDataModel<History> implements SelectableDataModel<History> {

    public HistoryDataModel() {
    }

    public HistoryDataModel(List<History> data) {
        super(data);
    }
    
    @Override
    public Object getRowKey(History history) {
        return history.getIdhistory();
    }

    @Override
    public History getRowData(String rowKey) {
        List<History> list = (List<History>) getWrappedData();

        for (History h: list) {
            if (h.getIdhistory().equals(rowKey)) {
                return h;
            }
        }

        return null;
    }

}
