import { THeader } from 'src/models/table';
import CandyPage from '../generic/page';
import CandyTableGeneric from '../generic/table';
import { TActions, ModalTypes } from 'src/models/action-types';
import React, { useCallback, useState } from 'react';
import { getActionText } from 'src/utils/actions-utils';
import { routes } from 'src/router/routes';
import GetPrioridad from 'src/domain/prioridad/GetPrioridad';
import { useNavigate } from 'react-router-dom';


const PrioridadLista = () => {
    const [selectedRow, setSelectedRow] = useState<any | null>(null);
    const navigate = useNavigate();

    const breadcrumbs = [
        { label: "Prioridad", bold: true }
    ];

    const headers = [
        new THeader('Id', 'id', 20),
        new THeader('Descripcion', 'descripcion', 20),

    ];

    const handleEdit = async () => {
        navigate(routes.prioridad + "/" + selectedRow.id);
    };



    const actions = [

        new TActions(
            ModalTypes.None,
            "Editar",
            handleEdit
        ),
    ];

    const loadEmpleados = useCallback(async () => {
        const empleados = await GetPrioridad();
        return Array.isArray(empleados) ? empleados : [];
    }, []);

    const handleSelectItem = (row: any) => {
        setSelectedRow(row);
    };

    return (
        <CandyPage
            title='Listado de Prioridades'
            description={'Módulo de gestión de prioridades'}
            breadcrumbs={breadcrumbs}
            isExportable={false}
            redirectLabel="Agregar"
            redirectTo={routes.prioridad}
        >
            <CandyTableGeneric
                headers={headers}
                isActionsEnabled={true}
                onLoadData={loadEmpleados}
                showFilter={true}
                actions={actions}
                onSelectItem={handleSelectItem}
            />
        </CandyPage>
    );
};

export default PrioridadLista;
