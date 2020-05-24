import java.awt.Color
import java.awt.Graphics
import java.awt.event.MouseListener
import javax.swing.JPanel

class MyJPanel(val gameOfLife: GameOfLife, val k: Int) : JPanel() {

    private val color = Color(48, 196, 196)

    init {
        this.background = Color.darkGray
        addMouseListener(MouseEvent(this))
        setSize(700, 700)
    }

    override fun paintComponent(g: Graphics?) {

        super.paintComponent(g)

        val width = this.width / k
        val height = this.height / k

        for (x in gameOfLife.board.fieldList.indices) {
            for (y in gameOfLife.board.fieldList[x].indices) {
                g!!.color = Color.black
                g.drawRect(width * y, height * x, width, height)
                if (gameOfLife.board.fieldList[x][y].isAlive) {
                    g.color = color
                    g.fillRect(width * y, height * x, width, height)
                }
            }
        }
    }
}

private class MouseEvent(val myJPanel: MyJPanel): MouseListener {
    override fun mouseReleased(e: java.awt.event.MouseEvent?) {}

    override fun mouseEntered(e: java.awt.event.MouseEvent?) {}

    override fun mouseClicked(e: java.awt.event.MouseEvent?) {
        val x = ((e!!.x*myJPanel.k)/myJPanel.width)
        val y = ((e.y*myJPanel.k)/myJPanel.height)
        myJPanel.gameOfLife.board.fieldList[y][x].isAlive = !myJPanel.gameOfLife.board.fieldList[y][x].isAlive
        myJPanel.repaint()
    }

    override fun mouseExited(e: java.awt.event.MouseEvent?) {}

    override fun mousePressed(e: java.awt.event.MouseEvent?) {}
}