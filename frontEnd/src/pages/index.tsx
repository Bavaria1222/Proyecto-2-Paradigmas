import { Box, Container, Typography, Grid } from '@mui/material';
import { FC } from 'react';
const logoImage = '/lg.png';


const Page: FC = () => {

  return (
    <Box
      component="main"
      display="flex"
      flexDirection="column"
      minHeight="100%"
      sx={{ backgroundColor: '#E9E9E9' }}
    >
      <Container
        disableGutters
        maxWidth="xl"
        sx={{ flex: 1, display: 'flex', flexDirection: 'column', alignItems: 'center', px: { xs: 2, md: 3 } }}
      >
        <Grid container
          spacing={2}
          sx={{ width: '100%', mt: { xs: 5, md: 10 }, display: 'flex', justifyContent: 'center', alignItems: 'center', textAlign: 'center' }}
        >
          <Grid item
            xs={12}>

          </Grid>
          <Grid item
            xs={12}>
            <Typography variant="h3"
              component="div"
              sx={{ fontWeight: 'bold', mt: 1, fontSize: { xs: '1.5rem', md: '2.125rem' } }}>
              Te damos la bienvenida al proyecto de Paradigmas
            </Typography>
          </Grid>
        </Grid>
      </Container>

    </Box>
  );
};

export default Page;
