package states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.jpong.game.states.States
import shapes.Button
import utility.FontColection
import kotlin.system.exitProcess

class MenuGame(gsm: GameStateManager?) : States(gsm) {
    private val startbutton: Button =
        Button(Gdx.files.internal("start.png"), 400f, 240f, 150f, 45f)
    private val exitbutton: Button

    init {
        startbutton.isPressed = true
        exitbutton = Button(Gdx.files.internal("exit.png"), 400f, 170f, 150f, 45f)
    }

    override fun handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) || Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            startbutton.isPressed = !startbutton.isPressed
            exitbutton.isPressed = !exitbutton.isPressed
        }

        if (startbutton.isPressed && Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            gsm!!.push(PlayGame(gsm))
        }

        if (exitbutton.isPressed && Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            gsm!!.clean()
            Gdx.app.exit()
            exitProcess(0)
        }
    }

    override fun update() {
    }

    override fun render(render: SpriteBatch?) {
        FontColection.TITLE.font.draw(render, "Jpong", 235f, 500f)
        FontColection.SUBTITLE.font.draw(render, "A pong game", 335f, 380f)
        startbutton.draw(render)
        exitbutton.draw(render)
    }

    override fun dispose() {
        startbutton.dispose()
        exitbutton.dispose()
    }
}
