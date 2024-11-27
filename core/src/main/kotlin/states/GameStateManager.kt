package states

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils
import com.jpong.game.states.States
import java.util.*

class GameStateManager {
    private val states: Stack<States> = Stack<States>()

    fun push(state: States) {
        states.push(state)
    }

    fun pop() {
        states.pop().dispose()
    }

    fun set(state: States) {
        this.pop()
        states.push(state)
    }

    fun update() {
        states.peek().handleInput()
        states.peek().update()
    }

    fun render(batch: SpriteBatch) {
        ScreenUtils.clear(0f,0f,0f,0f)
        batch.begin()
        states.peek().render(batch)
        batch.end()
    }

    fun restart() {
        this.clean()
        states.add(MenuGame(this))
    }

    fun clean() {
        while (!states.empty()) {
            this.pop()
        }
    }
}
