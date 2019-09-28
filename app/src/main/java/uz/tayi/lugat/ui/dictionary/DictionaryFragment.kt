package uz.tayi.lugat.ui.dictionary

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.core.widget.doOnTextChanged
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
import uz.tayi.lugat.ui.dictionary.suggestion.SuggestionAdapter
import uz.tayi.lugat.ui.dictionary.suggestion.SuggestionItemClickListener
import uz.tayi.lugat.ui.translation.TranslationActivity

class DictionaryFragment : Fragment(R.layout.fragment_dictionary), SuggestionItemClickListener {

    private val viewModel: DictionaryViewModel by viewModel()

    private val adapter = SuggestionAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvList.adapter = adapter
        rvList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        viewModel.suggestionList.observe(this, Observer {
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

        spTo.setSelection(1)
        viewModel.getDictionaries(spFrom.selectedItemPosition, spTo.selectedItemPosition)

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
                viewModel.getDictionaries(position, spTo.selectedItemPosition)
                adapter.updateItems(position, spTo.selectedItemPosition)
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
                viewModel.getDictionaries(spFrom.selectedItemPosition, position)
                adapter.updateItems(spFrom.selectedItemPosition, position)
            }

        }
        spFrom.onItemSelectedListener = spFromItemSelectedListener
        spTo.onItemSelectedListener = spToItemSelectedListener

        etSearch.doOnTextChanged { text, start, count, after ->
            viewModel.searchWordByQuery(text.toString(), spFrom.selectedItemPosition, spTo.selectedItemPosition)
        }
    }

    override fun onItemClicked(model: LugatEntity) {
        val intent = Intent(context, TranslationActivity::class.java)
        intent.putExtra(TranslationActivity.WORD, model.word)
        intent.putExtra(TranslationActivity.TRANSLATION, model.translation)
        intent.putExtra(TranslationActivity.MODEL_ID, model.id)
        startActivity(intent)
    }

}