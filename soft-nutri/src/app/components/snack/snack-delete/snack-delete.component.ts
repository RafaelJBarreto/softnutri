import { SnackService } from 'src/app/services/snack/snack.service';

import { Component, Inject, Optional } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-snack-delete',
  templateUrl: './snack-delete.component.html',
  styleUrls: ['./snack-delete.component.scss']
})
export class SnackDeleteComponent {

  public action: any;

  constructor(
    @Optional() public dialogRef: MatDialogRef<SnackDeleteComponent>,
    @Optional() @Inject(MAT_DIALOG_DATA) public data: any,
    public service: SnackService,
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
        this.snackBar.open(this.translate.instant('SNACK.ERROR_DELETE_SNACK'), 'Error', {
          horizontalPosition: 'center',
          verticalPosition: 'top',
          duration: 3000
        });
      }
    });
    this.dialogRef.close();
  }

}


