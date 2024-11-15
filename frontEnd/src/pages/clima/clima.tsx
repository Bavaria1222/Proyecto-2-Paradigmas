import { Box, TextField, Typography, MenuItem, Select, InputLabel, FormControl } from '@mui/material';
import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import { PostClima } from 'src/domain/clima/PostClima';
import GetDiaSemana from 'src/domain/diaSemana/GetDiaSemana';
import TransactionModalManager from 'src/pages/generic/modal-confirmation';
import { routes } from 'src/router/routes';
import CandyPage from '../generic/page';

const Clima = () => {
  const params = useParams();
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    tipo: '',
    horaInicio: '',
    horaFin: '',
    diaSemanaId: ''
  });

  const [titulo, setTitulo] = useState<string>('Agregar');
  const [errorMessage, setErrorMessage] = useState<string | null>(null);
  const [diasSemana, setDiasSemana] = useState<any[]>([]);

  const checkUser = () => {
    if (params.id) {
      setTitulo('Editar');
    }
  };

  useEffect(() => {
    checkUser();
    fetchDiasSemana();
  }, [params.id]);

  const fetchDiasSemana = async () => {
    try {
      const dias = await GetDiaSemana();
      setDiasSemana(dias);
    } catch (error) {
      console.error('Error al obtener los días de la semana:', error);
      setErrorMessage('Error al cargar los días de la semana.');
    }
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | { name?: string; value: unknown }>) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSave = async () => {
    try {
      const currentDate = new Date();

      const horaInicioISO = new Date(`${currentDate.toISOString().split('T')[0]}T${formData.horaInicio}:00Z`).toISOString();
      const horaFinISO = new Date(`${currentDate.toISOString().split('T')[0]}T${formData.horaFin}:00Z`).toISOString();

      const dataToSend = {
        ...formData,
        horaInicio: horaInicioISO,
        horaFin: horaFinISO,
      };
      
      console.log('Enviando datos:', dataToSend);
      
      await PostClima(dataToSend);
      navigate(routes.climas);
    } catch (error) {
      console.error('Error al crear clima:', error);
      setErrorMessage('Error al guardar los datos. Inténtalo de nuevo.');
    }
  };

  const handleCancel = () => {
    navigate(routes.clima);
  };

  return (
    <CandyPage
      title={`${titulo} Clima`}
      breadcrumbs={[
        { label: 'Clima', bold: true, route: routes.prioridads, underline: true },
        { label: `${titulo} clima`, bold: true },
      ]}
      isExportable={false}
    >
      <Box component="form" sx={{ mt: 3 }}>
        <Grid container spacing={2}>
          <Grid item xs={12}>
            <TextField
              label="Tipo"
              name="tipo"
              value={formData.tipo}
              onChange={handleChange}
              margin="normal"
              fullWidth
              required
            />
            <TextField
              label="Hora Inicio"
              name="horaInicio"
              type="time"
              value={formData.horaInicio}
              onChange={handleChange}
              margin="normal"
              fullWidth
              required
            />
            <TextField
              label="Hora Fin"
              name="horaFin"
              type="time"
              value={formData.horaFin}
              onChange={handleChange}
              margin="normal"
              fullWidth
              required
            />
            <FormControl fullWidth margin="normal" required>
              <InputLabel>Día de la Semana</InputLabel>
              <Select
                name="diaSemanaId"
                value={formData.diaSemanaId}
                onChange={handleChange}
                label="Día de la Semana"
                disabled={diasSemana.length === 0}
              >
                {diasSemana.length > 0 ? (
                  diasSemana.map((dia) => (
                    <MenuItem key={dia.id} value={dia.id}>
                      {`${dia.id} ${dia.nombre}`}
                    </MenuItem>
                  ))
                ) : (
                  <MenuItem disabled>
                    No hay datos en el sistema
                  </MenuItem>
                )}
              </Select>
            </FormControl>
          </Grid>
        </Grid>

        {errorMessage && (
          <Typography color="error" variant="body2" sx={{ mt: 2 }}>
            {errorMessage}
          </Typography>
        )}

        <Box sx={{ display: 'flex', justifyContent: 'flex-end', gap: 2, mt: 5, mb: 4 }}>
          <TransactionModalManager
            onSave={handleSave}
            onCancel={handleCancel}
          />
        </Box>
      </Box>
    </CandyPage>
  );
};

export default Clima;