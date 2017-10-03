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

package ai.saiy.android.apiexample.ui.activity;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;

import ai.saiy.android.api.Defaults;
import ai.saiy.android.api.remote.SaiyListener;
import ai.saiy.android.api.request.SaiyKeyphrase;
import ai.saiy.android.api.request.SaiyRequest;
import ai.saiy.android.api.request.SaiyRequestParams;
import ai.saiy.android.apiexample.R;
import ai.saiy.android.apiexample.configuration.SaiyConfiguration;
import ai.saiy.android.apiexample.ui.fragment.FragmentDemoBasic;
import ai.saiy.android.apiexample.ui.fragment.FragmentDemoCommand;
import ai.saiy.android.apiexample.ui.fragment.FragmentDemoInteraction;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        SaiyListener {

    /**
     * TAG
     */
    private final String CLS_NAME = MainActivity.class.getSimpleName();

    /**
     * Arbitrary request id.
     */
    private static final int PERMISSIONS_SAIY_REQUEST = 23;

    /**
     * Required permissions that must be declared in your manifest
     */
    private static final String[] PERMISSIONS_SAIY = {android.Manifest.permission.RECORD_AUDIO,
            SaiyRequest.CONTROL_SAIY};

    /**
     * Fragment Index Constants
     */
    public static final int INDEX_FRAGMENT_DEMO_BASIC = 0;
    public static final int INDEX_FRAGMENT_DEMO_COMMAND = 1;
    public static final int INDEX_FRAGMENT_DEMO_INTERACTION = 2;

    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView navigationView;

    /**
     * If you want to prompt the user to install Saiy, it's best only to do it once
     */
    private boolean showInstallSaiyDialog = true;

    /**
     * Both parameters are needed in order to interact with Saiy. We initialise them
     * only once in the parent activity. Child fragments can use getters {@link #getSaiyRequest()}
     * and {@link #getParams()}
     */
    public SaiyRequest saiyRequest;
    public SaiyRequestParams params;

    /**
     * Updated from different threads, an identifier as to whether you are able to
     * connect to the Saiy API service.
     */
    public static volatile boolean saiyAvailable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Do not set to true in production!!
        // Do not set to true in production!!
        // Do not set to true in production!!
        Defaults.setLogging(true);

        // Set up the UI
        setupUI();

        // Start the initial Fragment
        doFragmentReplaceTransaction(FragmentDemoBasic.newInstance(), INDEX_FRAGMENT_DEMO_BASIC);
    }

    @Override
    protected void onNewIntent(final Intent intent) {
        Log.i(CLS_NAME, "onNewIntent");

        if (intent == null || !intent.hasExtra(SaiyKeyphrase.SAIY_ACTION)) {
            return;
        }

        final FragmentDemoCommand fragment = (FragmentDemoCommand) getSupportFragmentManager()
                .findFragmentByTag(String.valueOf(INDEX_FRAGMENT_DEMO_COMMAND));

        if (fragment != null) {
            fragment.onExampleCallback(intent.getExtras());
        } else {
            Log.w(CLS_NAME, "onNewIntent: received example keyphrase intent, but Fragment is no longer " +
                    "visible");
        }
    }

    /**
     * Self explanatory utility
     */
    private void setupUI() {
        Log.i(CLS_NAME, "setupUI");

        setupToolbar();
        setupDrawer();
        setupNavigation();
    }

    /**
     * Self explanatory utility
     */
    private void setupToolbar() {
        Log.i(CLS_NAME, "setupToolbar");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * Self explanatory utility
     */
    private void setupDrawer() {
        Log.i(CLS_NAME, "setupDrawer");

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    /**
     * Self explanatory utility
     */
    private void setupNavigation() {
        Log.i(CLS_NAME, "setupNavigation");

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_basic);
        navigationView.getMenu().getItem(INDEX_FRAGMENT_DEMO_BASIC).setChecked(true);
    }


    /**
     * Helper method to replace the current fragment
     *
     * @param fragment to transact
     * @param tag      of the fragment
     */
    public void doFragmentReplaceTransaction(@NonNull final Fragment fragment, final int tag) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContent, fragment, String.valueOf(tag)).commit();
    }

    /**
     * Check if we are able to use the Saiy API
     */
    private void checkSaiyAvailability() {
        Log.i(CLS_NAME, "checkSaiyAvailability");

        final Pair<Boolean, SaiyRequest.SaiyAvailability> availabilityPair =
                SaiyRequest.isSaiyAvailable(getApplicationContext());

        saiyAvailable = availabilityPair.first;

        Log.i(CLS_NAME, "Saiy available " + saiyAvailable);

        if (!saiyAvailable) {

            switch (availabilityPair.second) {

                case ALL_GOOD:
                    break;
                case NOT_INSTALLED:
                    // Saiy not installed - show dialog once if you want!
                    if (showInstallSaiyDialog) {
                        showInstallSaiyDialog = false;
                        showInstallDialog();
                    }
                    break;
                case MISSING_PERMISSION:
                    checkPermissionsAndPrompt();
                    break;
                case NO_VR_PROVIDER:
                case NO_TTS_ENGINE:
                    /*
                     * Saiy will have already made the user aware of this, and for whatever reason it
                     * remains unresolved. Therefore the API is unusable.
                     */
                    break;
            }
        } else {
            initialiseSaiy();
        }
    }

    /**
     * Initialise the Saiy request as soon as the UI finishes drawing, so any background checks,
     * for text to speech and recognition languages can be prepared.
     */
    private void initialiseSaiy() {
        Log.i(CLS_NAME, "initialiseSaiy");

        if (saiyRequest == null) {
            params = SaiyConfiguration.getDefaultParams(getApplicationContext());
            saiyRequest = new SaiyRequest(getApplicationContext(), MainActivity.this,
                    params, true);
        }
    }

    /**
     * Exposed getter for Fragments
     *
     * @return the {@link SaiyRequestParams}
     */
    public SaiyRequestParams getParams() {
        return params;
    }

    /**
     * Exposed getter for Fragments
     *
     * @return the {@link SaiyRequest}
     */
    public SaiyRequest getSaiyRequest() {
        return saiyRequest;
    }

    /**
     * Send the callback information from the {@link SaiyListener} to the relevant
     * fragment
     *
     * @param requestId the unique request id sent with each API request
     */
    @Override
    public void onUtteranceCompleted(final String requestId) {

        final int currentTag = Integer.parseInt(getSupportFragmentManager().findFragmentById(
                R.id.fragmentContent).getTag());

        Fragment fragment;

        switch (currentTag) {

            case INDEX_FRAGMENT_DEMO_BASIC:
                fragment = getSupportFragmentManager()
                        .findFragmentByTag(String.valueOf(INDEX_FRAGMENT_DEMO_BASIC));

                if (fragment != null) {
                    ((FragmentDemoBasic) fragment).onUtteranceCompleted(requestId);
                }
                break;
            case INDEX_FRAGMENT_DEMO_INTERACTION:
                fragment = getSupportFragmentManager()
                        .findFragmentByTag(String.valueOf(INDEX_FRAGMENT_DEMO_INTERACTION));
                if (fragment != null) {
                    ((FragmentDemoInteraction) fragment).onUtteranceCompleted(requestId);
                }
                break;
        }
    }

    /**
     * Send the callback information from the {@link SaiyListener} to the relevant
     * fragment
     *
     * @param results   the {@link Bundle} containing result information
     * @param requestId the unique request id sent with each API request
     */
    @Override
    public void onSpeechResults(final Bundle results, final String requestId) {

        final int currentTag = Integer.parseInt(getSupportFragmentManager().findFragmentById(
                R.id.fragmentContent).getTag());

        Fragment fragment;

        switch (currentTag) {

            case INDEX_FRAGMENT_DEMO_BASIC:
                fragment = getSupportFragmentManager()
                        .findFragmentByTag(String.valueOf(INDEX_FRAGMENT_DEMO_BASIC));

                if (fragment != null) {
                    ((FragmentDemoBasic) fragment).onSpeechResults(results, requestId);
                }
                break;
            case INDEX_FRAGMENT_DEMO_INTERACTION:
                fragment = getSupportFragmentManager()
                        .findFragmentByTag(String.valueOf(INDEX_FRAGMENT_DEMO_INTERACTION));
                if (fragment != null) {
                    ((FragmentDemoInteraction) fragment).onSpeechResults(results, requestId);
                }
                break;
        }
    }

    /**
     * Send the callback information from the {@link SaiyListener} to the relevant
     * fragment
     *
     * @param error     one of {@link Defaults.ERROR}
     * @param requestId the unique request id sent with each API request
     */
    @Override
    public void onError(final Defaults.ERROR error, final String requestId) {

        final int currentTag = Integer.parseInt(getSupportFragmentManager().findFragmentById(
                R.id.fragmentContent).getTag());

        Fragment fragment;

        switch (currentTag) {

            case INDEX_FRAGMENT_DEMO_BASIC:
                fragment = getSupportFragmentManager()
                        .findFragmentByTag(String.valueOf(INDEX_FRAGMENT_DEMO_BASIC));

                if (fragment != null) {
                    ((FragmentDemoBasic) fragment).onError(error, requestId);
                }
                break;
            case INDEX_FRAGMENT_DEMO_INTERACTION:
                fragment = getSupportFragmentManager()
                        .findFragmentByTag(String.valueOf(INDEX_FRAGMENT_DEMO_INTERACTION));
                if (fragment != null) {
                    ((FragmentDemoInteraction) fragment).onError(error, requestId);
                }
                break;
        }
    }

    /**
     * Example dialog for prompting to install Saiy
     */
    public void showInstallDialog() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_menu_share);
        builder.setTitle(getString(R.string.install_dialog_title));
        builder.setMessage(getString(R.string.install_dialog_content));
        builder.setNegativeButton(R.string.install_dialog_negative, null);
        builder.setPositiveButton(R.string.install_dialog_positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int which) {

                final Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=ai.saiy.android"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                try {
                    startActivity(intent);
                } catch (final ActivityNotFoundException e) {
                    Log.e(CLS_NAME, "showInstall: ActivityNotFoundException");
                    e.printStackTrace();
                } catch (final Exception e) {
                    Log.e(CLS_NAME, "showInstall: Exception");
                    e.printStackTrace();
                }
            }
        });

        builder.show();
    }

    /**
     * Check if the required permissions have been granted
     *
     * @return true if the permission have granted, false if they are awaiting approval or have
     * been denied. For API < 23 this will immediately return true.
     */
    private boolean checkPermissionsAndPrompt() {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, SaiyRequest.CONTROL_SAIY)
                != PackageManager.PERMISSION_GRANTED) {

            Log.i(CLS_NAME, "checkPermissionsAndPrompt: PERMISSION_DENIED or not granted");

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.RECORD_AUDIO)
                    || ActivityCompat.shouldShowRequestPermissionRationale(this, SaiyRequest.CONTROL_SAIY)) {
                Log.i(CLS_NAME, "checkPermissionsAndPrompt: shouldShowRequestPermissionRationale");

                showSnackBar();
            } else {
                Log.i(CLS_NAME, "checkPermissionsAndPrompt: shouldShowRequestPermissionRationale: false");

                ActivityCompat.requestPermissions(this, PERMISSIONS_SAIY, PERMISSIONS_SAIY_REQUEST);
            }

        } else {
            Log.i(CLS_NAME, "checkPermissionsAndPrompt: PERMISSION_GRANTED");

            return true;
        }

        return false;
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String permissions[],
                                           @NonNull final int[] grantResults) {
        Log.i(CLS_NAME, "onRequestPermissionsResult");

        switch (requestCode) {

            case PERMISSIONS_SAIY_REQUEST:

                if (granted(grantResults)) {
                    Log.i(CLS_NAME, "onRequestPermissionsResult: PERMISSION_GRANTED");

                    saiyAvailable = true;

                    checkSaiyAvailability();

                } else {
                    Log.w(CLS_NAME, "onRequestPermissionsResult: PERMISSION_DENIED");

                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.RECORD_AUDIO)
                            || ActivityCompat.shouldShowRequestPermissionRationale(this, SaiyRequest.CONTROL_SAIY)) {
                        Log.i(CLS_NAME, "checkPermissionsAndPrompt: shouldShowRequestPermissionRationale");

                        showSnackBar();

                    } else {
                        Log.e(CLS_NAME, "checkPermissionsAndPrompt: shouldShowRequestPermissionRationale: false: game over");

                        saiyAvailable = false;
                    }

                }
                break;
        }
    }

    /**
     * Utility to check the permission outcome
     *
     * @param grantResults array of permission results
     * @return true if granted, false otherwise
     */
    private boolean granted(final int[] grantResults) {
        for (final int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * Just for example's sake of showing the permission rationale.
     */
    private void showSnackBar() {
        Snackbar.make(findViewById(R.id.fragmentContent), getString(R.string.permission_rationale),
                Snackbar.LENGTH_INDEFINITE).setAction(android.R.string.ok, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat
                        .requestPermissions(MainActivity.this, PERMISSIONS_SAIY,
                                PERMISSIONS_SAIY_REQUEST);
            }
        }).show();
    }

    /**
     * Standard check to default behaviour of closing the drawer, returning to the 'home'
     * Fragment or exiting the application
     */
    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            try {

                switch (Integer.valueOf(getSupportFragmentManager().findFragmentById(R.id.fragmentContent).getTag())) {

                    case INDEX_FRAGMENT_DEMO_BASIC:
                        Log.d(CLS_NAME, "onBackPressed: INDEX_FRAGMENT_DEMO_BASIC");
                        super.onBackPressed();
                        break;
                    default:
                        Log.i(CLS_NAME, "onBackPressed: default");

                        doFragmentReplaceTransaction(FragmentDemoBasic.newInstance(), INDEX_FRAGMENT_DEMO_BASIC);
                        navigationView.setCheckedItem(R.id.nav_basic);
                        navigationView.getMenu().getItem(INDEX_FRAGMENT_DEMO_BASIC).setChecked(true);
                        break;
                }

            } catch (final NullPointerException e) {
                Log.w(CLS_NAME, "onBackPressed: NullPointerException");
                super.onBackPressed();
            } catch (final NumberFormatException e) {
                Log.w(CLS_NAME, "onBackPressed: NumberFormatException");
                super.onBackPressed();
            }
        }
    }

    /**
     * Apply a standard check to make sure we are not wasting resource by
     * re-instantiating the same fragment that is already displayed.
     *
     * @param item the menu item that was clicked
     * @return true - regardless.
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        Log.i(CLS_NAME, "onNavigationItemSelected");

        drawer.closeDrawer(GravityCompat.START);

        new Thread(new Runnable() {
            @Override
            public void run() {

                final int currentTag = Integer.parseInt(getSupportFragmentManager().findFragmentById(
                        R.id.fragmentContent).getTag());
                final int id = item.getItemId();
                boolean proceed;

                switch (currentTag) {

                    case INDEX_FRAGMENT_DEMO_BASIC:
                        proceed = (id != R.id.nav_basic);
                        break;
                    case INDEX_FRAGMENT_DEMO_COMMAND:
                        proceed = (id != R.id.nav_command);
                        break;
                    case INDEX_FRAGMENT_DEMO_INTERACTION:
                        proceed = (id != R.id.nav_interaction);
                        break;
                    default:
                        proceed = false;
                        break;
                }

                Log.i(CLS_NAME, "onNavigationItemSelected: proceeding: " + proceed);

                if (proceed) {

                    final int tag;
                    final Fragment fragment;

                    switch (id) {

                        case R.id.nav_basic:
                            fragment = FragmentDemoBasic.newInstance();
                            tag = INDEX_FRAGMENT_DEMO_BASIC;
                            break;
                        case R.id.nav_command:
                            fragment = FragmentDemoCommand.newInstance();
                            tag = INDEX_FRAGMENT_DEMO_COMMAND;
                            break;
                        case R.id.nav_interaction:
                            fragment = FragmentDemoInteraction.newInstance();
                            tag = INDEX_FRAGMENT_DEMO_INTERACTION;
                            break;
                        default:
                            fragment = FragmentDemoBasic.newInstance();
                            tag = INDEX_FRAGMENT_DEMO_BASIC;
                            break;
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            doFragmentReplaceTransaction(fragment, tag);
                        }
                    });

                } else {
                    Log.i(CLS_NAME, "same fragment ignoring");
                }
            }
        }).start();

        return true;
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.i(CLS_NAME, "onPause");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(CLS_NAME, "onResume: saiyAvailable: " + saiyAvailable);

        /*
         * Always recheck in onResume, as the user may have returned
         * to the app after correcting a condition that previously
         * made the Saiy API unavailable. This check uses minimal resource.
         */
        if (!saiyAvailable) {
            checkSaiyAvailability();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(CLS_NAME, "onDestroy");
        saiyRequest = null;
        params = null;
        System.gc();
        System.exit(0);
    }
}
