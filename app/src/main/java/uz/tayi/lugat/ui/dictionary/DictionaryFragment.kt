package uz.tayi.lugat.ui.dictionary

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.clear_recycler_view.*
import kotlinx.android.synthetic.main.fragment_dictionary.*
import org.koin.android.viewmodel.ext.android.viewModel
import uz.tayi.lugat.R
import uz.tayi.lugat.data.Status
import uz.tayi.lugat.data.local.LugatEntity
import uz.tayi.lugat.extensions.invisible
import uz.tayi.lugat.extensions.visible
import uz.tayi.lugat.ui.dictionary.list.DictionaryAdapter
import uz.tayi.lugat.ui.dictionary.list.DictionaryItemClickListener
import uz.tayi.lugat.ui.translation.TranslationActivity

class DictionaryFragment : Fragment(R.layout.fragment_dictionary), DictionaryItemClickListener {

    private val viewModel: DictionaryViewModel by viewModel()

    private val adapter = DictionaryAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvList.adapter = adapter
        rvList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        viewModel.dictionaryList.observe(this, Observer {
            when(it.status) {
                Status.SUCCESS -> {
                    adapter.setData(it.data!!)
                    pbLoading.invisible()
                    rvList.visible()
                }
                Status.ERROR -> {
                    pbLoading.invisible()
                    tvEmpty.visible()
                    tvEmpty.text = it.message
                }
                else -> {
                    return@Observer
                }
            }
        })
        viewModel.getDictionaries(spFrom.selectedItemPosition + 1, spTo.selectedItemPosition + 1)

        val spFromItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Nothing to do
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                adapter.setData(viewModel.setWordAndTranslation(position+1, spTo.selectedItemPosition+1))
            }

        }

        val spToItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Nothing to do
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                adapter.setData(viewModel.setWordAndTranslation(spFrom.selectedItemPosition+1, position+1))
            }

        }

        spFrom.onItemSelectedListener = spFromItemSelectedListener
        spTo.onItemSelectedListener = spToItemSelectedListener
    }

    override fun onItemClicked(model: LugatEntity) {
        val intent = Intent(context, TranslationActivity::class.java)
        intent.putExtra(TranslationActivity.WORD, model.word)
        intent.putExtra(TranslationActivity.TRANSLATION, model.translation)
        startActivity(intent)
    }

}