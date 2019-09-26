package uz.tayi.lugat.ui.translation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_translation.*
import uz.tayi.lugat.R

class TranslationActivity : AppCompatActivity() {

    companion object {
        const val WORD = "word"
        const val TRANSLATION = "translation"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translation)
        setSupportActionBar(translationToolbar)
        val actionBar = supportActionBar
        actionBar?.title = ""
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val word: String = intent.getStringExtra(WORD)!!
        val translation: String = intent.getStringExtra(TRANSLATION)!!

        tvWord.text = word
        tvTranslation.text = translation
    }
}
