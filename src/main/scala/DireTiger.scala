import org.apache.spark.graphx.VertexId

class DireTiger(monVertexId: VertexId, monEquipe: Boolean) extends Creature(monVertexId){
  val nom: String = "Dire Tiger (Cat, Great)"
  var vie: Int = 105
  val vieMax: Int = 105
  val atk: Int = 8
  val arm: Int = 17
  val nbAtk: Int = 1
  val prec: Int = 18
  val atkDice: Int = 4
  val nbDice: Int =2
  val vit: Int = 40
  var canAttack: Boolean = false
  val equipe: Boolean = monEquipe // TODO trouver une solution car peut etre méchant et gentil
  val regen: Int = 0


  def attaquer(creature: Creature, vertexId: VertexId): Int = {

    val damages = specialAttack(creature)
    damages

  }

  //this methode chech the edge attribute (distance) and if
  // the creature are less than an distance we can attack
  override def checkCanAttack(edgeAttr: Int): Unit = {
    if (edgeAttr > 5) { //reach offense
      canAttack = false
    }
    else {
      canAttack = true
    }

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










}
