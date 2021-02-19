import { Component, OnInit } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';
import { KeycloakProfile } from 'keycloak-js';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  title = 'Crash Dump Portal';
  userDetails: KeycloakProfile;

  constructor(private keycloakService: KeycloakService) { }

  /**
   * Awaits Keycloak Login Process. Returns with User Details.
   * @returns String
   */
  async ngOnInit() {
    if (await this.keycloakService.isLoggedIn()) {
      this.userDetails = await this.keycloakService.loadUserProfile();
    }
  }

  /**
   * LogOut
   */
  async logout() {
    this.keycloakService.logout();
  }

}
