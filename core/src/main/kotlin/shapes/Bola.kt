package shapes

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import java.util.Random

class Bola : Shape(Texture("boll.png")) {
    private val rand = Random()
    var radius = 15f
    private var xspeed = 1f
    private var yspeed = 9f

    init {
        this.y = rand.nextFloat(600f)
        centralize()
    }

    override fun draw(batch: SpriteBatch?) {
        batch?.draw(this.texture, this.x, this.y, this.radius * 2, this.radius * 2)
    }

    fun update() {
        x += xspeed // * randacceleration;
        y += yspeed

        if (y < 0 || y > 585) {
            yspeed *= -1f
        }
    }

    fun centralize() {
        this.xspeed = (if (rand.nextBoolean()) -6 else 6).toFloat()
        this.y = 300f
        this.x = 400f
        this.yspeed = 8f
    }

    fun changeDirection() {
        xspeed *= -1
    }

    fun accelerate(fator: Float) {
        xspeed = if (xspeed > 0) xspeed + fator else xspeed - fator
    }
}
