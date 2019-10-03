package uz.tayi.lugat.ui.translation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_translation.*
import org.koin.android.viewmodel.ext.android.viewModel
import uz.tayi.lugat.R

class TranslationActivity : AppCompatActivity(), TranslationView {

    companion object {
        const val WORD = "word"
        const val TRANSLATION = "translation"
        const val MODEL_ID = "modelId"
    }

    private val viewModel: TranslationViewModel by viewModel()

    private var modelId: Int = 0
    private lateinit var menuItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translation)
        setSupportActionBar(translationToolbar)
        viewModel.init(this)
        modelId = intent.getIntExtra(MODEL_ID, 0)
        viewModel.setModel(modelId)

        val actionBar = supportActionBar
        actionBar?.title = ""
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        window.statusBarColor = ContextCompat.getColor(this, R.color.color_primary_dark)

        val word: String = intent.getStringExtra(WORD)!!
        val translation: String = intent.getStringExtra(TRANSLATION)!!

        tvWord.text = word
        tvTranslation.text = translation
        viewModel.setLastAccessed(System.currentTimeMillis(), word, translation)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.translate, menu)
        menuItem = menu!!.findItem(R.id.menu_favourite)
        viewModel.setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.menu_favourite -> {
                viewModel.toggleFavorite()
                true
            }
            R.id.menu_share -> {
                viewModel.shareTranslation()
                true
            }
            else -> false
        }
    }

    override fun goToShare(message: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, message)
        startActivity(Intent.createChooser(intent, resources.getString(R.string.share_translation)))
    }

    override fun showFavorite(favourite: Boolean?) {
        if (favourite != null && favourite) {
            menuItem.setIcon(R.drawable.ic_bookmark_white_24dp)
        } else {
            menuItem.setIcon(R.drawable.ic_bookmark_border_white_24dp)
        }
    }
}
