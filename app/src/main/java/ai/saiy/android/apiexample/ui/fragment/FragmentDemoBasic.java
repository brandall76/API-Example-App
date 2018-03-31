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

package ai.saiy.android.apiexample.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

import ai.saiy.android.api.Defaults;
import ai.saiy.android.api.attributes.Gender;
import ai.saiy.android.api.remote.Request;
import ai.saiy.android.api.request.SaiyRequest;
import ai.saiy.android.api.request.SaiyRequestParams;
import ai.saiy.android.apiexample.R;
import ai.saiy.android.apiexample.ui.activity.MainActivity;

/**
 * Demonstration of all available Text to Speech, Voice Recognition and Natural Language providers
 * that are currently supported via the Saiy API.
 * <p>
 * For demonstration purposes, you can enter your credentials for each provider in the
 * {@link ai.saiy.android.apiexample.configuration.SaiyConfiguration} file, although in production
 * you will of course need to store these more securely, or better still fetch them from your server.
 * <p>
 * Created by benrandall76@gmail.com on 26/09/2016.
 */

public class FragmentDemoBasic extends Fragment implements AdapterView.OnItemSelectedListener,
        View.OnClickListener {

    /**
     * TAG
     */
    private final String CLS_NAME = FragmentDemoBasic.class.getSimpleName();

    /**
     * Constants for TTS, VR and NLU options.
     */
    private static final int TTS_DEVICE_DEFAULT = 0;
    private static final int TTS_NUANCE = 1;

    private static final int VR_NONE = 0;
    private static final int VR_NATIVE = 1;
    private static final int VR_GOOGLE_CLOUD = 2;
    private static final int VR_GOOGLE_CHROMIUM = 3;
    private static final int VR_NUANCE = 4;
    private static final int VR_MICROSOFT = 5;
    private static final int VR_IBM = 6;
    private static final int VR_WIT = 7;

    private static final int NLU_NONE = 0;
    private static final int NLU_NUANCE = 1;
    private static final int NLU_MICROSOFT = 2;
    private static final int NLU_API_AI = 3;
    private static final int NLU_WIT = 4;

    /**
     * UI components
     */
    private Spinner spinnerTTS;
    private Spinner spinnerVR;
    private Spinner spinnerNLU;
    private EditText etSpeech;
    private TextView tvResults;

    /**
     * Will increment. Set your own in production that will correspond to an action
     * when a request has finished processing.
     */
    private int testRequestID = 555;

    /**
     * Locale US used for the sake of example, as it's supported by all
     * TTS, VR and NLU options.
     */
    private final Locale locale = Locale.US;

    /**
     * Standard empty fragment constructor
     */
    public FragmentDemoBasic() {
    }

    /**
     * Suggested static instantiation method
     *
     * @return a new instance of this fragment
     */
    public static FragmentDemoBasic newInstance() {
        return new FragmentDemoBasic();
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(CLS_NAME, "onCreate");
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        Log.i(CLS_NAME, "onCreateView");

        final View rootView = inflater.inflate(R.layout.fragment_demo_basic, container, false);

        spinnerTTS = rootView.findViewById(R.id.spinnerTTS);
        spinnerTTS.setOnItemSelectedListener(this);
        spinnerVR = rootView.findViewById(R.id.spinnerVR);
        spinnerVR.setOnItemSelectedListener(this);
        spinnerNLU = rootView.findViewById(R.id.spinnerNLU);
        spinnerNLU.setOnItemSelectedListener(this);

        etSpeech = rootView.findViewById(R.id.etSpeech);

        tvResults = rootView.findViewById(R.id.tvResults);
        tvResults.setMovementMethod(ScrollingMovementMethod.getInstance());

        final ImageButton imageButton = rootView.findViewById(R.id.ibRun);
        imageButton.setOnClickListener(this);

        return rootView;
    }


    /**
     * Make sure VR and NLU providers match
     */
    @Override
    public void onItemSelected(final AdapterView<?> parent, final View view, final int position, final long id) {

        switch (parent.getId()) {

            case R.id.spinnerTTS:
                Log.i(CLS_NAME, "onItemSelected: spinnerTTS: " + parent.getItemAtPosition(position).toString());

                switch (position) {

                    case TTS_DEVICE_DEFAULT:
                        break;
                    case TTS_NUANCE:
                        break;
                }

                break;

            case R.id.spinnerVR:
                Log.i(CLS_NAME, "onItemSelected: spinnerVR: " + parent.getItemAtPosition(position).toString());

                switch (position) {

                    case VR_NONE:
                        if (spinnerNLU.getSelectedItemPosition() != NLU_NONE) {
                            spinnerNLU.setSelection(NLU_NONE, true);
                        }
                        break;
                    case VR_NATIVE:
                        if (spinnerNLU.getSelectedItemPosition() != NLU_NONE) {
                            spinnerNLU.setSelection(NLU_NONE, true);
                        }
                        break;
                    case VR_GOOGLE_CLOUD:
                        if (spinnerNLU.getSelectedItemPosition() != NLU_NONE) {
                            spinnerNLU.setSelection(NLU_NONE, true);
                        }
                        break;
                    case VR_GOOGLE_CHROMIUM:
                        if (spinnerNLU.getSelectedItemPosition() != NLU_NONE) {
                            spinnerNLU.setSelection(NLU_NONE, true);
                        }
                        break;
                    case VR_NUANCE:
                        if (spinnerNLU.getSelectedItemPosition() != NLU_NUANCE
                                && spinnerNLU.getSelectedItemPosition() != NLU_NONE) {
                            spinnerNLU.setSelection(NLU_NONE, true);
                        }
                        break;
                    case VR_MICROSOFT:
                        if (spinnerNLU.getSelectedItemPosition() != NLU_MICROSOFT
                                && spinnerNLU.getSelectedItemPosition() != NLU_NONE) {
                            spinnerNLU.setSelection(NLU_NONE, true);
                        }
                        break;
                    case VR_IBM:
                        if (spinnerNLU.getSelectedItemPosition() != NLU_NONE) {
                            spinnerNLU.setSelection(NLU_NONE, true);
                        }
                        break;
                    case VR_WIT:
                        if (spinnerNLU.getSelectedItemPosition() != NLU_WIT
                                && spinnerNLU.getSelectedItemPosition() != NLU_NONE) {
                            spinnerNLU.setSelection(NLU_NONE, true);
                        }
                        break;
                }

                break;

            case R.id.spinnerNLU:
                Log.i(CLS_NAME, "onItemSelected: spinnerNLU: " + parent.getItemAtPosition(position).toString());

                switch (position) {

                    case NLU_NONE:
                        break;
                    case NLU_NUANCE:
                        if (spinnerVR.getSelectedItemPosition() != VR_NUANCE) {
                            spinnerVR.setSelection(VR_NUANCE, true);
                        }
                        break;
                    case NLU_MICROSOFT:
                        if (spinnerVR.getSelectedItemPosition() != VR_MICROSOFT) {
                            spinnerVR.setSelection(VR_MICROSOFT, true);
                        }
                        break;
                    case NLU_API_AI:
                        if (spinnerVR.getSelectedItemPosition() == VR_NONE) {
                            spinnerVR.setSelection(VR_NATIVE, true);
                        }
                        break;
                    case NLU_WIT:
                        if (spinnerVR.getSelectedItemPosition() != VR_WIT) {
                            spinnerVR.setSelection(VR_WIT, true);
                        }
                        break;
                }

                break;
        }
    }

    /**
     * Callback method to be invoked when the selection disappears from this
     * view. The selection can disappear for instance when touch is activated
     * or when the adapter becomes empty.
     *
     * @param parent The AdapterView that now contains no selected item.
     */
    @Override
    public void onNothingSelected(final AdapterView<?> parent) {
        Log.i(CLS_NAME, "onNothingSelected");
    }

    @Override
    public void onClick(final View v) {

        if (etSpeech.getText() != null) {

            String commandText = etSpeech.getText().toString();

            //noinspection ConstantConditions
            if (commandText == null || commandText.trim().isEmpty()) {
                Log.w(CLS_NAME, "onClick: text naked");

                commandText = getString(R.string.default_intro);
            }

            hideIME();
            runAPI(commandText);

        } else {
            Log.e(CLS_NAME, "onClick: getText null");
        }
    }

    /**
     * Run the API using the configuration selected in the spinners.
     */
    private void runAPI(@NonNull final String utterance) {
        Log.i(CLS_NAME, "runAPI");

        appendText(null);

        if (MainActivity.saiyAvailable) {

            boolean apiParamsPass = true;

            final SaiyRequest saiyRequest = ((MainActivity) getActivity()).getSaiyRequest();
            final SaiyRequestParams params = ((MainActivity) getActivity()).getParams();

            if (saiyRequest != null && params != null) {

                params.setUtterance(utterance);
                params.setRequestId(String.valueOf(testRequestID));
                params.setAction(Defaults.ACTION.SPEAK_LISTEN);

                switch (spinnerTTS.getSelectedItemPosition()) {

                    case TTS_DEVICE_DEFAULT:

                        if (saiyRequest.isTTSLanguageLocalAvailable(locale)) {
                            params.setTTSProvider(Defaults.TTS.LOCAL);
                            params.setTTSLanguageLocal(locale);
                        } else {
                            Log.e(CLS_NAME, "Local Text to Speech does not support the locale " + locale.toString());
                            apiParamsPass = false;
                        }

                        break;
                    case TTS_NUANCE:

                        if (saiyRequest.isTTSLanguageNuanceAvailable(locale)) {
                            params.setTTSProvider(Defaults.TTS.NETWORK_NUANCE);
                            params.setTTSLanguageNuance(locale, Gender.FEMALE);
                        } else {
                            Log.e(CLS_NAME, "Nuance Text to Speech does not support the locale " + locale.toString());
                            apiParamsPass = false;
                        }

                        break;
                }

                switch (spinnerVR.getSelectedItemPosition()) {

                    case VR_NONE:
                        params.setAction(Defaults.ACTION.SPEAK_ONLY);
                        break;
                    case VR_NATIVE:

                        if (saiyRequest.isVRLanguageNativeAvailable(locale)) {
                            params.setVRProvider(Defaults.VR.NATIVE);
                            params.setVRLanguageNative(locale);
                        } else {
                            Log.e(CLS_NAME, "Native Voice Recognition does not support the locale " + locale.toString());
                            apiParamsPass = false;
                        }

                        break;
                    case VR_GOOGLE_CLOUD:

                        if (saiyRequest.isVRLanguageGoogleAvailable(locale)) {
                            params.setVRProvider(Defaults.VR.GOOGLE_CLOUD);
                            params.setVRLanguageGoogle(locale);
                        } else {
                            Log.e(CLS_NAME, "Google Voice Recognition does not support the locale " + locale.toString());
                            apiParamsPass = false;
                        }

                        break;
                    case VR_GOOGLE_CHROMIUM:

                        if (saiyRequest.isVRLanguageGoogleAvailable(locale)) {
                            params.setVRProvider(Defaults.VR.GOOGLE_CHROMIUM);
                            params.setVRLanguageGoogle(locale);
                        } else {
                            Log.e(CLS_NAME, "Google Voice Recognition does not support the locale " + locale.toString());
                            apiParamsPass = false;
                        }

                        break;
                    case VR_NUANCE:

                        if (saiyRequest.isVRLanguageNuanceAvailable(locale)) {
                            params.setVRProvider(Defaults.VR.NUANCE);
                            params.setVRLanguageNuance(locale);
                        } else {
                            Log.e(CLS_NAME, "Nuance Voice Recognition does not support the locale " + locale.toString());
                            apiParamsPass = false;
                        }

                        break;
                    case VR_MICROSOFT:

                        Log.e(CLS_NAME, "Microsoft Voice Recognition is currently disabled until they fix the architecture issues");
                        apiParamsPass = false;

                        /*
                         * Due to a lack of architecture support, this needs to be disabled for now
                         * If your test device is armeabi or X86 you should be good to go.
                         *
                         * https://github.com/Azure-Samples/Cognitive-Speech-STT-Android/issues/54
                         */
//                        if (saiyRequest.isVRLanguageMicrosoftAvailable(locale)) {
//                            params.setVRProvider(Defaults.VR.MICROSOFT);
//                            params.setVRLanguageMicrosoft(locale);
//                        } else {
//                            Log.e(CLS_NAME, "Microsoft Voice Recognition does not support the locale " + locale.toString());
//                            apiParamsPass = false;
//                        }

                        break;
                    case VR_IBM:

                        if (saiyRequest.isVRLanguageIBMAvailable(locale)) {
                            params.setVRProvider(Defaults.VR.IBM);
                            params.setVRLanguageIBM(locale);
                        } else {
                            Log.e(CLS_NAME, "IBM Voice Recognition does not support the locale " + locale.toString());
                            apiParamsPass = false;
                        }

                        break;
                    case VR_WIT:

                        if (saiyRequest.isVRLanguageWitAvailable(locale)) {
                            params.setVRProvider(Defaults.VR.WIT);
                            params.setVRLanguageWit(locale);
                        } else {
                            Log.e(CLS_NAME, "WIT Voice Recognition does not support the locale " + locale.toString());
                            apiParamsPass = false;
                        }

                        break;
                }

                switch (spinnerNLU.getSelectedItemPosition()) {

                    case NLU_NONE:
                        params.setLanguageModel(Defaults.LanguageModel.LOCAL);
                        break;
                    case NLU_NUANCE:

                        // At time of writing, the Mix.nlu BETA supports only English-USA.
                        if (saiyRequest.isNLULanguageNuanceAvailable(locale)) {
                            params.setNLULanguageNuance(locale);
                            params.setLanguageModel(Defaults.LanguageModel.NUANCE);
                        } else {
                            Log.e(CLS_NAME, "Nuance NLU does not support the locale " + locale.toString());
                            apiParamsPass = false;
                        }

                        break;
                    case NLU_MICROSOFT:

                        if (saiyRequest.isNLULanguageMicrosoftAvailable(locale)) {
                            params.setNLULanguageMicrosoft(locale);
                            params.setLanguageModel(Defaults.LanguageModel.MICROSOFT);
                        } else {
                            Log.e(CLS_NAME, "Microsoft NLU does not support the locale " + locale.toString());
                            apiParamsPass = false;
                        }

                        break;
                    case NLU_API_AI:

                        if (saiyRequest.isNLULanguageAPIAIAvailable(locale)) {
                            params.setNLULanguageAPIAI(locale);
                            params.setLanguageModel(Defaults.LanguageModel.API_AI);
                        } else {
                            Log.e(CLS_NAME, "Microsoft NLU does not support the locale " + locale.toString());
                            apiParamsPass = false;
                        }

                        break;
                    case NLU_WIT:

                        if (saiyRequest.isNLULanguageWitAvailable(locale)) {
                            params.setNLULanguageWit(locale);
                            params.setLanguageModel(Defaults.LanguageModel.WIT);
                        } else {
                            Log.e(CLS_NAME, "Microsoft NLU does not support the locale " + locale.toString());
                            apiParamsPass = false;
                        }

                        break;
                }

                if (apiParamsPass) {

                    // Add the SaiyRequestParams
                    saiyRequest.setParams(params);

                    // Send the request
                    saiyRequest.execute();

                    // Increment the request id
                    testRequestID++;

                } else {
                    Log.e(CLS_NAME, "The request could not be processed. " +
                            "If you don't know why, please check the logcat in more detail");
                }
            } else {
                Log.e(CLS_NAME, "Saiy has not correctly initialised. " +
                        "Try opening and closing the demo app again and checking the logcat");
            }
        } else {
            Log.e(CLS_NAME, "Saiy is reporting unavailable. " +
                    "If you don't know why, please check the logcat in more detail");
            appendText("BAD REQUEST");
        }
    }

    /**
     * Hide the IME once the input is complete
     */
    private void hideIME() {
        Log.i(CLS_NAME, "hideIME");

        ((InputMethodManager)
                etSpeech.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(etSpeech.getApplicationWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
    }


    /**
     * The {@link ai.saiy.android.api.remote.SaiyListener} is implemented in the Main Activity
     * class and passed here to the method of its namesake. You could implement the listener
     * directly here should you wish, depending on your design pattern. This callback will not
     * be on the main thread.
     *
     * @param requestId the unique request id sent with each API request
     */
    public void onUtteranceCompleted(final String requestId) {
        Log.d(CLS_NAME, "onUtteranceCompleted: " + requestId);
        appendText("onUtteranceCompleted: id: " + requestId + "\n");
    }

    /**
     * As above
     *
     * @param results   the {@link Bundle} containing result information
     * @param requestId the unique request id sent with each API request
     */
    public void onSpeechResults(final Bundle results, final String requestId) {
        Log.d(CLS_NAME, "onSpeechResults: id: " + requestId);
        appendText("onSpeechResults: id: " + requestId + "\n");

        final ArrayList<String> voiceData = results.getStringArrayList(Request.RESULTS_RECOGNITION);
        final float[] confidenceScore = results.getFloatArray(Request.CONFIDENCE_SCORES);
        final String nluString = results.getString(Request.RESULTS_NLU);

        if (confidenceScore != null && voiceData != null && confidenceScore.length == voiceData.size()) {
            for (int i = 0; i < voiceData.size(); i++) {
                Log.i(CLS_NAME, "onSpeechResults: " + voiceData.get(i) + " ~ " + confidenceScore[i]);
                appendText(voiceData.get(i) + " ~ " + confidenceScore[i]);
            }
        } else if (voiceData != null) {
            for (int i = 0; i < voiceData.size(); i++) {
                Log.i(CLS_NAME, "onSpeechResults: " + voiceData.get(i));
                appendText(voiceData.get(i));
            }
        } else {
            Log.w(CLS_NAME, "onSpeechResults: values error");
            appendText("onSpeechResults: values error");
        }

        if (nluString != null) {
            try {
                final JSONObject nulObject = new JSONObject(nluString);
                Log.i(CLS_NAME, "nulObject: " + nulObject.toString(4));
                appendText("\n" + nulObject.toString(4));
            } catch (final JSONException e) {
                Log.w(CLS_NAME, "onSpeechResults: JSONException");
                e.printStackTrace();
            }
        }
    }

    /**
     * As above
     *
     * @param error     one of {@link Defaults.ERROR}
     * @param requestId the unique request id sent with each API request
     */
    public void onError(final Defaults.ERROR error, final String requestId) {
        Log.d(CLS_NAME, "onError: id: " + requestId);

        switch (error) {

            case ERROR_NO_MATCH:
                Log.w(CLS_NAME, "onError: ERROR_NO_MATCH");
                appendText("onError: ERROR_NO_MATCH");

                /*
                The speech recogniser returned empty results. The most common reason for this,
                would be the user failing to speak or using naughty words with the profanity
                filter enabled....

                Saiy will announce this to the user and direct them as to how to correct the issue.
                */

                break;
            case ERROR_BUSY:
                Log.w(CLS_NAME, "onError: ERROR_BUSY");
                appendText("onError: ERROR_BUSY");

                /*
                Your request was returned due to Saiy either listening or speaking. In this
                situation, an instruction to cancel the listening or speech was made, but it is not
                guaranteed to have been successful.

                The user will be aware of this scenario.
                */

                break;
            case ERROR_INTERRUPTED:
                Log.w(CLS_NAME, "onError: ERROR_INTERRUPTED");
                appendText("onError: ERROR_INTERRUPTED");


                /*
                Your request was cancelled mid process, due most probably, to an incoming call. The
                user will be aware of this scenario.
                 */

                break;
            case ERROR_NETWORK:
                Log.w(CLS_NAME, "onError: ERROR_NETWORK");
                appendText("onError: ERROR_NETWORK");

                /*
                Your request could not be processed due to network conditions. Saiy will have informed
                the user of this issue.
                 */

                break;
            case ERROR_SAIY:
                Log.w(CLS_NAME, "onError: ERROR_SAIY");
                appendText("onError: ERROR_SAIY");

                /*
                An error has occurred which Saiy has handled and directed the user to the solution.
                This will mean your request has failed, but hopefully the user is taking action via
                Saiy to resolve the problem and it may be resolved for the next request.

                Examples of such errors are missing voice/language installations or a hardware error.
                */

                break;
            case ERROR_DEVELOPER:
                Log.w(CLS_NAME, "onError: ERROR_DEVELOPER");
                appendText("onError: ERROR_DEVELOPER");

                /*
                This is returned if parameters included in the default configuration
                of the TTS or VR (etc) provider haven't worked or are invalid. Double check the API keys
                you provided are functioning and other parameters are correct. Check the logcat output
                for Saiy, to see if a more specific error has been reported.

                Make sure you declare <uses-permission android:name="ai.saiy.android.permission.CONTROL_SAIY" />
                in your Manifest.

                This error should not happen in production, as such things should become
                apparent in testing, but it is here just in case.
                */

                MainActivity.saiyAvailable = false;

                break;
            case ERROR_DENIED:
                Log.w(CLS_NAME, "onError: REQUEST_DENIED");
                appendText("onError: REQUEST_DENIED");

                /*
                Whilst the parameters passed for the remote request to Saiy were validated
                correctly (as far as not null or missing), the further request to the TTS or
                VR (etc) provider was returned as declined. Check the logcat output
                for Saiy, to see if a more specific error has been reported.

                One valid scenario here, would be your API key being over its usage quota.

                A further reason could be your application has been 'blacklisted' by Saiy. This can
                occur if too many misconfigured requests have been sent within a short period of time.
                Saiy will decline all further requests, to make sure the performance of the app
                remains unaffected.

                Any blacklisted apps can be 'released' by the user from within the Saiy Application
                Settings. The blacklist is cleared each time the Saiy Service restarts, or a application
                is updated - giving the previously misbehaving app a chance to right its wrongs.
                */

                MainActivity.saiyAvailable = false;

                break;
            case ERROR_UNKNOWN:
            default:
                Log.e(CLS_NAME, "onError: ERROR_UNKNOWN");
                appendText("onError: ERROR_UNKNOWN");

                /*
                Saiy will default to this error if something inexplicable has happened. There is no
                guarantee that Saiy will have announced an issue to the user, but it should have.
                 */

                MainActivity.saiyAvailable = false;
                break;
        }
    }

    /**
     * Append results on the main thread.
     */
    private void appendText(@Nullable final String words) {

        if (getActivity() != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    if (words != null) {
                        tvResults.append("\n" + words);
                    } else {
                        tvResults.setText("");
                    }
                }
            });
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(CLS_NAME, "onPause");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(CLS_NAME, "onResume");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(CLS_NAME, "onDestroy");
    }
}
