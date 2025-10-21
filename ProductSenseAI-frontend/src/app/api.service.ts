import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ApiService {
  api = 'https://product-sense-ai-backend.onrender.com/api';

  constructor(private http: HttpClient) {}

  startInterview(name: string, choice: string): Observable<any> {
    return this.http.post(`${this.api}/start`, { name, choice });
  }

  getMcq(sessionId: string, questionType: string, questionNumber: number): Observable<any> {
    return this.http.post(`${this.api}/question/mcq`, { sessionId, questionType, questionNumber });
  }

  getRca(sessionId: string, questionType: string, questionNumber: number): Observable<any> {
    return this.http.post(`${this.api}/question/rca`, { sessionId, questionType, questionNumber });
  }

  getEstimation(sessionId: string, questionType: string, questionNumber: number): Observable<any> {
    return this.http.post(`${this.api}/question/estimation`, { sessionId, questionType, questionNumber });
  }

  submitFinalScore(payload: any): Observable<any> {
    return this.http.post(`${this.api}/final-score`, payload);
  }

  // getFinalScore(sessionId: string): Observable<any> {
  //   return this.http.post(`${this.api}/final-score`, { sessionId });
  // }
}
