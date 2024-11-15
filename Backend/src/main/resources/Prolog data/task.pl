% Estado actual del clima (puedes ajustar dinámicamente)
clima_actual(soleado).

% Tareas con estructura tarea(Nombre, Prioridad, Duracion, Dependencias, CondicionesClimaticas).
tarea(desayunar, alta, 20, [], [soleado]).
tarea(ir_a_la_escuela, alta, 30, [desayunar], [soleado]).
tarea(estudiar, media, 180, [], [independiente]).
% Aquí defines todas tus tareas

% Dependencias entre tareas
dependencia(desayunar, ir_a_la_escuela).

% Verificar todas las dependencias están completadas
verificar_dependencias([]).
verificar_dependencias([Tarea | Resto]) :-
    verificar_dependencia_individual(Tarea),
    verificar_dependencias(Resto).

verificar_dependencia_individual(Tarea) :-
    tarea(Tarea, _, _, Dependencias, _),
    verificar_dependencias_realizadas(Dependencias).

verificar_dependencias_realizadas([]).
verificar_dependencias_realizadas([Dependencia | Resto]) :-
    tarea_completada(Dependencia),
    verificar_dependencias_realizadas(Resto).

% Prioridad entre tareas
prioridad_mayor(alta, media).
prioridad_mayor(alta, baja).
prioridad_mayor(media, baja).

% Comparar prioridad para ordenar
comparar_prioridades(Delta, Tarea1, Tarea2) :-
    tarea(Tarea1, Prioridad1, _, _, _),
    tarea(Tarea2, Prioridad2, _, _, _),
    (prioridad_mayor(Prioridad1, Prioridad2) -> Delta = '<' ; Delta = '>').

% Ordenar tareas
ordenar_por_prioridad(Tareas, TareasOrdenadas) :-
    predsort(comparar_prioridades, Tareas, TareasOrdenadas).

% Condiciones climáticas
condiciones_climaticas_cumplidas(Tarea) :-
    tarea(Tarea, _, _, _, Condiciones),
    verificar_condiciones_climaticas(Condiciones).

verificar_condiciones_climaticas([]).
verificar_condiciones_climaticas([Condicion | Resto]) :-
    clima_actual(Condicion),
    verificar_condiciones_climaticas(Resto).

% Plan óptimo
plan_optimo(Tareas, Plan) :-
    verificar_dependencias(Tareas),
    condiciones_climaticas_cumplidas_para_lista(Tareas),
    ordenar_por_prioridad(Tareas, Plan).

condiciones_climaticas_cumplidas_para_lista([]).
condiciones_climaticas_cumplidas_para_lista([Tarea | Resto]) :-
    condiciones_climaticas_cumplidas(Tarea),
    condiciones_climaticas_cumplidas_para_lista(Resto).
