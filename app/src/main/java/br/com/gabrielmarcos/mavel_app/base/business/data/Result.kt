package br.com.gabrielmarcos.mavel_app.base.business.data

import br.com.gabrielmarcos.mavel_app.base.business.expection.BusinessError

sealed class Result<out P> {
    data class Success<out R>(val data: R) : Result<R>()
    data class Error(val exception: BusinessError) : Result<Nothing>()
    object Loading : Result<Nothing>()
}