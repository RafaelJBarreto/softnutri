import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { SignIn } from 'src/app/model';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss']
})
export class LoginFormComponent implements OnInit {
  @Output() sendLoginForm = new EventEmitter<SignIn>();
  public form!: FormGroup;
  loginUser!: SignIn;

  public ngOnInit(): void {
    this.form = new FormGroup({
      email: new FormControl('ana_eliza1@outlook.com.br', [Validators.required, Validators.email]),
      password: new FormControl('123456', [Validators.required])
    });
  }

  public login(): void {
    if (this.form.valid) {
      this.loginUser = {
        username: this.form.controls['email'].value,
        password: this.form.controls['password'].value
      }
      this.sendLoginForm.emit(this.loginUser);
    }
  }
}