/*
 *    Copyright 2016 Saiy™ Ltd
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package ai.saiy.android.apiexample.configuration;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.Locale;

import ai.saiy.android.api.Defaults;
import ai.saiy.android.api.language.nlu.NLULanguageNuance;
import ai.saiy.android.api.language.tts.TTSLanguageNuance;
import ai.saiy.android.api.language.vr.VRLanguageNuance;
import ai.saiy.android.api.request.SaiyRequestParams;
import ai.saiy.android.apiexample.R;

/**
 * Class containing your Text to Speech and Voice Recognition API keys. Obviously structured like this
 * for testing purposes only.
 * <p/>
 * Created by benrandall76@gmail.com on 12/02/2016.
 */
public class SaiyConfiguration {

    /**
     * MUST BE SET TO FALSE FOR PRODUCTION!
     */
    private static boolean DEBUG = false;

    public static void setLogging(final boolean enabled) {
        DEBUG = enabled;
    }

    public static boolean getLogging() {
        return DEBUG;
    }

    /**
     * Enter your Google Chromium Speech API key below. You need to register in the Google Group and
     * enable this in your API console. Without doing both, it WILL NOT WORK!
     */
    public static String GOOGLE_CHROMIUM_API_KEY = "_your_key_here_";

    /**
     * Saiy will not handle refreshing your access token. If you want to use Google's Cloud Speech API
     * you will need to introduce your own code to fetch an access token from your server. For testing purposes,
     * you can enter the details below.
     */
    public static String GOOGLE_CLOUD_ACCESS_TOKEN = "_your_access_token_here";
    public static long GOOGLE_CLOUD_ACCESS_EXPIRY = -1L;

    /**
     * Enter your Nuance App configuration details below. Your credentials can be found in your
     * Nuance Developers portal, under "Manage My Apps
     */
    public static final String NUANCE_APP_KEY = "_your_app_key_here_";
    public static final String NUANCE_APP_ID = "_your_app_id_here_";
    public static final String NUANCE_SERVER_HOST = "hiy.nmdp.nuancemobility.net";
    public static final String NUANCE_SERVER_HOST_NLU = "nmsps.dev.nuance.com";
    public static final String NUANCE_SERVER_PORT = "443";

    public static final Uri NUANCE_SERVER_URI = Uri.parse("nmsps://" + NUANCE_APP_ID + "@"
            + NUANCE_SERVER_HOST + ":" + NUANCE_SERVER_PORT);
    public static final Uri NUANCE_SERVER_URI_NLU = Uri.parse("nmsps://" + NUANCE_APP_ID + "@"
            + NUANCE_SERVER_HOST_NLU + ":" + NUANCE_SERVER_PORT);

    //Only needed if using NLU
    public static final String NUANCE_CONTEXT_TAG = "_your_context_tag_here_";

    /**
     * Enter your API AI configuration details below.
     */
    public static final String API_AI_CLIENT_ACCESS_TOKEN = "_your_access_token_here_";

    /**
     * Enter your Microsoft configuration details below.
     */
    public static final String OXFORD_KEY_1 = "_your_key_here_";
    public static final String OXFORD_KEY_2 = "_your_key_here_";

    // Only needed if using NLU
    public static final String LUIS_APP_ID = "_your_app_id_here_";
    public static final String LUIS_SUBSCRIPTION_ID = "_your_subscription_id_here_";

    /**
     * Enter your IBM configuration details below.
     */
    public static String IBM_SERVICE_USER_NAME = "_your_user_name_here";
    public static String IBM_SERVICE_PASSWORD = "_your_password_here";

    /**
     * Enter your Wit configuration details below.
     */
    public static String WIT_SERVER_ACCESS_TOKEN = "_your_access_token_here";
    public static final String WIT_SPEECH_URL = "https://api.wit.ai/speech?v=20160526";

    /**
     * Enter your own personal server configuration details below.
     */
    public static final Uri REMOTE_SERVER_URI = Uri.parse("_your_server_uri_here");
    public static final String REMOTE_ACCESS_TOKEN = "_your_remote_access_token_here";


    /**
     * An example of fixed parameters you would use in production.
     *
     * @param ctx the application context
     * @return populated {@link SaiyRequestParams}
     */
    public static SaiyRequestParams getProductionParams(@NonNull final Context ctx) {

        final Locale locale = Locale.getDefault();

        final SaiyRequestParams productionParams = new SaiyRequestParams();

        // Local
        productionParams.setTTSProvider(Defaults.TTS.LOCAL);
        productionParams.setVRProvider(Defaults.VR.NATIVE);
        productionParams.setLanguageModel(Defaults.LanguageModel.LOCAL);

        productionParams.setTTSLanguageLocal(locale);
        productionParams.setVRLanguageNative(locale);

        productionParams.setAction(Defaults.ACTION.SPEAK_LISTEN);
        productionParams.setUtterance(ctx.getApplicationContext().getString(R.string.default_intro));

        return productionParams;

    }

    /**
     * If you are likely to repeatedly use the same Text to Speech and Voice Recognition provider
     * then change the values below. You'll be able to use them in the {@link ai.saiy.android.api.request.SaiyRequest}
     * constructor.
     * <p>
     * Obviously, only leave in the parameters you are going to use.
     *
     * @param ctx the application context
     * @return populated {@link SaiyRequestParams}
     */
    public static SaiyRequestParams getDefaultParams(@NonNull final Context ctx) {

        final Locale locale = Locale.getDefault();

        final SaiyRequestParams defaultParams = new SaiyRequestParams();

        // Local
        defaultParams.setTTSLanguageLocal(locale);
        defaultParams.setTTSProvider(Defaults.TTS.LOCAL);
        defaultParams.setVRProvider(Defaults.VR.NATIVE);
        defaultParams.setVRLanguageNative(locale);
        defaultParams.setLanguageModel(Defaults.LanguageModel.LOCAL);
        defaultParams.setAction(Defaults.ACTION.SPEAK_LISTEN);
        defaultParams.setUtterance(ctx.getApplicationContext().getString(R.string.default_intro));

        // Google Cloud
        defaultParams.setGOOGLE_CLOUD_ACCESS_TOKEN(GOOGLE_CLOUD_ACCESS_TOKEN);
        defaultParams.setGOOGLE_CLOUD_ACCESS_EXPIRY(GOOGLE_CLOUD_ACCESS_EXPIRY);

        // Google Chromium
        defaultParams.setGOOGLE_CHROMIUM_API_KEY(GOOGLE_CHROMIUM_API_KEY);

        // Google (both)
        defaultParams.setVRLanguageGoogle(locale);

        // Nuance
        defaultParams.setNUANCE_APP_KEY(NUANCE_APP_KEY);
        defaultParams.setNUANCE_CONTEXT_TAG(NUANCE_CONTEXT_TAG);
        defaultParams.setNUANCE_SERVER_URI(NUANCE_SERVER_URI);
        defaultParams.setNUANCE_SERVER_URI_NLU(NUANCE_SERVER_URI_NLU);
        defaultParams.setVRLanguageNuance(VRLanguageNuance.ENGLISH_US);
        defaultParams.setTTSLanguageNuance(TTSLanguageNuance.FRENCH_AUDREY_ML);
        defaultParams.setNLULanguageNuance(NLULanguageNuance.English_US);

        // Microsoft
        defaultParams.setVRLanguageMicrosoft(locale);
        defaultParams.setNLULanguageMicrosoft(locale);
        defaultParams.setOXFORD_KEY_1(OXFORD_KEY_1);
        defaultParams.setOXFORD_KEY_2(OXFORD_KEY_2);
        defaultParams.setLUIS_APP_ID(LUIS_APP_ID);
        defaultParams.setLUIS_SUBSCRIPTION_ID(LUIS_SUBSCRIPTION_ID);

        // API AI
        defaultParams.setNLULanguageAPIAI(locale);
        defaultParams.setAPI_AI_ACCESS_TOKEN(API_AI_CLIENT_ACCESS_TOKEN);

        // Wit
        defaultParams.setVRLanguageWit(locale);
        defaultParams.setNLULanguageWit(locale);
        defaultParams.setWIT_SERVER_ACCESS_TOKEN(WIT_SERVER_ACCESS_TOKEN);

        // IBM
        defaultParams.setVRLanguageIBM(locale);
        defaultParams.setIBM_SERVICE_USER_NAME(IBM_SERVICE_USER_NAME);
        defaultParams.setIBM_SERVICE_PASSWORD(IBM_SERVICE_PASSWORD);

        // Remote
        defaultParams.setREMOTE_ACCESS_TOKEN(REMOTE_ACCESS_TOKEN);
        defaultParams.setREMOTE_SERVER_URI(REMOTE_SERVER_URI);
        defaultParams.setVRLanguageRemote(locale);

        return defaultParams;
    }
}

