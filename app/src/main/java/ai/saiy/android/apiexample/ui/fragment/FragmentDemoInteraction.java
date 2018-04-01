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
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import ai.saiy.android.api.Defaults;
import ai.saiy.android.api.remote.Request;
import ai.saiy.android.api.request.SaiyRequest;
import ai.saiy.android.api.request.SaiyRequestParams;
import ai.saiy.android.apiexample.R;
import ai.saiy.android.apiexample.ui.activity.MainActivity;
import ai.saiy.android.apiexample.utils.UtilsColour;

/**
 * Simple demonstration of interacting with your user, in order to set the button colour
 * in your UI (as long as it is present in {@link UtilsColour}. This could be expanded to
 * setting their desired theme and then expanded further to absolutely anything you
 * wish to 'converse' with your user about.
 * <p>
 * If the language by which you interact with will become complex or diverse, you should consider
 * using a machine learning NLU provider, rather than just basic string examination.
 * <p>
 * Created by benrandall76@gmail.com on 03/10/2016.
 */

public class FragmentDemoInteraction extends Fragment implements View.OnClickListener {

    /**
     * TAG
     */
    private final String CLS_NAME = FragmentDemoInteraction.class.getSimpleName();

    /**
     * UI components
     */
    private TextView tvResults;
    private Button button;

    /**
     * Will increment. Set your own in production that will correspond to an action
     * when a request has finished processing.
     */
    private int testRequestID = 777;

    /**
     * Locale US used for the sake of example, as it's supported by all
     * TTS, VR and NLU options.
     */
    private final Locale locale = Locale.US;

    /**
     * Standard empty fragment constructor
     */
    public FragmentDemoInteraction() {
    }

    /**
     * Suggested static instantiation method
     *
     * @return a new instance of this fragment
     */
    public static FragmentDemoInteraction newInstance() {
        return new FragmentDemoInteraction();
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(CLS_NAME, "onCreate");
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        Log.i(CLS_NAME, "onCreateView");

        final View rootView = inflater.inflate(R.layout.fragment_demo_interaction, container, false);

        button = rootView.findViewById(R.id.button);
        button.setOnClickListener(this);

        tvResults = rootView.findViewById(R.id.tvResults);
        tvResults.setMovementMethod(ScrollingMovementMethod.getInstance());

        return rootView;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(final View v) {
        runAPI();
    }

    /**
     * Run the API.
     */
    @SuppressWarnings("ConstantConditions")
    private void runAPI() {
        Log.i(CLS_NAME, "runAPI");

        appendText(null);

        if (MainActivity.saiyAvailable) {

            final SaiyRequest saiyRequest = ((MainActivity) getActivity()).getSaiyRequest();
            final SaiyRequestParams params = ((MainActivity) getActivity()).getParams();

            params.setUtterance("Please say your favourite colour");
            params.setRequestId(String.valueOf(testRequestID));
            params.setAction(Defaults.ACTION.SPEAK_LISTEN);
            params.setTTSProvider(Defaults.TTS.LOCAL);
            params.setTTSLanguageLocal(locale);
            params.setVRProvider(Defaults.VR.NATIVE);
            params.setVRLanguageNative(locale);
            params.setLanguageModel(Defaults.LanguageModel.LOCAL);
            saiyRequest.setParams(params);
            saiyRequest.execute();
            testRequestID++;

        } else {
            Log.e(CLS_NAME, "Saiy is reporting unavailable. " +
                    "If you don't know why, please check the logcat in more detail");
            appendText("BAD REQUEST");
        }
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

        if (voiceData != null && !voiceData.isEmpty()) {
            setColour(UtilsColour.getColourFromName(voiceData));
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
     * Update the colour of the button on the main thread based on the voice results.
     *
     * @param colour the integer representation of the chosen colour.
     */
    private void setColour(final int colour) {

        if (getActivity() != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    button.setBackgroundColor(colour);
                }
            });
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
