package frame.basis.jitpack.extension.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

typealias Scope<T> = suspend (CoroutineScope) -> T
typealias Error = suspend (Exception) -> Unit
typealias Cancel = suspend () -> Unit

fun ViewModel.launchExpand(scope: Scope<Unit>, error: Error? = null, cancel: Cancel? = null): Job {
    return viewModelScope.launch {
        try {
            scope.invoke(this)
        } catch (e: Exception) {
            when (e) {
                is CancellationException -> cancel?.invoke()
                else -> error?.invoke(e)
            }
        }
    }
}

suspend inline fun <T> ViewModel.asyncExpand(crossinline scope: Scope<T>): T {
    return withContext(viewModelScope.coroutineContext) { scope.invoke(this) }
}

fun Job.safeCancelExpand() {
    if (job.isActive && !job.isCompleted && !job.isCancelled) {
        job.cancel()
    }
}