package states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.jpong.game.states.States

class Pause(gsm: GameStateManager?) : States(gsm) {
    override fun handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            this.gsm!!.pop()
        }
    }

    override fun update() {
    }

    override fun render(batch: SpriteBatch?) {
    }

    override fun dispose() {
    }
}
