import { Box, Grid, TextField } from '@mui/material';
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
    nombre: '',
    descripcion: '',
    tiempoEstimado: 0,
    restriccionClima: 'NO_APLICA', // Valor por defecto
    estado: 'PENDIENTE', // Valor por defecto
    horaInicio: '',
    fechaLimite: '',
  });

  const [titulo, setTitulo] = useState<string>('Agregar');

  // Verificar si se trata de una edición o una creación
  const checkUser = () => {
    const userId = params.id;
    if (userId) {
      setTitulo('Editar');
      loadTarea(userId); // Cargar datos de la tarea existente
    }
  };

  const loadTarea = async (id: string) => {
    // Implementa tu lógica para obtener los datos de la tarea por ID
    // Por ejemplo:
    // const tareaData = await GetTarea(id);
    // setFormData(tareaData);
  };

  useEffect(() => {
    checkUser();
  }, []);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSave = async () => {
    const userId = params.id;

    const dataToSend = {
      nombre: formData.nombre,
      descripcion: formData.descripcion,
      tiempoEstimado: formData.tiempoEstimado,
      restriccionClima: formData.restriccionClima,
      estado: formData.estado,
      horaInicio: formData.horaInicio,
      fechaLimite: formData.fechaLimite,
    };

    if (userId) {
      try {
        console.log('Updating tarea:', dataToSend);
        await PatchPrioridad(userId, dataToSend);
        console.log(userId, dataToSend);
      } catch (error) {
        console.log('Error updating tarea:', error);
      }
    } else {
      try {
        console.log('Creating tarea:', dataToSend);
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
      <Box component="form" sx={{ mt: 3 }}>
        <Grid container spacing={2}>
          <Grid item xs={12}>
            <TextField
              label="Nombre"
              name="nombre"
              value={formData.nombre}
              onChange={handleChange}
              margin="normal"
              fullWidth
              required
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              label="Descripción"
              name="descripcion"
              value={formData.descripcion}
              onChange={handleChange}
              margin="normal"
              fullWidth
              required
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              label="Tiempo Estimado"
              name="tiempoEstimado"
              type="number"
              value={formData.tiempoEstimado}
              onChange={handleChange}
              margin="normal"
              fullWidth
              required
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              label="Restricción de Clima"
              name="restriccionClima"
              value={formData.restriccionClima}
              onChange={handleChange}
              margin="normal"
              fullWidth
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              label="Estado"
              name="estado"
              value={formData.estado}
              onChange={handleChange}
              margin="normal"
              fullWidth
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              label="Hora de Inicio"
              name="horaInicio"
              type="datetime-local" // Use datetime-local for date and time input
              value={formData.horaInicio}
              onChange={handleChange}
              margin="normal"
              fullWidth
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              label="Fecha Límite"
              name="fechaLimite"
              type="datetime-local" // Use datetime-local for date and time input
              value={formData.fechaLimite}
              onChange={handleChange}
              margin="normal"
              fullWidth
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