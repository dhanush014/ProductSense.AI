import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from '../api.service';
import { InterviewStateService } from '../interview-state-service';
import { Navbar } from '../navbar/navbar';

@Component({
  selector: 'app-sectionthree',
  imports: [CommonModule, FormsModule,Navbar],
  templateUrl: './sectionthree.html',
  styleUrl: './sectionthree.css'
})
export class Sectionthree implements OnInit{
  timer = signal(300); // 5 minutes
  question = '';
  hints: string[] = [];
  estimate: number | null = null;
  reasoning: string = '';
  sessionId = '';
  loading = true;
  questionIndex = 0;

  constructor(private router: Router, private api: ApiService,private stateService: InterviewStateService) {}


  ngOnInit(): void {
    this.sessionId = localStorage.getItem('sessionId') ?? '';

    // Fetch estimation question from API
    this.api.getEstimation(this.sessionId, 'estimation', this.questionIndex).subscribe({
      next: (resp) => {
        this.question = resp.question;
        this.hints = resp.hints || [];
        this.loading = false;
        this.startTimer();
      },
      error: (err) => {
        this.loading = false;
        alert('Failed to load estimation question.');
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
        this.onSubmit();
      }
    }, 1000);
  }

  onSubmit(): void {

    this.stateService.setState('estimation', {
      question: { question: this.question, hints: this.hints },
      estimate: this.estimate,
      reasoning: this.reasoning
    });
    this.submitFinalAnswers();
    // const answerObj = {
    //   estimate: this.estimate,
    //   reasoning: this.reasoning
    // };
    // localStorage.setItem('estimationAnswer', JSON.stringify(answerObj));
    // // Submit answer to backend
    // this.api.submitAnswer(
    //   this.sessionId, 'estimation', this.questionIndex, answerObj
    // ).subscribe({
    //   next: () => {
    //     this.router.navigate(['/loading']); // or your results/next page
    //   },
    //   error: (err) => {
    //     alert('Failed to submit estimation.');
    //     console.error(err);
    //   }
    // });
  }
  submitFinalAnswers(): void {
    const state = this.stateService.getState();
    const userName = localStorage.getItem('userName') || 'User';
    const choice = localStorage.getItem('choice') || 'product';

    const payload = {
      userName,
      choice,
      mcqAnswers: state.mcqAnswers,
      rca: state.rca,
      estimation: state.estimation
    };
    this.api.submitFinalScore(payload).subscribe({
      next: (resp) => {
        // 🟢 NEW: Store feedback and navigate to results
        localStorage.setItem('finalScore', JSON.stringify(resp));
        this.router.navigate(['/results']);
      },
      error: (err) => {
        alert('Failed to submit interview.');
        console.error(err);
      }
    });
  }

}
