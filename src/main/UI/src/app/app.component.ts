import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  // New property for storing welcome messages
  welcomeMessages: any = {};

  constructor(private httpClient: HttpClient) {}

  private baseURL: string = 'http://localhost:8080';
  private postUrl: string = this.baseURL + '/room/reservation/v1';
  public submitted!: boolean;
  roomsearch!: FormGroup;
  rooms!: Room[];
  request!: ReserveRoomRequest;
  currentCheckInVal!: string;
  currentCheckOutVal!: string;

  ngOnInit() {
    this.roomsearch = new FormGroup({
      checkin: new FormControl(' '),
      checkout: new FormControl(' ')
    });

    const roomsearchValueChanges$ = this.roomsearch.valueChanges;
    roomsearchValueChanges$.subscribe(x => {
      this.currentCheckInVal = x.checkin;
      this.currentCheckOutVal = x.checkout;
    });

    // Fetch welcome messages
    this.httpClient.get(this.baseURL + '/welcome').subscribe(data => {
      this.welcomeMessages = data;
    });
  }

  onSubmit({ value, valid }: { value: Roomsearch, valid: boolean }) {
    this.getAll().subscribe(
      rooms => { console.log(Object.values(rooms)[0]); this.rooms = <Room[]>Object.values(rooms)[0]; }
    );
  }

  reserveRoom(value: string) {
    this.request = new ReserveRoomRequest(value, this.currentCheckInVal, this.currentCheckOutVal);
    this.createReservation(this.request);
  }

  createReservation(body: ReserveRoomRequest) {
    let bodyString = JSON.stringify(body); // Stringify payload
    let headers = new Headers({ 'Content-Type': 'application/json' }); // ... Set content type to JSON

    const options = {
      headers: new HttpHeaders().append('key', 'value'),
    }

    this.httpClient.post(this.postUrl, body, options)
      .subscribe(res => console.log(res));
  }

  getAll(): Observable<any> {
    return this.httpClient.get(this.baseURL + '/room/reservation/v1?checkin=' + this.currentCheckInVal + '&checkout=' + this.currentCheckOutVal, { responseType: 'json' });
  }
}

export interface Roomsearch {
  checkin: string;
  checkout: string;
}

export interface Room {
  id: string;
  roomNumber: string;
  price: string;
  links: string;
  priceCAD: string;
  priceEUR: string;
}

export class ReserveRoomRequest {
  roomId: string;
  checkin: string;
  checkout: string;

  constructor(roomId: string, checkin: string, checkout: string) {
    this.roomId = roomId;
    this.checkin = checkin;
    this.checkout = checkout;
  }
}
