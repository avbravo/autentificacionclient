/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.datamodel;


import com.avbravo.autentificacionclient.entity.User;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author avbravo
 */
public class UserDataModel extends ListDataModel<User> implements SelectableDataModel<User> {

    public UserDataModel() {
    }

    public UserDataModel(List<User> data) {
        super(data);
    }

    @Override
    public User getRowData(String rowKey) {
        List<User> list = (List<User>) getWrappedData();

        for (User usero : list) {
            if (usero.getUsername().equals(rowKey)) {
                return usero;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(User usero) {
        return usero.getUsername();
    }

}
