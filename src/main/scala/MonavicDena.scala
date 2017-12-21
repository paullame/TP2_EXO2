import org.apache.spark.graphx.VertexId

class MonavicDena(monVertexId: VertexId) extends Creature(monVertexId) {
  val nom: String = "Angel, Movanic Deva"
  var vie: Int = 126
  val vieMax: Int = 126
  val atk: Int = 7
  val arm: Int = 24
  val nbAtk: Int = 3
  val prec: Int = 24
  val atkDice: Int = 6
  val nbDice: Int = 2
  val vit: Int = 40
  var canAttack: Boolean = false
  val regen: Int = 0
  val equipe: Boolean = true //true = gentils false = méchants

  def attaquer(creature: Creature, vertexId: VertexId): Int = {

    val damages = attaqueMelee(creature)
    damages
  }







}
