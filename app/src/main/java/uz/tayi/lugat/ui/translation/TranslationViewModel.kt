package uz.tayi.lugat.ui.translation

import uz.tayi.lugat.data.local.LugatDao
import uz.tayi.lugat.data.local.LugatEntity
import uz.tayi.lugat.ui.base.BaseViewModel

class TranslationViewModel(val dao: LugatDao) : BaseViewModel() {

    lateinit var model: LugatEntity
    lateinit var view: TranslationView

    fun init(view: TranslationView) {
        this.view = view
    }

    fun setModel(modelId: Int) {
        model = dao.getModelById(modelId)
    }

    fun shareTranslation() {
        view.goToShare(model.getMessageForShare())
    }

    fun setFavorite() {
        view.showFavorite(model.isFavorite)
    }

    fun toggleFavorite() {
        var isFavorite = false
        if (model.isFavorite != null && !model.isFavorite!!) isFavorite = true
        model.isFavorite = isFavorite
        dao.update(model)
        view.showFavorite(isFavorite)
    }
}