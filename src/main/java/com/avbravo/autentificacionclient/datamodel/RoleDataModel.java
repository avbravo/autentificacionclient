/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.datamodel;


import com.avbravo.autentificacionclient.entity.Role;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author avbravo
 */
public class RoleDataModel extends ListDataModel<Role> implements SelectableDataModel<Role> {

    public RoleDataModel() {
    }

    public RoleDataModel(List<Role> data) {
        super(data);
    }

    @Override
    public Role getRowData(String rowKey) {
        List<Role> list = (List<Role>) getWrappedData();

        for (Role roleo : list) {
            if (roleo.getIdrole().equals(rowKey)) {
                return roleo;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(Role role) {
        return role.getIdrole();
    }

}
