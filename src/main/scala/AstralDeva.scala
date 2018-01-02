import org.apache.spark.graphx.VertexId

class AstralDeva(monVertexId: VertexId) extends Creature(monVertexId) {
  val nom: String = "Angel, Astral Deva"
  var vie: Int = 172
  val vieMax: Int = 172
  val arm: Int = 29

  val meleeAtk: Int = 14
  val meleenbAtk: Int = 3
  val meleePrec: Int = 26
  val meleeAtkDice: Int = 8
  val meleenbDice: Int = 1

  val distAtk: Int = 0
  val distnbAtk: Int = 0
  val distPrec: Int = 0
  val distAtkDice: Int = 0
  val distnbDice: Int = 0


  val vit: Int = 50
  var canAttack: Boolean = false
  val regen: Int = 0
  val equipe: Boolean = false //true = gentils false = méchants

  def attaquer(creature: Creature, distance: Int): Int = {
    attaqueMelee(creature)
  }

  // on override car on a un multiplicateur x3
  override def attaqueMelee(creature: Creature): Int = {
    var attackLeft: Int = meleenbAtk
    var precisionLeft: Int = meleePrec
    var totalDamage: Int = 0
    var pvEnemie: Int = creature.vie

    while (attackLeft > 0 && vie > 0) {
      if (pvEnemie > 0) {
        val touch: Int = Dice.launch(1, 20) + precisionLeft
        if (touch >= creature.arm) {
          val degat = (Dice.launch(meleenbDice, meleeAtkDice) + meleeAtk)*3
          pvEnemie -= degat
          totalDamage += degat

        }
      }
      precisionLeft -= 5
      attackLeft -= 1
    }
    totalDamage

  }


}
