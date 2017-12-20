import org.apache.spark.graphx.VertexId

class MonavicDena(monVertexId: VertexId) extends Creature(monVertexId) {
  val nom: String = "Angel, Movanic Deva"
  var vie: Int = 126
  val vieMax: Int = 126
  val atk: Int = 12
  val arm: Int = _
  val nbAtk: Int = _
  val prec: Int = 24
  val atkDice: Int = 6
  val nbDice: Int = 2
  val vit: Int = _
  var canAttack: Boolean = _
  val regen: Int = 0
  val equipe: Boolean = _

  def attaquer(creature: Creature, vertexId: VertexId): Int = {

    val damages = attaqueMelee(creature)
    return damages
  }


  def attaqueMelee(creature: Creature): Int = {

    var attackDispo: Int = nbAtk
    var precision: Int = prec
    var totalDamage: Int = 0
    val strengh = Array(17, 12, 7)
    var i = 0

    while (attackDispo > 0 && vie > 0)
    {

      if(i<2)
        i += 1


      if (creature.vie > 0) {
        var calc: Int = (Dice.launch(2, 6) + precision)
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
