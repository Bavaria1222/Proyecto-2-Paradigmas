package com.example.proyecto_2_paradigmas.Prolog;

import org.jpl7.Query;
import org.jpl7.Term;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PrologEngine {

    public PrologEngine() {
        String consultQuery = "consult('src/main/resources/Prolog data/task.pl')";
        Query q1 = new Query(consultQuery);
        if (q1.hasSolution()) {
            System.out.println("Archivo Prolog cargado correctamente.");
        } else {
            System.out.println("Error al cargar el archivo Prolog.");
        }
    }

    public List<Map<String, Term>> obtenerPlanDeTareas(List<Map<String, Object>> tareas) {
        StringBuilder tareasProlog = new StringBuilder("[");

        for (Map<String, Object> tarea : tareas) {
            tareasProlog.append(tarea.get("nombre")).append(",");
        }
        tareasProlog.setLength(tareasProlog.length() - 1);  // Remover la última coma
        tareasProlog.append("]");

        String consulta = "plan_optimo(" + tareasProlog + ", Plan)";
        Query query = new Query(consulta);

        List<Map<String, Term>> resultados = new ArrayList<>();
        while (query.hasMoreSolutions()) {
            resultados.add(query.nextSolution());
        }
        return resultados;
    }
    public boolean verificarTarea(String nombreTarea) {
        String consulta = "tarea(" + nombreTarea + ", _, _, _, _).";
        Query query = new Query(consulta);
        return query.hasSolution();
    }
}
