/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.siuclient.datamodel;

import com.avbravo.siuclient.entity.Matricula;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author avbravo
 */
public class MatriculaDataModel extends ListDataModel<Matricula> implements SelectableDataModel<Matricula> {

    public MatriculaDataModel() {
    }

    public MatriculaDataModel(List<Matricula> data) {
        super(data);
    }

    @Override
    public Matricula getRowData(String rowKey) {
        List<Matricula> list = (List<Matricula>) getWrappedData();

        for (Matricula matriculao : list) {
            if (matriculao.getIdmatricula().equals(rowKey)) {
                return matriculao;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(Matricula matricula) {
        return matricula.getIdmatricula();
    }

}
