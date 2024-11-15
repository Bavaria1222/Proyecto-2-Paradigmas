import { THeader } from 'src/models/table';
import CandyPage from '../generic/page';
import CandyTableGeneric from '../generic/table';
import { TActions, ModalTypes } from 'src/models/action-types';
import React, { useCallback, useState } from 'react';
import { getActionText } from 'src/utils/actions-utils';
import { routes } from 'src/router/routes';
import GetPrioridad from 'src/domain/prioridad/GetPrioridad';
import GetTareas from 'src/domain/tarea/GetTarea';
import { useNavigate } from 'react-router-dom';
import GetTarea from 'src/domain/tarea/GetTarea';


const TareaLista = () => {
    const [selectedRow, setSelectedRow] = useState<any | null>(null);
    const navigate = useNavigate();

    const breadcrumbs = [
        { label: "Tareas", bold: true }
    ];

    const headers = [
        new THeader('Id', 'id', 20),
        new THeader('Nombre', 'nombre', 20),
        new THeader('Tiempo Estimado', 'tiempoEstimado', 20),
        new THeader('Restriccion de Clima', 'restriccionClima', 20),
        new THeader('Estado', 'estado', 20),
        new THeader('Hora de Inicio', 'horaInicio', 20),
        new THeader('Fecha Limite', 'fechaLimite', 20),

    ];

    const handleEdit = async () => {
        navigate(routes.tarea + "/" + selectedRow.id);
    };

    const actions = [

        new TActions(
            ModalTypes.None,
            "Editar",
            handleEdit
        ),
    ];

    const loadTareas = useCallback(async () => {
        const tareas = await GetTareas();
        console.log('Fetched Tareas:', tareas);
        return Array.isArray(tareas) ? tareas : [];
    }, []);

    const handleSelectItem = (row: any) => {
        setSelectedRow(row);
        console.log('Selected Row:', row);
    };

    return (
        <CandyPage
            title='Listado de Tareas'
            description={'Módulo de gestión de tareas'}
            breadcrumbs={breadcrumbs}
            isExportable={false}
            redirectLabel="Agregar"
            redirectTo={routes.tarea}
        >
            <CandyTableGeneric
                headers={headers}
                isActionsEnabled={true}
                onLoadData={loadTareas}
                showFilter={true}
                actions={actions}
                onSelectItem={handleSelectItem}
            />
        </CandyPage>
    );
};

export default TareaLista;
