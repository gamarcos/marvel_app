package br.com.gabrielmarcos.mavel_app.utils.extensions

import br.com.gabrielmarcos.mavel_app.base.business.expection.BusinessError
import br.com.gabrielmarcos.mavel_app.base.business.data.Result

fun <T> T.asSuccess(): Result.Success<T> = Result.Success(this)

fun <T : BusinessError> T.asError(): Result.Error = Result.Error(this)