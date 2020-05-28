/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.datamodel;


import com.avbravo.autentificacionclient.entity.Applicative;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author avbravo
 */
public class ApplicativeDataModel extends ListDataModel<Applicative> implements SelectableDataModel<Applicative> {

    public ApplicativeDataModel() {
    }

    public ApplicativeDataModel(List<Applicative> data) {
        super(data);
    }

    @Override
    public Applicative getRowData(String rowKey) {
        List<Applicative> list = (List<Applicative>) getWrappedData();

        for (Applicative applicativeo : list) {
            if (applicativeo.getIdapplicative().equals(rowKey)) {
                return applicativeo;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(Applicative applicativeo) {
        return applicativeo.getIdapplicative();
    }

}
