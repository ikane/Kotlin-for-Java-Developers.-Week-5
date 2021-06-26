package board

fun main() {
    operator fun <T> GameBoard<T>.get(i: Int, j: Int) = get(getCell(i, j))
    operator fun <T> GameBoard<T>.set(i: Int, j: Int, value: T) = set(getCell(i, j), value)

    val gameBoard = createGameBoard<Char>(2)
    gameBoard[1, 1] = 'a'
    println(gameBoard[1, 1])
}

class GameBoardImpl<T>( width: Int) : GameBoard<T>, SquareBoardImpl(width) {

    private var cellValues: Map<Cell, T?> = mutableMapOf()

    init {
        for (cell in getAllCells()) {
            this.cellValues += cell to null
        }
    }

    override fun get(cell: Cell): T? {
        return cellValues.get(cell)
    }

    override fun set(cell: Cell, value: T?) {
            this.cellValues += cell to value
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> {
        return cellValues.filterValues { predicate.invoke(it) }.keys
    }

    override fun find(predicate: (T?) -> Boolean): Cell? {
        val filterValues = cellValues.filterValues { predicate.invoke(it) }
        return filterValues.keys.first()
    }

    override fun any(predicate: (T?) -> Boolean): Boolean {
        val filterValues = cellValues.filterValues { predicate.invoke(it) }
        return filterValues.isNotEmpty() //|| getAllCells().isNotEmpty()
    }

    override fun all(predicate: (T?) -> Boolean): Boolean {
        val filterValues = cellValues.filterValues { predicate.invoke(it) }
        return filterValues.size == getAllCells().size
    }

}