package shapes

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

open class Retangle : Shape {
    var altura: Float
    var largura: Float

    internal constructor(texture: Texture, x: Float, y: Float, largura: Float, altura: Float) : super(texture) {
        this.x = x
        this.y = y
        this.largura = largura
        this.altura = altura
    }

    internal constructor(texturepath: FileHandle?, x: Float, y: Float, largura: Float, altura: Float) : super(
        Texture(
            texturepath
        )
    ) {
        this.x = x
        this.y = y
        this.largura = largura
        this.altura = altura
    }

    constructor(x: Float, y: Float) : super(Texture(Gdx.files.internal("player.png"))) {
        this.x = x
        this.y = y
        this.largura = 20f
        this.altura = 75f
    }

    override fun draw(batch: SpriteBatch?) {
        batch?.draw(this.texture, this.x, this.y, this.largura, this.altura)
    }
}
