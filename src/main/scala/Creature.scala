import org.apache.spark.graphx.VertexId


/*
 * This class is an abstraction for all the creature
 * It contain all the common atrtributs
 * The method attaqueMelee will be implemented for all the specific creature
 */

abstract class Creature(monVertexId: Long) extends Serializable {

  //attributes
  val nom: String
  val vertexId: Long = monVertexId
  var vie: Int
  var vieMax: Int
  val atk: Int
  val arm: Int
  val nbAtk: Int
  val prec: Int
  val atkDice: Int
  val nbDice: Int
  val vit: Int
  var canAttack: Boolean
  val regen: Int
  val equipe: Boolean //gentils = true et méchants = false


  def takeDamage(degat: Int): Unit = {
    vie -= degat
  }

  // Attack creature based on atk and nbAtk
  def attaquer(creature: Creature, vertexId: VertexId): Int

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


  //this methode chech the edge attribute (distance) and if
  // the creature are less than an distance we can attack
  def checkCanAttack(edgeAttr: Int): Unit = {
    if (edgeAttr > 2) {
      canAttack = false
    }
    else {
      canAttack = true
    }

  }

  //deplacement vers le solar
  def deplacerEx1(edgeAttr: Int): Int = {
    val zero = 0
    var distance = edgeAttr - vit

    //reduce the distance between the "mechant" and the solar check if value is > 0
    if (distance > zero)
      distance
    else
      zero

  }

  /*  def choseTarget(): VertexId = {

    }*/


  def die(): Unit = {
    this.vie = 0
  }

  //TODO completer la fonction
  //TODO ajouter la logique de qui attaque qui
  def update(message: Message): Creature = {
    this.vie -= message.damage
    this
  }

  def regeneration(): Unit = {
    vie += regen

    //limit the level of live during the regeneration
    // at the top fo the live value (vieMax)
    vie = math.min(this.vie+regen, vieMax)

  }


  def isDead: Boolean = {
    if (vie<=0) {
      true
    }
    else {
      false
    }
  }

}
