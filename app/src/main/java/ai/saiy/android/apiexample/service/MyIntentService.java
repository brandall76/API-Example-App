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

package ai.saiy.android.apiexample.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Set;

import ai.saiy.android.api.request.SaiyKeyphrase;
import ai.saiy.android.apiexample.ui.activity.MainActivity;

/**
 * An example Intent Service class.
 * <p/>
 * <a href="http://developer.android.com/training/run-background-service/create-service.html#CreateClass">Intent Service</a>
 * <p/>
 * Before being able to receive data here, you'll need to make sure your user has accepted the
 * permission 'control Saiy'. An Intent filter is needed in the
 * Manifest <action android:name="ai.saiy.android.SAIY_INTENT_RECEIVER"/> which must set 'android:exported="true'
 * <p/>
 * Demonstrated here is how Saiy will communicate that your registered keyphrase has been detected and
 * for the sake of example, how you could send them on to your Activity.
 * <p>
 * Created by benrandall76@gmail.com on 26/04/2016.
 */
public class MyIntentService extends IntentService {

    /**
     * TAG
     */
    private final String CLS_NAME = MyIntentService.class.getSimpleName();

    /**
     * Constants to identify keyphrases
     */
    public static final int DO_NOT_USE_ZERO = 0;
    public static final int DO_SOMETHING_1 = 1;
    public static final int DO_SOMETHING_2 = 2;
    public static final int DO_SOMETHING_3 = 3;

    /**
     * Compulsory Constructor
     */
    public MyIntentService() {
        super(MyIntentService.class.getSimpleName());
    }

    @Override
    public void onCreate() {
        Log.i(CLS_NAME, "onCreate");
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(final Intent intent) {
        Log.i(CLS_NAME, "onHandleIntent");

        if (intent != null) {
            examineIntent(intent.getExtras());

            switch (intent.getIntExtra(SaiyKeyphrase.SAIY_ACTION, 0)) {

                case DO_SOMETHING_1:
                    Log.i(CLS_NAME, "onHandleIntent: DO_SOMETHING_1");
                    // Do whatever you like here!
                    simulateDoingSomething(intent.getExtras());
                    break;
                case DO_SOMETHING_2:
                    Log.i(CLS_NAME, "onHandleIntent: DO_SOMETHING_2");
                    // Do whatever you like here!
                    simulateDoingSomething(intent.getExtras());
                    break;
                case DO_SOMETHING_3:
                    Log.i(CLS_NAME, "onHandleIntent: DO_SOMETHING_3");
                    // Do whatever you like here!
                    simulateDoingSomething(intent.getExtras());
                    break;
                case DO_NOT_USE_ZERO:
                    Log.e(CLS_NAME, "onHandleIntent: DO_NOT_USE_ZERO");
                    // Don't deliberately use zero as the 'action number'. It's the default number
                    // set above if the SAIY_ACTION is missing - which it shouldn't be.
                    break;
            }
        } else {
            Log.e(CLS_NAME, "onHandleIntent: intent should not be null!?!");
        }
    }

    /**
     * Reacting to the keyphrase callback, you obviously can kick off any functionality
     * your application provides. Here we'll simply send the results
     * to {@link ai.saiy.android.apiexample.ui.fragment.FragmentDemoCommand} (via the parent activity)
     * and display them in the text view.
     *
     * @param bundle the bundle of extras returned with
     */
    private void simulateDoingSomething(@NonNull final Bundle bundle) {

        final Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(intent);
    }

    /**
     * For debugging the intent extras
     *
     * @param bundle containing potential extras
     */
    private void examineIntent(final Bundle bundle) {
        Log.i(CLS_NAME, "examineIntent");

        if (bundle != null) {
            final Set<String> keys = bundle.keySet();
            for (final String key : keys) {
                Log.v(CLS_NAME, "examineIntent: " + key + " ~ " + bundle.get(key));

            }
        } else {
            Log.w(CLS_NAME, "examineIntent: bundle was null. This should not happen, as the speech results" +
                    "and confidence should be returned, in addition to any parameters you originally set");
        }
    }
}
