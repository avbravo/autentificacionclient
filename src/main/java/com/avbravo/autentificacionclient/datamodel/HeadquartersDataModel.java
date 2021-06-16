/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.datamodel;


import com.avbravo.autentificacionclient.entity.Headquarters;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author avbravo
 */
public class HeadquartersDataModel extends ListDataModel<Headquarters> implements SelectableDataModel<Headquarters> {

    public HeadquartersDataModel() {
    }

    public HeadquartersDataModel(List<Headquarters> data) {
        super(data);
    }

    @Override
    public Headquarters getRowData(String rowKey) {
        List<Headquarters> list = (List<Headquarters>) getWrappedData();

        for (Headquarters headquarterso : list) {
            if (headquarterso.getIdheadquarters().equals(rowKey)) {
                return headquarterso;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(Headquarters headquarters) {
        return headquarters.getIdheadquarters();
    }

}
