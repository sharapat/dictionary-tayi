package uz.tayi.lugat.ui.history

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.fragment_history.*
import org.koin.android.viewmodel.ext.android.viewModel
import uz.tayi.lugat.R
import uz.tayi.lugat.data.Status
import uz.tayi.lugat.data.local.LugatEntity
import uz.tayi.lugat.extensions.invisible
import uz.tayi.lugat.extensions.visible
import uz.tayi.lugat.ui.base.BaseFragment
import uz.tayi.lugat.ui.history.list.HistoryAdapter
import uz.tayi.lugat.ui.history.list.OnHistoryItemClickListener
import uz.tayi.lugat.ui.translation.TranslationActivity

class HistoryFragment : BaseFragment(R.layout.fragment_history), OnHistoryItemClickListener {

    private val adapter = HistoryAdapter(this)
    private val viewModel: HistoryViewModel by viewModel()
  
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        historyList.adapter = adapter
        historyList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        viewModel.historyList.observe(this, Observer {
            when(it.status) {
                Status.LOADING -> return@Observer
                Status.SUCCESS -> {
                    if (it.data.isNullOrEmpty()) {
                        emptyHistory.visibility
                        historyList.invisible()
                    } else {
                        emptyHistory.invisible()
                        historyList.visible()
                        adapter.setData(it.data)
                    }
                }
                Status.ERROR -> toast(it.message!!)
            }
        })
        viewModel.getHistories()
    }

    override fun onItemClicked(model: LugatEntity) {
        val intent = Intent(context, TranslationActivity::class.java)
        intent.putExtra(TranslationActivity.MODEL_ID, model.id)
        intent.putExtra(TranslationActivity.WORD, model.word)
        intent.putExtra(TranslationActivity.TRANSLATION, model.translation)
        startActivity(intent)
    }
}