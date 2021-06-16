/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.siuclient.datamodel;

import com.avbravo.siuclient.entity.Colegio;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author avbravo
 */
public class ColegioDataModel extends ListDataModel<Colegio> implements SelectableDataModel<Colegio> {

    public ColegioDataModel() {
    }

    public ColegioDataModel(List<Colegio> data) {
        super(data);
    }

    @Override
    public Colegio getRowData(String rowKey) {
        List<Colegio> list = (List<Colegio>) getWrappedData();

        for (Colegio colegioo : list) {
            if (colegioo.getIdcolegio().equals(rowKey)) {
                return colegioo;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(Colegio colegio) {
        return colegio.getIdcolegio();
    }

}
