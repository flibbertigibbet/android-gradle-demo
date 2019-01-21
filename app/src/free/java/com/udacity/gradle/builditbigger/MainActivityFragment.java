package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements MainActivity.JokeInterface {

    private static final String LOG_LABEL = "MainFragment";

    private PublisherInterstitialAd interstitialAd;

    public MainActivityFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Log.d(LOG_LABEL, "onCreateView");

        ((MainActivity)getActivity()).setCallback(this);

        MobileAds.initialize(getActivity(), getString(R.string.admob_app_id));

        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        AdView mAdView = root.findViewById(R.id.adView);
        mAdView.loadAd(adRequest);

        interstitialAd = new PublisherInterstitialAd(getActivity());
        interstitialAd.setAdUnitId(getString(R.string.interstitial_unit_id));
        interstitialAd.loadAd(new PublisherAdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build());
        Log.d(LOG_LABEL, "loaded ads in free version");


        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                Log.d(LOG_LABEL, "ad failed to load with code: " + i);
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                Log.d(LOG_LABEL, "onAdClosed");

                interstitialAd.loadAd(new PublisherAdRequest.Builder()
                        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                        .build());
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
                Log.d(LOG_LABEL, "onAdLeftApp");
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
                Log.d(LOG_LABEL, "onAdImpression");
            }
        });
        return root;
    }


    public void clickedShowJoke() {
        Log.d(LOG_LABEL, "clickedShowJoke");
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            Log.w(LOG_LABEL, "cannot show interstitial ad yet");
        }
    }
}
