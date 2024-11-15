import { Box, Button, Grid, TextField, Typography } from '@mui/material';
import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import PatchPrioridad from 'src/domain/prioridad/PatchPrioridad';
import { PostPrioridad } from 'src/domain/prioridad/PostPrioridad';
import TransactionModalManager from 'src/pages/generic/modal-confirmation';
import { routes } from 'src/router/routes';
import CandyPage from '../generic/page';

const Tarea = () => {
  const params = useParams();
  const location = useLocation();
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    descripcion: '',
  });

  const [titulo, setTitulo] = useState<string>('Agregar');
  const maxNombreCharacters = 100;
  const maxDescripcionCharacters = 250;
  interface Prioridad {
    descripcion: string;
  }
  // Función para cargar el empleado si params.id está presente
  /*const loadUser = async (id: string) => {
           const empleado = await GetUser(id) as Empleado;
        setFormData({
            descripcion: Prioridad.descripcion,
        
        });
    };*/

  // Verificar si se trata de una edición o una creación
  const checkUser = () => {
    const userId = params.id;
    if (userId) {
      setTitulo('Editar');
      // loadUser(userId);
    }
  };

  useEffect(() => {
    checkUser();
  }, []);

  const handleChange = (e: any) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSave = async () => {
    const userId = params.id;

    const dataToSend = {
      descripcion: formData.descripcion,
    };

    if (userId) {
      try {
        console.log('Updating tarea:', dataToSend);
        // Aquí va la llamada a la API para actualizar, por ejemplo:
        await PatchPrioridad(userId, dataToSend);
        console.log(userId, dataToSend);
      } catch (error) {
        console.log('Error updating tarea:', error);
      }
    } else {
      try {
        console.log('Creating tarea:', dataToSend);
        // Aquí va la llamada a la API para crear, por ejemplo:
        await PostPrioridad(dataToSend);
      } catch (error) {
        console.log('Error creating tarea:', error);
      }
    }
  };

  const navigateAfterSuccess = () => {
    navigate(routes.tareas);
  };

  const handleCancel = () => {
    navigate(routes.tareas);
  };
  const breadcrumbs = [
    { label: 'Tareas', bold: true, route: routes.tareas, underline: true },
    { label: `${titulo} tarea`, bold: true },
  ];
  return (
    <CandyPage
      title={`${titulo} Tarea`}
      breadcrumbs={breadcrumbs}
      isExportable={false}
    >
      <Box
        component="form"
        sx={{ mt: 3 }}
      >
        <Grid
          container
          spacing={2}
        >
          <Grid
            item
            xs={12}
          >
            <TextField
              label="Descripcion"
              name="descripcion"
              value={formData.descripcion}
              onChange={handleChange}
              margin="normal"
              fullWidth
              required
            />
          </Grid>
        </Grid>

        <Box sx={{ display: 'flex', justifyContent: 'flex-end', gap: 2, mt: 5, mb: 4 }}>
          <TransactionModalManager
            onSave={handleSave}
            onSuccessNavigate={navigateAfterSuccess}
            onCancel={handleCancel}
          />
        </Box>
      </Box>
    </CandyPage>
  );
};

export default Tarea;
