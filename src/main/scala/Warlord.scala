import org.apache.spark.graphx.VertexId

/*
 * This class extends creature and implement
 * specific attrributes and fight method
 */

class Warlord(monVertexId: Long) extends Creature(monVertexId) {

  val nom = "Warlord"
  var vie = 141
  val vieMax: Int = 141
  val arm = 27

  val atk = 10
  val nbAtk = 3
  val prec = 20
  val atkDice = 8
  val nbDice = 1

  val meleeAtk: Int = 10
  val meleenbAtk: Int = 3
  val meleePrec: Int = 20
  val meleeAtkDice: Int = 8
  val meleenbDice: Int = 1

  val distAtk: Int = 0
  val distnbAtk: Int = 0
  val distPrec: Int = 0
  val distAtkDice: Int = 0
  val distnbDice: Int = 0


  val vit = 30
  var canAttack = false
  val regen: Int = 0
  val equipe = false // gentils = true et méchants =false

   def attaquer(creature: Creature, distance: Int): Int = {
    attaqueMelee(creature)
  }


}
