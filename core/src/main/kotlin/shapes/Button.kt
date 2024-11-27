package shapes

import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class Button(texturepath: FileHandle?, x: Float, y: Float, largura: Float, altura: Float) :
    Retangle(texturepath, x, y, largura, altura) {
    var isPressed: Boolean = false
    private val pressedL = largura + 20
    private val pressedA = altura + 15

    override fun draw(batch: SpriteBatch?) {
        if (this.isPressed) {
            batch?.draw(texture, x - pressedL / 2, y, pressedL, pressedA)
        } else {
            batch?.draw(texture, x - largura / 2, y, largura, altura)
        }
    }
}
