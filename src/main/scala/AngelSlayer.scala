import org.apache.spark.graphx.VertexId

class AngelSlayer(monVertexId: Long) extends Creature(monVertexId){
  val nom: String = "Angel Slayer (Half-Orc Ranger 15)"
  var vie: Int = 112
  val vieMax: Int = 112
  val arm: Int = 26

  val meleeAtk: Int = 7
  val meleenbAtk: Int = 3
  val meleePrec: Int = 21
  val meleeAtkDice: Int = 8
  val meleenbDice: Int = 1

  val distAtk: Int = 6
  val distnbAtk: Int = 3
  val distPrec: Int = 19
  val distAtkDice: Int = 8
  val distnbDice: Int = 1


  val vit: Int = 40
  var canAttack: Boolean = true
  val equipe: Boolean = false //true = gentils false = méchants
  val regen: Int = 0


  def attaquer(creature: Creature, distance: Int): Int = {
     attaqueDistance(creature)
  }


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

  override def attaqueDistance(creature: Creature): Int = {
    var attackLeft: Int = distnbAtk
    var precisionLeft: Int = distPrec
    var totalDamage: Int = 0
    var pvEnemie: Int = creature.vie

    while (attackLeft > 0 && vie > 0) {
      if (pvEnemie > 0) {
        val touch: Int = Dice.launch(1, 20) + precisionLeft
        if (touch >= creature.arm) {
          val degat = (Dice.launch(distnbDice, distAtkDice) + distAtk)*3
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
