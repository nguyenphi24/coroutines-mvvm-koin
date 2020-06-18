package vn.phitn.app.coroutines

/*
* Created by phitn on 6/19/2020
*/
sealed class UseCaseResult<out T: Any> {
    class Success<out T: Any>(val data: T): UseCaseResult<T>()
    class Error(val exception: Throwable): UseCaseResult<Nothing>()
}