package states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import shapes.Button
import utility.FontColection
import kotlin.system.exitProcess

class MenuGame(gsm: GameStateManager) : States(gsm) {
    private val startbutton: Button =
        Button(Gdx.files.internal("start.png"), 400f, 240f, 150f, 45f)
    private val exitbutton: Button = Button(Gdx.files.internal("exit.png"), 400f, 170f, 150f, 45f)

    init {
        startbutton.isSelected = true
    }

    override fun handleInput() {

        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) || Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            startbutton.isSelected = !startbutton.isSelected
            exitbutton.isSelected = !exitbutton.isSelected
        }

        if (startbutton.isSelected && Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            gsm.push(PlayGame(gsm))
//            gsm.push(RemotePlay(gsm))
        }

        if (exitbutton.isSelected && Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            gsm.clean()
            Gdx.app.exit()
            exitProcess(0)
        }
    }

    override fun update() {
    }

    override fun render(batch: SpriteBatch) {
        FontColection.TITLE.font.draw(batch, "Jpong", 235f, 500f)
        FontColection.SUBTITLE.font.draw(batch, "A pong game", 335f, 380f)
        startbutton.draw(batch)
        exitbutton.draw(batch)
    }

    override fun dispose() {
        startbutton.dispose()
        exitbutton.dispose()
    }
}
