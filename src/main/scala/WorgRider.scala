import org.apache.spark.graphx.VertexId

/*
 * This class extends creature and implement
 * specific attrributes and fight method
 */


class WorgRider(monVertexId: Long) extends Creature(monVertexId) {

  val nom = "Worg Rider"
  var vie = 13
  var vieMax: Int = 13
  val atk = 6
  val arm = 18
  val nbAtk = 1
  val prec = 6
  val atkDice = 8
  val nbDice = 1
  val vit = 20
  var canAttack = false
  val regen: Int = 0
  val equipe = false //gentils = true et méchants = false


  override def attaquer(creature: Creature, vertexId: VertexId): Int = {

    attaqueMelee(creature)

  }

  //this methode chech the edge attribute (distance) and if
  // the creature are less than an distance we can attack
  override def checkCanAttack(edgeAttr: Int): Unit = {
    if (edgeAttr > 2) { //default
      canAttack = false
    }
    else {
      canAttack = true
    }

  }


  /*This method implemente the fight as a melee and return the damages inflicts
* parameter :  the target an Creature
* output : Int : the total damage value
*/
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
