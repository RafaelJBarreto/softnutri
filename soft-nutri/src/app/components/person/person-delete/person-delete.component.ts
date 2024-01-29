import { PersonService } from 'src/app/services';

import { Component, Inject, Optional } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-person-delete',
  templateUrl: './person-delete.component.html',
  styleUrls: ['./person-delete.component.scss']
})
export class PersonDeleteComponent {
  public action: any;

  constructor(
    @Optional() public dialogRef: MatDialogRef<PersonDeleteComponent>,
    @Optional() @Inject(MAT_DIALOG_DATA) public data: any,
    private service: PersonService,
    public translate: TranslateService,
    private snackBar: MatSnackBar
  ) {
  }

  onClickDelete(): void {
    this.service.delete(this.data.id).subscribe({
      next: data => {
        this.snackBar.open(this.translate.instant(data.message), '', {
          horizontalPosition: 'center',
          verticalPosition: 'top',
          duration: 3000
        });
      },
      error: err => {
        this.snackBar.open(this.translate.instant('PATIENT.ERROR_DELETE_PATIENT'), 'Error', {
          horizontalPosition: 'center',
          verticalPosition: 'top',
          duration: 3000
        });
      }
    });
    this.dialogRef.close();
  }

}
