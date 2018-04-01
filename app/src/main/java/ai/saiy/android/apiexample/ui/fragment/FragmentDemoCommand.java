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

import android.app.Activity;
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
import java.util.Set;

import ai.saiy.android.api.remote.Request;
import ai.saiy.android.api.request.Regex;
import ai.saiy.android.api.request.SaiyKeyphrase;
import ai.saiy.android.api.request.SaiyKeyphraseListener;
import ai.saiy.android.apiexample.R;
import ai.saiy.android.apiexample.ui.activity.MainActivity;

import static ai.saiy.android.apiexample.service.MyIntentService.DO_NOT_USE_ZERO;
import static ai.saiy.android.apiexample.service.MyIntentService.DO_SOMETHING_1;
import static ai.saiy.android.apiexample.service.MyIntentService.DO_SOMETHING_2;
import static ai.saiy.android.apiexample.service.MyIntentService.DO_SOMETHING_3;

/**
 * Demonstration of registering a keyphrase that will trigger your application. This
 * example includes a simple keyword registration, as well as circumstances where you will use
 * the additional speech provided to react to the voice command.
 * <p>
 * Specifically, 'Spotify playlist' and 'Spotify album' are demonstrated and the voice data
 * extracted to show which playlist/album would be started.
 * <p>
 * Created by benrandall76@gmail.com on 03/10/2016.
 */

public class FragmentDemoCommand extends Fragment implements SaiyKeyphraseListener, View.OnClickListener {

    /**
     * TAG
     */
    private final String CLS_NAME = FragmentDemoCommand.class.getSimpleName();

    /**
     * String phrases that will be registered in this example
     */
    private static final String SAUSAGES = "sausages";
    private static final String SPOTIFY_PLAYLIST = "spotify playlist";
    private static final String SPOTIFY_ALBUM = "spotify album";

    /**
     * Constant ids for each keyphrase
     */
    private static final int KEYPHRASE_ID_1 = 123;
    private static final int KEYPHRASE_ID_2 = 456;
    private static final int KEYPHRASE_ID_3 = 789;

    /**
     * UI component
     */
    private TextView tvResults;

    /**
     * Standard empty fragment constructor
     */
    public FragmentDemoCommand() {
    }

    /**
     * Suggested static instantiation method
     *
     * @return a new instance of this fragment
     */
    public static FragmentDemoCommand newInstance() {
        return new FragmentDemoCommand();
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(CLS_NAME, "onCreate");
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        Log.i(CLS_NAME, "onCreateView");

        final View rootView = inflater.inflate(R.layout.fragment_demo_command, container, false);

        final Button buttonC1 = rootView.findViewById(R.id.buttonC1);
        buttonC1.setOnClickListener(this);
        final Button buttonC2 = rootView.findViewById(R.id.buttonC2);
        buttonC2.setOnClickListener(this);
        final Button buttonC3 = rootView.findViewById(R.id.buttonC3);
        buttonC3.setOnClickListener(this);

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
        registerKeyphrase(v.getId());
    }

    /**
     * Handle the button click and register the corresponding keyphrase
     *
     * @param id of the view that was clicked
     */
    @SuppressWarnings("ConstantConditions")
    public void registerKeyphrase(final int id) {
        Log.i(CLS_NAME, "registerKeyphrase");

        if (MainActivity.saiyAvailable) {

            final SaiyKeyphrase keyphraseRequest = new SaiyKeyphrase(getActivity().getApplicationContext(),
                    this);

            switch (id) {
                case R.id.buttonC1:
                    keyphraseRequest.setDefinedAction(DO_SOMETHING_1);
                    keyphraseRequest.setSaiyKeyphrase(SAUSAGES);
                    keyphraseRequest.setRegex(Regex.MATCHES);
                    break;
                case R.id.buttonC2:
                    keyphraseRequest.setDefinedAction(DO_SOMETHING_2);
                    keyphraseRequest.setSaiyKeyphrase(SPOTIFY_PLAYLIST);
                    keyphraseRequest.setRegex(Regex.STARTS_WITH);
                    break;
                case R.id.buttonC3:
                    keyphraseRequest.setDefinedAction(DO_SOMETHING_3);
                    keyphraseRequest.setSaiyKeyphrase(SPOTIFY_ALBUM);
                    keyphraseRequest.setRegex(Regex.STARTS_WITH);
                    break;
            }

            /*
             * When registering a keyphrase, you can include a bundle containing
             * any type of parameter. When the keyphrase is detected, a bundle will
             * be returned containing these original parameters, which can be used
             * to handle the action you intend to make.
             */
            final Bundle bundle = new Bundle();

            bundle.putString("some bundle String", "random String value");
            bundle.putBoolean("some bundle boolean", true);
            bundle.putInt("some bundle int", Integer.MAX_VALUE);
            keyphraseRequest.setExtraBundle(bundle);

            /*
             * In production, you would identify the keyphrase detected by the
             * constant set here.
             */
            final int myUniqueId;

            switch (id) {
                case R.id.buttonC1:
                    myUniqueId = KEYPHRASE_ID_1;
                    break;
                case R.id.buttonC2:
                    myUniqueId = KEYPHRASE_ID_2;
                    break;
                case R.id.buttonC3:
                    myUniqueId = KEYPHRASE_ID_3;
                    break;
                default:
                    myUniqueId = 666;
                    break;
            }

            final boolean interimSuccess = keyphraseRequest.sendRequest(myUniqueId);

            if (interimSuccess) {
                Log.i(CLS_NAME, "The keyphrase request initially returned: true from the library ~ However, " +
                        "if the request fails to work, please view the logcat for " +
                        "information, as on receipt of the request, Saiy will perform more stringent tests which " +
                        "are subject to change. The final outcome will be received in onEnrollKeyphrase");
            } else {
                Log.e(CLS_NAME, "The keyphrase request was declined. Check the logcat for more information");
            }

        } else {
            Log.w(CLS_NAME, "Saiy is unavailable. Please check the log for more information");
        }
    }

    @Override
    public void onEnrollKeyphrase(final int result, final int requestId) {
        appendText(null);

        switch (result) {
            case Activity.RESULT_OK:
                Log.i(CLS_NAME, "onEnrollKeyphrase: RESULT_OK: " + requestId);
                appendText("onEnrollKeyphrase: RESULT_OK: " + requestId);
                break;
            case Activity.RESULT_CANCELED:
                Log.e(CLS_NAME, "onEnrollKeyphrase: RESULT_CANCELED: " + requestId);
                appendText("onEnrollKeyphrase: RESULT_CANCELED: " + requestId);
                break;
            default:
                Log.w(CLS_NAME, "onEnrollKeyphrase: default? Should not happen");
                appendText("onEnrollKeyphrase: default? Should not happen");
                break;
        }
    }

    /**
     * This example method is exposed to simulate a callback from your Intent Service, once a keyphrase
     * has been detected.
     *
     * @param bundle containing associated keyphrase extras
     */
    public void onExampleCallback(final Bundle bundle) {

        if (getActivity() != null && tvResults != null && bundle != null && !bundle.isEmpty()) {

            appendText(null);

            final ArrayList<String> voiceData = bundle.getStringArrayList(Request.RESULTS_RECOGNITION);
            final float[] confidenceScore = bundle.getFloatArray(Request.CONFIDENCE_SCORES);

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

            appendText("\n");

            bundle.remove(Request.RESULTS_RECOGNITION);
            bundle.remove(Request.CONFIDENCE_SCORES);

            final Set<String> keys = bundle.keySet();
            for (final String key : keys) {
                appendText(key + ": " + bundle.get(key));
            }

            if (voiceData != null && !voiceData.isEmpty()) {

                switch (bundle.getInt(SaiyKeyphrase.SAIY_ACTION, 0)) {

                    case DO_SOMETHING_1:
                        Log.i(CLS_NAME, "onHandleIntent: DO_SOMETHING_1");
                        appendText("Heard: " + SAUSAGES + "!");
                        break;
                    case DO_SOMETHING_2:
                        Log.i(CLS_NAME, "onHandleIntent: DO_SOMETHING_2");
                        appendText("\nStart the playlist: " + voiceData.get(0).toLowerCase(Locale.US)
                                .replaceFirst(SPOTIFY_PLAYLIST, "") + "!");
                        break;
                    case DO_SOMETHING_3:
                        Log.i(CLS_NAME, "onHandleIntent: DO_SOMETHING_3");
                        appendText("\nStart the album: " + voiceData.get(0).toLowerCase(Locale.US)
                                .replaceFirst(SPOTIFY_ALBUM, "") + "!");
                        break;
                    case DO_NOT_USE_ZERO:
                        Log.e(CLS_NAME, "onHandleIntent: DO_NOT_USE_ZERO");
                        break;
                }
            } else {
                Log.e(CLS_NAME, "onHandleIntent: voice data empty - should not happen");
            }
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
