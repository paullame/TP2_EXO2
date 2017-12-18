import org.apache.spark.graphx.VertexId

class WorgRider(monVertexId: Long) extends Creature(monVertexId) {

  val nom = "Worg Rider"
  var vie = 13
  val atk = 6
  val arm = 18
  val nbAtk = 1
  val prec = 6
  val atkDice = 8
  val nbDice = 1
  val vit = 20
  var canAttack = false
  val equipe = false //gentils = true et méchants = false


  override def attaquer(creature: Creature, vertexId: VertexId): Int = {

    attaqueMelee(creature)

  }

  def attaqueMelee(creature: Creature): Int = {

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
