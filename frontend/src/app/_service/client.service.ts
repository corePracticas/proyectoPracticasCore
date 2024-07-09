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
    return this.http.post(`${apiUrl}/api/auth/login`, client)
  }
  getSessionStatus(username: string, token: string) {
    return this.http.get(`${apiUrl}/api/auth/status/${username}`, {
      headers: {
        Authorization: `${token}`,
      },
    });
  }
}
