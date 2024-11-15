import { CandyClient, CandyClientOptions } from '@cabysis/candy-client';
import { getToken, isDevelopment } from 'src/utils/constants';

const authOptions: CandyClientOptions = {
  baseUrl: isDevelopment() ? 'http://localhost:8080/api' : 'http://localhost:8080/api',
  headers: {
    Accept: 'application/json',
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*',
  },
  credentials: false,
};

export class AuthClient extends CandyClient {
  constructor() {
    super(authOptions);
  }

  async get<T>(url: string): Promise<T> {
    return super.get<T>(url);
  }

  async post<T>(url: string, data?: any): Promise<T> {
    super.setHeader('X-Client-URL', window.location.href);

    return super.post<T>(url, data);
  }

  async put<T>(url: string, body?: any): Promise<T> {
    super.setHeader('X-Client-URL', window.location.href);
    return super.put<T>(url, body);
  }

  async delete<T>(url: string): Promise<T> {
    super.setHeader('X-Client-URL', window.location.href);
    return super.delete<T>(url);
  }

  //auth
  async login(authCredentialsDto) {
    return this.post(`/login`, authCredentialsDto);
  }

  async registerClient(data: any): Promise<{ message: string }> {
    try {
      const response = await this.post('/registrarCliente', data);
      if (response && typeof response === 'object' && 'message' in response) {
        return response as { message: string };
      } else {
        return { message: 'Registro exitoso' }; // Mensaje por defecto en caso de falta de mensaje
      }
    } catch (error) {
      console.error('Error en el registro:', error);
      throw error;
    }
  }

  //Prioridad
  async getPrioridad() {
    return this.get(`/prioridad`);
  }

  async postPrioridad(empleadoDto) {
    return this.post(`/prioridad`, empleadoDto);
  }

  async patchPrioridad(id: string, updateUserDTO) {
    return this.put(`/prioridad/${id}`, updateUserDTO);
  }
  /*
    async getEmpleado(id: string) {
      return this.get(`/cedula/${id}`);
    }

    async patchStatusEmpleado(id: string, estado: string) {
      return this.patch(`/actualizarEstadoEmpleado/${id}/${estado}`);
    }
  
    async patchEmpleado(id: string, updateUserDTO) {
      return this.patch(`/actualizarEmpleado/${id}`, updateUserDTO);
    }*/

    //Clima
    async getClima() {
      return this.get(`/clima`);
    }
  
    async deleteClima(id: string) {
      return this.delete(`/clima/${id}`);
    }

    async postClima(clima) {
      return this.post(`/clima`, clima);
    }

    //DiaSemana
    async getDiaSemana() {
      return this.get(`/diasemana`);
    }

    //Tarea
    async getTarea() {
      return this.get(`/tarea`);
    }

}
