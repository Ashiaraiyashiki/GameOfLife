import java.awt.*
import javax.swing.*

class GameOfLife(var board: Board): JFrame() {

    private val rowI = arrayOf(-1, -1, -1, 0, 0, 1, 1, 1)
    private val colI = arrayOf(1, -1, 0, 1, -1, -1, 0, 1)
    private var copyOfBoard = board

    init {
        createUI()
    }

    private fun nextGeneration() {
        board = getBoardFromNewGeneration()
    }

    private fun getBoardFromNewGeneration(): Board {
        val newBoard = Board(board.k)
        for (i in 0 until board.k) {
            for (j in 0 until board.k) {
                var count = 0
                for (k in 0 until 8) {
                    val col = j + colI[k]
                    val row = i + rowI[k]
                    if ((col >= 0) && (col < board.k) && (row >= 0) && (row < board.k))
                        if (board.fieldList[row][col].isAlive) count++
                }
                if (board.fieldList[i][j].isAlive) {
                    if (count == 2 || count == 3) newBoard.fieldList[i][j].isAlive = true
                } else
                    if (count == 3) newBoard.fieldList[i][j].isAlive = true
            }
        }
        return newBoard
    }

    private lateinit var grid: MyJPanel
    private var start = false

    private fun createUI() {
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(1014, 767)
        setLocationRelativeTo(null)
        grid = MyJPanel(this, board.k)
        title = "Game Of Life"

        val container = JPanel()
        container.preferredSize = Dimension(width, height)
        container.layout = BorderLayout()

        val menu = JPanel()
        val startButton = Button("Play")
//        val restartButton = Button("Restart")
        val saveButton = Button("Save")
        val loadButton = Button("Load")
        val clearButton = Button("Clear")
        val nextGenerationButton = Button("Next Generation")
        val getForumulaButton = Button("Get Formula")

        menu.layout = GridLayout(1, 6)
        menu.preferredSize = Dimension(width, 30)
        menu.add(startButton)
//        menu.add(restartButton)
        menu.add(saveButton)
        menu.add(loadButton)
        menu.add(clearButton)
        menu.add(getForumulaButton)
        menu.add(nextGenerationButton)

        container.add(menu, BorderLayout.PAGE_START)
        container.add(grid, BorderLayout.CENTER)
        add(container)
        @Suppress("DEPRECATION")
        show()

        startButton.addActionListener {
            if(start){
                start = !start
                startButton.label = "Play"
//                board.getFormula()
            }else{
                startButton.label = "Pause"
                val thread = Thread {
                    while (start) {
                        nextGeneration()
                        grid.repaint()
                        Thread.sleep(120)
                    }
                }
                start = !start
                thread.start()
            }
        }

//        restartButton.addActionListener{
//            board = copyOfBoard
//            grid.repaint()
//        }

        saveButton.addActionListener {
            copyOfBoard = board
        }

        loadButton.addActionListener {
            board = copyOfBoard
            grid.repaint()
        }

        clearButton.addActionListener{
            board = Board(board.k)
            grid.repaint()
        }

        getForumulaButton.addActionListener {
            println("=====NEW FORMULA=====")
            board.getFormula()
            println("=====================")
        }

        nextGenerationButton.addActionListener {
            nextGeneration()
            grid.repaint()
        }
    }
}