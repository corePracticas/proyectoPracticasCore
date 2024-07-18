import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { apiUrl } from '../util/sharedVariables';

@Injectable({
  providedIn: 'root'
})
export class RentasService {
  
  constructor(private http: HttpClient) { }
  private get tokenConfig() {
    const token: string | null = sessionStorage.getItem('token');
    return {
      headers: {
        Authorization: `Bearer ${token}`
      }
    };
  }
  getAllRents(){
    return this.http.get(`${apiUrl}/api/alquileres`, this.tokenConfig);
  }
  getRentById(id: number){
    return this.http.get(`${apiUrl}/api/alquileres/${id}`, this.tokenConfig);
  }
  updateRentById(id: number){
    return this.http.put(`${apiUrl}/api/alquileres/${id}`, this.tokenConfig);
  }
  deleteRentById(id: number){
    return this.http.delete(`${apiUrl}/api/alquileres/${id}`, this.tokenConfig)
  }
}
