package vn.phitn.app.coroutines.repo

import vn.phitn.app.coroutines.CatApi
import vn.phitn.app.coroutines.UseCaseResult
import vn.phitn.app.coroutines.model.Cat
import java.lang.Exception

/*
* Created by phitn on 6/19/2020
*/

const val NUMBER_OF_CATS = 30

interface CatRepository {
    suspend fun getCatList(): UseCaseResult<List<Cat>>
}

class CatRepositoryImpl(private val catApi: CatApi): CatRepository{
    override suspend fun getCatList(): UseCaseResult<List<Cat>> {
        return try {
            val result = catApi.getCats(NUMBER_OF_CATS).await()
            UseCaseResult.Success(result)
        } catch (ex: Exception){
            UseCaseResult.Error(ex)
        }
    }
}