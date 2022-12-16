package frame.basis.net

import androidx.annotation.Keep

@Keep
data class DataResult<T>(
    val code: Int,
    val message: String?,
    private val data: T?,
) {

    companion object {
//        inline fun <reified T> mutableLiveData(): MutableLiveData<DataWrapper<DataResult<T>>> {
//            return MutableLiveData<DataWrapper<DataResult<T>>>()
//        }
    }

    val notNullData: T
        get() = data ?: throw KotlinNullPointerException("data == null")

}