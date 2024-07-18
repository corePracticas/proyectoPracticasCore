import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { apiUrl } from '../util/sharedVariables';
import { Grue } from '../_model/Grue';

@Injectable({
  providedIn: 'root'
})
export class GruasService {
  constructor(private http: HttpClient) { }
  
  private get tokenConfig() {
    const token: string | null = sessionStorage.getItem('token');
    return {
      headers: {
        Authorization: `Bearer ${token}`
      }
    };
  }

  getAllGrues(){
    return this.http.get<Grue[]>(`${apiUrl}/api/gruas`, this.tokenConfig);
  }
  getGrueById(id: number){
    return this.http.get<Grue>(`${apiUrl}/api/gruas/${id}`, this.tokenConfig);
  }
  updateGrueById(id: number){
    return this.http.put(`${apiUrl}/api/gruas/${id}`, this.tokenConfig);
  }
  deleteGrueById(id: number){
    return this.http.delete(`${apiUrl}/api/gruas/${id}`, this.tokenConfig)
  }
}
