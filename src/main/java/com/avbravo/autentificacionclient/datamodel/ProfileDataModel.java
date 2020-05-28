/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.datamodel;


import com.avbravo.autentificacionclient.entity.Profile;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author avbravo
 */
public class ProfileDataModel extends ListDataModel<Profile> implements SelectableDataModel<Profile> {

    public ProfileDataModel() {
    }

    public ProfileDataModel(List<Profile> data) {
        super(data);
    }

    @Override
    public Profile getRowData(String rowKey) {
        List<Profile> list = (List<Profile>) getWrappedData();

        for (Profile profileo : list) {
            if (profileo.getIdprofile().equals(rowKey)) {
                return profileo;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(Profile profileo) {
        return profileo.getIdprofile();
    }

}
