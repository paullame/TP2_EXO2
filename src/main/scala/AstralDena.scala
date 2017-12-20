import org.apache.spark.graphx.VertexId

class AstralDena(monVertexId: VertexId) extends Creature(monVertexId) {
  val nom: String = "Angel, Astral Deva"
  var vie: Int = 172
  val vieMax: Int = 172
  val atk: Int = 15
  val arm: Int = _
  val nbAtk: Int = _
  val prec: Int = 25
  val atkDice: Int = 8
  val nbDice: Int = 1
  val vit: Int = _
  var canAttack: Boolean = _
  val regen: Int =0
  val equipe: Boolean = _

  def attaquer(creature: Creature, vertexId: VertexId): Int = {

    val damages = attaqueMelee(creature)
    return damages
  }

  def attaqueMelee(creature: Creature): Int = {

    var attackDispo: Int = nbAtk
    var precision: Int = prec
    var totalDamage: Int = 0
    val strengh = Array(33, 33, 31, 31)
    var i = 0

    while (attackDispo > 0 && vie > 0)
    {

      //strenth counter
      if(i<3)
        i += 1


      if (creature.vie > 0) {
        var calc: Int = (Dice.launch(4, 8) + precision)
        calc += strengh(i)
        val touch = 2 * calc
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
