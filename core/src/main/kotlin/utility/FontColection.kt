package utility

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter

enum class FontColection(size: Int) {
    TITLE(120),
    SUBTITLE(20),
    PLACAR(30);

    val font: BitmapFont

    init {
        val gen = FreeTypeFontGenerator(Gdx.files.internal("regular.ttf"))
        val parameter = FreeTypeFontParameter()
        parameter.color = Color.WHITE
        parameter.size = size
        font = gen.generateFont(parameter)
    }
}
