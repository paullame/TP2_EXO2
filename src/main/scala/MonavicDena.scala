import org.apache.spark.graphx.VertexId

class MonavicDena(monVertexId: VertexId) extends Creature(monVertexId) {
  val nom: String = "Angel, Movanic Deva"
  var vie: Int = 126
  val vieMax: Int = 126
  val arm: Int = 24

  val meleeAtk: Int = 7
  val meleenbAtk: Int = 3
  val meleePrec: Int = 24
  val meleeAtkDice: Int = 6
  val meleenbDice: Int = 2

  val distAtk: Int = 0
  val distnbAtk: Int = 0
  val distPrec: Int = 0
  val distAtkDice: Int = 0
  val distnbDice: Int = 0

  val vit: Int = 40
  var canAttack: Boolean = false
  val regen: Int = 0
  val equipe: Boolean = true //true = gentils false = méchants

  def attaquer(creature: Creature, distance: Int): Int = {
    attaqueMelee(creature)
  }


}
