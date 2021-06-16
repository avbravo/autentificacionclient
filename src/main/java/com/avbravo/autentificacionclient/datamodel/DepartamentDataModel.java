/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.datamodel;


import com.avbravo.autentificacionclient.entity.Departament;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author avbravo
 */
public class DepartamentDataModel extends ListDataModel<Departament> implements SelectableDataModel<Departament> {

    public DepartamentDataModel() {
    }

    public DepartamentDataModel(List<Departament> data) {
        super(data);
    }

    @Override
    public Departament getRowData(String rowKey) {
        List<Departament> list = (List<Departament>) getWrappedData();

        for (Departament departamento : list) {
            if (departamento.getIddepartament().equals(rowKey)) {
                return departamento;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(Departament departament) {
        return departament.getIddepartament();
    }

}
