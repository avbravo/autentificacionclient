/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.datamodel;


import com.avbravo.autentificacionclient.entity.Institution;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author avbravo
 */
public class InstitutionDataModel extends ListDataModel<Institution> implements SelectableDataModel<Institution> {

    public InstitutionDataModel() {
    }

    public InstitutionDataModel(List<Institution> data) {
        super(data);
    }

    @Override
    public Institution getRowData(String rowKey) {
        List<Institution> list = (List<Institution>) getWrappedData();

        for (Institution institutiono : list) {
            if (institutiono.getIdinstitution().equals(rowKey)) {
                return institutiono;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(Institution institutiono) {
        return institutiono.getIdinstitution();
    }

}
