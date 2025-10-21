import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Navbar } from '../navbar/navbar';
import { ApiService } from '../api.service';
import { InterviewStateService } from '../interview-state-service';

@Component({
  selector: 'app-sectiontwo',
  imports: [CommonModule, FormsModule, Navbar],
  templateUrl: './sectiontwo.html',
  styleUrl: './sectiontwo.css'
})
export class Sectiontwo {
  timer = signal(300); // 5 min timer
  question: any = null;
  scenario = '';
  dataPoints: string[] = [];
  followUp = '';
  questionIndex = 0;
  answer = '';
  sessionId = '';

  loading = true;

  constructor(private router: Router,private api: ApiService,private stateService: InterviewStateService) {}

  ngOnInit(): void {
    this.sessionId = localStorage.getItem('sessionId') ?? '';
    // Fetch RCA question from API on component load
    this.api.getRca(this.sessionId, 'rca', this.questionIndex).subscribe({
      next: (resp) => {
        this.question = resp;
        this.scenario = resp.scenario;
        this.dataPoints = resp.dataPoints || [];
        this.followUp = resp.followUp;
        this.loading = false;
        // Start timer after data is loaded
        this.startTimer();
      },
      error: (err) => {
        this.loading = false;
        alert('Failed to load RCA question.');
        console.error(err);
      }
    });
    }

  startTimer(): void {
    const interval = setInterval(() => {
      const current = this.timer();
      if (current > 0) {
        this.timer.set(current - 1);
      } else {
        clearInterval(interval);
        this.onNext();
      }
    }, 1000);
  }
  onNext(): void {
    this.stateService.setState('rca', {
      question: this.question,
      answer: this.answer
    });

    // localStorage.setItem('rcaAnswer', this.answer);
    // this.api
    //   .submitAnswer(this.sessionId, 'rca', this.questionIndex, this.answer)
    //   .subscribe({
    //     next: () => {
          this.router.navigate(['/section3']); 
    //     },
    //     error: (err) => {
    //       alert('Failed to submit answer.');
    //       console.error(err);
    //     }
    //   });
  }
}
