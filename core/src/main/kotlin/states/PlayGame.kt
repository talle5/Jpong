package states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.jpong.game.states.GameOver
import com.jpong.game.states.States
import shapes.Bola
import shapes.Retangle
import utility.FontColection

class PlayGame internal constructor(gsm: GameStateManager?) : States(gsm) {
    private val bola: Bola = Bola()
    private val player1: Retangle = Retangle(780f, 300f)
    private val player2: Retangle = Retangle(0f, 300f)
    private var player1points: Int = 0
    private var player2points: Int = 0
    private val startime: Long = System.currentTimeMillis()
    private var wait = 0

    override fun handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && player1.y < 525) {
            player1.y += 12
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && player1.y > 0) {
            player1.y -= 12
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W) && player2.y < 525) {
            player2.y += 12
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S) && player2.y > 0) {
            player2.y -= 12
        }
    }

    override fun update() {
        bola.update()

        if (checkColisionWhithplayer(player1) || checkColisionWhithplayer(player2)) {
            bola.x = if (bola.x > 300) 755f else 25f
            bola.changeDirection()
            bola.accelerate((System.currentTimeMillis() - startime).toFloat() / 700000)
        }

        if (bola.x > 840) {
            player1points++
            bola.centralize()
            wait = 0
        }

        if (bola.x < -40) {
            player2points++
            bola.centralize()
            wait = 0
        }

        if (player2points == 10 || player1points == 10) {
            gsm!!.set(GameOver(gsm, if (player1points > player2points) "play1" else "play2"))
        }

        wait++
    }

    override fun render(batch: SpriteBatch?) {
        FontColection.PLACAR.font.draw(batch, player1points.toString(), 200f, 550f)
        FontColection.PLACAR.font.draw(batch, player2points.toString(), 600f, 550f)
        bola.draw(batch)
        player1.draw(batch)
        player2.draw(batch)
    }

    override fun dispose() {
        bola.dispose()
        player1.dispose()
        player2.dispose()
    }

    private fun checkColisionWhithplayer(rec: Retangle): Boolean {
        return bola.x < rec.x + rec.largura &&
            bola.x + bola.radius * 2 > rec.x &&
            bola.y < rec.y + rec.altura &&
            bola.y + bola.radius * 2 > rec.y
    }
}
