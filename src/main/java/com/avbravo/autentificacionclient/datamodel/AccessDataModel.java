/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.datamodel;


import com.avbravo.autentificacionclient.entity.Access;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author avbravo
 */
public class AccessDataModel extends ListDataModel<Access> implements SelectableDataModel<Access> {

    public AccessDataModel() {
    }

    public AccessDataModel(List<Access> data) {
        super(data);
    }

    @Override
    public Access getRowData(String rowKey) {
        List<Access> list = (List<Access>) getWrappedData();

        for (Access accesso : list) {
            if (accesso.getIdaccess().equals(rowKey)) {
                return accesso;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(Access access) {
        return access.getIdaccess();
    }

}
