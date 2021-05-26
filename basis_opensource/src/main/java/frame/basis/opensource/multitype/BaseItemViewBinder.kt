package frame.basis.opensource.multitype

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.drakeet.multitype.ItemViewBinder

abstract class BaseItemViewBinder<T, VB : ViewBinding> :
    ItemViewBinder<T, BaseItemViewBinder.BaseViewHolder<VB>>() {

    override fun onBindViewHolder(holder: BaseViewHolder<VB>, item: T) {
        onBindItem(holder.viewBinding, item)
    }

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<VB> {
        return BaseViewHolder(onCreateViewBinding(inflater, parent))
    }

    abstract fun onCreateViewBinding(inflater: LayoutInflater, parent: ViewGroup): VB

    abstract fun onBindItem(viewBinding: VB, data: T)

    class BaseViewHolder<VB : ViewBinding>(val viewBinding: VB) :
        RecyclerView.ViewHolder(viewBinding.root)

}