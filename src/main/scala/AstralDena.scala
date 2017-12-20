import org.apache.spark.graphx.VertexId

class AstralDena(monVertexId: VertexId) extends Creature(monVertexId) {
  val nom: String = _
  var vie: Int = _
  val atk: Int = _
  val arm: Int = _
  val nbAtk: Int = _
  val prec: Int = _
  val atkDice: Int = _
  val nbDice: Int = _
  val vit: Int = _
  var canAttack: Boolean = _
  val regen: Int =0
  val equipe: Boolean = _

  def attaquer(creature: Creature, vertexId: VertexId): Int = ???

  def attaqueMelee(creature: Creature): Int = ???


}
