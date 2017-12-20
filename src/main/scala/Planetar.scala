import org.apache.spark.graphx.VertexId

class Planetar(monVertexId: VertexId) extends Creature(monVertexId) {
  val nom: String = "Angel, Planetar"
  var vie: Int = 229
  val vieMax: Int = 229
  val atk: Int = 17
  val arm: Int = _
  val nbAtk: Int = _
  val prec: Int = 32
  val atkDice: Int = 6
  val nbDice: Int = 3
  val vit: Int = _
  var canAttack: Boolean = _
  val regen: Int = 10
  val equipe: Boolean = _

  def attaquer(creature: Creature, vertexId: VertexId): Int = {
    val damages = attaqueMelee(creature)
    return damages
  }

  def attaqueMelee(creature: Creature): Int = {

    var attackDispo: Int = nbAtk
    var precision: Int = prec
    var totalDamage: Int = 0
    val strengh = Array(27, 22, 17)
    var i = 0

    while (attackDispo > 0 && vie > 0)
    {

      if(i<2)
        i += 1


      if (creature.vie > 0) {


        var calc1 = (Dice.launch(3, 6) + 15)
        calc1 += strengh(i)

        // OR

        var calc2 = (Dice.launch(2, 8) + 12)
        calc2 += 24

        val touch = Math.max(calc1, calc2)

        if (touch >= creature.arm) {
          val degat = Dice.launch(nbDice, atkDice) + atk
          creature.takeDamage(degat)
          totalDamage += degat
          if (creature.vie <= 0)
            creature.die()
        }
      }
      precision -= 5
      attackDispo -= 1
    }
    totalDamage
  }


}
