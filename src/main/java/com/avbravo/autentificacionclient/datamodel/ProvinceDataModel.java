/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.datamodel;

import com.avbravo.autentificacionclient.entity.Province;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author avbravo
 */
public class ProvinceDataModel extends ListDataModel<Province> implements SelectableDataModel<Province> {

    public ProvinceDataModel() {
    }

    public ProvinceDataModel(List<Province> data) {
        super(data);
    }

    @Override
    public Province getRowData(String rowKey) {
        List<Province> list = (List<Province>) getWrappedData();

        for (Province provinceo : list) {
            if (provinceo.getIdprovince().equals(rowKey)) {
                return provinceo;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(Province provinceo) {
        return provinceo.getIdprovince();
    }

}

