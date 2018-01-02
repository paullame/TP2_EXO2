import org.apache.spark.graphx.VertexId

/*
 * This class extends creature and implement
 * specific attrributes and fight method
 */

class Barbare(monVertexId: Long) extends Creature(monVertexId) {

  val nom: String = "Barbare"
  var vie: Int = 142
  val vieMax: Int = 142
  val arm: Int = 17

  val meleeAtk: Int = 10
  val meleenbAtk: Int = 3
  val meleePrec: Int = 19
  val meleeAtkDice: Int = 8
  val meleenbDice: Int = 1

  val distAtk: Int = 0
  val distnbAtk: Int = 0
  val distPrec: Int = 0
  val distAtkDice: Int = 0
  val distnbDice: Int = 0

  val vit: Int = 40
  var canAttack = false
  val regen: Int = 0
  val equipe: Boolean = false

  def attaquer(creature: Creature, distance: Int): Int = {
    val degats = attaqueMelee(creature)
    println("le "+nom+" inflige "+degats+" degats")
    degats
  }


}
