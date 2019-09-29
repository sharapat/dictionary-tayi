package uz.tayi.lugat.ui.translation

import uz.tayi.lugat.data.local.LugatDao
import uz.tayi.lugat.data.local.LugatEntity
import uz.tayi.lugat.repository.DatabaseQueryRepository
import uz.tayi.lugat.ui.base.BaseViewModel

class TranslationViewModel(val databaseQueryRepository: DatabaseQueryRepository) : BaseViewModel() {

    lateinit var model: LugatEntity
    lateinit var view: TranslationView

    fun init(view: TranslationView) {
        this.view = view
    }

    fun setModel(modelId: Int) {
        model = databaseQueryRepository.getModelById(modelId)
    }

    fun shareTranslation() {
        view.goToShare(model.getMessageForShare())
    }

    fun setFavorite() {
        view.showFavorite(model.isFavorite)
    }

    fun toggleFavorite() {
        val isFavorite = model.isFavorite == null || !model.isFavorite!!
        model.isFavorite = isFavorite
        databaseQueryRepository.updateData(model)
        view.showFavorite(isFavorite)
    }
}