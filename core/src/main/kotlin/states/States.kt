package states

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch

abstract class States internal constructor(val gsm: GameStateManager) {
    protected var cam: OrthographicCamera = OrthographicCamera()

    abstract fun handleInput()

    abstract fun update()

    abstract fun render(batch: SpriteBatch)

    abstract fun dispose()
}
