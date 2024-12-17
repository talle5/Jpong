package states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import java.io.IOException
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress


class RemotePlay(gsm: GameStateManager) : PlayGame(gsm) {

    private val socket = DatagramSocket(8000)
    private val receivedata = Thread(this::recevUdpMessage)
    private var stopsocket = false
    private val setfield = mapOf("p1" to player1, "p2" to player2)

    init {
        receivedata.start()
    }

    override fun handleInput() {

        if (Gdx.input.isKeyPressed(Input.Keys.UP) && player1.y < 525) {
            player1.y += 12
            sendUdpMessage("PLAYMOVE p1 12")
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && player1.y > 0) {
            player1.y -= 12
            sendUdpMessage("PLAYMOVE p1 -12")
        }

    }

    private fun recevUdpMessage() {
        try {
            val receiveBuffer = ByteArray(1024)
            while (!stopsocket) {
                val receivePacket = DatagramPacket(receiveBuffer, receiveBuffer.size)
                socket.receive(receivePacket)
                val receivedMessage = String(receivePacket.data, 0, receivePacket.length).split(" ")
                when (receivedMessage[0]) {
                    "PLAYMOVE" -> setfield[receivedMessage[1]]!!.y += receivedMessage[2].toFloat()
                    "SETBOLL" -> {
                        bola.x = receivedMessage[1].toFloat()
                        bola.y = receivedMessage[2].toFloat()
                        bola.xspeed = receivedMessage[3].toFloat()
                        bola.yspeed = receivedMessage[4].toFloat()
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun sendUdpMessage(message: String) {
        try {
            val data: ByteArray = message.toByteArray()
            val sendPacket = DatagramPacket(data, data.size, InetAddress.getByName("172.18.101.170"), 8000)
            socket.send(sendPacket)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    override fun update() {
        bola.update()

        if (checkColisionWhithplayer(player1) || checkColisionWhithplayer(player2)) {
            bola.x = if (bola.x > 300) 755f else 25f
            bola.changeDirection()
            bola.accelerate((System.currentTimeMillis() - startime).toFloat() / 700000)
            sendUdpMessage("SETBOLL ${bola.x} ${bola.y} ${bola.xspeed} ${bola.yspeed}")
        }

        if (bola.x > 840) {
            player1points++
            waitingturn()
        }

        if (bola.x < -40) {
            player2points++
            waitingturn()
        }

        if (player2points == 10 || player1points == 10) {
            gsm.set(GameOver(gsm, if (player1points > player2points) "play1" else "play2"))
        }
    }

    private fun waitingturn() {
        Thread {
            Thread.sleep(1000)
            bola.centralize()
            sendUdpMessage("SETBOLL ${bola.x} ${bola.y} ${bola.xspeed} ${bola.yspeed}")
        }.start()
    }

    override fun dispose() {
        super.dispose()
        stopsocket = true
        socket.disconnect()
        receivedata.interrupt()
    }
}
