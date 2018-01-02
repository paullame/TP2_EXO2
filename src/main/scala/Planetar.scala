import org.apache.spark.graphx.VertexId

class Planetar(monVertexId: VertexId) extends Creature(monVertexId) {
  val nom: String = "Angel, Planetar"
  var vie: Int = 229
  val vieMax: Int = 229
  val arm: Int = 32

  val meleeAtk: Int = 15
  val meleenbAtk: Int = 3
  val meleePrec: Int = 27
  val meleeAtkDice: Int = 6
  val meleenbDice: Int = 3

  val distAtk: Int = 0
  val distnbAtk: Int = 0
  val distPrec: Int = 0
  val distAtkDice: Int = 0
  val distnbDice: Int = 0

  val vit: Int = 30
  var canAttack: Boolean = false
  val regen: Int = 10
  val equipe: Boolean = true //true = gentils false = méchants

  def attaquer(creature: Creature, distance: Int): Int = {
    attaqueMelee(creature)
  }




}
