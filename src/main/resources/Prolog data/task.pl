% Definición de tareas: tarea(Nombre, Prioridad, Duracion, Dependencias, CondicionesClimaticas).

tarea(comprar_comida, alta, 60, [], [soleado]).
tarea(limpiar_casa, media, 120, [comprar_comida], [independiente]).
tarea(informe, alta, 180, [], [independiente]).
tarea(reparar_auto, alta, 240, [], [soleado, nublado]).
tarea(plantar_flores, baja, 90, [comprar_comida], [soleado]).
tarea(reunion_equipo, alta, 60, [], [independiente]).
tarea(entrenamiento, media, 90, [reunion_equipo], [independiente]).
tarea(lavado_de_ropa, baja, 60, [], [nublado, lluvioso]).
tarea(construir_mueble, media, 150, [limpiar_casa], [independiente]).
tarea(jardineria, baja, 120, [plantar_flores], [soleado]).
tarea(estudio, alta, 180, [], [independiente]).
tarea(cita_medica, alta, 30, [], [independiente]).
tarea(paseo_perro, media, 45, [], [soleado, nublado]).
tarea(limpiar_auto, baja, 40, [lavado_de_ropa], [soleado]).
tarea(mantenimiento_jardin, media, 120, [jardineria], [soleado, nublado]).

% Dependencias entre tareas
dependencia(comprar_comida, limpiar_casa).
dependencia(reunion_equipo, entrenamiento).
dependencia(limpiar_casa, construir_mueble).
dependencia(plantar_flores, jardineria).
dependencia(lavado_de_ropa, limpiar_auto).
dependencia(jardineria, mantenimiento_jardin).

% Reglas para verificar si todas las dependencias de una tarea están completadas
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

tarea_completada(Tarea) :-
    tarea(Tarea, _, _, Dependencias, _),
    (Dependencias = [] ; verificar_dependencias_realizadas(Dependencias)).

% Prioridades de tareas
prioridad_mayor(alta, media).
prioridad_mayor(alta, baja).
prioridad_mayor(media, baja).

% Ordenar tareas por prioridad
ordenar_por_prioridad(Tareas, TareasOrdenadas) :-
    predsort(comparar_prioridades, Tareas, TareasOrdenadas).

comparar_prioridades(Delta, Tarea1, Tarea2) :-
    tarea(Tarea1, Prioridad1, _, _, _),
    tarea(Tarea2, Prioridad2, _, _, _),
    (prioridad_mayor(Prioridad1, Prioridad2) -> Delta = '<' ; Delta = '>').

% Condiciones climáticas de tareas
condiciones_climaticas_cumplidas(Tarea) :-
    tarea(Tarea, _, _, _, Condiciones),
    verificar_condiciones_climaticas(Condiciones).

verificar_condiciones_climaticas([]).
verificar_condiciones_climaticas([Condicion | Resto]) :-
    clima_actual(Condicion),
    verificar_condiciones_climaticas(Resto).

% Estado actual del clima
clima_actual(soleado).
clima_actual(nublado).

% Cálculo del tiempo total de un conjunto de tareas
calcular_tiempo([], 0).
calcular_tiempo([Tarea | Resto], TiempoTotal) :-
    tarea(Tarea, _, Duracion, _, _),
    calcular_tiempo(Resto, TiempoResto),
    TiempoTotal is Duracion + TiempoResto.

% Planificación óptima que respeta prioridades, dependencias y condiciones climáticas
plan_optimo(Tareas, Plan) :-
    verificar_dependencias(Tareas),
    condiciones_climaticas_cumplidas_para_lista(Tareas),
    ordenar_por_prioridad(Tareas, Plan).

condiciones_climaticas_cumplidas_para_lista([]).
condiciones_climaticas_cumplidas_para_lista([Tarea | Resto]) :-
    condiciones_climaticas_cumplidas(Tarea),
    condiciones_climaticas_cumplidas_para_lista(Resto).
