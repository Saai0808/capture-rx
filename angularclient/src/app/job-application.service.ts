import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { JobApplication } from './job-application';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class UserService {

  private usersUrl: string;

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8080/job';
  }

  public findAll(): Observable<JobApplication[]> {
    return this.http.get<JobApplication[]>(this.usersUrl);
  }

  public save(user: JobApplication) {
    return this.http.post<JobApplication>(this.usersUrl, user);
  }
}
