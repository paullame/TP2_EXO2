import org.apache.spark.graphx.VertexId

class Planetar(monVertexId: VertexId) extends Creature(monVertexId) {
  val nom: String = "Angel, Planetar"
  var vie: Int = 229
  val vieMax: Int = 229
  val atk: Int = 15
  val arm: Int = 32
  val nbAtk: Int = 3
  val prec: Int = 27
  val atkDice: Int = 6
  val nbDice: Int = 3
  val vit: Int = 30
  var canAttack: Boolean = false
  val regen: Int = 10
  val equipe: Boolean = false //true = gentils false = méchants

  def attaquer(creature: Creature, vertexId: VertexId): Int = {
    val damages = attaqueMelee(creature)
    return damages
  }

  //this methode chech the edge attribute (distance) and if
  // the creature are less than an distance we can attack
  override def checkCanAttack(edgeAttr: Int): Unit = {
    if (edgeAttr > 10) { //reach
      canAttack = false
    }
    else {
      canAttack = true
    }

  }



}
