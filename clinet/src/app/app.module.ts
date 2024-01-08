import { BrowserModule } from '@angular/platform-browser';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { FormsModule } from '@angular/forms';

import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';

import {ToastModule} from 'primeng/toast';
import { RippleModule } from 'primeng/ripple';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { MessageModule } from 'primeng/message';
import {MessagesModule} from 'primeng/messages';


import { JwtModule } from '@auth0/angular-jwt';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material/material.module';
import {MatPaginatorModule} from '@angular/material/paginator';
import {DataTableAdminComponent} from './components/common/data-table-admin/data-table-admin.component';





@NgModule({
  declarations: [
    AppComponent,
      DataTableAdminComponent,
  ],
  imports: [
      MatPaginatorModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    ToastModule,
    RippleModule,
    InputTextModule,
    ButtonModule,
    MessageModule,
    MessagesModule,
      JwtModule.forRoot({
      config: {
        tokenGetter: () => {
          return localStorage.getItem('currentUser');
        }
      }
    }),
    BrowserAnimationsModule,
    MaterialModule

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
