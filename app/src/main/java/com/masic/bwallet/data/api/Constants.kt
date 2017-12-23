package com.masic.bwallet.data.api

import org.bitcoinj.params.MainNetParams
import org.bitcoinj.params.RegTestParams
import org.bitcoinj.params.TestNet3Params

/**
 * Created by masic on 20.12.2017
 * Project: bWallet
 */
enum class NetworkParams {
    TEST, MAIN, REG_TEST;

    fun get() = when (this) {
        TEST -> TestNet3Params.get()
        MAIN -> MainNetParams.get()
        REG_TEST -> RegTestParams.get()
    }!!

}

const val DEFAULT_WALLET_RESTORE_TIME = 1409478661L
const val DEFAULT_PASSPHRASE = ""
const val DEFAULT_WALLET_NAME = "bwallet-data"
const val DEFAULT_WALLET_KIT_DIR = "wallet-kit"
const val DEFAULT_QR_CODE_IMAGE_SIZE = 512
val DEFAULT_NET_PARAMS = NetworkParams.TEST