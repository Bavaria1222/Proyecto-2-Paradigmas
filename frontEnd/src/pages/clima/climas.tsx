import { THeader } from 'src/models/table';
import CandyPage from '../generic/page';
import CandyTableGeneric from '../generic/table';
import { TActions, ModalTypes } from 'src/models/action-types';
import React, { useCallback, useState } from 'react';
import { routes } from 'src/router/routes';
import GetClima from 'src/domain/clima/GetClima';
import DeleteClima from 'src/domain/clima/DeleteClima';
import { useNavigate } from 'react-router-dom';

const Climas = () => {
    const [selectedRow, setSelectedRow] = useState<any | null>(null);
    const navigate = useNavigate();

    const breadcrumbs = [
        { label: "Clima", bold: true }
    ];

    const headers = [
        new THeader('Id', 'id', 20),
        new THeader('Tipo', 'tipo', 20),
    ];

    const handleDelete = async () => {
        if (!selectedRow) return;
        const confirmDelete = window.confirm("¿Estás seguro de que deseas eliminar este clima?");
        if (!confirmDelete) return;

        try {
            await DeleteClima(selectedRow.id);
            alert("Clima eliminado exitosamente.");
            setSelectedRow(null);
            navigate(routes.climas);
        } catch (error) {
            console.error('Error al eliminar clima:', error);
            alert("Error al eliminar el clima. Inténtalo de nuevo.");
        }
    };

    const actions = [
        new TActions(
            ModalTypes.None,
            "Eliminar",
            handleDelete
        ),
    ];

    const loadClimas = useCallback(async () => {
        const climas = await GetClima();
        return Array.isArray(climas) ? climas : [];
    }, []);

    const handleSelectItem = (row: any) => {
        setSelectedRow(row);
    };

    return (
        <CandyPage
            title='Listado de climas'
            description={'Módulo de gestión de climas'}
            breadcrumbs={breadcrumbs}
            isExportable={false}
            redirectLabel="Agregar"
            redirectTo={routes.clima}
        >
            <CandyTableGeneric
                headers={headers}
                isActionsEnabled={true}
                onLoadData={loadClimas}
                showFilter={true}
                actions={actions}
                onSelectItem={handleSelectItem}
            />
        </CandyPage>
    );
};

export default Climas;