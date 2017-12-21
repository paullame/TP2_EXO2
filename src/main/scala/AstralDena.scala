import org.apache.spark.graphx.VertexId

class AstralDena(monVertexId: VertexId) extends Creature(monVertexId) {
  val nom: String = "Angel, Astral Deva"
  var vie: Int = 172
  val vieMax: Int = 172
  val atk: Int = 14*3
  val arm: Int = 29
  val nbAtk: Int = 3
  val prec: Int = 26
  val atkDice: Int = 8
  val nbDice: Int = 1
  val vit: Int = 50
  var canAttack: Boolean = false
  val regen: Int = 0
  val equipe: Boolean = false //true = gentils false = méchants

  def attaquer(creature: Creature, vertexId: VertexId): Int = {

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
