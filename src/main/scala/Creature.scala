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

  def attaqueMelee(creature: Creature): Int


  def checkCanAttack(edgeAttr: Int): Unit = {
    if (edgeAttr > 2) {
      canAttack = false
    }
    else {
      canAttack = true
    }

  }

  def deplacer(edgeAttr: Int): Int = {
    edgeAttr - vit
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


  def isDead(): Boolean = {
    if (vie<=0) {
      true
    }
    else {
      false
    }
  }

}
