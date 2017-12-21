import org.apache.spark.graphx.VertexId

/*
 * This class extends creature and implement
 * specific attrributes and fight method
 */

class Barbare(monVertexId: Long) extends Creature(monVertexId) {

  val nom: String = "Barbare"
  var vie: Int = 142
  var vieMax: Int = 142
  val atk: Int = 10
  val arm: Int = 17
  val nbAtk: Int = 3
  val prec: Int = 19
  val atkDice: Int = 8
  val nbDice: Int = 1
  val vit: Int = 40
  var canAttack = false
  val regen: Int = 0
  val equipe: Boolean = false

  override def attaquer(creature: Creature, vertexId: VertexId): Int = {
    val damages = attaqueMelee(creature)
    return damages
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





}
