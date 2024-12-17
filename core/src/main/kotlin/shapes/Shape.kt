package shapes

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

abstract class Shape protected constructor(protected var texture: Texture) {
    var x: Float = 0f
    var y: Float = 0f

    abstract fun draw(batch: SpriteBatch)

    fun dispose() {
        texture.dispose()
    }
}
