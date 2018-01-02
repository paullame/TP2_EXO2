import org.apache.spark.graphx.VertexId

/**
  * Created by Etudes on 19/12/2017.
  */
class GreatWyrm (monVertexId: VertexId) extends Creature(monVertexId) {

  val nom: String = "Green Dragon, Great Wyrm"
  var vie: Int = 391
  val vieMax: Int = 391
  val arm: Int = 37


  val meleeAtk: Int = 21
  val meleenbAtk: Int = 1
  val meleePrec: Int = 33
  val meleeAtkDice: Int = 8
  val meleenbDice: Int = 4

  val distAtk: Int = 0
  val distnbAtk: Int = 1
  val distPrec: Int = 100000
  val distAtkDice: Int = 6
  val distnbDice: Int = 24

  val vit: Int = 250
  var canAttack: Boolean = true
  val regen: Int = 0
  val equipe: Boolean = false

  def attaquer(creature: Creature, distance: Int): Int = {
    jetAcide(creature)
  }

  def jetAcide(creature: Creature): Int = {
    var attackLeft: Int = meleenbAtk
    var totalDamage: Int = 0
    var pvEnemie: Int = creature.vie

    while (attackLeft > 0 && vie > 0) {
      if (pvEnemie > 0) {
          val degat = Dice.launch(distnbDice, distAtkDice) + distAtk
          pvEnemie -= degat
          totalDamage += degat

      }
      attackLeft -= 1
    }
    totalDamage
  }


}
