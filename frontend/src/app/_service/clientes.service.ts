import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { apiUrl } from '../util/sharedVariables';

@Injectable({
  providedIn: 'root'
})
export class ClientesService {

  constructor(private http: HttpClient) { }
  private get tokenConfig() {
    const token: string | null = sessionStorage.getItem('token');
    return {
      headers: {
        Authorization: `Bearer ${token}`
      }
    };
  }
  getAllClients(){
    return this.http.get(`${apiUrl}/api/clientes`, this.tokenConfig);
  }
  getClientById(id: number){
    return this.http.get(`${apiUrl}/api/clientes/${id}`, this.tokenConfig);
  }
  updateClientById(id: number){
    return this.http.put(`${apiUrl}/api/clientes/${id}`, this.tokenConfig)
  }
  deleteClientById(id: number){
    return this.http.delete(`${apiUrl}/api/clientes/${id}`, this.tokenConfig);
  }
}
