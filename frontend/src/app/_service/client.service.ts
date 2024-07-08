import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Client, ClientLogin } from '../_model/Client';
import { apiUrl } from '../util/sharedVariables';

@Injectable({
  providedIn: 'root',
})
export class ClientService {
  constructor(private http: HttpClient) {}

  registerClient(client: Client) {
    return this.http.post(`${apiUrl}/api/auth/register`, client);
  }
  loginClient(client: ClientLogin) {
    return this.http
      .post(`${apiUrl}/api/auth/login`, client)

  }
  getSessionStatus(id: number) {
    return this.http.get(`${apiUrl}/api/auth/status/${id}`, {
      headers: {
        Authorization: `Bearer ${sessionStorage.getItem('token')}`,
      },
    });
  }
}
