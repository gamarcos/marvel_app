package br.com.gabrielmarcos.mavel_app.base.business.interactor

import androidx.lifecycle.MutableLiveData
import br.com.gabrielmarcos.mavel_app.base.business.data.Result
import br.com.gabrielmarcos.mavel_app.base.business.expection.handleError
import br.com.gabrielmarcos.mavel_app.utils.extensions.asSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class UseCase<P, R> {

    @Throws(RuntimeException::class)
    abstract suspend fun execute(param: P? = null): R

    operator fun invoke(
        param: P? = null,
        result: MutableLiveData<Result<R>>
    ) {
        result.value = Result.Loading

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = withContext(Dispatchers.IO) { execute(param) }.asSuccess()
                result.postValue(response)
            } catch (e: Exception) {
                result.postValue(e.handleError())
            }
        }
    }
}
