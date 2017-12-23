package com.masic.bwallet.data.api;

import android.os.Handler;

import org.bitcoinj.utils.Threading;

/**
 * Created by masic on 20.12.2017
 * Project: bWallet
 */

public final class ThreadHelper {
    public static void setBtcSDKThread() {
        final Handler handler = new Handler();
        Threading.USER_THREAD = handler::post;
    }
}
