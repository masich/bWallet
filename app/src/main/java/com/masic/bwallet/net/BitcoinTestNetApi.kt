package net

import com.squareup.okhttp.ResponseBody
import net.responces.BlockCountResponse
import net.responces.GetTransactionsResponse
import net.responces.SendTransactionResponse
import net.models.UtxoModel
import net.responces.InfoResponce
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by masic_0 on 26.12.2017.
 * Project name: Wallet
 */
interface BitcoinTestNetApi {
    @get:GET("api/status?q=getBlockCount")
    val blockCount: Call<BlockCountResponse>

    @FormUrlEncoded
    @Headers("Cache-control: no-cache; Postman-token: d6ac725c-f877-6ced-b32a-0f2418be8720")
    @POST("api/addrs/txs")
    fun getTxsForAddresses(@Field("addrs") addrs: String, @Field("from") from : Int, @Field("to") to : Int): Call<GetTransactionsResponse>


    @FormUrlEncoded
    @POST("api/addrs/utxo")
    fun getUtxoForAddresses(@Field("addrs") addrs: String): Call<List<UtxoModel>>

    @FormUrlEncoded
    @POST("api/tx/send")
    fun sendTx(@Field("rawtx") rawtx: String): Call<SendTransactionResponse>


    @GET("api/status?q=getInfo")
    fun getNetworkInfo(): Call<InfoResponce>
}
