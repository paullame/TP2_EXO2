import org.apache.spark.graphx.VertexId

class Warlord(monVertexId: Long) extends Creature(monVertexId) {

  val nom = "Warlord"
  var vie = 141
  val atk = 10
  val arm = 27
  val nbAtk = 3
  val prec = 20
  val atkDice = 8
  val nbDice = 1
  val vit = 20
  var canAttack = false
  val equipe = false // gentils = true et méchants =false

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
