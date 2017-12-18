import org.apache.spark.graphx.VertexId

/*
 * This class extends creature and implement
 * specific attrributes and fight method
 */

class Barbare(monVertexId: Long) extends Creature(monVertexId) {

  val nom: String = "Barbare"
  var vie: Int = 142
  val atk: Int = 10
  val arm: Int = 17
  val nbAtk: Int = 3
  val prec: Int = 19
  val atkDice: Int = 8
  val nbDice: Int = 1
  val vit: Int = 40
  var canAttack = false
  val equipe: Boolean = false

  override def attaquer(creature: Creature, vertexId: VertexId): Int = {
    attaqueMelee(creature)
  }


  /*This method implemente the fight as a melee and return the damages inflicts
  * parameter :  the target an Creature
  * output : Int : the total damage value
  */
  override def attaqueMelee(creature: Creature): Int = {

    var attackLeft: Int = nbAtk
    var precisionLeft: Int = prec
    var totalDamage: Int = 0

    while (attackLeft > 0 && vie > 0) // && distance <= 5)
    {
      if (creature.vie > 0) {
        val touch: Int = Dice.launch(1, 20) + precisionLeft
        if (touch >= creature.arm) {
          val degat = Dice.launch(nbDice, atkDice) + atk
          creature.takeDamage(degat)
          totalDamage += degat
          if (creature.vie <= 0)
            creature.die()
        }
      }
      precisionLeft -= 5
      attackLeft -= 1
    }
    totalDamage
  }


}
