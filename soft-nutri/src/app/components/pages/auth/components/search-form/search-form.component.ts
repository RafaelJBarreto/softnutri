import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-search-form',
  templateUrl: './search-form.component.html',
  styleUrls: ['./search-form.component.scss']
})
export class SearchFormComponent implements OnInit {
  @Output() sendSignForm = new EventEmitter<void>();
  public formSearch!: FormGroup;

  public ngOnInit(): void {
    this.formSearch = new FormGroup({
      emailCad: new FormControl('', [Validators.required, Validators.email]),
      passwordCad: new FormControl('', [Validators.required])
    });
  }

  public sign(): void {
    if (this.formSearch.valid) {
      this.sendSignForm.emit();
    }
  }

}