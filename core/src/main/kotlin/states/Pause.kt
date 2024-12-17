package states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import utility.FontColection

class Pause(gsm: GameStateManager) : States(gsm) {
    override fun handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            this.gsm.pop()
        }
    }

    override fun update() {
    }

    override fun render(batch: SpriteBatch) {
        FontColection.TITLE.font.draw(batch, "PAUSED", 145f, 400f)
    }

    override fun dispose() {
    }
}
