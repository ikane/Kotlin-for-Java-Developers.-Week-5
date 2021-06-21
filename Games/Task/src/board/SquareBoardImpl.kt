package board

import java.lang.IllegalArgumentException

open class SquareBoardImpl(override val width: Int) : SquareBoard {
    private var cells: List<Cell> = mutableListOf()

    init {
        for (i in 1..width)
            for (j in 1..width)
                cells += Cell(i,j)
    }

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        for (cell in cells) {
            if (cell.i==i && cell.j==j)
                return cell
        }
        return null
    }

    override fun getCell(i: Int, j: Int): Cell {
        if (i<=width && j<=width)
            return cells.first { cell -> cell.i==i && cell.j==j }
        else throw IllegalArgumentException("Incorrect coordinates ($i, $j)")
    }

    override fun getAllCells(): Collection<Cell> {
        return cells
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        var rowCells: List<Cell> = emptyList()
        for(j in jRange) {
            if(j <= width)
                rowCells += this.getCell(i, j)
        }
        return rowCells
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        var columnCells: List<Cell> = emptyList()
        for(i in iRange) {
            if(i <= width)
                columnCells += this.getCell(i, j)
        }
        return columnCells
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        return when(direction) {
            Direction.UP -> getCellOrNull(this.i-1, this.j)
            Direction.DOWN -> getCellOrNull(this.i+1, this.j)
            Direction.LEFT -> getCellOrNull(this.i, this.j-1)
            Direction.RIGHT -> getCellOrNull(this.i, this.j+1)
        }
    }
}