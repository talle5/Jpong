package com.jpong.game.states

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import states.GameStateManager

abstract class States internal constructor(gsm: GameStateManager?) {
    protected var cam: OrthographicCamera = OrthographicCamera()

    protected var gsm: GameStateManager? = gsm

    abstract fun handleInput()

    abstract fun update()

    abstract fun render(batch: SpriteBatch?)

    abstract fun dispose()
}
