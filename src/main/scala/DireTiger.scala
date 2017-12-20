import org.apache.spark.graphx.VertexId

class DireTiger(monVertexId: VertexId) extends Creature(monVertexId){
  val nom: String = "Dire Tiger (Cat, Great)"
  var vie: Int = 105
  val vieMax: Int = 105
  val atk: Int = 10
  val arm: Int = _
  val nbAtk: Int = _
  val prec: Int = 17
  val atkDice: Int = 4
  val nbDice: Int =8
  val vit: Int = _
  var canAttack: Boolean = _
  val equipe: Boolean = _
  val regen: Int = _


  def attaquer(creature: Creature, vertexId: VertexId): Int = {

    val damages = specialAttack(creature)
    return damages

  }


  def specialAttack(creature: Creature): Int = {

    var damages =0
    var attackDispo: Int = nbAtk

    while (attackDispo > 0 && vie > 0){
      var SpecialValue = 0
      if (creature.vie > 0) {
        SpecialValue += Dice.launch(2, 4) + 8
        SpecialValue += 18 //griffe
      }
      if (SpecialValue >= creature.arm) {
        creature.takeDamage(SpecialValue)
        damages += SpecialValue
        if (creature.vie <= 0)
          creature.die()
      }

      attackDispo -= 1

    }

  return damages
  }



  def attaqueMelee(creature: Creature): Int = {

    var attackDispo: Int = nbAtk

    var totalDamage: Int = 0

    var i = 0

    while (attackDispo > 0 && vie > 0)
    {


      if (creature.vie > 0) {
        var calc: Int = (Dice.launch(2, 4) + 8)

        val touch = 2 * calc
        if (touch >= creature.arm) {
          val degat = Dice.launch(nbDice, atkDice) + atk
          creature.takeDamage(degat)
          totalDamage += degat
          if (creature.vie <= 0)
            creature.die()
        }
      }

      attackDispo -= 1
    }
    totalDamage
  }







}
