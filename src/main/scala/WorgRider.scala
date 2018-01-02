import org.apache.spark.graphx.VertexId

/*
 * This class extends creature and implement
 * specific attrributes and fight method
 */


class WorgRider(monVertexId: Long) extends Creature(monVertexId) {

  val nom = "Worg Rider"
  var vie = 13
  val vieMax: Int = 13
  val arm = 18


  val meleeAtk: Int = 6
  val meleenbAtk: Int = 1
  val meleePrec: Int = 6
  val meleeAtkDice: Int = 8
  val meleenbDice: Int = 1

  val distAtk: Int = 0
  val distnbAtk: Int = 0
  val distPrec: Int = 0
  val distAtkDice: Int = 0
  val distnbDice: Int = 0

  val vit = 20
  var canAttack = false
  val regen: Int = 0
  val equipe = false //gentils = true et méchants = false


  def attaquer(creature: Creature, distance: Int): Int = {

    attaqueMelee(creature)

  }


}
