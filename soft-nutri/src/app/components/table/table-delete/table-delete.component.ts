import { TableService } from 'src/app/services/table/table.service';

import { Component, Inject, Optional } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-table-delete',
  templateUrl: './table-delete.component.html',
  styleUrls: ['./table-delete.component.scss']
})
export class TableDeleteComponent{

  public action: any;

  constructor(
    @Optional() public dialogRef: MatDialogRef<TableDeleteComponent>,
    @Optional() @Inject(MAT_DIALOG_DATA) public data: any,
    public service: TableService,
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
        this.snackBar.open(this.translate.instant('TABLE.ERROR_DELETE_TABLE'), 'Error', {
          horizontalPosition: 'center',
          verticalPosition: 'top',
          duration: 3000
        });
      }
    });
    this.dialogRef.close();
  }

}
