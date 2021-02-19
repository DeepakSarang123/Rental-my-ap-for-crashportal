import { KeycloakService } from 'keycloak-angular';
import { SettingshttpService } from '../../app/settings/settingshttp.service';

export function initializer(keycloak: KeycloakService, settingshttpService: SettingshttpService): () => Promise<any> {

    return (): Promise<any> => {

        return new Promise(async (resolve, reject) => {
            try {
                await settingshttpService.init();
                await keycloak.init({
                    config: {
                        url: settingshttpService.getKeycloakUrl(),
                        realm: settingshttpService.getRealm(),
                        clientId: settingshttpService.getClientId(),
                    },
                    loadUserProfileAtStartUp: false,
                    initOptions: {
                        onLoad: 'login-required',
                        checkLoginIframe: false,
                    },
                    bearerExcludedUrls: [],
                });
                resolve();
            } catch (error) {
                reject(error);
            }
        });
    };
}
