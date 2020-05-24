class Board(val k: Int) {

    var fieldList: ArrayList<ArrayList<Field>> = ArrayList()

    init {
        for (i in 0 until k) {
            fieldList.add(ArrayList())
            for (j in 0 until k) {
                fieldList[i].add(Field())
            }
        }
    }

    fun getFormula() {
        for (i in 0 until k) {
            for (j in 0 until k) {
                if(fieldList[i][j].isAlive) println("board.fieldList[$i][$j].isAlive = true")
            }
        }
    }

}