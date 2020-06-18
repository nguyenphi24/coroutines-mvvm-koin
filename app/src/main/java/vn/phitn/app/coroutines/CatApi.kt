package vn.phitn.app.coroutines

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query
import vn.phitn.app.coroutines.model.Cat

/*
* Created by phitn on 6/19/2020
*/
interface CatApi {
    @GET("images/search")
    fun getCats(@Query("limit") limit: Int): Deferred<List<Cat>>
}