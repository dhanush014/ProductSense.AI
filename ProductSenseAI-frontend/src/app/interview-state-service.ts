import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InterviewStateService {
  private state = new BehaviorSubject({
    userName: '',
    choice: '',
    mcqAnswers: [],
    rca: { question: null, answer: '' },
    estimation: { question: null, estimate: null, reasoning: '' }
  });

  setState(key: string, value: any) {
    const current = this.state.value;
    this.state.next({ ...current, [key]: value });
  }

  getState() {
    return this.state.value;
  }

  resetState() {
    this.state.next({
      userName: '',
      choice: '',
      mcqAnswers: [],
      rca: { question: null, answer: '' },
      estimation: { question: null, estimate: null, reasoning: '' }
    });
  }
}
