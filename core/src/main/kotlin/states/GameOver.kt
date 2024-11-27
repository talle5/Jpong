package com.jpong.game.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import states.GameStateManager
import states.MenuGame
import utility.FontColection

class GameOver(gsm: GameStateManager?, private val winner: String) : States(gsm) {
    override fun handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            gsm!!.clean()
            gsm!!.push(MenuGame(gsm))
        }
    }

    override fun update() {
    }

    override fun render(batch: SpriteBatch?) {
        FontColection.TITLE.font.draw(batch, "$winner win!", 145f, 400f)
        FontColection.SUBTITLE.font.draw(batch, "press space to return start menu", 250f, 280f)
    }

    override fun dispose() {
    }
}
