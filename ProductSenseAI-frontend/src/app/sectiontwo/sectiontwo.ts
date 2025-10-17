import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Navbar } from '../navbar/navbar';

@Component({
  selector: 'app-sectiontwo',
  imports: [CommonModule, FormsModule, Navbar],
  templateUrl: './sectiontwo.html',
  styleUrl: './sectiontwo.css'
})
export class Sectiontwo {
  timer = signal(300); // 5 min timer

  bigQuestions = [
    {
      scenario: 'You are the PM for the User Identity Service, which handles logins, permissions, and user profiles. Over the past week, you’ve seen a 20% drop in successful new user sign-ups, a critical funnel metric.',
      dataPoints: [
        'The drop is not uniform — Android is stable, but iOS and Web sign-ups have declined.',
        'The backend team hasn’t deployed any changes in two weeks.',
        'The Marketing team launched a new ad campaign driving users to a new sign-up page on the web.'
      ],
      followUp: 'How would you investigate the root cause?'
    }
    // Add more questions as needed
  ];

  questionIndex = 0;
  answer = '';

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
        // Optional: disable input or auto-submit
        this.onNext();
      }
    }, 1000);
  }
  onNext(): void {
    console.log('Section two response:', this.answer);
    this.router.navigate(['/section3']); // replace with your next component/route
  }
}
