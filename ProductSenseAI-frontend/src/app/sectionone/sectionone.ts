import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

interface Question {
  question: string;
  options: string[];
  selectedAnswer?: string;
}

@Component({
  selector: 'app-sectionone',
  imports: [CommonModule],
  templateUrl: './sectionone.html',
  styleUrl: './sectionone.css'
})
export class Sectionone {
  name = 'User';  // TODO: Replace with actual passed value or service state
  timer = signal(300); // 300 seconds = 5 mins
  questions: Question[] = [
    {
      question: 'What is the main responsibility of a Platform PM?',
      options: ['Option A: Manage hardware', 'Option B: Manage APIs and infrastructure', 'Option C: Handle sales', 'Option D: Customer support']
    },
    {
      question: 'Which metric is most important for Growth PMs?',
      options: ['Option A: Revenue', 'Option B: User activation', 'Option C: Internal tools', 'Option D: Server up-time']
    },
    {
      question: 'Data PMs focus mostly on:',
      options: ['Option A: UI design', 'Option B: Data pipelines and analytics', 'Option C: Marketing', 'Option D: Customer support']
    },
    {
      question: 'Choose the correct approach for product scaling:',
      options: ['Option A: Ignore data', 'Option B: Use experiments', 'Option C: Reduce team size', 'Option D: Stop development']
    },
    {
      question: 'Effective PM communication includes:',
      options: ['Option A: Clarity', 'Option B: Vague instructions', 'Option C: Avoiding meetings', 'Option D: Not sharing plans']
    }
  ];

  selectedAnswers: (string | null)[] = Array(this.questions.length).fill(null);
  constructor(private router: Router) {}

  ngOnInit(): void {
    this.startTimer();
  }

  startTimer(): void {
    const interval = setInterval(() => {
      const current = this.timer();
      if (current > 0) {
        this.timer.set(current - 1);
      } else {
        clearInterval(interval);
        // Optionally auto-submit or disable inputs here
      }
    }, 1000);
  }
  onAnswerSelect(questionIndex: number, selectedOption: string): void {
    this.selectedAnswers[questionIndex] = selectedOption;
    console.log('Current Answers:', this.selectedAnswers);
  }

  onNext(): void {
    // Optionally validate all questions answered before routing
    this.router.navigate(['/section2']);
  }
}
