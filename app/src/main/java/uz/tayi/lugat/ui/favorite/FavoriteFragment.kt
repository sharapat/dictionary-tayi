package uz.tayi.lugat.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.fragment_favorites.*
import org.koin.android.viewmodel.ext.android.viewModel
import uz.tayi.lugat.R
import uz.tayi.lugat.data.Status
import uz.tayi.lugat.data.local.LugatEntity
import uz.tayi.lugat.extensions.invisible
import uz.tayi.lugat.extensions.visible
import uz.tayi.lugat.ui.translation.TranslationActivity

class FavoriteFragment : Fragment(R.layout.fragment_favorites), OnFavoriteItemClickListener {

    private val viewModel: FavoriteViewModel by viewModel()

    private val adapter: FavoritesAdapter = FavoritesAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerFavorites.adapter = adapter
        recyclerFavorites.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        viewModel.favoriteList.observe(this, Observer {
            when(it.status) {
                Status.ERROR -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                Status.SUCCESS -> {
                    if (it.data != null && it.data.isNotEmpty()) {
                        adapter.setData(it.data)
                        recyclerFavorites.visible()
                        emptyFavorites.invisible()
                        favoritesProgress.invisible()
                    } else {
                        favoritesProgress.invisible()
                        recyclerFavorites.invisible()
                        emptyFavorites.visible()
                    }
                }
                Status.LOADING -> {
                    emptyFavorites.invisible()
                    recyclerFavorites.invisible()
                    favoritesProgress.visible()
                }
            }
        })
        viewModel.getFavorites()
    }

    override fun onFavoriteButtonClicked(model: LugatEntity) {
        viewModel.unFavorite(model)
    }

    override fun onFavoriteItemClicked(model: LugatEntity) {
        val intent = Intent(context, TranslationActivity::class.java)
        intent.putExtra(TranslationActivity.MODEL_ID, model.id)
        intent.putExtra(TranslationActivity.WORD, model.word)
        intent.putExtra(TranslationActivity.TRANSLATION, model.translation)
        startActivity(intent)
    }
}