import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Navbar } from '../navbar/navbar';
import { ApiService } from '../api.service';
import { InterviewStateService } from '../interview-state-service';

interface Question {
  question: string;
  options: string[];
  selectedAnswer?: string;
}

@Component({
  selector: 'app-sectionone',
  imports: [CommonModule, Navbar],
  templateUrl: './sectionone.html',
  styleUrl: './sectionone.css'
})
export class Sectionone {
  name = '';
  timer = signal(300); // 300 seconds = 5 mins

  questions: Question[] = [];
  wittyGreeting = '';
  selectedAnswers: (string | null)[] = [];

  sessionId = '';

  constructor(private router: Router, private api:ApiService,private stateService: InterviewStateService) {}

  ngOnInit(): void {

    this.sessionId = localStorage.getItem('sessionId') ?? '';
    this.wittyGreeting = localStorage.getItem('wittyGreeting') ?? '';

    this.api.getMcq(this.sessionId, 'mcq', 0).subscribe({
      next: (resp) => {
        this.questions = resp.questions;
        this.selectedAnswers = Array(this.questions.length).fill(null);
      },
      error: (err) => {
        console.error('Failed to load MCQ questions', err);
      }
    });

    this.startTimer();
    
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
  onAnswerSelect(questionIndex: number, selectedOption: string): void {
    this.selectedAnswers[questionIndex] = selectedOption;
    console.log('Current Answers:', this.selectedAnswers);
  }

  onNext(): void {
    const mcqAnswersFormatted = this.questions.map((q, index) => ({
      question: q.question,
      answer: this.selectedAnswers[index]
    }));
    this.stateService.setState('mcqAnswers', mcqAnswersFormatted);
     // Example: submit all answers sequentially or batched
    //  for (let i=0; i < this.questions.length; i++) {
    //   const answer = this.selectedAnswers[i];
    //   if (answer != null) {
    //     this.api.submitAnswer(this.sessionId, 'mcq', i, answer).subscribe({
    //       next: () => console.log(`Answer ${i} submitted`),
    //       error: e => console.error(`Submit failed for answer ${i}`, e)
    //     });
    //   }
    // }
    this.router.navigate(['/section2']);
  
    // Optionally validate all questions answered before routing
    // this.router.navigate(['/section2']);
  }
}
