/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.siuclient.datamodel;

import com.avbravo.siuclient.entity.Estudiante;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author avbravo
 */
public class EstudianteDataModel extends ListDataModel<Estudiante> implements SelectableDataModel<Estudiante> {

    public EstudianteDataModel() {
    }

    public EstudianteDataModel(List<Estudiante> data) {
        super(data);
    }

    @Override
    public Estudiante getRowData(String rowKey) {
        List<Estudiante> list = (List<Estudiante>) getWrappedData();

        for (Estudiante estudianteo : list) {
            if (estudianteo.getIdestudiante().equals(rowKey)) {
                return estudianteo;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(Estudiante estudiante) {
        return estudiante.getIdestudiante();
    }

}

