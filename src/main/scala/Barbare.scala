import org.apache.spark.graphx.VertexId

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

  override def attaqueMelee(creature: Creature): Int = {

    var attackLeft: Int = nbAtk
    var precisionLeft: Int = prec
    var totalDamage: Int = 0

    while (attackLeft > 0 && vie > 0) // && distance <= 5)
    {
      if (creature.vie > 0) {
        val touch: Int = dice(1, 20) + precisionLeft
        if (touch >= creature.arm) {
          val degat = dice(nbDice, atkDice) + atk
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
