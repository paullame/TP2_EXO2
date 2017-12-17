import org.apache.spark.graphx.VertexId

class Solar(monVertexId: Long) extends Creature(monVertexId) {

  val nom = "Solar"
  var vie = 363
  val atk = 18
  val arm = 44
  val nbAtk = 4
  val prec = 35
  val atkDice = 6
  val nbDice = 3
  val vit = 50
  var canAttack = true
  val equipe = true //gentils = true et méchants = false

  //nouvel attribut
  val regen = 15


  override def attaquer(creature: Creature, vertexId: VertexId): Int = {
    if (vertexId > 2) {
      attaqueDistance(creature)
    }
    else {
      attaqueMelee(creature)

    }
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


  def attaqueDistance(creature: Creature): Int = {

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
